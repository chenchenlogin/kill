package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Person;
import com.xinghuofirst.kill.model.mapper.PersonRepository;
import com.xinghuofirst.kill.server.dto.MailDto;
import com.xinghuofirst.kill.server.dto.PersonAndActivity;
import com.xinghuofirst.kill.server.service.MailService;
import com.xinghuofirst.kill.server.service.ReceiverEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @author: 杜鹏
 * @date: 2019-12-09 18:35
 * @version: V1.0
 */
@Service
public class ReceiverEmailServiceImpl implements ReceiverEmailService {
    public static final Logger log= LoggerFactory.getLogger(ReceiverEmailServiceImpl.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private Environment env;

    /*@Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;*/

    @Autowired
    private PersonRepository personRepository;
    /**
     * 秒杀异步邮件通知-接收消息
     */
    @Override
    @RabbitListener(queues = {"${mq.kill.item.success.email.queue}"},containerFactory = "singleListenerContainer")
    public void consumeEmailMsg(PersonAndActivity info){
        try {
            log.info("秒杀异步邮件通知-接收消息:{}",info);

            //TODO:真正的发送邮件....
            //MailDto dto=new MailDto(env.getProperty("mail.kill.item.success.subject"),"这是测试内容",new String[]{info.getEmail()});
            //mailService.sendSimpleEmail(dto);

            final String content=String.format(env.getProperty("mail.kill.item.success.content"),info.getUserName(),info.getActivityId(),info.getUserId());
            MailDto dto=new MailDto(env.getProperty("mail.kill.item.success.subject"),content,new String[]{info.getEmail()});
            mailService.sendHTMLMail(dto);
        }catch (Exception e){
            log.error("秒杀异步邮件通知-接收消息-发生异常：",e.fillInStackTrace());
        }
    }
}
