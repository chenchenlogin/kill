/**
 * @author:姜爽
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: duanlian js
 * @date: 2019/12/08 17:25
 */
package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;
import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.model.entity.KillSuccess;
import com.xinghuofirst.kill.model.entity.Person;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.dto.ActivityDto;
import com.xinghuofirst.kill.server.service.KillService;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import com.xinghuofirst.kill.server.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: duanlian js
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@RestController
@RequestMapping("/kill")
public class KillSuccessController {
    private static final Logger log = LoggerFactory.getLogger(KillSuccessController.class);

    @Autowired
    KillSuccessService killSuccessService;

    @Autowired
    private KillService killService;

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

    @PostMapping("/seckill")
    public BaseResponse iskill(HttpServletRequest request, ActivityDto activityDto, BindingResult result) throws Exception {
        //String token = request.getHeader("token");
       // Map<String, String> map = TokenUtil.verifyToken(token);
        //Integer personId = Integer.parseInt(map.get("userId")) ;

        if (result.hasErrors() || activityDto.getActivityId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }

        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            int killSuccesses =  killSuccessService.countByActivityPersonId(activityDto.getPersonId(),activityDto.getActivityId());
            //TODO 判断鑫管家是否已经参加过秒杀
            log.info("健康快乐进口量就骷髅精灵"+killSuccesses);
            if(killSuccesses <1){
                //TODO 判断秒杀商户剩余数量
                int killNumber = killSuccessService.selectActivitySurplus(activityDto.getActivityId());
                if(killNumber >0){
                    Integer userId=activityDto.getPersonId();
                    //TODO:基于Redis的分布式锁进行控制
                    Boolean res=killService.killItem(activityDto.getActivityId(),userId);
                    if (!res){
                        return new BaseResponse(StatusCode.Fail.getCode(),"商品已抢购完毕或者不在抢购时间段哦!");
                    }
                }else{
                    return new BaseResponse(602,"商户已抢完，欢迎下次秒杀");
                }
         }else{
                response=new BaseResponse(603,"您已经参与过此活动，欢迎下次再来");
            }
        }catch (Exception e){
            e.printStackTrace();
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;


    }

}
