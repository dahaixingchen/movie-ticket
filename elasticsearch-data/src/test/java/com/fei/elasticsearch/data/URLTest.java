package com.fei.elasticsearch.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sun.java2d.pipe.SpanIterator;

import java.io.IOException;

/**
 * @ClassName: URLTest
 * @Author chengfei
 * @Date 2020/10/29 18:52
 * @Description: TODO
 **/
public class URLTest {
    public static void main(String[] args) throws IOException {
        for (int i = 1000000; i < 10000000; i++) {
            Document document = Jsoup.connect("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i)).get();
            if ("链接可能失效或不存在，请重新获取。".equals(document.getElementsByTag("title").text())){
            }else {
                System.out.println("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
            }
        }
    }
}
