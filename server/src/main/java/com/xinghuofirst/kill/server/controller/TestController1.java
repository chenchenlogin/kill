package com.xinghuofirst.kill.server.controller;

import com.xinghuofirst.kill.model.entity.Activity;
import com.xinghuofirst.kill.model.entity.KillSuccess;
import com.xinghuofirst.kill.model.entity.Person;
import com.xinghuofirst.kill.model.entity.Province;
import com.xinghuofirst.kill.model.mapper.KillSuccessRepository;
import com.xinghuofirst.kill.model.mapper.PersonRepository;
import com.xinghuofirst.kill.model.mapper.ProvinceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: killproject
 * @description: 测试基础方法
 * @author: Yuyue
 * @create: 2019-12-10 10:43
 **/
@RestController
public class TestController1 {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired PersonRepository personRepository;


    @GetMapping("person1")
    public Person selectById(Integer personId){
        return personRepository.selectById(personId);
    }

    @PostMapping("person2")
    public int insertSelective(@RequestBody Person person){
        System.out.println(person.toString());
        return personRepository.insertSelective(person);
    }
    @PostMapping("person3")
    public List<Person> findAll(Person person){
        return personRepository.findAllWithResult(person);
    }


    @Autowired KillSuccessRepository killSuccessRepository;

    @GetMapping("kill1")
    public KillSuccess selectByIdkill(Integer killId){

        return killSuccessRepository.selectById(killId);
    }
    @PostMapping("kill2")
    public int insertSelective(@RequestBody KillSuccess killSuccess){
        System.out.println(killSuccess.toString());
        return killSuccessRepository.insertSelective(killSuccess);
    }

    @Autowired ProvinceRepository provinceRepository;

    @RequestMapping("province")
    public List<Province> findAll(){
        return provinceRepository.findAllWithResult(null);
    }

}