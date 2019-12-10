/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.model.mapper.BusinessRepository;
import com.xinghuofirst.kill.response.BaseResponse;
import com.xinghuofirst.kill.server.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:21
 * @version: V1.0
 */
@Service

public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public Integer selectBusinessByProvinceService(String province) {
        return businessRepository.selectBusinessByProvince(province);
    }
    /**
     *
     * @param business
     * @return解析excel，向数据库中添加
     * duanlian
     */
    @Override
    public int insertWhiteBusiness(Business business) {
        return businessRepository.insertWhiteBusiness(business);
    }


}
