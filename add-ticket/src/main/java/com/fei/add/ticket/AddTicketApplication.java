package com.fei.add.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.fei.add.ticket.mapper")
public class AddTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddTicketApplication.class, args);
    }

}
