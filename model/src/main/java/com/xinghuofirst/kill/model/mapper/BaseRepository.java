package com.xinghuofirst.kill.model.mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<T, ID extends Serializable> {
    int deleteByPrimaryKey(ID var1);

    int insert(T var1);

    int insertSelective(T var1);

    int updateByPrimaryKeySelective(T var1);

    int updateByPrimaryKey(T var1);

    T selectById(ID var1);

    T findOne(T var1);

    List<T> findAllWithResult(T var1);

    List<T> findPageWithResult(T var1);

    Integer findPageWithCount(T var1);

    List<T> findPageWithResultLike(T var1);

    Integer findPageWithCountLike(T var1);
}
