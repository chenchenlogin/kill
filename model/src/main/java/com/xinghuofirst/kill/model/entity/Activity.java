/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:24
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @date: 2019/12/08 17:24
 * @version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Activity implements Serializable {
    /** 活动id **/
    private Integer activityId;
    /** 开始时间 **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /** 结束时间 **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /** 省份 **/
    private String province;
    /** 活动描述 **/
    private String description;
    /** 配置用户数量 **/
    private Integer quentity;
    /** 剩余库存，初始值等于配置用户数量 **/
    private Integer surplus;
    /** 创建时间 **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
