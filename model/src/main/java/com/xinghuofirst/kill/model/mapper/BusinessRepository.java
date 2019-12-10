/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.mapper;

import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.model.entity.KillSuccess;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @version: V1.0
 */
@Mapper
@Repository
public interface BusinessRepository extends BaseRepository<Business, Integer> {

    /**
     *@Author:Yuyue
     *@Description:添加白名单
     *@Date:12:04  2019/12/9
     *@Param:
     *@Return:
     */
    int insertWhiteBusiness(Business business);
    /**
     *@Author:Yuyue
     *@Description:按照省份查询用户资源
     *@Date:12:41  2019/12/9
     *@Param:
     *@Return:
     */
    Integer selectBusinessByProvince(String province);


}

