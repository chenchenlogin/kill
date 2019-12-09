/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:24
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.mapper;

import com.xinghuofirst.kill.model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:24
 * @version: V1.0
 */
@Mapper
@Repository
public interface ActivityRepository extends BaseRepository<Activity, Integer> {
    /**
     *@Author:Yuyue
     *@Description:查询之后的活动
     *@Date:11:19  2019/12/9
     *@Param:
     *@Return:
     */
    public List<Activity> selectAfterActivity();
}
