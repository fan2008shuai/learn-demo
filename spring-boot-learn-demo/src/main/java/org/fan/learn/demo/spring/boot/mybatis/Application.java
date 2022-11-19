package org.fan.learn.demo.spring.boot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author
 * @date 2022/11/14
 */
@SpringBootApplication
@MapperScan("org.fan.learn.demo.spring.boot.mybatis.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
