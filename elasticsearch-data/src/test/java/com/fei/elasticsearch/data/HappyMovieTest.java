package com.fei.elasticsearch.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName: HappyMovieTest
 * @Author chengfei
 * @Date 2020/11/3 9:48
 * @Description: TODO
 **/
public class HappyMovieTest {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\movie\\happy_movie.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("C:\\movie\\more_happy_movie.txt"));
        String line = "";
        while ((line =bufferedReader.readLine()) != null){
            Document document = Jsoup.connect(line).get();
            Elements good = document.getElementsByClass("good");
            for (Element element : good) {
                System.out.println(element.getElementsByClass("titlea fl").text() + line);
                bufferedWriter.write(element.getElementsByClass("titlea fl").text() + line + "\n");
                bufferedWriter.flush();
            }
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
