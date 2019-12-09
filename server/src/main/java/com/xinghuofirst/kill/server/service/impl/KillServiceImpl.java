package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.model.entity.KillSuccess;
import com.xinghuofirst.kill.model.entity.Person;
import com.xinghuofirst.kill.model.mapper.ActivityRepository;
import com.xinghuofirst.kill.model.mapper.KillSuccessRepository;
import com.xinghuofirst.kill.server.service.KillService;
import com.xinghuofirst.kill.server.service.SendEmailService;
import com.xinghuofirst.kill.server.utils.RandomUtil;
import com.xinghuofirst.kill.server.utils.SnowFlake;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description: 秒杀用户服务实现层
 * @author: dupeng
 * @date: 2019-12-09 14:19
 * @version: V1.0
 */
@Service
public class KillServiceImpl  implements KillService {
    private static final Logger log= LoggerFactory.getLogger(KillService.class);

    private SnowFlake snowFlake=new SnowFlake(2,3);

    /*@Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;*/

    /*@Autowired
    private ItemKillMapper itemKillMapper;*/

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private KillSuccessRepository killSuccessRepository;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * @description: 用户秒杀核心业务逻辑的处理-redis的分布式锁
     * @param activityId
     * @param personId
     * @return
     * @throws Exception
     * @author: dupeng
     * @date: 2019-12-09 14:25
     */
    @Override
    public Boolean killItem(Integer activityId, Integer personId) throws Exception {
        Boolean result=false;
        //TODO:判断当前鑫管家是否已经抢购过用户
        if (activityRepository.countByActivityPersonId(activityId,personId) <= 0){
            //TODO:借助Redis的原子操作实现分布式锁-对共享操作-资源进行控制
            ValueOperations valueOperations=stringRedisTemplate.opsForValue();
            final String key=new StringBuffer().append(activityId).append(personId).append("-RedisLock").toString();
            final String value= RandomUtil.generateOrderCode();
            //luna脚本提供“分布式锁服务”，就可以写在一起
            Boolean cacheRes=valueOperations.setIfAbsent(key,value);
            //TODO:redis部署节点宕机了
            if (cacheRes){
                stringRedisTemplate.expire(key,30, TimeUnit.SECONDS);
                try {
                    //TODO:判断当前活动用户是否可以被秒杀
                    Integer i = activityRepository.selectActivitySurplus(activityId);
                    if (i!=null && i>0){
                        //TODO:扣减库存-减一
                        int res=killSuccessRepository.updateSurpus(activityId);
                        if (res>0){
                            //TODO:扣减是否成功?是-通知用户秒杀成功的消息
                            commonRecordKillSuccessInfo(activityId,personId);
                            result=true;
                        }
                    }
                }catch (Exception e){
                    throw new Exception("还没到抢购日期、已过了抢购时间或已被抢购完毕！");
                }finally {
                    if (value.equals(valueOperations.get(key).toString())){
                        stringRedisTemplate.delete(key);
                    }
                }
            }
        }else{
            throw new Exception("Redis-您已经抢购过该商品了!");
        }
        return result;
    }


    /**
     * @description: 记录用户秒杀成功后生成的订单-并进行异步邮件消息的通知
     * @param persionId
     * @return
     * @throws Exception
     * @author: dupeng
     * @date: 2019-12-09 14:25
     */
    private void commonRecordKillSuccessInfo(Integer activityId,Integer persionId) throws Exception{
        //TODO:记录抢购成功后生成的秒杀订单记录
        KillSuccess entity=new KillSuccess();
        String orderNo=String.valueOf(snowFlake.nextId());
        //TODO:使用雪花算法生成秒杀编码
        entity.setKillNumber(orderNo);
        entity.setActivity(new Activity().setActivityId(activityId));
        entity.setPerson(new Person().setUserId(persionId));
        entity.setKillTime(DateTime.now().toDate());
        //TODO:判断当前鑫管家是否已经抢购过用户
        if (activityRepository.countByActivityPersonId(activityId,persionId) <= 0){
            int res=killSuccessRepository.insertSelective(entity);
            if (res>0){
                //TODO:进行异步邮件消息的通知=rabbitmq+mail
                sendEmailService.sendKillSuccessEmailMsg(activityId,persionId);
            }
        }
    }

}
