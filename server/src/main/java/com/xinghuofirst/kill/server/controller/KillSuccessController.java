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
import com.xinghuofirst.kill.model.entity.KillSuccess;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private KillSuccessService killSuccessService;
    @PostMapping("/getKillSuccess")
    public BaseResponse getKillSuccessMethod(Integer personId) {
        BaseResponse baseResponses = null;
        List<KillSuccess> killSuccesses = killSuccessService.selectKillSuccessByPersonIdService(personId);
        if (killSuccesses.size() == 0 ) {
            baseResponses =  new BaseResponse(StatusCode.Fail.getCode(),"暂无秒杀到的用户信息",null);
            return baseResponses;
        }
        baseResponses = new BaseResponse(StatusCode.Success.getCode(),"该地区存在沉默用户",killSuccesses);
        return baseResponses;
    }
}
