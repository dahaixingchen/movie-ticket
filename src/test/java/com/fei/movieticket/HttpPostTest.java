package com.fei.movieticket;

import com.fei.movieticket.util.HttpConstant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;

/**
 * @ClassName: JsoupPostTest
 * @Author chengfei
 * @Date 2020/10/21 19:25
 * @Description: TODO
 **/
public class HttpPostTest {

    //不带json串的post请求
    @Test
    public void test() throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://res.91kami.com/Index/Item?urlPath=3VAPYPN7A4&spId=52135");
        httpPost.setHeader("Referer","http://shop.ajiyouhuiquan.top/");
        httpPost.setHeader("Cookie","visit_stat_key=KNG8CE3U37D67CGWRTCMHZM2; visit_stat_key=KNG8CE3U37D67CGWRTCMHZM2; Cookie_1=value; ASP.NET_SessionId=ks2sf1gvtmcfrozrbe4obnsu");
        HttpResponse execute = httpclient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        if (entity != null){
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        }
    }

    //带json串的post请求
    @Test
    public void test2() throws IOException {
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://10.3.0.52:8081/movie/ticket");
        httpPost.setHeader("Host","10.3.0.52:8081");

        StringEntity stringEntity = new StringEntity("{{\"cinema\":\"猫眼\"}}",HttpConstant.UTF8_ENCODE);
        stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
        stringEntity.setContentType(HttpConstant.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);

        HttpEntity entity = build.execute(httpPost).getEntity();
        if (entity != null){
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        }
    }
}
