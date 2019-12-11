/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: yuyue<yuyue@suixingpay.com>
 * @date: 2019/12/08 17:24
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.mapper;

import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.model.entity.KillSuccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author:于悦<yuyue@suixingpay.com>
 * @date: 2019/12/08 17:24
 * @version: V1.0
 */
@Mapper
@Repository
public interface ActivityRepository extends BaseRepository<Activity, Integer> {
    /**
     *@Author:Yuyue
     *@Description:查询当前时间之后的活动,不包括正在进行的活动 @
     *@Date:11:19  2019/12/9
     *@Param:
     *@Return:
     */
    List<Activity> selectAfterActivity();

    /**
     *@Author:Yuyue
     *@Description:查询下一场活动 @
     *@Date:11:47  2019/12/9
     *@Param:
     *@Return:
     */
    Activity selectNextActivity();
    /**
     *@Author:Yuyue
     *@Description:查询上一场活动 @
     *@Date:11:47  2019/12/9
     *@Param:
     *@Return:
     */
    Activity selectLastActivity();

    /**
     *@Author:Yuyue
     *@Description:查询正在进行的活动 @
     *@Date:11:55  2019/12/9
     *@Param:
     *@Return:
     */
    Activity selectNowActivity();

    /**
     *@Author:Yuyue
     *@Description:查询活动库存 @
     *@Date:12:50  2019/12/9
     *@Param:
     *@Return:
     */
    int selectActivitySurplus(Integer activityId);



    /**@Author:Yuyue
    *@Description:查询该鑫管家在活动中抢购的数量
    *@Date:12:58  2019/12/9
    *@Param:
    *@Return:
    */
    int countByActivityPersonId(@Param("activityId") Integer activityId, @Param("personId") Integer personId);







}
