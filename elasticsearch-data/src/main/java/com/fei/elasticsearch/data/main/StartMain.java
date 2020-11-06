package com.fei.elasticsearch.data.main;

import com.fei.elasticsearch.data.service.MovieTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StartMain implements CommandLineRunner {

    @Autowired
    private MovieTicketService movieTicketService;

    Logger logger = LoggerFactory.getLogger(StartMain.class);

    //真正的入口函数
    @Override
    public void run(String... args) throws Exception {
        logger.info("数据开始入Elasticsearch");
        movieTicketService.disposeTicketResult();
        logger.info("数据收集结束");
    }

    @Scheduled(cron = "0 0 */6 *  * ?")
    public void test(){
        logger.info("数据开始入Elasticsearch");
        movieTicketService.disposeTicketResult();
    }
}