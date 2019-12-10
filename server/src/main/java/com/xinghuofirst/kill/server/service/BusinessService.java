package com.xinghuofirst.kill.server.service;


/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:21
 * @version: V1.0
 */
public interface BusinessService  {
    /*
     *@Description:根据省份查询人数
     */
    Integer selectBusinessByProvinceService(String province);
}
