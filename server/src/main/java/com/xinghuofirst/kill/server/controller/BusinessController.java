
package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.enums.StatusCode;

import com.xinghuofirst.kill.model.entity.Province;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: 姜爽
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


    @PostMapping("/getCount")
    public BaseResponse getLocalCount(@RequestBody Province province, HttpServletRequest request) {
        BaseResponse  baseResponses = null;
        if (province.getProvinceName() == null ||province.getProvinceName().equals("")){
            baseResponses = new BaseResponse(StatusCode.Fail.getCode(),"省份不能为空");
            return baseResponses;
        }
        int countnum = businessService.selectBusinessByProvinceService(province.getProvinceName());
        if (countnum <= 0) {
            baseResponses = new BaseResponse(StatusCode.Fail.getCode(),"该地区无沉默用户信息",0);
            return baseResponses;
        }
        baseResponses =  new BaseResponse(StatusCode.Success.getCode(),"该地区存在沉默用户",countnum);
        return baseResponses;
    }

}
