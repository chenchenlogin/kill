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
import com.xinghuofirst.kill.model.mapper.ActivityRepository;
import com.xinghuofirst.kill.model.mapper.KillSuccessRepository;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@Service
public class KillSuccessServiceImpl implements KillSuccessService {

    @Autowired private KillSuccessRepository killSuccessRepository;
    @Autowired private ActivityRepository activityRepository;

    @Override @Transactional(readOnly = true)
    public List<KillSuccess> selectKillSuccessByPersonIdService(Integer personId) {
        return killSuccessRepository.selectKillSuccessByPersonId(personId);

    }
        /** 查询用户资源* duanlian*/
        @Override public int selectActivitySurplus (Integer activityId){

            return activityRepository.selectActivitySurplus(activityId);
        }

        /** 判断是否有参加本次活动的资格 duanlian**/
        @Override public int countByActivityPersonId (Integer personId, Integer activityId){

            return killSuccessRepository.countByActivityPersonId(personId, activityId);
        }

        /** 查询用户剩余资源 duanlian**/
        @Override public int updateSurpus (Integer activityId){

            return killSuccessRepository.updateSurpus(activityId);
        }
    }


