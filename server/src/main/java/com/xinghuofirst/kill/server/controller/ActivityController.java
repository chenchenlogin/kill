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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    private BusinessService businessService;


    @Autowired
    private ActivityService activityService;

    @RequestMapping("/admin")
    public String get(){
        return "success";
    }


    @RequestMapping("addActivity")
    public BaseResponse addactive(@RequestBody Activity activity) {
        BaseResponse baseResponses = null;
        Boolean flag = true;
        String flaseMess = "";
        if (activity.getProvince() == null || activity.getProvince().equals("")) {
            flag = false;
            flaseMess += "省份，";
        }
        if (activity.getQuentity() == null || activity.getQuentity().equals("")) {
            flag = false;
            flaseMess += "用户数量，";
        }
        if (activity.getDescription() == null || activity.getDescription().equals("")) {
            flag = false;
            flaseMess += "活动描述，";
        }
        if (activity.getStartTime() == null ||activity.getStartTime().equals("")){
            flag = false;
            flaseMess += "活动开始时间，";
        }
        if (flag == false) {
            baseResponses = new BaseResponse(StatusCode.Fail.getCode(),flaseMess + "不能为空");
            return  baseResponses;
        }
        if (activity.getEndTime() == null || activity.getEndTime().equals("")) {
            activity.setEndTime(DateUtil.addThreeMin(activity.getStartTime()));
        }

    /**
     * duanlian
     * 判断归属地是否相同
     *
     * **/
    @RequestMapping("/isProvince" )
    private BaseResponse isProvince(HttpServletRequest request) {
        Activity activity = activityService.selectNowActivity();
        String activityProvince = activity.getProvince();
        Map<String, String> maps = (Map<String, String>) request.getAttribute("request_parameters");
        String personProvince = maps.get("province");
        if (!personProvince.equals(activityProvince)) {
            return new BaseResponse(600, "您的归属地，不在本次活动范围内，请期待后续活动");
        }else{
            return new BaseResponse(200,"进入成功");
        }
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
        if (activityService.showNextService() == null && activityService.showNowActivityService() == null
            /*!DateUtil.activityCompany(new Date(),activityService.showAll())*/) {
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),"目前无活动，敬请期待");
            return baseResponses;
        }
        if (activityService.showNextService() != null &&
                DateUtil.compare(DateUtil.dateToString(new Date()),DateUtil.dateToString(activityService.showNextService().getStartTime()))) {
            Map<String ,String> maps= (Map<String, String>) request.getAttribute("request_parameters");
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),
                    "下一场活动的开放城市为" + activityService.showNextService().getProvince(),activityService.showNextService());
            return baseResponses;
        }
        if (activityService.showNextService() != null && activityService.showNowActivityService() == null) {
            baseResponses = new BaseResponse(StatusCode.Success.getCode(),
                    "下一场活动开始城市为" + activityService.showNextService().getProvince() + "，" +
                            activityService.showNextService().getStartTime() + "时，敬请期待");
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
