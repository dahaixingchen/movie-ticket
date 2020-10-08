package com.fei.movieticket;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @ClassName: JsoupTEst
 * @Author chengfei
 * @Date 2020/10/8 10:00
 * @Description: TODO
 **/
public class JsoupTest {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("http://mingming.84fk.cn/").get();
            Elements titles = document.getElementsByAttributeValue("class","elli");
            Elements price = document.getElementsByAttributeValue("class","price");
            for (int i = 0; i < titles.size(); i++) {
                System.out.println(titles.get(i).text());
                System.out.println(price.get(i).text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test1(){
        try {
            Document document = Jsoup.connect("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/1057981").get();
            Elements titles = document.getElementsByAttributeValue("class","good");
            for (int i = 0; i < titles.size(); i++) {
                Elements p = titles.get(i).getElementsByTag("p");
                System.out.println(p.get(0).text());
                Elements span = titles.get(i).getElementsByTag("span");
                System.out.println(span.get(0).text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            Document document = Jsoup.connect("http://tmzdfk.com/").get();
            Elements titles = document.getElementsByTag("tr");
            for (int i = 0; i < titles.size(); i++) {
                Elements p = titles.get(i).getElementsByTag("td");
                for (int j = 0; j <p.size(); j++) {
                    System.out.println(p.get(0).text());
                    System.out.println(p.get(1).text());
                    System.out.println(p.get(2).text());

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
