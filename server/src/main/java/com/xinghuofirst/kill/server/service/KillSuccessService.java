/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.service;



import com.xinghuofirst.kill.model.entity.KillSuccess;

import java.util.List;

/**
 * @description:
 * @author: 姜爽
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
public interface KillSuccessService  {

    /**
     *@Description:鑫管家查询秒杀成功用户列表
     *@Return: 秒杀成功的信息
     */
    List<KillSuccess> selectKillSuccessByPersonIdService(Integer personId);



    /** 查询用户资源* duanlian*/
    int selectActivitySurplus(Integer activityId);
    /** 判断是否有参加本次活动的资格 duanlian**/
    int countByActivityPersonId(Integer personId,Integer activityId);
    /** 查询用户剩余资源 duanlian**/
    int updateSurpus  (Integer activityId);

}
