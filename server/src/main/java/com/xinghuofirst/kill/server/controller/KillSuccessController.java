/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.model.entity.KillSuccess;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@RestController
@RequestMapping("/")
public class KillSuccessController {
    @Autowired
    KillSuccessService killSuccessService;

    /**
     *
     * duanlian
     * 马上抢相关判断
     *
     */

    @RequestMapping("/isKill")
    private BaseResponse isKill(HttpServletRequest request,Integer activityId){
        /** 查询用户资源**/
       Integer source =  killSuccessService.selectBusiness();
        Map<String, String> maps = (Map<String, String>) request.getAttribute("request_parameters");
        Integer personId = Integer.parseInt(maps.get("userId")) ;
        /** 判断是否有参加本次活动的资格**/
       KillSuccess killSuccesses =  killSuccessService.kiiSuccesById(personId);
       while(source != 0){
           if(killSuccesses.getActivityId()==null){
               /** 查询用户的剩余资源 **/
               killSuccessService.updateSurpus(activityId);
               /** 更新做标记，轮空**/

               /** 秒杀相关在此处写代码**/
               return new BaseResponse(200,"成功参加活动");

           }else{
               return new BaseResponse(200,"已参加，等待结果公布");
           }
       }
        return new BaseResponse(200,"用户资源不充足");
    }

}
