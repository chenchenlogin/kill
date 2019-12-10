/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhangleying<zhang_yy2@suixingpay.com>
 * @date: 2019/12/08 17:25
 * @Copyright: 2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.mapper.BusinessRepository;
import com.xinghuofirst.kill.model.mapper.KillSuccessRepository;
import com.xinghuofirst.kill.server.dto.PersonAndActivity;
import com.xinghuofirst.kill.server.service.KillService;
import com.xinghuofirst.kill.server.service.KillSuccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @description: 给鑫管家分配用户
 * @author: dupeng
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@Service
public class KillSuccessServiceImpl implements KillSuccessService{

    private static final Logger log= LoggerFactory.getLogger(KillService.class);

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private KillSuccessRepository killSuccessRepository;

    /**
     * @description: 鑫管家分配用户
     * @author: dupeng
     * @param:
     * @return:
     * @date: 2019-12-10 11:35
     */
    @Override
    public void assignPerson(PersonAndActivity info) throws Exception {
        try {
            //TODO:获取当前活动中任意一个用户
           /* Integer businessId = businessRepository.selectPerson(info.getProvince());
            log.info("剩余商户id"+businessId);
            if(businessId==null){
                throw new Exception("分配任务出错，没有剩余商户");
                log.info(info.getUserId()+"----"+info.getUserName()+"分配任务出错，没有剩余商户");
            }
            //TODO:给鑫管家添加商户
            killSuccessRepository.updateBusiness(info.getOrderNo(),info.getUserId());*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
