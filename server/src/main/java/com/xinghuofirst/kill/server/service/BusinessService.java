/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.service;


import com.xinghuofirst.kill.model.entity.Business;
import com.xinghuofirst.kill.response.BaseResponse;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @version: V1.0
 */
@Service
public interface BusinessService  {
    int insertWhiteBusiness(Business business);
}
