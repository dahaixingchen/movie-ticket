package com.fei.elasticsearch.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MoreThreadURLTest
 * @Author chengfei
 * @Date 2020/10/30 22:27
 * @Description: TODO
 **/
public class MoreThreadURLTest {
    public static void main(String[] args) throws IOException {

        List<String> tmpList = new ArrayList<String>();
        for (int i = 1057900; i < 2100000; i++) {
            tmpList.add("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
        }

        int length = tmpList.size();
        int num = 10000; //初始线程数

        //启动多线程
        if (num > length) {
            num = length;
        }
        int baseNum = length / num;
        int remainderNum = length % num;
        int end = 0;
        for (int i = 0; i < num; i++) {
            int start = end;
            end = start + baseNum;
            if (i == (num - 1)) {
                end = length;
            } else if (i < remainderNum) {
                end = end + 1;
            }
            MovieThread thread = new MovieThread("线程[" + (i + 1) + "] ", tmpList, start, end);
            thread.start();
        }
    }

    static class MovieThread extends Thread {
        private int i;
        private String threadName;
        private List<String> list;
        private int startIndex;
        private int endIndex;

        public MovieThread(String threadName, List<String> list, int startIndex, int endIndex) {
            this.threadName = threadName;
            this.list = list;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        File file = new File("C:\\movie\\movie.txt");
        FileWriter out;

        File file1 = new File("C:\\movie\\not_movie.txt");
        FileWriter out1;

        File file2 = new File("C:\\movie\\happy_movie.txt");
        FileWriter out2;

        {
            try {
                out1 = new FileWriter(file1, true);
                out2 = new FileWriter(file2, true);
                out = new FileWriter(file, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            List<String> list1 = list.subList(startIndex, endIndex);
            try {
                for (String a : list1) {
                    Document document = Jsoup.connect(a).get();
                    if ("链接可能失效或不存在，请重新获取。".equals(document.getElementsByTag("title").text())) {
                    } else if ("店铺未审核".equals(document.getElementsByTag("title").text())) {
                        out1.write("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/"
                                + Integer.toString(i) + "\n");
                        System.out.println("店铺未审核  http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
                        out1.flush();
                    } else {
                        Elements good = document.getElementsByClass("good");
                        if (good.size() >= 1) {
                            out2.write("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/"
                                    + Integer.toString(i) + "\n");
                            System.out.println("高质量往网站：http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
                            out2.flush();
                        } else {

                            out1.write("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/"
                                    + Integer.toString(i) + "\n");
                            System.out.println("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
                            out1.flush();
                        }

                    }

                }
                out.close();
                out1.close();
                out2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
