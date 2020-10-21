package com.fei.movieticket;

import com.fei.movieticket.entity.MovieTicketEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName: MovieTest
 * @Author chengfei
 * @Date 2020/9/27 19:12
 * @Description: TODO
 **/
public class MovieTest {

    public static final String REQUEST_URL = "http://res.91kami.com/Index/Index?" +
            "&q=&p=1&size=200&showInStore=false";

    @Test
    public static void main(String[] args) {
        //第一步通过请求url解析出响应内容
        String htmlContent = parseContext(REQUEST_URL);
        System.out.println(htmlContent);
    }

    /****
     * 通过httpclient，读取url中的响应内容并返回
     * @param url 请求的url路径
     * @return
     */
    public static String parseContext(String url) {

        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("访问地址url不能为空");
        }

        String html = null;
        // 创建httpclient对象
        HttpClient httpclient = HttpClientBuilder.create().build();
        ArrayList<MovieTicketEntity> movieTicketEntities = new ArrayList<>();
        try {
            // 创建httpget对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Referer", "http://shop.ajiyouhuiquan.top/");
            httpPost.setHeader("Cookie", "visit_stat_key=KNG8CE3U37D67CGWRTCMHZM2; ASP.NET_SessionId=xxmswej4w5ar3ue5il52mp0o; visit_stat_key=KNG8CE3U37D67CGWRTCMHZM2; Cookie_1=value");
            // 执行post请求.
            HttpResponse response = httpclient.execute(httpPost);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    html = EntityUtils.toString(entity);
                    JSONArray ticketData = (JSONArray) ((JSONObject) JSONObject.fromObject(html).get("Data")).get("Goods");
                    JSONObject ticketNum = (JSONObject) ((JSONObject) JSONObject.fromObject(html).get("Data")).get("StockNumsDict");

                    for (Object o : ticketData) {
                        MovieTicketEntity movieTicketEntity = new MovieTicketEntity();
                        String name = (String) JSONObject.fromObject(o).get("Name");
                        String summary = (String) JSONObject.fromObject(o).get("Summary");
                        Double price = (Double) JSONObject.fromObject(o).get("Price");
                        Integer spId = (Integer) JSONObject.fromObject(o).get("SpId");
                        movieTicketEntity.setName(name);
                        movieTicketEntity.setSummary(summary);
                        movieTicketEntity.setPrice(price);
                        movieTicketEntity.setSpId(spId);
                        //票的数量
                        Integer num = (Integer) ticketNum.get(Integer.toString(spId));
                        movieTicketEntity.setNum(num);
                        movieTicketEntities.add(movieTicketEntity);
                    }
                    System.out.println(movieTicketEntities.size());
                    movieTicketEntities.forEach(a -> System.out.println(a));

                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return html;
    }}
