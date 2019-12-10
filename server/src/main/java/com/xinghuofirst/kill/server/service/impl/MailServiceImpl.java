package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.server.dto.MailDto;
import com.xinghuofirst.kill.server.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @description: TODO
 * @author: 杜鹏
 * @date: 2019-12-09 18:36
 * @version: V1.0
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger log= LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    /**
     * 发送花哨邮件
     * @param dto
     */
    @Override
    @Async
    public void sendHTMLMail(final MailDto dto){
        try {
            MimeMessage message=mailSender.createMimeMessage();
            MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"utf-8");
            messageHelper.setFrom(env.getProperty("mail.send.from"));
            messageHelper.setTo(dto.getTos());
            messageHelper.setSubject(dto.getSubject());
            messageHelper.setText(dto.getContent(),true);

            mailSender.send(message);
            log.info("发送花哨邮件-发送成功!");
        }catch (Exception e){
            log.error("发送花哨邮件-发生异常： ",e.fillInStackTrace());
        }
    }
}
