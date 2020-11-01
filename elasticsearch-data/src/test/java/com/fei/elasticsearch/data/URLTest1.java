package com.fei.elasticsearch.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: URLTest1
 * @Author chengfei
 * @Date 2020/10/29 19:09
 * @Description: TODO
 **/
public class URLTest1 {
    static class HandleThread extends Thread {
        private String threadName;
        private List<String> list;
        private int startIndex;
        private int endIndex;

        public HandleThread(String threadName, List<String> list, int startIndex, int endIndex) {
            this.threadName = threadName;
            this.list = list;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public void run() {
            List<String> list1 = list.subList(startIndex, endIndex);
            list1.forEach(a->{
                try {
                    Document document = Jsoup.connect(a).get();
                    if ("链接可能失效或不存在，请重新获取。".equals(document.getElementsByTag("title").text())){
                    }else {
                        System.out.println(a);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(threadName+"处理了"+list1.size()+"条！startIndex:"+startIndex+"|endIndex:"+endIndex);
        }

    }

    public static void main(String[] args) {
        URLTest1 test = new URLTest1();
        List<String> tmpList = new ArrayList<String>();
        for (int i = 1057900; i < 2100000; i++) {
            tmpList.add("http://fk.gofaka.cn/fkpt/index.php/Home/index/index/uid/" + Integer.toString(i));
        }

        int length = tmpList.size();
        int num = 10000; //初始线程数

        //启动多线程
        if(num > length){
            num = length;
        }
        int baseNum = length / num;
        int remainderNum = length % num;
        int end  = 0;
        for (int i = 0; i < num; i++) {
            int start = end ;
            end = start + baseNum;
            if(i == (num-1)){
                end = length;
            }else if( i < remainderNum){
                end = end + 1;
            }
            HandleThread thread = new HandleThread("线程[" + (i + 1) + "] ",  tmpList,start , end);
            thread.start();
        }
    }
}
