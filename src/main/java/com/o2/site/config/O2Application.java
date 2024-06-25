package com.o2.site.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan
@SpringBootApplication
public class O2Application {

    public static void main(String[] args) {
        SpringApplication.run(O2Application.class, args);
    }

}
