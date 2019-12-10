package com.xinghuofirst.kill.server.utils;

/**
 * @author: 姜爽
 * @date: 2019/12/9 16:14
 * @description: 类的功能
 * @version: V1.0
 */
import com.xinghuofirst.kill.model.entity.Activity;

import java.util.List;

public class DateKit {

    /**
     时间段的比较处理 , 如果包含了传来的时段了, 就说明时间冲突了 , (允许首尾相等而不包含的情况)
     */
    public static boolean isContainEnd(Activity newActivity, Activity oldActivity) {

        long newStart = newActivity.getStartTime().getTime();
        long newEnd   = newActivity.getEndTime().getTime();
        long oldStart = oldActivity.getStartTime().getTime();
        long oldEnd   = oldActivity.getEndTime().getTime();
        // a0 包在了 b0 ~ b1 之间
        if( newStart >= oldStart && newStart < oldEnd ) {
            return true;
        }

        // b0 包在了 a0 ~ a1 之间
        if( newStart <= oldStart && newEnd > oldStart ) {
            return true;
        }
        // 相等
        if( newStart == oldStart && newEnd == oldEnd )  {
            return true;
        }
        return false;
    }
    public static boolean timeCompany(Activity newActivity, List<Activity> oldActivitys) {
        for (int i = 0 ; i < oldActivitys.size() ;i++){
            if (isContainEnd(newActivity,oldActivitys.get(i))) {
                return false;
            }
        }
        return true;
    }

}
