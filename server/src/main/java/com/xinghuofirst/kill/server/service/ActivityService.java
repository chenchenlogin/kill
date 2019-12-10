package com.xinghuofirst.kill.server.service;

import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.model.entity.KillSuccess;

import java.util.List;

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
    Activity showBeforeLastService();
    Activity showNowActivityService();

     /** 判断归属地是否相同  duanlian**/
     Activity selectNowActivity();



}
