/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.mapper;

import com.xinghuofirst.kill.model.entity.KillSuccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@Mapper
@Repository
public interface KillSuccessRepository extends BaseRepository<KillSuccess, Integer> {
    /**
    *@Author:Yuyue
    *@Description:剩余库存-1
    *@Date:12:02  2019/12/9
    *@Param:
    *@Return:
    */
    int updateSurpus(@Param("killId") Integer activityId);

    /**
    *@Author:Yuyue
    *@Description:鑫管家查询秒杀成功用户列表
    *@Date:12:44  2019/12/9
    *@Param: 
    *@Return: 
    */
    List<KillSuccess> selectKillSuccessByPersonId(Integer personId);
}