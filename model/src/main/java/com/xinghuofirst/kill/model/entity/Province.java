/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: yuyue<yuyue@suixingpay.com>
 * @date: 2019/12/08 17:23
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
 * @author: yuyue<yuyue@suixingpay.com>
 * @date: 2019/12/08 17:23
 * @version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Province implements Serializable {
    /** 省份id **/
    private Integer id;
    /** 省份编码 **/
    private String provinceNumber;
    /** 省份 **/
    private String provinceName;
}
