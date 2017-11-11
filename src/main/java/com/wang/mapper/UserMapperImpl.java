package com.wang.mapper;

import com.wang.common.BaseDaoImpl;
import com.wang.entity.UserAcctInfo;
import com.wang.entity.UserParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王欣宇 on 2017/11/10.
 */
public class UserMapperImpl extends BaseDaoImpl {

    List<UserAcctInfo> testFind(){
        String sql = "from UserAcctInfo  ";
        Map<String,Object> params = new HashMap<>();
        return findByHql(sql,params,0,3);
    }

}
