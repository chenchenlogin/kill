package com.xinghuofirst.kill.server.service.impl;

import com.xinghuofirst.kill.model.entity.BusinessResources;
import com.xinghuofirst.kill.model.mapper.BusinessResourcesRepository;
import com.xinghuofirst.kill.server.service.BusinessRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 姜爽
 * @date: 2019/12/11 13:44
 * @description: 类的功能
 * @version: V1.0
 */
@Service
public class BusinessRepositoryServiceImpl implements BusinessRepositoryService {
    @Autowired
    BusinessResourcesRepository businessResourcesRepository;
    @Override
    public List<BusinessResources> selectBusinessService() {
        return businessResourcesRepository.selectBusinessResources();
    }
}
