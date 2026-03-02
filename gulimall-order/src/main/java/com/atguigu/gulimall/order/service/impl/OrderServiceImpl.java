package com.atguigu.gulimall.order.service.impl;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.entity.OrderItemEntity;
import com.atguigu.gulimall.order.feign.CartFeign;
import com.atguigu.gulimall.order.feign.MemberFeign;
import com.atguigu.gulimall.order.feign.ProductFeign;
import com.atguigu.gulimall.order.feign.WareFeign;
import com.atguigu.gulimall.order.mapper.OrderItemMapper;
import com.atguigu.gulimall.order.mapper.OrderMapper;
import com.atguigu.gulimall.order.service.OrderService;
import com.atguigu.gulimall.order.vo.MemberAddressVo;
import com.atguigu.gulimall.order.vo.OrderConfirmVo;
import com.atguigu.gulimall.order.vo.OrderItemTo;
import com.atguigu.gulimall.order.vo.OrderSubmitVo;
import com.atguigu.gulimall.order.vo.SpuInfoVo;
import com.atguigu.gulimall.order.vo.SubmitOrderResponseVo;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rabbitmq.client.Channel;
import constant.OrderConstant;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import to.MemberEntityVo;
import to.SkuHasStockTo;
import to.cart.CartItemTo;
import to.order.LockSkuTo;
import to.ware.WareItemTo;
import to.ware.WareTo;
import utils.R;

/**
 * @author tifa
 * @description 针对表【oms_order(订单)】的数据库操作Service实现
 * @createDate 2025-05-08 21:18:35
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity>
    implements OrderService {

  private final RabbitTemplate rabbitTemplate;

  private final MemberFeign memberFeign;

  private final CartFeign cartFeign;

  private final WareFeign wareFeign;
  private final ProductFeign productFeign;

  private final StringRedisTemplate stringRedisTemplate;

  private final ThreadPoolExecutor threadPoolExecutor;

  private final OrderItemMapper orderItemMapper;

  public OrderServiceImpl(RabbitTemplate rabbitTemplate, MemberFeign memberFeign,
      CartFeign cartFeign, WareFeign wareFeign, ProductFeign productFeign,
      StringRedisTemplate stringRedisTemplate, ThreadPoolExecutor threadPoolExecutor,
      OrderItemMapper orderItemMapper) {
    this.rabbitTemplate = rabbitTemplate;
    this.memberFeign = memberFeign;
    this.cartFeign = cartFeign;
    this.wareFeign = wareFeign;
    this.productFeign = productFeign;
    this.stringRedisTemplate = stringRedisTemplate;
    this.threadPoolExecutor = threadPoolExecutor;
    this.orderItemMapper = orderItemMapper;
  }

  @RabbitListener(queues = {"hello"})
  void listen(Message message, @Payload OrderEntity orderEntity, Channel channel)
      throws IOException {
    System.out.println("消费者" + orderEntity);
    long deliveryTag = message.getMessageProperties().getDeliveryTag();
    channel.basicAck(deliveryTag, false);
  }


  /**
   * 确认回调
   */
  @PostConstruct
  public void initRabbitTemplate() {

//    消息是否成功到达 Exchange
    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
      log.info("confirm{}", correlationData);
      if (ack) {
        log.info("ack为{}", true);
      } else {
        log.error("失败原因{}", cause);
      }
    });

