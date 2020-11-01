package com.fei.search.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fei.search.ticket.mapper")
public class SearchTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchTicketApplication.class, args);
    }

}
