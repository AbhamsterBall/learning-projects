package com.hyh;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.hyh"}) // 替换成你的 Controller 所在
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Config.class, args);
    }
}
