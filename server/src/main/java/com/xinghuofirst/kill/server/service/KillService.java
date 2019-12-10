package com.xinghuofirst.kill.server.service;


public interface KillService {

    /**
     * 用户秒杀核心业务逻辑
     * @param activityId
     * @param personId
     * @return
     * @throws Exception
     */
    Boolean killItem(Integer activityId, Integer personId) throws Exception;
}
