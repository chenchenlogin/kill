/**
 *@Author:Yuyue
 *@Description:活动Repository
 *@Date:14:16  2019/12/9
 *@Param:
 *@Return:
 */
package com.xinghuofirst.kill.model.mapper;

import com.xinghuofirst.kill.model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     *@Author:Yuyue
     *@Description:查询上一场活动 @
     *@Date:11:47  2019/12/9
     *@Param:
     *@Return:
     */
    Activity selectLastActivity();


}
