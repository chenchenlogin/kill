/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: yuyue<yuyue@suixingpay.com>
 * @date: 2019/12/09 09:46
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
 * @date: 2019/12/09 09:46
 * @version: V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person implements Serializable {
    /** 用户id **/
    private Integer userId;
    /** 用户名 **/
    private String userName;
    /** 密码 **/
    private String password;
    /** 角色
0管理员
1鑫管家 **/
    private Integer role;
    /** 省份 **/
    private String province;
    /** 邮箱 **/
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    @Override public String toString() {
        return "Person{" + "userId=" + userId + ", userName='" + userName + '\'' + ", password='" + password + '\''
                + ", role=" + role + ", province='" + province + '\'' + ", email='" + email + '\'' + '}';
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
