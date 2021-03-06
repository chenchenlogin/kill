/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:23
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.Province;
import com.xinghuofirst.kill.model.mapper.ProvinceRepository;
import com.xinghuofirst.kill.server.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:23
 * @version: V1.0
 */
@Service
public class ProvinceServiceImpl  implements ProvinceService {
    @Autowired
    ProvinceRepository provinceRepository;
    @Override
    public List<Province> showAllProcince() {
        return provinceRepository.findAllWithResult(null);
    }

    @Override
    public Province showProvinceById(Integer provinceId) {
        return provinceRepository.selectById(provinceId);
    }
}
