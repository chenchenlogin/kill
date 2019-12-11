package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Activity;

import com.xinghuofirst.kill.model.entity.KillSuccess;

import com.xinghuofirst.kill.model.mapper.ActivityRepository;
import com.xinghuofirst.kill.server.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;


/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:24
 * @version: V1.0
 */
@Service

public class ActivityServiceImpl implements ActivityService {

    @Autowired
    @Resource
    private ActivityRepository activityRepository;

    @Override
    public Integer insertActivity(Activity activity) {
        return activityRepository.insert(activity);
    }

    @Override
    public List<Activity> showAll() {
        return activityRepository.findAllWithResult(null);
    }

    @Override
    public Activity showNextService() {
        return activityRepository.selectNextActivity();

    }

    /**
     *
     * @param
     * @return duanlian  省份是否相同
     */
    @Override
    public Activity selectNowActivity(){
        return activityRepository.selectNowActivity();
    }

    @Override
    public Integer showNumByPro(String province) {
        return activityRepository.selectSurplusCountByProvince(province);
    }


    @Override
    public Activity showBeforeLastService() {
        return activityRepository.selectLastActivity();
    }

    @Override
    public Activity showNowActivityService() {
        return activityRepository.selectNowActivity();
    }


}
