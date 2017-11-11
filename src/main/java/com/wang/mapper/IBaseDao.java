package com.wang.mapper;

import com.wang.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by 王欣宇 on 2017/11/10.
 */
public interface IBaseDao {
     List findByHql(String hql , Map<String,Object> params);

     public <M extends BaseEntity> List<M> findBySql(String sql,Object[] params,Class<M> classType);

     List findByHql(String hql,Map<String,Object> params,Integer startRow,Integer maxSize);
}
