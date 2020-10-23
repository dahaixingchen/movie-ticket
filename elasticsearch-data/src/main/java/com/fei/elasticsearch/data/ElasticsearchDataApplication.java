package com.fei.elasticsearch.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.fei.elasticsearch.data.mapper")
public class ElasticsearchDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchDataApplication.class, args);
    }

}
