package com.xinghuofirst.kill.server.utils;

import com.xinghuofirst.kill.model.entity.Activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: 姜爽
 * @date: 2019/12/10 8:39
 * @description: 日期工具类
 * @version: V1.0
 */
public class DateUtil {
    public static String dateToString(Date datetemp) {
        String dataString = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        dataString = formatter.format(datetemp);
        return dataString;
    }

    public static boolean compare(String date1,String data2) {
        return date1.equals(data2);
    }
    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致,在的话返回true，不在的话返回false
     * @param nowTime 当前时间
     * @param activity 开始时间
     * @return
     * @author 姜爽
     */
    public static boolean isEffectiveDate(Date nowTime, Activity activity) {
        Date startTime = activity.getStartTime();
        Date endTime = activity.getEndTime();
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean activityCompany(Date nowTime, List<Activity> activitys) {
        for (int i = 0 ; i < activitys.size() ;i++){
            if (isEffectiveDate(nowTime,activitys.get(i))) {
                return false;
            }
        }
        return true;
    }
}
