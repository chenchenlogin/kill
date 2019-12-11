package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Person;
import com.xinghuofirst.kill.model.mapper.PersonRepository;
import com.xinghuofirst.kill.server.dto.MailDto;
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
    private Environment env;

    @Autowired
    private PersonRepository personRepository;

    /**
     * @description: TODO 秒杀异步邮件通知-接收消息，并将商户分配给鑫管家
     * @author: dupeng
     * @param: info 发送邮件信息
     * @date: 2019-12-10 14:51
     */
    @Override
    @RabbitListener(queues = {"${mq.kill.item.success.email.queue}"},containerFactory = "singleListenerContainer")
    public void consumeEmailMsg(Person info){
        try {
            log.info("秒杀异步邮件通知-接收消息:{}",info);
            //TODO:真正的发送邮件....
            final String content=String.format(env.getProperty("mail.kill.item.success.content"),info.getUserName());
            MailDto dto=new MailDto(env.getProperty("mail.kill.item.success.subject"),content,new String[]{info.getEmail()});
            mailService.sendHTMLMail(dto);

        }catch (Exception e){
            e.printStackTrace();
            log.error("秒杀异步邮件通知-接收消息-发生异常：",e.fillInStackTrace());
        }
    }

}
