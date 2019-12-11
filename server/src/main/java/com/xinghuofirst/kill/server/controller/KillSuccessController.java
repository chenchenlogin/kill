/**
 * @author:姜爽
 * @date: 2019/12/08 17:25
 */
package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;
import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.model.entity.KillSuccess;
import com.xinghuofirst.kill.model.entity.Person;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import com.xinghuofirst.kill.server.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@RestController
@RequestMapping("/")
public class KillSuccessController {
    @Autowired
    KillSuccessService killSuccessService;

    @PostMapping("/getKillSuccess")
    public BaseResponse getKillSuccessMethod(@RequestBody Person person, HttpServletRequest request) {
        BaseResponse baseResponses = null;
        if (person.getUserId() == null ||person.getUserId().equals("")) {
            baseResponses =  new BaseResponse(StatusCode.Fail.getCode(),"登录失效了，请重新登录");
            return baseResponses;
        }
        List<KillSuccess> killSuccesses = killSuccessService.selectKillSuccessByPersonIdService(person.getUserId());
        if (killSuccesses.size() == 0 ) {
            baseResponses =  new BaseResponse(StatusCode.Fail.getCode(),"暂无秒杀到的用户信息",null);
            return baseResponses;
        }
        baseResponses = new BaseResponse(StatusCode.Success.getCode(),"曾秒杀到的沉默用户",killSuccesses);
        return baseResponses;
    }
    /**
     *
     * duanlian
     * 马上抢相关判断
     *
     */

    @RequestMapping("/isKill")
    private BaseResponse isKill(HttpServletRequest request, Activity activity) throws Exception {
        String token = request.getHeader("token");
        Map<String, String> map = TokenUtil.verifyToken(token);
        Integer personId = Integer.parseInt(map.get("userId")) ;
        int killSuccesses =  killSuccessService.countByActivityPersonId(personId,activity.getActivityId());
        /** 判断是否有参加本次活动的资格**/
        if(killSuccesses >0){
            return new BaseResponse(200,"已参加，等待结果公布");
        }
        /** 判断用户的剩余资源，即库存 **/
        int stock =  killSuccessService.updateSurpus(activity.getActivityId());
        if(stock <=0){
            return new BaseResponse(602,"秒杀失败");
        }
        /** 秒杀代码写在**/
        /** 减库存**/

        return new BaseResponse(200, "成功参加活动");

       /* String token = request.getHeader("token");
        Map<String, String> map = TokenUtil.verifyToken(token);
        *//** 查询用户资源**//*
        Integer source =  killSuccessService.selectActivitySurplus(activity.getActivityId());
        Integer personId = Integer.parseInt(map.get("userId")) ;
        *//** 判断是否有参加本次活动的资格**//*
       int killSuccesses =  killSuccessService.countByActivityPersonId(personId,activity.getActivityId());
       while(source != 0){
           if(killSuccesses  < 1){
               *//** 查询用户的剩余资源 **//*
              int stock =  killSuccessService.updateSurpus(activity.getActivityId());
               *//** 更新做标记，轮空**//*
               if(stock <=0){
                    return new BaseResponse(602,"秒杀失败");
               }else {
                    *//** 秒杀代码写在这里**//*

                   return new BaseResponse(200, "成功参加活动");
               }
           }else{
               return new BaseResponse(200,"已参加，等待结果公布");
           }
       }
        return new BaseResponse(200,"用户资源不充足");*/
    }

}
