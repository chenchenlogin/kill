package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.model.mapper.ActivityRepository;
import com.xinghuofirst.kill.server.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ActivityRepository activityRepository;

    @Override
    public Integer insertActivity(Activity activity) {
        return null;
    }

    @Override
    public List<Activity> showAll() {
        return activityRepository.findAllWithResult(null);
    }

    @Override
    public Activity showNextService() {
        return activityRepository.selectNextActivity();
    }

    @Override
    public Activity showBeforeLastService() {
        return activityRepository.selectLastActivity();
    }
}
