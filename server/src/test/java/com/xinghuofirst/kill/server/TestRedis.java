package com.xinghuofirst.kill.server;
import com.xinghuofirst.kill.server.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhou_gc@suixingpay.com
 * @description
 * @date 2019/12/10 14:00
 */


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestRedis {


    @Autowired
    RedisUtil redisUtil;
    @Test

    /*zhou_gc测试redis*/
    public void add(){
            RedisUtil.set("k11","v11");

    }


}
