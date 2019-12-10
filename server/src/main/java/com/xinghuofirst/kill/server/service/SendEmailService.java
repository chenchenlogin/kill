package com.xinghuofirst.kill.server.service;

public interface SendEmailService {

     /**
      * TODO 进行异步邮件消息的通知
      * @param activityId 活动id
      * @param persionId 鑫管家id
      * @param orderNo 秒杀编号
      */
     void sendKillSuccessEmailMsg(Integer activityId,Integer persionId,String orderNo);



}
