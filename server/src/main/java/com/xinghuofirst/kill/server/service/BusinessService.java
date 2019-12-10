package com.xinghuofirst.kill.server.service;



import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.response.BaseResponse;
import org.springframework.stereotype.Service;


/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:21
 * @version: V1.0
 */
@Service
public interface BusinessService  {

    /*
     *@Description:根据省份查询人数
     */
    Integer selectBusinessByProvinceService(String province);

    int insertWhiteBusiness(Business business);

}
