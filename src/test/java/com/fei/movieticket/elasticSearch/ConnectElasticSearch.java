package com.fei.movieticket.elasticSearch;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void createIndex(){
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"travelying out Elasticsearch\"" +
                "}";
        IndexResponse indexResponse = client.prepareIndex("myindex1", "article", "1").setSource(json, XContentType.JSON).get();
        client.close();
    }
}
