package com.xinghuofirst.kill.server.service.impl;
import	java.lang.ref.Reference;

import com.xinghuofirst.kill.model.entity.Person;
import com.xinghuofirst.kill.model.mapper.PersonRepository;
import com.xinghuofirst.kill.server.dto.PersonAndActivity;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import com.xinghuofirst.kill.server.service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.*;

import javax.annotation.Resource;


/**
 * @description: TODO 进行异步邮件消息的通知
 * @author: 杜鹏
 * @date: 2019-12-09 14:30
 * @version: V1.0
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {
    public static final Logger log= LoggerFactory.getLogger(SendEmailServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Environment env;

    @Autowired
    private KillSuccessService killSuccessService;
    @Resource
    private PersonRepository personRepository;

    /**
     * @description: TODO 秒杀成功异步发送邮件通知消息
     * @author: 杜鹏
     * @param activityId 活动id
     * @param personId 鑫管家id
     * @param orderNo 秒杀编号
     * @date: 2019-12-10 14:46
     */
    @Override
    public void sendKillSuccessEmailMsg(Integer activityId,Integer personId,String orderNo){
        log.info("秒杀成功异步发送邮件通知消息-准备发送消息：{}",personId);
        try {
            if (personId!=null){
                //TODO:获取鑫管家信息
                Person info = personRepository.selectById(personId);

                if (info!=null){
                    //TODO:rabbitmq发送消息的逻辑
                    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
                    rabbitTemplate.setExchange(env.getProperty("mq.kill.item.success.email.exchange"));
                    rabbitTemplate.setRoutingKey(env.getProperty("mq.kill.item.success.email.routing.key"));
                    //TODO：将info充当消息发送至队列
                    rabbitTemplate.convertAndSend(info, new MessagePostProcessor() {
                        @Override
                        public Message postProcessMessage(Message message) throws AmqpException {
                            MessageProperties messageProperties=message.getMessageProperties();
                            messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                            messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,PersonAndActivity.class);
                            return message;
                        }
                    });
                    //TODO:将商户分配给鑫管家
                    killSuccessService.assignPerson(info,orderNo);
                }
            }
        }catch (Exception e){
            log.error("秒杀成功异步发送邮件通知消息-发生异常，消息为：{}",personId,e.fillInStackTrace());
        }
    }
}
