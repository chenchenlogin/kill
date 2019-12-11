package com.xinghuofirst.kill.model.mapper;

import com.xinghuofirst.kill.model.entity.BusinessResources;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: killproject
 * @description: 用户资源mappper
 * @author: Yuyue
 * @create: 2019-12-11 11:51
 **/
@Mapper
@Repository
public interface BusinessResourcesRepository {
    List<BusinessResources> selectBusinessResources();
}
