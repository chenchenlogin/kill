/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:23
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.service;


import com.xinghuofirst.kill.model.entity.Province;

import java.util.List;

/**
 * @description:
 * @author: jiansghuang
 * @date: 2019/12/08 17:23
 * @version: V1.0
 */
public interface ProvinceService {
    /**
    *@Author:jiangshuang
    *@Param: 顯示所有的省份信息
    */
    List<Province> showAllProcince();
}
