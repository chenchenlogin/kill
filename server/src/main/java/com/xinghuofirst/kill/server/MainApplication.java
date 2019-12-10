package com.xinghuofirst.kill.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * @Author:zhou_gc
 * @Date: 2019/12/8 22:50
 **/

@SpringBootApplication
@MapperScan(basePackages = "com.xinghuofirst.kill.model.mapper")
//@ServletComponentScan("com.xinghuofirst.kill.server.listener")
public class MainApplication   {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }

}
