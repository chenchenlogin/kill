/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: yuyue<yuyue@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.mapper;

import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.model.entity.KillSuccess;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: yuyue<yuyue@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @version: V1.0
 */
@Mapper
@Repository
public interface BusinessRepository extends BaseRepository<Business, Integer> {




    /**
     *@Author:Yuyue
     *@Description:按照省份查询用户资源
     *@Date:12:41  2019/12/9
     *@Param:
     *@Return:
     */
    Integer selectBusinessByProvince(String province);

    /**
    *@Author:Yuyue
    *@Description:查出来一个可被分配的用户
    *@Date:13:44  2019/12/10
    *@Param:
    *@Return:
    */
    Integer selectPerson(String province);

    /**
    *@Author:Yuyue
    *@Description:秒杀后，更新分配商户结果
    *@Date:14:08  2019/12/10
    *@Param:
    *@Return:
    */
    Integer updateKillSuccess(String killNumber,Integer businessId);

   /**
   *@Author:Yuyue
   *@Description:被秒杀后修改商户安装状态2
   *@Date:21:29  2019/12/10
   *@Param:
   *@Return:
   */
    Integer updateBusinessInstallStatus(Integer businessId);
}

