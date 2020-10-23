package com.fei.movieticket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fei.movieticket.util.HttpConstant;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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

    //不带json串的post请求,这个请求现在没有用了
    @Test
    public void test() throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://res.91kami.com/Index/Item?urlPath=3VAPYPN7A4&spId=52135");
        httpPost.setHeader("Referer","http://shop.ajiyouhuiquan.top/");
        HttpResponse execute = httpclient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        if (entity != null){
            String dataJson = EntityUtils.toString(entity);
            JSONObject jsonObject = JSONObject.parseObject(dataJson);
            JSONObject data = jsonObject.getJSONObject("Data");
            JSONArray goods = new JSONArray();
            try {

                goods  = JSONArray.parseArray(data.getString("Goods"));
            }catch (Exception e){

            }
            for (int i = 0; i < goods.size(); i++) {
                JSONObject jsonObject1 = goods.getJSONObject(i);
                String name = jsonObject1.getString("Name");
                System.out.println(name);
            }

            System.out.println();
        }
    }
    //不带json串的post请求
    @Test
    public void test1() throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://res.91kami.com/Index/Index?cid=7790&q=&p=1&size=20&showInStore=false");
        httpPost.setHeader("Referer","http://shop.ajiyouhuiquan.top/");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("Cookie","visit_stat_key=KNG8CE3U37D67CGWRTCMHZM2; Cookie_1=value; ASP.NET_SessionId=o11jjxcf2zrssn0arobv23hn");
        HttpResponse execute = httpclient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        if (entity != null){
            String dataJson = EntityUtils.toString(entity);
            JSONObject jsonObject = JSONObject.parseObject(dataJson);
            JSONObject data = jsonObject.getJSONObject("Data");
            JSONObject stockNumsDict = data.getJSONObject("StockNumsDict");
            JSONArray goods = new JSONArray();
            try {

                goods  = JSONArray.parseArray(data.getString("Goods"));
            }catch (Exception e){

            }
            for (int i = 0; i < goods.size(); i++) {
                JSONObject jsonObject1 = goods.getJSONObject(i);
                String name = jsonObject1.getString("Name");
                String SpId = jsonObject1.getString("SpId");
                Integer num = (Integer)stockNumsDict.get(SpId);
                System.out.println(name);
                System.out.println(num);
            }
        }
    }

    //不带json串的post请求
    @Test
    public void test11() throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://res.91kami.com/Index/Index?q=&p=1&size=20&showInStore=false");
        httpPost.setHeader("Referer","http://shop.dypf.com.cn/");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("Cookie","visit_stat_key=6AXSXNCGXM6BVFNHMP45S4A2");
        HttpResponse execute = httpclient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        if (entity != null){
            String dataJson = EntityUtils.toString(entity);
            JSONObject jsonObject = JSONObject.parseObject(dataJson);
            JSONObject data = jsonObject.getJSONObject("Data");
            JSONArray goods = JSONArray.parseArray(data.getString("Goods"));
            for (int i = 0; i < goods.size(); i++) {
                JSONObject jsonObject1 = goods.getJSONObject(i);
                String name = jsonObject1.getString("Name");
                System.out.println(name);
            }

            System.out.println();
        }
    }

    //不带json串的post请求
    @Test
    public void test111() throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://res.91kami.com/Index/Index?q=&p=1&size=20&showInStore=false");
        httpPost.setHeader("Referer","http://shop.slc0929.top");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("Cookie","visit_stat_key=6AXSXNCGXM6BVFNHMP45S4A2");
        HttpResponse execute = httpclient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        if (entity != null){
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        }
    }

    //不带json串的post请求
    @Test
    public void test1111() throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://res.91kami.com/Index/Index?q=&p=1&size=20&showInStore=false");
        httpPost.setHeader("Referer","http://shop.ajiyouhuiquan.top/");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
        HttpResponse execute = httpclient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        if (entity != null){
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        }
    }

    //不带json串的post请求
    @Test
    public void test11111() throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://res.91kami.com/Index/Index?q=&p=1&size=20&showInStore=false");
        httpPost.setHeader("Referer","http://shop.slc0929.top/");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
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
        HttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://10.3.0.52:8081/movie/ticket");
        httpPost.setHeader("Host","10.3.0.52:8081");

        StringEntity stringEntity = new StringEntity("{\"cinema\":\"猫眼\"}",HttpConstant.UTF8_ENCODE);
        stringEntity.setContentEncoding(HttpConstant.UTF8_ENCODE);
        stringEntity.setContentType(HttpConstant.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);

        HttpEntity entity = build.execute(httpPost).getEntity();
        if (entity != null){
            String s = EntityUtils.toString(entity);
            System.out.println(s);
        }
    }

    @Test
    public void test3() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"cinema\":\"猫眼\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8081/movie/ticket")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        ResponseBody body1 = response.body();
        String string = body1.string();
        System.out.println(string);
    }
}
