package com.fei.movieticket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fei.movieticket.bo.QueryConditionBo;
import com.fei.movieticket.service.SearchData;
import com.fei.movieticket.util.ConnectES;
import com.fei.movieticket.vo.TicketVo;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SearchDataServiceImpl
 * @Author chengfei
 * @Date 2020/10/14 14:18
 * @Description: TODO
 **/
@Service
public class SearchDataImpl implements SearchData {

    private Logger logger = LoggerFactory.getLogger(SearchData.class);
    private TransportClient client = ConnectES.connect();


    @Override
    public List<TicketVo> goodTicket(List<TicketVo> ticketVos, QueryConditionBo queryConditionBo) {
        //把数据存入ES中
        for (int i = 0; i < ticketVos.size(); i++) {
            String json = JSONObject.toJSONString(ticketVos.get(i));
            client.prepareIndex("ticket", "html", String.valueOf(i))
                    .setSource(json, XContentType.JSON).get();
        }
        ticketVos.clear();
        //查询数据
        Map<String, Object> map = new HashMap<>();
        List<QueryBuilder> queryBuilders = new ArrayList<>();
        RangeQueryBuilder price = null;
        if (queryConditionBo.getArea() != null) {
            map.put("area", queryConditionBo.getArea());
        }
        if (queryConditionBo.getPlatform() != null) {
            map.put("platform", queryConditionBo.getPlatform());
        }
        if (queryConditionBo.getPrice() != null) {
            price = QueryBuilders.rangeQuery("price").gt(0).lt(queryConditionBo.getPrice());
        }
        if (queryConditionBo.getCinema() != null) {
            map.put("cineam", queryConditionBo.getCinema());
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //这里固定就是"name"，
            TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", entry.getValue());
            queryBuilders.add(termsQueryBuilder);
        }
        if (price != null) {
            queryBuilders.add(price);
        }

        //如果是一个条件
        if (queryBuilders.size() == 1) {
            SearchResponse searchResponse = client.prepareSearch("ticket").setTypes("html")
                    .setQuery(
                            QueryBuilders.boolQuery()
                                    .must(QueryBuilders.boolQuery()
                                                    .must(queryBuilders.get(0))
                                    )
                    )
                    .setSize(1000).get();
            SearchHits hits = searchResponse.getHits();
            SearchHit[] hits1 = hits.getHits();
            for (SearchHit documentFields : hits1) {
                TicketVo ticketVo = JSONObject.parseObject(documentFields.getSourceAsString(), TicketVo.class);
                ticketVos.add(ticketVo);
            }
        }

        //如果是2个条件
        if (queryBuilders.size() == 2) {
            SearchResponse searchResponse = client.prepareSearch("ticket").setTypes("html")
                    .setQuery(
                            QueryBuilders.boolQuery()
                                    .must(QueryBuilders.boolQuery()
                                            .must(queryBuilders.get(0))
                                            .must(queryBuilders.get(1))
                                    )
                    )
                    .setSize(1000).get();
            SearchHits hits = searchResponse.getHits();
            SearchHit[] hits1 = hits.getHits();
            for (SearchHit documentFields : hits1) {
                TicketVo ticketVo = JSONObject.parseObject(documentFields.getSourceAsString(), TicketVo.class);
                ticketVos.add(ticketVo);
            }
        }

        //如果是3个条件
        if (queryBuilders.size() == 3) {
            SearchResponse searchResponse = client.prepareSearch("ticket").setTypes("html")
                    .setQuery(
                            QueryBuilders.boolQuery()
                                    .must(QueryBuilders.boolQuery()
                                            .must(queryBuilders.get(0))
                                            .must(queryBuilders.get(1))
                                            .must(queryBuilders.get(2))
                                    )
                    )
                    .setSize(1000).get();
            SearchHits hits = searchResponse.getHits();
            SearchHit[] hits1 = hits.getHits();
            for (SearchHit documentFields : hits1) {
                TicketVo ticketVo = JSONObject.parseObject(documentFields.getSourceAsString(), TicketVo.class);
                ticketVos.add(ticketVo);
            }
        }

        //如果是4个条件
        if (queryBuilders.size() == 4) {
            SearchResponse searchResponse = client.prepareSearch("ticket").setTypes("html")
                    .setQuery(
                            QueryBuilders.boolQuery()
                                    .must(QueryBuilders.boolQuery()
                                            .must(queryBuilders.get(0))
                                            .must(queryBuilders.get(1))
                                            .must(queryBuilders.get(2))
                                            .must(queryBuilders.get(3))
                                    )
                    )
                    .setSize(1000).get();
            SearchHits hits = searchResponse.getHits();
            SearchHit[] hits1 = hits.getHits();
            for (SearchHit documentFields : hits1) {
                TicketVo ticketVo = JSONObject.parseObject(documentFields.getSourceAsString(), TicketVo.class);
                ticketVos.add(ticketVo);
            }
        }
//        client.close();
        return ticketVos;
    }
}
