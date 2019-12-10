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
import java.util.Date;
import java.util.Map;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
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
        if (activity == null || activity.getStartTime() == null ||activity.getProvince() == null ||activity.getEndTime() ==null ||activity.getQuentity() == null) {
            return new BaseResponse(StatusCode.Fail.getCode(),"请输入准确的活动信息");
        }
        if (activity.getEndTime().before(activity.getStartTime())){
            return new BaseResponse(StatusCode.Fail.getCode(),"活动结束时间必须晚于开始时间");
        } else if (false == DateKit.timeCompany(activity,activityService.showAll())) {
            return new BaseResponse(StatusCode.Fail.getCode(),"该时间已有活动，请重新选择时间");
        } else if (activity.getQuentity() > businessService.selectBusinessByProvinceService(activity.getProvince())) {
            return new BaseResponse(StatusCode.Fail.getCode(),"库存用户信息不足请重新输入");
        }
        activity.setCreateTime(new Date());
        activity.setSurplus(activity.getQuentity());
        activityService.insertActivity(activity);
        return new BaseResponse(StatusCode.Success.getCode(),"创建活动成功");
    }
    @RequestMapping("activityHaving")
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

    @RequestMapping("enterActivity")
    public BaseResponse enterActivityController() {
        if (activityService.showNextService() == null &&
                DateUtil.isEffectiveDate(new Date(),activityService.showBeforeLastService())) {
            return new BaseResponse(StatusCode.Success.getCode(),"成功进入活动",activityService.showBeforeLastService());
        }
        if (activityService.showNextService() == null && activityService.showBeforeLastService() == null) {
            return new BaseResponse(StatusCode.Success.getCode(),"暂无活动信息");
        }
        if (activityService.showNextService() == null && activityService.showBeforeLastService() != null) {
            return new BaseResponse(StatusCode.Success.getCode(),"抱歉，本场活动已结束",activityService.showBeforeLastService());
        }
        if (activityService.showNextService() != null) {
            return new BaseResponse(StatusCode.Success.getCode(),"“活动还未到开启时间",activityService.showNextService());
        }
        return new BaseResponse(StatusCode.Fail.getCode(),"系统出差了,请等候专业人员维护");
    }

}
