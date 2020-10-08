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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @ClassName: MovieHTMLTest
 * @Author chengfei
 * @Date 2020/10/1 22:45
 * @Description: TODO
 **/
public class MovieHTMLTest {
    public static final String REQUEST_URL = "http://kmg.kamigo.cn/link/6J83ZJGJ2L76JJ55";

    @Test
    public static void main(String[] args) {
        //第一步通过请求url解析出响应内容
        String htmlContent = parseCon(REQUEST_URL);
    }

    private static String parseCon(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("访问地址url不能为空");
        }
        String html = null;

        // 创建httpclient对象
        HttpClient httpclient = HttpClientBuilder.create().build();
        ArrayList<MovieTicketEntity> movieTicketEntities = new ArrayList<>();
        try {
            // 创建httpget对象
            HttpGet httpGet = new HttpGet(url);
            // 执行get请求.
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);
            Document parse = Jsoup.parse(s);
            Elements elementsByClass = parse.getElementsByClass("qs-cleaar goods");
            elementsByClass = elementsByClass.get(0).getElementsByTag("li");
            for (int i = 0; i < elementsByClass.size(); i++) {
                Elements titlea_fl = elementsByClass.get(i).getElementsByClass("shoname");
                System.out.println(titlea_fl.text());
                Elements jqzs_fr = elementsByClass.get(i).getElementsByClass("price active");
                System.out.println(jqzs_fr.text());
                Elements jqzs_fr1 = elementsByClass.get(i).getElementsByClass("hideitemcon");
                System.out.println(jqzs_fr1.text());
                Elements jqzs_fr2 = elementsByClass.get(i).getElementsByClass("kc");
                System.out.println(jqzs_fr2.text());
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
