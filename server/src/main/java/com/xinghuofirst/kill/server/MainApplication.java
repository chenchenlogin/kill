package com.xinghuofirst.kill.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author:zhou_gc
 * @Date: 2019/12/8 22:50
 **/
@SpringBootApplication
@MapperScan("com.xinghuofirst.kill.model.mapper")
public class MainApplication   {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

}
