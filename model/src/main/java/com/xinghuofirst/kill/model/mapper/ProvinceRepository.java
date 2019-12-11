/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: yuyue<yuyue@suixingpay.com>
 * @date: 2019/12/08 17:23
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.mapper;

import com.xinghuofirst.kill.model.entity.Province;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: yuyue<yuyue@suixingpay.com>
 * @date: 2019/12/08 17:23
 * @version: V1.0
 */
@Mapper
@Repository
public interface ProvinceRepository extends BaseRepository<Province, Integer> {
}
