/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:24
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;
import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    private ActivityService activityService;
    @RequestMapping("/admin")
    public String get(){
        return "success";
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


}
