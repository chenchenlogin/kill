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
public class KillSuccess  implements Serializable {
    /** 秒杀id **/
    private Integer killId;
    /** 秒杀编码 **/
    private String killNumber;
    /** 商户 **/
    private Business business;
    /** 活动 **/
    private Activity activity;
    /** 人员 **/
    private Person person;
    /** 商户联系方式 **/
    private String busPhone;
    /** 秒杀时间 **/
    private Date killTime;

    public Integer getKillId() {
        return killId;
    }

    public void setKillId(Integer killId) {
        this.killId = killId;
    }

    public String getKillNumber() {
        return killNumber;
    }

    public void setKillNumber(String killNumber) {
        this.killNumber = killNumber;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getBusPhone() {
        return busPhone;
    }

    public void setBusPhone(String busPhone) {
        this.busPhone = busPhone;
    }

    public Date getKillTime() {
        return killTime;
    }

    public void setKillTime(Date killTime) {
        this.killTime = killTime;
    }
}
