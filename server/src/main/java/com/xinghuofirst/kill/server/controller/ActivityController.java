package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;
import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.ActivityService;
import com.xinghuofirst.kill.server.service.BusinessService;
import com.xinghuofirst.kill.server.utils.DateKit;
import com.xinghuofirst.kill.server.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:24
 * @version: V1.0
 */
@RestController
@Slf4j
public class ActivityController  {

    @Autowired
    ActivityService activityService;

    @Autowired
    private BusinessService businessService;

    @RequestMapping("/admin")
    public String get(){
        return "success";
    }

    @RequestMapping("addActivity")
    public BaseResponse addactive(@RequestBody Activity activity) {
        BaseResponse baseResponses = null;
        if (activity.getEndTime() == null) {
            activity.setEndTime(DateUtil.addThreeMin(activity.getStartTime()));
        }
        if (activity == null || activity.getStartTime() == null ||activity.getProvince() == null ||activity.getEndTime() ==null ||activity.getQuentity() == null) {
            baseResponses = new BaseResponse(StatusCode.Fail.getCode(),"请输入准确的活动信息");
            return baseResponses;
        }
        if (activity.getEndTime().before(activity.getStartTime())){
            baseResponses = new BaseResponse(StatusCode.Fail.getCode(),"活动结束时间必须晚于开始时间");
            return  baseResponses;
        } else if (false == DateKit.timeCompany(activity,activityService.showAll())) {
            baseResponses = new BaseResponse(StatusCode.Fail.getCode(),"该时间已有活动，请重新选择时间");
            return baseResponses;
        } else if (activity.getQuentity() > businessService.selectBusinessByProvinceService(activity.getProvince())) {
            baseResponses = new BaseResponse(StatusCode.Fail.getCode(),"库存用户信息不足请重新输入");
            return baseResponses;
        }
        activity.setCreateTime(new Date());
        activity.setSurplus(activity.getQuentity());
        activityService.insertActivity(activity);
        baseResponses = new BaseResponse(StatusCode.Success.getCode(),"创建活动成功");
        return baseResponses;
    }
    @RequestMapping("activityHaving")
    public BaseResponse activityHavingController(HttpServletRequest request) {
        BaseResponse  baseResponses = null;
        if (activityService.showNextService() == null && !DateUtil.activityCompany(new Date(),activityService.showAll())) {
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),"目前无活动，敬请期待");
            return baseResponses;
        }
        if (activityService.showNextService() != null && !DateUtil.activityCompany(new Date(),activityService.showAll())) {
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),
                    "下一场活动开始城市为" + activityService.showNextService().getProvince() + "，" +
                            activityService.showNextService().getStartTime() + "时，敬请期待");
            return baseResponses;
        }
        if (activityService.showNextService() != null &&
                DateUtil.compare(DateUtil.dateToString(new Date()),DateUtil.dateToString(activityService.showNextService().getStartTime()))) {
            Map<String ,String> maps= (Map<String, String>) request.getAttribute("request_parameters");
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),
                        "下一场活动的开放城市为" + activityService.showNextService().getProvince());
            return baseResponses;
        }
        baseResponses = new BaseResponse(StatusCode.Fail.getCode(),
                "系统出差了,请等候专业人员维护");
        return baseResponses;
    }

    @RequestMapping("enterActivity")
    public BaseResponse enterActivityController() {
        BaseResponse  baseResponses = null;
        if (activityService.showNextService() == null &&
                DateUtil.isEffectiveDate(new Date(),activityService.showBeforeLastService())) {
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),"成功进入活动",activityService.showBeforeLastService());
            return baseResponses;
        }
        if (activityService.showNextService() == null && activityService.showBeforeLastService() == null) {
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),"暂无活动信息");
            return baseResponses;
        }
        if (activityService.showNextService() == null && activityService.showBeforeLastService() != null) {
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),"抱歉，本场活动已结束",activityService.showBeforeLastService());
            return baseResponses;
        }
        if (activityService.showNextService() != null) {
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),"“活动还未到开启时间",activityService.showNextService());
            return baseResponses;
        }
        baseResponses = new BaseResponse(StatusCode.Fail.getCode(),"系统出差了,请等候专业人员维护");
        return baseResponses;
    }

}
