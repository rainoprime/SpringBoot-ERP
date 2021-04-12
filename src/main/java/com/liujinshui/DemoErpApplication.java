package com.liujinshui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liujinshui.*.dao")

public class DemoErpApplication{


    public static void main(String[] args) {
            SpringApplication.run(DemoErpApplication.class, args);
    }
}