//    当生产者的消息到达交换机却没有到达队列，才会回调这个
    rabbitTemplate.setReturnsCallback(
        returned -> log.error("消息到达交换机却没有到达队列{}", returned));
  }


  /**
   * 从这个人的id查出来它购物车中所有checked的产品
   *
   * @param memberId
   * @return
   */
  private List<OrderItemTo> getHasStockSkuItem(Long memberId) {

    /**
     * 从购物车选出来所有被选择的商品
     */
    var cartItemsFuture = CompletableFuture.supplyAsync(
        () -> cartFeign.getCartItems(memberId).stream().filter(CartItemTo::getChecked).toList(),
        threadPoolExecutor);
    //选出有库存的商品
    var skuHasFuture = cartItemsFuture.thenApplyAsync((cartItems) -> {
      R skuHasStock = wareFeign.getSkuHasStock(
          cartItems.stream().map(CartItemTo::getSkuId).toList());
      List<SkuHasStockTo> skuList = skuHasStock.getData(new TypeReference<>() {
      });

      return skuList.stream().filter(SkuHasStockTo::getHasStock)
          .collect(Collectors.toMap(SkuHasStockTo::getSkuId, s -> s));
    }, threadPoolExecutor);

    var newestCartItemsFuture = cartItemsFuture.thenApplyAsync(
        (cartItemTos -> productFeign.skuInfoNewestEntityList(cartItemTos)), threadPoolExecutor);

    // 合并库存信息和最新商品信息

    //注意这里只筛选出来了有货的
    return newestCartItemsFuture.thenCombine(skuHasFuture, (newestCartItems, skuHasStockedMap) ->
        newestCartItems.stream()
            .filter(item -> skuHasStockedMap.containsKey(item.getSkuId())
                && item.getCount() <= skuHasStockedMap.get(item.getSkuId()).getSkuNums())
            .map(a -> {
              OrderItemTo orderItemTo = new OrderItemTo();
              BeanUtils.copyProperties(a, orderItemTo);
              orderItemTo.setHasStock(true);
              return orderItemTo;
            })
            .toList()
    ).join();
  }


  @Override
  public OrderConfirmVo confirmOrder(MemberEntityVo memberEntityVo) {
    OrderConfirmVo confirmVo = new OrderConfirmVo();
    var future1 = CompletableFuture.runAsync(() -> {
      List<MemberAddressVo> memberByMemberId = memberFeign.getMemberByMemberId(
          memberEntityVo.getId());
      confirmVo.setAddress(memberByMemberId);
    }, threadPoolExecutor);

    var future2 = CompletableFuture.supplyAsync(() -> getHasStockSkuItem(memberEntityVo.getId()),
        threadPoolExecutor);
    CompletableFuture.allOf(future1, future2);
    confirmVo.setItems(future2.join());
    //积分
    Integer integration = memberEntityVo.getIntegration();
    confirmVo.setIntegration(integration);

    //防重令牌
    String orderKey = UUID.randomUUID().toString().replace("-", "");
    String token = UUID.randomUUID().toString().replace("-", "");
    stringRedisTemplate.opsForValue()
        .set(OrderConstant.USER_ORDER_TOKEN_PREFIX + orderKey, token, 10,
            TimeUnit.MINUTES);

    confirmVo.setToken(token);
    confirmVo.setOrderKey(orderKey);
    return confirmVo;
  }

  //最难的分布式事务方法
  @Override
  public SubmitOrderResponseVo submit(OrderSubmitVo orderSubmitVo, MemberEntityVo memberEntityVo) {
    SubmitOrderResponseVo submitOrderResponseVo = new SubmitOrderResponseVo();
    submitOrderResponseVo.setCode(0); //0是快速失败

    String orderToken = orderSubmitVo.getOrderToken();
    String token = orderSubmitVo.getToken();
    String script =
        "if redis.call('get', KEYS[1]) == ARGV[1] then " +
            "   return redis.call('del', KEYS[1]) " +
            "else " +
            "   return 0 " +
            "end";

    //原子操作，这意味着下面的所有代码只可能被执行一次
    Long result = stringRedisTemplate.execute(
        new DefaultRedisScript<>(script, Long.class),
        Collections.singletonList(OrderConstant.USER_ORDER_TOKEN_PREFIX + orderToken),
        token
    );

    if (result != 1L) {
      //如果前端传过来的token部队，那么快速失败。
      return submitOrderResponseVo;
    }

    //开始创建订单
    String orderId = IdWorker.getTimeId();
    OrderEntity orderEntity = new OrderEntity();
    submitOrderResponseVo.setOrderEntity(orderEntity);

    orderEntity.setOrderSn(orderId);
    orderEntity.setMemberId(memberEntityVo.getId());
    orderEntity.setMemberUsername(memberEntityVo.getUsername());

    //运费直接为0
    orderEntity.setFreightAmount(BigDecimal.ZERO);
    orderEntity.setPayType(orderSubmitVo.getPayType());
    //等待付款
    orderEntity.setStatus(0);
    //确认收货状态
    orderEntity.setConfirmStatus(0);
    //未删除
    orderEntity.setDeleteStatus(0);

    //从购物车中获得最新的价格数据
    var OrderItemFuture = CompletableFuture.supplyAsync(
        () -> getHasStockSkuItem(memberEntityVo.getId()), threadPoolExecutor);

    var addFuture = CompletableFuture.runAsync(() -> {
      R addressData = memberFeign.getAddressById(memberEntityVo.getId());
      MemberAddressVo addrEntity = addressData.getData(new TypeReference<>() {
      });
      /**
       * 下面是收货人的一些信息
       */
      orderEntity.setReceiverName(addrEntity.getName());
      orderEntity.setReceiverPhone(addrEntity.getPhone());
      orderEntity.setReceiverPostCode(addrEntity.getPostCode());
      orderEntity.setReceiverProvince(addrEntity.getProvince());
      orderEntity.setReceiverCity(addrEntity.getCity());
      orderEntity.setReceiverRegion(addrEntity.getRegion());
      orderEntity.setReceiverDetailAddress(addrEntity.getDetailAddress());
    }, threadPoolExecutor);

    CompletableFuture.allOf(addFuture, OrderItemFuture).join();
    List<OrderItemTo> orderItemTos = OrderItemFuture.join();

    orderEntity.setTotalAmount(
        orderItemTos.stream().map(OrderItemTo::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add));

    //生成订单具体项
    List<OrderItemEntity> orderItemEntities = getOrderItem(orderItemTos, orderId);
    submitOrderResponseVo.setOrderItemEntityList(orderItemEntities);

    //校验价格，因为价格可能会在瞬间内变动

    int change = computePrice(orderSubmitVo, orderItemEntities);
    boolean hasLockStock;
    //如果价格没有发生变化
    if (change == 0) {
      //锁库存
      hasLockStock = lock(orderItemEntities, orderEntity);
      //保存
      if (hasLockStock) {
        this.save(orderEntity);
        orderItemMapper.insert(orderItemEntities);
        submitOrderResponseVo.setCode(200);
      }
    }
    log.info("订单的数据{}", orderEntity);
    //延时队列来取消订单
    rabbitTemplate.convertAndSend("order-event-exchange", "order.release.order", orderEntity);
    return submitOrderResponseVo;
  }

  @Override
  public OrderEntity preparePayInfo(String orderSn, MemberEntityVo memberEntityVo) {
    //TODO 下一步要写的
    return null;
  }

  /**
   * 调用仓库服务来进行锁库存
   *
   * @param orderItemEntities
   * @param orderEntity
   * @return
   */
  private boolean lock(List<OrderItemEntity> orderItemEntities, OrderEntity orderEntity) {
    WareTo wareTo = new WareTo();
    wareTo.setOrderSn(orderEntity.getOrderSn());

    wareTo.setConsignee(orderEntity.getReceiverName());
    wareTo.setConsigneeTel(orderEntity.getReceiverPhone());
    wareTo.setDeliveryAddress(orderEntity.getReceiverDetailAddress());

    List<LockSkuTo> list = orderItemEntities.stream().map(a -> {
      LockSkuTo lockTo = new LockSkuTo();
      lockTo.setSkuId(a.getSkuId());
      lockTo.setStockLocked(a.getSkuQuantity());
      return lockTo;

    }).toList();
    List<WareItemTo> wareItemToList = list.stream().map(a -> {
      WareItemTo wareItemTo = new WareItemTo();
      wareItemTo.setLockStatus(1);
      wareItemTo.setSkuId(a.getSkuId());
      wareItemTo.setSkuNum(a.getStockLocked());
      return wareItemTo;
    }).toList();
    wareTo.setWareItemToList(wareItemToList);

    R lock = wareFeign.lock(wareTo);
    return R.ok().getCode().equals(lock.getCode());
  }


  //验价，对比前端传过来的价格和最终价格是否一致
  private int computePrice(OrderSubmitVo orderSubmitVo, List<OrderItemEntity> orderItemEntities) {
    BigDecimal originalPrice = orderSubmitVo.getTotalPrice();
    BigDecimal nowPrice = orderItemEntities.stream().map(a -> {
      BigDecimal skuPrice = a.getSkuPrice();
      Integer skuQuantity = a.getSkuQuantity();
      return skuPrice.multiply(BigDecimal.valueOf(skuQuantity));
    }).reduce(BigDecimal.ZERO, BigDecimal::add);
    return nowPrice.compareTo(originalPrice);
  }

  private List<OrderItemEntity> getOrderItem(List<OrderItemTo> orderItemTos, String orderId) {
    List<OrderItemEntity> list = orderItemTos.stream().map(a -> {
      OrderItemEntity orderItem = new OrderItemEntity();
      orderItem.setOrderSn(orderId);
      orderItem.setSkuId(a.getSkuId());
      orderItem.setSkuPic(a.getImage());
      orderItem.setSkuName(a.getTitle());
      orderItem.setSkuPrice(a.getPrice());
      orderItem.setSkuQuantity(a.getCount());
      orderItem.setSpuId(a.getSpuId());
      orderItem.setGiftGrowth(a.getPrice().intValue());
      orderItem.setGiftIntegration(a.getPrice().intValue());
      //最后的金额
      orderItem.setRealAmount(a.getPrice().multiply(BigDecimal.valueOf(a.getCount())));
      return orderItem;
    }).toList();

    R spuByIds = productFeign.getSpuByIds(list.stream().map(OrderItemEntity::getSpuId).toList());
    List<SpuInfoVo> data = spuByIds.getData(new TypeReference<>() {
    });
    Map<Long, SpuInfoVo> collect = data.stream()
        .collect(Collectors.toMap(SpuInfoVo::getId, item -> item));
    list.forEach(a -> {
      Long spuId = a.getSpuId();
      SpuInfoVo spuInfoVo = collect.get(spuId);
      a.setSpuName(spuInfoVo.getSpuName());
      a.setSpuBrand(spuInfoVo.getBrandId().toString());
      a.setCategoryId(spuInfoVo.getCatalogId());
    });

    return list;
  }

}




