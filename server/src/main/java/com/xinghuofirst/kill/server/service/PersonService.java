/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/09 09:46
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.service;



import com.xinghuofirst.kill.model.entity.Person;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/09 09:46
 * @version: V1.0
 */
public interface PersonService {

    /*zhou_gc  用户登陆*/
    public  Person userLogin(Person person);
    /*zhou_gc 用户注销*/
    public void userLoginOut(Person person);

}
