package com.xinghuofirst.kill.server.dto;/**
 * Created by Administrator on 2019/6/17.
 */

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 秒杀传入参数实体类
 * @author: dupeng
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@Data
@ToString
public class ActivityDto implements Serializable{

    /**
     * 活动id
     */
    @NotNull
    private Integer activityId;

    /**
     * 鑫管家id
     */
    private Integer personId;
}