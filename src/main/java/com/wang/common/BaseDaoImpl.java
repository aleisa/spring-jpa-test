package com.wang.common;

import com.wang.entity.BaseEntity;
import com.wang.mapper.IBaseDao;
import com.wang.util.InfoParser;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by 王欣宇 on 2017/11/9.
 */
public class BaseDaoImpl  implements IBaseDao {

    @PersistenceContext
    private EntityManager em;
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    protected static final Object[] EMPTY_ARRAY = new Object[]{};
    @Override
    public List findByHql(String hql , Map<String,Object> params){
        Query query = em.createQuery(hql);
        for(String key:params.keySet()){
            query.setParameter(key,params.get(key));
        }
        return query.getResultList();
    }

    public List findByHql(String hql,Map<String,Object> params,Integer startRow,Integer maxSize){
        Query query = em.createQuery(hql);
        for(String key:params.keySet()){
            query.setParameter(key,params.get(key));
        }
        query.setFirstResult(startRow);
        query.setMaxResults(maxSize);
        return query.getResultList();
    }

    @Override
    public <M extends BaseEntity> List<M> findBySql(String sql,Object[] params,Class<M> classType){
        RowMapper<M> rowMapper = new BeanPropertyRowMapper<>(classType);
        return jdbcTemplate.query(sql,params,rowMapper);
    }
}
