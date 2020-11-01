package com.fei.elasticsearch.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

        File file1 = new File("C:\\movie\\not_movie.txt");
        FileWriter out1 = new FileWriter(file1, true);

        File file2 = new File("C:\\movie\\happy_movie.txt");
        FileWriter out2 = new FileWriter(file2, true);


        for (int i = 2465656; i < 3057900; i++) {
            try {
                Document document = Jsoup.connect("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i)).get();
                if ("链接可能失效或不存在，请重新获取。".equals(document.getElementsByTag("title").text())) {
                }else if ("店铺未审核".equals(document.getElementsByTag("title").text())){
                    out1.write("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/"
                            + Integer.toString(i) + "\n");
                    System.out.println("店铺未审核  http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
                    out1.flush();
                }else {
                    Elements good = document.getElementsByClass("good");
                    if (good.size() >= 1){
                        out2.write("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/"
                                + Integer.toString(i) + "\n");
                        System.out.println("高质量往网站：http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
                        out2.flush();
                    }else {

                        out1.write("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/"
                                + Integer.toString(i) + "\n");
                        System.out.println("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
                        out1.flush();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        out.close();
        out1.close();
        out2.close();
    }
}
