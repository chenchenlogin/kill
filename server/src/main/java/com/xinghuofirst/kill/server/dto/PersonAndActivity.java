package com.xinghuofirst.kill.server.dto;

import com.xinghuofirst.kill.model.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: TODO
 * @author: 杜鹏
 * @date: 2019-12-09 19:35
 * @version: V1.0
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonAndActivity extends Person implements Serializable {
    private Integer activityId;
}
