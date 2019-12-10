/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @version: V1.0
 */
@RestController
@RequestMapping("/")
public class BusinessController  {
    @Autowired
    private BusinessService businessService;

    @PostMapping("/getCount")
    public BaseResponse getLocalCount(String province) {
        int countnum = businessService.selectBusinessByProvinceService(province);
        if (countnum <= 0) {
            return  new BaseResponse(StatusCode.Fail.getCode(),"该地区无沉默用户信息",0);
        }
        return new BaseResponse(StatusCode.Success.getCode(),"该地区存在沉默用户",countnum);
    }

}
