/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/09 09:46
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.model.mapper;
import com.xinghuofirst.kill.model.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/09 09:46
 * @version: V1.0
 */
@Mapper
@Repository
public interface PersonRepository extends BaseRepository<Person, Integer> {

    /*zhou_gc  根据用户信息查询用户是否存在*/
     public Person findOne(Person person);
}

