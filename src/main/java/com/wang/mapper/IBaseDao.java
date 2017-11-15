package com.wang.mapper;

import com.wang.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by 王欣宇 on 2017/11/10.
 */
@NoRepositoryBean
     public interface IBaseDao<M extends BaseEntity , ID extends Serializable> extends JpaRepository<M, ID> {
     List findByHql(String hql , Map<String,Object> params);

     public <M extends BaseEntity> List<M > findBySql(String sql,Object[] params,Class<M> classType);

     List findByHql(String hql,Map<String,Object> params,Integer startRow,Integer maxSize);

     public boolean support(String modelType);

     /**
      * 带自定义主键的保存
      * @param entity
      * @param <S>
      */
     public <S extends M> void saveEntity(S entity) throws Exception;

     /**
      * 带自定义主键的批量保存
      * @param entities
      * @param <S>
      */
     public <S extends M> void batchSave(Iterable<S> entities) throws Exception;
}
