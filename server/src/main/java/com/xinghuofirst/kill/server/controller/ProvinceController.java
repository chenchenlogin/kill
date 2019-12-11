/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: js
 * @date: 2019/12/08 17:23
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.controller;


import com.xinghuofirst.kill.model.entity.Province;
import com.xinghuofirst.kill.model.mapper.ActivityRepository;
import com.xinghuofirst.kill.server.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: jiangshuang
 * @date: 2019/12/08 17:23
 * @version: V1.0
 */
@RestController
@RequestMapping("/")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;
    @RequestMapping("showAllprovince")
    public List<Province> showAllPro () {
        return provinceService.showAllProcince();
    }
}
