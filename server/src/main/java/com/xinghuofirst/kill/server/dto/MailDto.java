package com.xinghuofirst.kill.server.dto;/**
 * Created by Administrator on 2019/6/22.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 邮件实体类
 * @author: dupeng
 * @date: 2019/12/08 17:25
 * @version: V1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailDto implements Serializable{
    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 接收人
     */
    private String[] tos;
}