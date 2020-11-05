package com.fei.add.ticket.main;

import com.fei.add.ticket.service.SaveDataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @ClassName: StartMain
 * @Author chengfei
 * @Date 2020/11/4 10:10
 * @Description: TODO
 **/
@Component("sa")
public class StartMain implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(StartMain.class);

    //    @Value(value = "${url.file}")
    private String urlFiePath = "C:\\movie\\movie_url.txt";


    @Autowired
    private SaveDataService save;

    @Override
    public void run(String... args) throws IOException {
        logger.info("读取文件开始");
        BufferedReader reader = new BufferedReader(new FileReader(urlFiePath));

        String line = "";
        while ((line = reader.readLine()) != null) {
            try {
                Document document = Jsoup.connect(line).get();
                logger.info(line);
                //链接数据库，写入数据库中
                save.savaData(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
