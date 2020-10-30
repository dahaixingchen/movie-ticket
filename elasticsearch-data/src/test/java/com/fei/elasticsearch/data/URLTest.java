package com.fei.elasticsearch.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sun.java2d.pipe.SpanIterator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName: URLTest
 * @Author chengfei
 * @Date 2020/10/29 18:52
 * @Description: TODO
 **/
public class URLTest {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\movie\\movie.txt");
        FileWriter out = new FileWriter(file, true);


        for (int i = 1060228; i < 2057900; i++) {
            try {
                Document document = Jsoup.connect("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i)).get();
                if ("链接可能失效或不存在，请重新获取。".equals(document.getElementsByTag("title").text())) {
                } else {

                    out.write("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/"
                            + Integer.toString(i) + "\n");
                    System.out.println("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        out.close();
    }
}
