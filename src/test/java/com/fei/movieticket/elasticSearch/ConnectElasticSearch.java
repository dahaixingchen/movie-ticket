package com.fei.movieticket.elasticSearch;

import com.alibaba.fastjson.JSONObject;
import com.fei.movieticket.vo.TicketVo;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @ClassName: TransportClient
 * @Author chengfei
 * @Date 2020/10/11 18:21
 * @Description: TODO
 **/
public class ConnectElasticSearch {

    private TransportClient client;

    @BeforeEach
    public void test() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "movie-title").build();
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("47.112.115.39"), 9300));
    }

    /**
     * 插入json格式的索引数据
     */
    @Test
    public void createIndex() {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2015-01-30\"," +
                "\"message\":\"travelying out Elasticsearch\"," +
                "\"name\":\"淘票票40优惠\"," +
                "\"aa\":\"travelying out Elasticsearch\"" +
                "}";
        IndexResponse indexResponse = client.prepareIndex("myindex3", "feifei")
                .setSource(json, XContentType.JSON).get();
        client.close();
    }

    /**
     * 通过XContentBuilder来实现索引的创建
     *
     * @throws IOException
     */
    @Test
    public void index3() throws IOException {
        IndexResponse indexResponse = client.prepareIndex("myindex11", "article")
                .setSource(new XContentFactory().jsonBuilder()
                        .startObject()
                        .field("pointid", "asdfasdfds")
                        .field("pointvalue", "ge我们发多少发党的爽肤水")
                        .field("inputtime", "个人工业化入贴源入贴源还让他")
                        .endObject()
                ).get();
        client.close();
    }

    /**
     * 通过指定索引字段的属性，如mapping来实现索引的创建
     *
     * @throws IOException
     */
    @Test
    public void index4() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("pointdata2")
                .startObject("_all")
                //关闭_all字段
                .field("enabled", false)
                .endObject()
                .startObject("_source")
                //关闭_source字段
                .field("enabled", false)
                .endObject()
                //properties：列出了文档中可能包含的每个字段的映射
                .startObject("properties")
                .startObject("pointid")
                .field("type", "string")
                .field("index", "not_analyzed")
                .field("store", true)
                .endObject()
                .startObject("pointvalue")
                .field("type", "string")
                .field("index", "not_analyzed")
                .endObject()
                .startObject("inputtime")
                .field("type", "date")
                .field("format", "yyyy-MM-dd HH:mm:ss")
                .field("index", "not_analyzed")
                .endObject()
                .endObject()
                .endObject()
                .endObject();
        IndexResponse indexResponse = client.prepareIndex("myindex11", "article", "3")
                .setSource(builder).get();
        client.close();
    }

    /**
     * 将java对象转换为json格式字符串进行创建索引
     */
    @Test
    public void objToIndex(){
        TicketVo ticketVo = new TicketVo();
        ticketVo.setName("猫眼电影40");
        ticketVo.setDescribe("猫眼电影，不可异地");
        ticketVo.setPrice(26.0);
        ticketVo.setUrl("www.baidu.com");
        String json = JSONObject.toJSONString(ticketVo);
        System.out.println(json);
        client.prepareIndex("ticket","html","1").setSource(json,XContentType.JSON).get();
        client.close();
    }

    /**
     * 删除索引
     * 删除整个索引库
     */
    @Test
    public  void  deleteIndex(){
        client.admin().indices().prepareDelete("indexsearch").execute().actionGet();
        client.close();
    }

    /**
     * 查询所有数据
     */
    @Test
    public void queryAll() {
        SearchResponse searchResponse = client
                .prepareSearch("ticket")
                .setTypes("html")
                .setQuery(new MatchAllQueryBuilder()).setSize(10000)
                .get();
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
        client.close();
    }

    /**
     * 查找价格18到28的人,包含18和28
     */
    @Test
    public void  rangeQuery(){
        SearchResponse searchResponse = client.prepareSearch("ticket")
                .setTypes("html")
                .setQuery(new RangeQueryBuilder("price").gt(0).lt(25)).setSize(1000)
                .get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields.getSourceAsString());
        }
        client.close();
    }

    /**
     * 词条查询
     */
    @Test
    public  void termQuery(){
        SearchResponse searchResponse = client.prepareSearch("ticket").setTypes("html")
                .setQuery(new TermQueryBuilder("name", "淘票票"))
                .get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields.getSourceAsString());
        }
    }


    /**
     * 模糊匹配查询有两种匹配符，分别是" * " 以及 " ? "， 用" * "来匹配任何字符，包括空字符串。用" ? "来匹配任意的单个字符
     */
    @Test
    public void wildCardQueryTest(){
        SearchResponse searchResponse = client.prepareSearch("ticket").setTypes("html")
                .setQuery(QueryBuilders.wildcardQuery("name", "河南"))
                .get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields.getSourceAsString());
        }
        client.close();
    }

    /**
     * fuzzyQuery表示英文单词的最大可纠正次数，最大可以自动纠正两次
     */
    @Test
    public void fuzzyQuery(){
        SearchResponse searchResponse = client.prepareSearch("ticket").setTypes("html")
                .setQuery(QueryBuilders.fuzzyQuery("name", "河南省万达").fuzziness(Fuzziness.TWO))
                .setSize(1000).get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields.getSourceAsString());
        }
        client.close();
    }


    /**
     * 多条件组合查询 boolQuery
     * 查询年龄是18到28范围内且性别是男性的，或者id范围在10到13范围内的
     * should表示或，求的是并集，must表示的是交集
     */
    @Test
    public void boolQueryTest(){
        String s = null;
        QueryBuilder price = QueryBuilders.rangeQuery("price").gt(0).lt(30);
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", "万达");
        TermsQueryBuilder termsQueryBuilder1 = QueryBuilders.termsQuery("name", "重庆");
        FuzzyQueryBuilder fuzziness = QueryBuilders.fuzzyQuery("name", "河南").fuzziness(Fuzziness.TWO);
        FuzzyQueryBuilder name = QueryBuilders.fuzzyQuery("name", "万达").fuzziness(Fuzziness.TWO);
        List<Object> values = termsQueryBuilder1.values();
        List<Object> values1 = termsQueryBuilder.values();

        SearchResponse searchResponse = client.prepareSearch("ticket").setTypes("html")
                .setQuery(
                        QueryBuilders.boolQuery()
                                .must(QueryBuilders.boolQuery()
                                        .must(price)
                                        .must(termsQueryBuilder)
                                        .must(termsQueryBuilder1))
                )
                .setSize(1000).get();
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println(documentFields.getFields());
            TicketVo ticketVo = JSONObject.parseObject(documentFields.getSourceAsString(), TicketVo.class);

            System.out.println(documentFields.getSourceAsString());

        }
        SearchRequestBuilder ticket = client.prepareSearch("ticket");
        SearchResponse searchResponse1 = ticket.setSize(1000).get();
        SearchHit[] hits2 = searchResponse1.getHits().getHits();
        for (SearchHit documentFields : hits2) {
            System.out.println(documentFields.getSourceAsString());
        }
        client.close();
    }



}
