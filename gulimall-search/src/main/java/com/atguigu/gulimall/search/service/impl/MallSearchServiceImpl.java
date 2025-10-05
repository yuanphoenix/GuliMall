package com.atguigu.gulimall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchPhraseQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.json.JsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.util.NamedValue;
import com.atguigu.gulimall.search.constant.EsConstant;
import com.atguigu.gulimall.search.service.MallSearchService;
import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;
import jakarta.json.stream.JsonGenerator;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import to.es.SkuEsModel;

@Service
public class MallSearchServiceImpl implements MallSearchService {

  private static final Logger logger = LoggerFactory.getLogger(MallSearchServiceImpl.class);
  private final ElasticsearchClient elasticsearchClient;

  public MallSearchServiceImpl(ElasticsearchClient elasticsearchClient) {
    this.elasticsearchClient = elasticsearchClient;
  }

  @Override
  public SearchResult search(SearchParam searchParam) {

    logger.warn(searchParam.toString());

    List<Query> mutiQuery = new ArrayList<>();
    if (StringUtils.hasText(searchParam.getKeyword())) {
      mutiQuery.add(MatchPhraseQuery.of(mp ->
          mp.field("skuTitle").query(searchParam.getKeyword())
      )._toQuery());
    }
    List<Query> filterQuery = new ArrayList<>();
    if (searchParam.getHasStock() != null) {
      filterQuery.add(
          TermQuery.of(stock -> stock.field("hasStock").value(searchParam.getHasStock() == 1))
              ._toQuery());

    }

    if (StringUtils.hasText(searchParam.getSkuPrice())) {
      String[] split = searchParam.getSkuPrice().split("_", -1);
      if (split.length >= 2) {

        var low_temp = 0.0;
        var high_temp = Double.MAX_VALUE;

        if (StringUtils.hasText(split[0]) && NumberUtils.isCreatable(split[0])) {
          low_temp = Double.parseDouble(split[0]);
        }
        if (StringUtils.hasText(split[1]) && NumberUtils.isCreatable(split[1])) {
          high_temp = Double.parseDouble(split[1]);
        }
        var high = high_temp;
        var low = low_temp;

        var rangeQuery = RangeQuery.of(r -> r.number(n -> n.field("skuPrice").gte(low)
            .lte(high)));
        filterQuery.add(rangeQuery._toQuery());
      }
    }

    if (StringUtils.hasText(searchParam.getCatalog3Id())) {
      var catalogTerm = TermQuery.of(t -> t.field("catalogId").value(searchParam.getCatalog3Id()));
      filterQuery.add(catalogTerm._toQuery());
    }
    if (!CollectionUtils.isEmpty(searchParam.getBrandIds())) {
      var brandIdQuery = TermsQuery.of(
          t -> t.field("brandId").terms(a -> a.value(searchParam.getBrandIds().stream().map(
              FieldValue::of).toList())));
      filterQuery.add(brandIdQuery._toQuery());
    }

    if (!CollectionUtils.isEmpty(searchParam.getAttrs())) {
      for (String attr : searchParam.getAttrs()) {
        // 前端传的格式假设为 attrId_attrValue1:attrValue2，例如 "7_111:222"
        String[] split = attr.split("_");
        String attrId = split[0];
        String[] attrValues = split[1].split(":");

        filterQuery.add(
            NestedQuery.of(n -> n
                .path("attrs")
                .query(q -> q
                    .bool(b -> b
                        .must(
                            TermQuery.of(t -> t.field("attrs.attrId").value(attrId))._toQuery(),
                            TermsQuery.of(t -> t.field("attrs.attrValue")
                                    .terms(a -> a.value(
                                        Arrays.stream(attrValues).map(FieldValue::of).toList())))
                                ._toQuery()
                        )
                    )
                )
            )._toQuery()
        );
      }
    }

    // 3. 分页
    int page = searchParam.getPageNum() != null ? searchParam.getPageNum() : 1;
    int size = EsConstant.pageNum; // 每页显示数量，可根据需要改
    int from = (page - 1) * size;

    // 4. 排序
    List<SortOptions> sortOptions = new ArrayList<>();
    if (searchParam.getSort() != null) {
      //TODO 这里看看前端怎么传的
      String[] sortArr = searchParam.getSort().split("_");
      if (sortArr.length == 2) {
        sortOptions.add(SortOptions.of(s -> s.field(f -> f
            .field(sortArr[0])
            .order(sortArr[1].equalsIgnoreCase("asc") ? SortOrder.Asc : SortOrder.Desc)
        )));
      }
    }

    // 5. 构建查询
    SearchRequest searchRequest = SearchRequest.of(s -> s
        .index(EsConstant.PRODUCTINDEX)
        .query(q -> q
            .bool(b -> b
                .must(mutiQuery)
                .filter(filterQuery)
            )
        )
        .sort(sortOptions)
        .from(from)
        .size(size)
        .highlight(h -> h
            .fields(List.of(new NamedValue<>("skuTitle", HighlightField.of(f -> f))))
            .preTags("<b style='color:red'>")
            .postTags("</b>")
        )
        .aggregations("brand_agg", a -> a
            .terms(t -> t.field("brandId").size(10))
            .aggregations("brand_name_agg", aa -> aa.terms(tt -> tt.field("brandName").size(10)))
            .aggregations("brand_img_agg", aa -> aa.terms(tt -> tt.field("brandImg").size(10)))
        )
        .aggregations("catalog_agg", a -> a
            .terms(t -> t.field("catalogId").size(10))
            .aggregations("catalogName_agg", aa -> aa.terms(tt -> tt.field("catalogName").size(10)))
        )
        .aggregations("attr_agg", a -> a
            .nested(n -> n.path("attrs"))
            .aggregations("attr_id_agg", aa -> aa
                .terms(t -> t.field("attrs.attrId").size(10))
                .aggregations("attr_name_agg",
                    aaa -> aaa.terms(tt -> tt.field("attrs.attrName").size(10)))
                .aggregations("attr_value_agg", aaa -> aaa
                    .terms(tt -> tt.field("attrs.attrValue").size(10))  // 这里 size 可以根据需求调整
                )
            )
        )
    );

    // 获取 Transport 的 mapper（client._transport().jsonpMapper()）
    ElasticsearchTransport transport = elasticsearchClient._transport();
    JsonpMapper mapper = transport.jsonpMapper();

// 输出为 JSON
    StringWriter sw = new StringWriter();
    JsonGenerator generator = mapper.jsonProvider().createGenerator(sw);
    searchRequest.serialize(generator, mapper);
    generator.close();
    logger.info(sw.toString());

    try {
      SearchResponse<SkuEsModel> response = elasticsearchClient.search(searchRequest,
          SkuEsModel.class);
      return buildSearchResult(response, searchParam.getPageNum(), EsConstant.pageNum);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public SearchResult buildSearchResult(SearchResponse<SkuEsModel> response, int pageNum,
      int pageSize) {
    SearchResult result = new SearchResult();

    // 1️⃣ 命中结果
    List<SkuEsModel> products = response.hits().hits().stream()
        .map(hit -> {
          SkuEsModel model = hit.source();
          // 高亮字段处理
          if (hit.highlight() != null && hit.highlight().containsKey("skuTitle")) {
            model.setSkuTitle(hit.highlight().get("skuTitle").get(0));
          }
          return model;
        })
        .toList();
    result.setEsModels(products);

    // 2️⃣ 分页信息
    long totalHits = response.hits().total() != null ? response.hits().total().value() : 0L;
    result.setTotalNum((int) totalHits);
    result.setCurrentPage(pageNum);
    result.setTotalPage((int) Math.ceil((double) totalHits / pageSize));

    // 3️⃣ 品牌聚合
    if (response.aggregations().containsKey("brand_agg")) {
      var brandBuckets = response.aggregations().get("brand_agg").lterms().buckets().array();
      List<SearchResult.BrandVo> brandVos = brandBuckets.stream().map(bucket -> {
        SearchResult.BrandVo vo = new SearchResult.BrandVo();
        vo.setBrandId(bucket.key());
        // 取子聚合 brand_name_agg 的第一个值
        var nameBucket = bucket.aggregations().get("brand_name_agg").sterms().buckets().array()
            .get(0);
        vo.setBrandName(nameBucket.key().stringValue());

        var brandImgBucket = bucket.aggregations().get("brand_img_agg").sterms().buckets().array()
            .get(0);
        vo.setBrandImg(brandImgBucket.key().stringValue());

        return vo;
      }).toList();
      result.setBrandVoList(brandVos);
    }

    // 4️⃣ 分类聚合
    if (response.aggregations().containsKey("catalog_agg")) {
      var catBuckets = response.aggregations().get("catalog_agg").lterms().buckets().array();
      List<SearchResult.CategoryVo> catVos = catBuckets.stream().map(bucket -> {
        SearchResult.CategoryVo vo = new SearchResult.CategoryVo();
        vo.setCatId(bucket.key());
        var nameBucket = bucket.aggregations().get("catalogName_agg").sterms().buckets().array()
            .getFirst();
        vo.setCategoryName(nameBucket.key().stringValue());
        return vo;
      }).toList();
      result.setCategoryVoList(catVos);
    }

    // 5️⃣ 属性聚合（nested）
    if (response.aggregations().containsKey("attr_agg")) {
      var nestedAgg = response.aggregations().get("attr_agg").nested();
      var attrBuckets = nestedAgg.aggregations().get("attr_id_agg").lterms().buckets().array();

      List<SearchResult.AttrVo> attrVos = attrBuckets.stream().map(bucket -> {
        SearchResult.AttrVo vo = new SearchResult.AttrVo();
        vo.setAttrId(bucket.key());
        var nameBucket = bucket.aggregations().get("attr_name_agg").sterms().buckets().array()
            .get(0);
        vo.setAttrName(nameBucket.key().stringValue());
        // attrValue 聚合
        var valueBuckets = bucket.aggregations().get("attr_value_agg").sterms().buckets().array();
        vo.setAttrValue(valueBuckets.stream().map(b -> b.key().stringValue()).toList());
        return vo;
      }).toList();

      result.setAttrVoList(attrVos);
    }
    logger.info(result.toString());
    return result;
  }


}
