package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.model.entity.KillSuccess;
import com.xinghuofirst.kill.model.mapper.ActivityRepository;
import com.xinghuofirst.kill.model.mapper.BusinessRepository;
import com.xinghuofirst.kill.model.mapper.KillSuccessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: 姜爽
 * @date: 2019/12/9 21:50
 * @description: 类的功能
 * @version: V1.0
 */
@RestController
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    ActivityRepository activityRepository;

    @GetMapping("test1")
    public List<Activity> test1() {
        return activityRepository.selectAfterActivity();
    }
    @GetMapping("test2")
    public Activity test2() {
        return activityRepository.selectNextActivity();
    }
    @GetMapping("test3")
    public Activity test3() {
        return activityRepository.selectNowActivity();
    }
    @GetMapping("test4")
    public Integer test4(Integer activityId) {
        return activityRepository.selectActivitySurplus(activityId);
    }

    @PostMapping("test11")
    public List<Activity> test5(@RequestBody Activity activity){
        return activityRepository.findAllWithResult(activity);
    }
    @Autowired
    BusinessRepository businessRepository;
    @GetMapping("test5")
    public Integer selectBusinessByProvince(String province){
        return businessRepository.selectBusinessByProvince(province);
    }
    @Autowired KillSuccessRepository killSuccessRepository;

    @GetMapping("test6")
    public Integer countByActivityPersonId(Integer activityId,Integer personId){
        return killSuccessRepository.countByActivityPersonId(activityId,personId);
    }
    @GetMapping("test7")
    public Integer updateSurpus(Integer activityId){
        return killSuccessRepository.updateSurpus(activityId);
    }

    @GetMapping("test8")
    public List<KillSuccess> selectKillSuccessByPersonId(Integer personId){
        return killSuccessRepository.selectKillSuccessByPersonId(personId);
    }

   /* @GetMapping("test9")
    public KillSuccess selectBusinessByPersonIdActiviityId(Integer personId,Integer activityId){

        System.out.println(personId+"-----"+activityId);
        return killSuccessRepository.selectBusinessByPersonIdActiviityId();
    }*/

}
