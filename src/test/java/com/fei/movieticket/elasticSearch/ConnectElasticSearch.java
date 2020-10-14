package com.fei.movieticket.elasticSearch;

import com.alibaba.fastjson.JSONObject;
import com.fei.movieticket.vo.TicketVo;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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

}
