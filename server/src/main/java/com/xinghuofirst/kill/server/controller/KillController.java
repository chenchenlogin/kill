/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.dto.ActivityDto;
import com.xinghuofirst.kill.server.service.KillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @description: 秒杀controller
 * @author: dupeng
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@RestController
@RequestMapping("/kill")
public class KillController {
    private static final Logger log = LoggerFactory.getLogger(KillController.class);

    @Autowired
    private KillService killService;

   /**
    * @description: 用户秒杀核心业务逻辑
    * @author: dupeng
    * @param:
    * @return:
    * @date: 2019-12-09 14:52
    */
    @PostMapping("/seckill")
    public BaseResponse execute(@RequestBody @Validated ActivityDto activityDto, BindingResult result, HttpSession session){
        if (result.hasErrors() || activityDto.getActivityId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        //Object uId=session.getAttribute("uid");
       /* if (uId==null){
            return new BaseResponse(StatusCode.UserNotLogin);
        }*/
        Integer userId=activityDto.getPersonId();
        //Integer userId= (Integer)uId ;

        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //基于Redis的分布式锁进行控制
            Boolean res=killService.killItem(activityDto.getActivityId(),userId);
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于Redis的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /**
     * @description: 用户秒杀核心业务逻辑（用于压测）
     * @author: dupeng
     * @param:
     * @return:
     * @date: 2019-12-09 14:52
     */
    @PostMapping ("/executeTest")
    public BaseResponse executeLock(@RequestBody @Validated ActivityDto activityDto, BindingResult result){
        if (result.hasErrors() || activityDto.getActivityId()<=0){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            //基于Redis的分布式锁进行控制
            Boolean res=killService.killItem(activityDto.getActivityId(),activityDto.getPersonId());
            log.info("");
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于Redis的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }
        }catch (Exception e){
            response=new BaseResponse(StatusCode.Fail.getCode(),e.getMessage());
        }
        return response;
    }


    /*@Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;*/

    /**
     * 查看订单详情
     * @return
     */
    /*@RequestMapping(value = prefix+"/record/detail/{orderNo}",method = RequestMethod.GET)
    public String killRecordDetail(@PathVariable String orderNo, ModelMap modelMap){
        if (StringUtils.isBlank(orderNo)){
            return "error";
        }
        KillSuccessUserInfo info=itemKillSuccessMapper.selectByCode(orderNo);
        if (info==null){
            return "error";
        }
        modelMap.put("info",info);
        return "killRecord";
    }*/

}
