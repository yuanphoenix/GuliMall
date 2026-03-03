package com.atguigu.gulimall.order.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rabbitmq.client.Channel;
import constant.OrderConstant;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", orderEntity);
    return submitOrderResponseVo;
  }

  @Override
  public OrderEntity preparePayInfo(String orderSn, MemberEntityVo memberEntityVo) {

    OrderEntity one = getOne(
        new LambdaQueryWrapper<OrderEntity>().eq(OrderEntity::getOrderSn, orderSn)
            .eq(OrderEntity::getStatus, 0)
            .eq(OrderEntity::getDeleteStatus, 0));
    if (one == null || !Objects.equals(memberEntityVo.getUsername(), one.getMemberUsername())) {
      return null;
    }
    return one;
  }

  @Override
  public String pay(String orderSn, MemberEntityVo memberEntityVo) {
    OrderEntity orderEntity = preparePayInfo(orderSn, memberEntityVo);
    if (orderEntity == null) {
      return "false";
    }
    // 初始化SDK
    AlipayClient alipayClient = null;
    try {
      alipayClient = new DefaultAlipayClient(getAlipayConfig());
    } catch (AlipayApiException e) {
      throw new RuntimeException(e);
    }

    // 构造请求参数以调用接口
    AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
    request.setReturnUrl("http://gulimall.com");
    request.setNotifyUrl("http://gulimall.com");

    AlipayTradePagePayModel model = new AlipayTradePagePayModel();

    // 设置商户订单号
    model.setOutTradeNo(orderSn);

    // 设置订单总金额
    model.setTotalAmount(orderEntity.getTotalAmount().setScale(2, RoundingMode.HALF_UP).toString());

    // 设置订单标题
    model.setSubject("Iphone6 16G");

    // 设置产品码
    model.setProductCode("FAST_INSTANT_TRADE_PAY");

    // 设置PC扫码支付的方式
    model.setQrPayMode("1");

    request.setBizModel(model);
    // 第三方代调用模式下请设置app_auth_token
    // request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");

    AlipayTradePagePayResponse response = null;
    try {
      response = alipayClient.pageExecute(request, "POST");
    } catch (AlipayApiException e) {
      throw new RuntimeException(e);
    }
    // 如果需要返回GET请求，请使用
    // AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "GET");
    return response.getBody();
  }

  private static AlipayConfig getAlipayConfig() {
    String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCzQHIjoohBc5UY5jzfBtfTfqU0maCVWh2wVHsFVteW12Vq9Y1GBW6PqYBL62QwSQp7j9umGaGSZq5V4Lz2wpZmliTa6uIjEZlZWgVbG14p9jQKj649wTrxQJG7TgbgYynhmwvOGgeTckwwxzaLaXYcNYAIWy7oXeDOGzDl2g5CweTNPpysnCqhU+Emg1P82NH4aNg7fOIoBqF6cxD1nWoAGxDA4h9gTkYHYYZPvRAdb/BpNgVb6vV0HQX67mZJN7lnLmE8UWE/GiPt47kdix1rFQ5VHkXslYkmmEYXTJs4vmDJnbAV1hrxwdKCclFfgqzOeeOhmwC6FnZE3WJwKa/LAgMBAAECggEAVj28bI6nNa5RXrBvOvKE5ll5TIxZaWH5BLwwkAoPIaCyR7qqZLT6U54+fsha5KxPodE94XVVeiVy3RiKccJ/MA4u3zDA6hRujkG0b/gD3vZ4ZVhpgYa1QBtLwi0xO8YaAVRiYp+9Y2BLbfB6uqpbzAild9+++scoAKGubffygoUN93r/rrTnfYf3yY3DnYWAgU3chaSbS6ckS+ySxqKsWJH9qjJBCYjjrrWPDt2xuUr3a+CcSL+83YUhKLNawESf2jvSFdmbqcKXa27iPZPBoK6ib31uPq9EOBrjYsiXsT/qbMwUCp7HS0LSwVe+Tv9psvVFQQGpkD53VXuwwxpd2QKBgQDrAF+fffm1kYQlBn0lY6um58uYiofPTFzK3h2TNZFO4ZkrX3Ul7QbWnKveTlLUFI2Kafrx1lsfIrFxBxuKP1xc1qAqgr2CHOYjK8sXxjeTsC4SnySHh1y4E9AZbMrqJbqTGhUnP+kecAiifFFYbCSpIXsDh8FOb54+8ZVRs35zjQKBgQDDRM4buFgeP/xg7aZBNn9KqFCO5WOET477KW9cWuWv35cwsu7T5MAt2/Wgsepx41pPZzG4zBM89M6SO+RNtzHBO7Thx08wWmBbBmQGnzAEB/MMD72AwUuYwctJxnoKLGPQLjfRGN5Ilum7HgCxtWWwc/v5pjIsMLMdXorXlLjutwKBgEhX2hgQQOH7RPHc+IOdFkeQTeXMp5hSSrKNBA/AStY8rtliTn75Y8SHgIU0GV1+YkA89eqi8XQ5SuSfqoO5k6Zkz/OmQc6fNN2Y6rGL7KoDb3t+EFHEgu+L8eER07mXGcOVIPPvQcWD+bSDjssop3SFgQgKL6EEzXNVDYGUPxY1AoGBAIJM44wz0vk8kVjMvGg/yWk0L61q/KFJxYtr9teWADb/6I+ilyPmPSdc4+c7Ucp1f8oEfnVmGGBQq5eBR7NkT1s2UFlo+jq11B5pgU254/yMoW6nAjlswtlIWDL+smkffetpK+3nvkyKB6XJO4VaGmVIwBezAz/hr2Qltlhs8Yq5AoGBAJb48LgFlg4A9FkmzhXRcPBYTM+Jvj7scur3iKpz979SIZ3Dfi/TG6aecuwy/EbhN8Me52hyVNfHuuwlcR6wU+OFSZ5FwJOM5WWGmvOfATMYoHuX2xXWmYnAA2nWd606gjSHV5EkBFiOeEufis7RgHbZKsfB+Qp0o45sM3YYdh4f";
    String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkAY4s5La08xGMGsdPThR6HAg03MoiIoNmwJ59jhuyZm6sDLYgw/rvtnxF0gn6r4P09d6Rqgc1DrLl1hpcLcFTKlTHZE2ORS+UQcIdl8XLP3VpXE2zLiGZ17EAhfsYAkr/XdBc6qCKFmfRS0Q430JQtK+b+Za577RoqJk7Eg/Xjkd6jJNrgkF0JqWgYpdc2yjaGe4yZKTqQLMThRY4k3Pgrc+cBOZKSRsyEvd0RK+QZZ1lyZ2POZGcOU3pdyn5rb9x8yk/cCmELzx/+/QQlJsKiaCR7hfW3MnshPB2UTXhGgY+dF5sj/o55HPhTKk6dYRs+PnXQHtvCWHLy4L/PyhhQIDAQAB";
    AlipayConfig alipayConfig = new AlipayConfig();
    alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
    alipayConfig.setAppId("9021000161691958");
    alipayConfig.setPrivateKey(privateKey);
    alipayConfig.setFormat("json");
    alipayConfig.setAlipayPublicKey(alipayPublicKey);
    alipayConfig.setCharset("UTF-8");
    alipayConfig.setSignType("RSA2");
    return alipayConfig;
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




