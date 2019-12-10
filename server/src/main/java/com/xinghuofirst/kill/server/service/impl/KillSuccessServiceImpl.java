/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.KillSuccess;
import com.xinghuofirst.kill.model.mapper.KillSuccessRepository;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@Service
public class KillSuccessServiceImpl implements KillSuccessService {
    @Autowired
    @Resource
    KillSuccessRepository killSuccessRepository;

    /** 查询用户资源**/
    @Override
    public Integer selectBusiness() {
        killSuccessRepository.selectBusiness();
        return null;
    }

    /** 通过用户id查询秒杀成功分配表**/
    @Override
    public KillSuccess kiiSuccesById(Integer personId) {
        killSuccessRepository.kiiSuccesById(personId);
        return null;
    }

    @Override
    public Integer updateSurpus(Integer activityId) {
        killSuccessRepository.updateSurpus(activityId);
        return null;
    }
}
