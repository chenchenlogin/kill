
package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;
import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/insertWhiteBusiness")
    public BaseResponse insertWhiteBusiness(Business business){
        int result = businessService.insertWhiteBusiness(business);
        if(result >0) {
            return new BaseResponse(200, "白名单添加成功！");
        }else{
            return  new BaseResponse(601,"未添加成功");
        }
    }


}
