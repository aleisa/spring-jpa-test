package com.wang.mapper;

import com.wang.entity.BaseEntity;
import com.wang.entity.UserAcctInfo;
import com.wang.entity.UserParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 王欣宇 on 2017/11/9.
 */
public interface UserMapper extends IBaseDao<UserAcctInfo,String>{

    List<UserAcctInfo> findByOrgId(Integer orgId);

    Long countByOrgId(Integer orgId);

/*    List<UserAcctInfo> testFind();*/

    @Query(value = "SELECT * FROM user_acct_info o WHERE o.`REAL_NAME` =?",nativeQuery = true)
    List<UserAcctInfo> testQuery(String realName);
}
