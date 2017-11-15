package com.wang.service;


import com.wang.entity.OrgDeptInfo;
import com.wang.entity.UserAcctInfo;
import com.wang.entity.UserParam;
import com.wang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by 王欣宇 on 2017/11/8.
 */
@Service
public class UserRepository {
    @Autowired
    private UserMapper userMapper;


    public void saveUserAcctInfo() throws Exception {
        UserAcctInfo info = new UserAcctInfo();
        info.setAcctName("王欣宇");
        info.setAcctPwd("d9b1d7db4cd6e70935368a1efb10e377");
        info.setOrgId(23123);
        info.setAcctRole((short)1);
        userMapper.saveEntity(info);
    }

/*    public Page<UserAcctInfo> findAcct(){
       return userMapper.findAll(new PageRequest(0,10));
    }*/
/*
    public List<UserAcctInfo> findUserAcct(){
        return userMapper.findAll(new Specification<UserAcctInfo>() {
            @Override
            public Predicate toPredicate(Root<UserAcctInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Join<UserAcctInfo,OrgDeptInfo> userJoin = root.join("orgId",JoinType.INNER);
                return cb.equal(userJoin.get("orgId"),33);
            }
        });
    }*/

    public Long countByOrgId(Integer orgId){
        return userMapper.countByOrgId(orgId);
    }

    public List<UserAcctInfo> findByOrgId(Integer orgId){
        return userMapper.findByOrgId(orgId);
    }

/*    public List<UserAcctInfo> testFind(){
        return userMapper.testFind();
    }*/

    public List<UserAcctInfo> testQuery(){
        return userMapper.testQuery("刘毅峰");
    }


    public void batchSave(List<UserAcctInfo> userAcctInfos){
        try {
            userMapper.batchSave(userAcctInfos);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
