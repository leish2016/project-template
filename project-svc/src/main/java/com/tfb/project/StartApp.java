package com.tfb.project;

import java.util.TimeZone;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@MapperScan(basePackages = "com.tfb.project.mapper")
public class StartApp {
    public static void main(String[] args) {
        //设置默认时区
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(StartApp.class, args);
    }

    @RequestMapping("/echo")
    public String echo(@RequestParam String check) {
        return check;
    }

}
