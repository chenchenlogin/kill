/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
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
import java.util.Date;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class KillSuccess implements Serializable {
    /** 秒杀id **/
    private Integer killId;
    /** 秒杀编码 **/
    private String killNumber;
    /** 人员id **/
    private Integer personId;
    /** 商户id **/
    private Integer businessId;
    /** 活动id **/
    private Integer activityId;
    /** 商户联系方式 **/
    private String busPhone;
    /** 秒杀时间 **/
    private Date killTime;
}
