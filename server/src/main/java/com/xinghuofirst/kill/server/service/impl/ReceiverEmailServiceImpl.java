package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.mapper.PersonRepository;
import com.xinghuofirst.kill.server.dto.PersonAndActivity;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import com.xinghuofirst.kill.server.service.MailService;
import com.xinghuofirst.kill.server.service.ReceiverEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: TODO 接收邮件
 * @author: dupeng
 * @date: 2019-12-09 18:35
 * @version: V1.0
 */
@Service
public class ReceiverEmailServiceImpl implements ReceiverEmailService {
    public static final Logger log= LoggerFactory.getLogger(ReceiverEmailServiceImpl.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private KillSuccessService killSuccessService;

    @Autowired
    private Environment env;

    @Autowired
    @Resource
    private PersonRepository personRepository;

    /**
     * @description: TODO 秒杀异步邮件通知-接收消息，并将商户分配给鑫管家
     * @author: dupeng
     * @param: info 发送邮件信息
     * @date: 2019-12-10 14:51
     */
   /* @Override
    @RabbitListener(queues = {"${mq.kill.item.success.email.queue}"},containerFactory = "singleListenerContainer")
    public void consumeEmailMsg(PersonAndActivity info){
        try {
            log.info("秒杀异步邮件通知-接收消息:{}",info);
            //TODO:真正的发送邮件....
            final String content=String.format(env.getProperty("mail.kill.item.success.content"),info.getUserName(),info.getActivityId(),info.getUserId());
            MailDto dto=new MailDto(env.getProperty("mail.kill.item.success.subject"),content,new String[]{info.getEmail()});
            mailService.sendHTMLMail(dto);
             //TODO:将商户分配给鑫管家
            killSuccessService.assignPerson(info);
        }catch (Exception e){
            log.error("秒杀异步邮件通知-接收消息-发生异常：",e.fillInStackTrace());
        }
    }*/

    /**
     * @description: TODO 压测--给鑫管家分配用户
     * @author: dupeng
     * @param: info 发送邮件信息
     * @date: 2019-12-10 14:51
     */
    @Override
    @RabbitListener(queues = {"${mq.kill.item.success.email.queue}"},containerFactory = "singleListenerContainer")
    public void consumeEmailMsg(PersonAndActivity info){
        try {
            log.info("秒杀异步邮件通知-接收消息:{}",info);

            //TODO:分配用户
           //killSuccessService.assignPerson(info);
        }catch (Exception e){
            log.error("秒杀异步邮件通知-接收消息-发生异常：",e.fillInStackTrace());
        }
    }
}
