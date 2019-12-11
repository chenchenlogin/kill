package com.xinghuofirst.kill.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: killproject
 * @description: 用户资源类
 * @author: Yuyue
 * @create: 2019-12-11 11:47
 **/
@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Accessors(chain = true)
public class BusinessResources implements Serializable {
    /*沉默用户数*/
    private Integer silentCount;
    /*可参加活动用户数*/
    private Integer beKillCount;
    /*省份名称*/
    private String provinceName;

    public Integer getSilentCount() {
        return silentCount;
    }

    public BusinessResources(Integer silentCount, Integer beKillCount, String provinceName) {
        this.silentCount = silentCount;
        this.beKillCount = beKillCount;
        this.provinceName = provinceName;
    }

    public void setSilentCount(Integer silentCount) {
        this.silentCount = silentCount;
    }

    public Integer getBeKillCount() {
        return beKillCount;
    }

    public void setBeKillCount(Integer beKillCount) {
        this.beKillCount = beKillCount;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
