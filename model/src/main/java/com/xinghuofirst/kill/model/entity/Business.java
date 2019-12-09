/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:21
 * @version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Business implements Serializable {
    /** 用户id **/
    private Integer businessId;
    /** 商户编号 **/
    private String businessNumber;
    /** 手机号 **/
    private String phone;
    /** 商户姓名 **/
    private String name;
    /** 性别0男1女 **/
    private String sex;
    /** 省份 **/
    private String province;
    /** 装机状态
0默认  1轮空2被秒杀装机
3被秒杀，未被装机 **/
    private Integer installStatus;
    /** 0沉默用户  1白名单 **/
    private Integer silentStatus;
    /** 身份证号 **/
    private String idcard;
}