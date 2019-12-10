package com.xinghuofirst.kill.server.service;

import com.xinghuofirst.kill.model.entity.Activity;

import java.util.List;

/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:24
 * @version: V1.0
 */
public interface ActivityService  {
    Integer insertActivity(Activity activity);
    List<Activity> showAll();
    Activity showNextService();
}
