package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;
import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.ActivityService;
import com.xinghuofirst.kill.server.utils.DateKit;
import com.xinghuofirst.kill.server.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:24
 * @version: V1.0
 */
@RestController
@RequestMapping
public class ActivityController  {

    @Autowired
    ActivityService activityService;

    @RequestMapping("/admin")
    public String get(){
        return "success";
    }

    @PostMapping("addActivity")
    public BaseResponse addactive(Activity activity) {
        if (activity.getEndTime().before(activity.getCreateTime())){
            return new BaseResponse(StatusCode.Fail.getCode(),"活动结束时间必须晚于开始时间");
        }else if (false == DateKit.timeCompany(activity,activityService.showAll())) {
            return new BaseResponse(StatusCode.Fail.getCode(),"该时间已有活动，请重新选择时间");
        }
        activity.setCreateTime(new Date());
        activityService.insertActivity(activity);
        return new BaseResponse(StatusCode.Success.getCode(),"创建活动成功");
    }
    @PostMapping("activityHaving")
    public BaseResponse activityHavingController(HttpServletRequest request) {
        if (activityService.showNextService() == null && !DateUtil.activityCompany(new Date(),activityService.showAll())) {
            return new BaseResponse(StatusCode.Success.getCode(),"目前无活动，敬请期待");
        }
        if (activityService.showNextService() != null && !DateUtil.activityCompany(new Date(),activityService.showAll())) {
            return new BaseResponse(StatusCode.Success.getCode(),
                    "下一场活动开始城市为" + activityService.showNextService().getProvince() + "，" +
                            activityService.showNextService().getStartTime() + "时，敬请期待");
        }
        if (activityService.showNextService() != null &&
                DateUtil.compare(DateUtil.dateToString(new Date()),DateUtil.dateToString(activityService.showNextService().getStartTime()))) {
            Map<String ,String> maps= (Map<String, String>) request.getAttribute("request_parameters");
            return new BaseResponse(StatusCode.Success.getCode(),
                        "下一场活动的开放城市为" + activityService.showNextService().getProvince());
        }
        return new BaseResponse(StatusCode.Fail.getCode(),
                "系统出差了,请等候专业人员维护");
    }

    @PostMapping("enterActivity")
    public BaseResponse enterActivityController(Activity activity) {
        if (DateUtil.isEffectiveDate(new Date(),activity)){
            return new BaseResponse(StatusCode.Success.getCode(),"您已进入活动页面");
        }
        return new BaseResponse(StatusCode.Success.getCode(),"未到活动时间");
    }

}
