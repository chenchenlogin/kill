package com.xinghuofirst.kill.server.service;

import com.xinghuofirst.kill.model.entity.BusinessResources;

import java.util.List;

/**
 * @author: 姜爽
 * @date: 2019/12/11 13:44
 * @description: 类的功能
 * @version: V1.0
 */
public interface BusinessRepositoryService {
    List<BusinessResources> selectBusinessService();
}
