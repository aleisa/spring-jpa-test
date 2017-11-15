package com.wang.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.entity.UserAcctInfo;

import com.wang.entity.UserParam;
import com.wang.mapper.UserMapper;
import com.wang.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王欣宇 on 2017/11/8.
 */
@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    public final ObjectMapper mapper = new ObjectMapper();
    @RequestMapping("saveUser")
    public String saveUser() throws Exception {
        userRepository.saveUserAcctInfo();
        return null;
    }

/*    @RequestMapping("findUserAcct")
    public List<UserAcctInfo> findUserAcct(){
        return userRepository.findUserAcct();
    }*/
    @RequestMapping("findByOrgId")
    public List<UserAcctInfo> findByOrgId(@RequestParam Integer orgId){
        return userRepository.findByOrgId(orgId);
    }
    @RequestMapping("countOrgId")
    public Long countOrgId(@RequestParam Integer orgId){
        return userRepository.countByOrgId(orgId);
    }
/*    @RequestMapping("testFind")
    public List<UserAcctInfo> testFind(){
        return userRepository.testFind();
    }*/


    @RequestMapping("testQuery")
    public List<UserAcctInfo> testQuery(){
        return userRepository.testQuery();
    }

    @RequestMapping("batchSave")
    public void batchSave( @RequestParam String userAcctInfos) throws Exception {
        JavaType javaType =  mapper.getTypeFactory().constructParametricType(ArrayList.class,UserAcctInfo.class);
        List<UserAcctInfo> userAcctInfoList = mapper.readValue(userAcctInfos,javaType);
        userRepository.batchSave(userAcctInfoList);
    }
}
