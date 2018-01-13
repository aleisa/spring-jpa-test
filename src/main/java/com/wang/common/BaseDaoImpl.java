package com.wang.common;

import com.wang.entity.BaseEntity;
import com.wang.mapper.IBaseDao;
import com.wang.util.InfoParser;
import com.wang.util.Util;
import org.hibernate.HibernateException;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 王欣宇 on 2017/11/9.
 */

@Transactional(readOnly = false)
public class BaseDaoImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T ,ID> implements IBaseDao<T ,ID>{

    @PersistenceContext
    private EntityManager em;
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    private final Class<T> domainClass;
    protected static final Object[] EMPTY_ARRAY = new Object[]{};
    private String tblkey;
    private static Object lock = new Object();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 格式化当前系统日期
    public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.domainClass = domainClass;
    }


    @Override
    public List findByHql(String hql , Map<String,Object> params){
        Query query = em.createQuery(hql);
        for(String key:params.keySet()){
            query.setParameter(key,params.get(key));
        }
        return query.getResultList();
    }
    @Override
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
    public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }

    @Override
    public <T extends BaseEntity> List<T> findBySql(String sql,Object[] params,Class<T> classType){
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<>(classType);
        return jdbcTemplate.query(sql,params,rowMapper);
    }

/*    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        return null;
    }*/

    @Override
    public <S extends T> void saveEntity(S entity) throws Exception {
        Method method = null;
            String idName = null;
            Field[] fields = entity.getClass().getDeclaredFields();
            for (int i=0;i<fields.length;i++){
                Id id = fields[i].getAnnotation(Id.class);
                if(Util.isNotEmpty(id)){
                    idName = fields[i].getName();
                    continue;
                }
            }
            if(Util.isNotEmpty(idName)){
                InfoParser infoParser =new InfoParser(entity.getClass());
                Object value =  infoParser.getGetterMethod(idName).invoke(entity);
                if(Util.isEmpty(value)){
                    Method setMethod = infoParser.getSetterMethod(idName);
                    setMethod.invoke(entity,createPKID());
                }
                save(entity);
            }else {
                throw new HibernateException("No primary key");
            }
    }


    @Override
    public <S extends T> void batchSave(Iterable<S> entities) throws Exception {
        if (Util.isNotEmpty(entities)){
            for (S item: entities ){
                Method method = null;
                String idName = null;
                Field[] fields = item.getClass().getDeclaredFields();
                for (int i=0;i<fields.length;i++){
                    Id id = fields[i].getAnnotation(Id.class);
                    if(Util.isNotEmpty(id)){
                        idName = fields[i].getName();
                        continue;
                    }
                }
                if(Util.isNotEmpty(idName)){
                    InfoParser infoParser =new InfoParser(item.getClass());
                    Object value =  infoParser.getGetterMethod(idName).invoke(item);
                    if(Util.isEmpty(value)){
                        Method setMethod = infoParser.getSetterMethod(idName);
                        setMethod.invoke(item,createPKID());
                    }
                }else {
                    throw new HibernateException("No primary key");
                }
            }
            save(entities);
        }
    }



    protected String createPKID(){
        StringBuilder idStb = new StringBuilder();

        if (tblkey != null){
            idStb.append(tblkey.trim());
        }

        synchronized(lock){
            idStb.append(dateFormat.format(new Date()));
            try{
                Thread.sleep(1);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        idStb.append(getServerNo()).append(Util.createRandomCode(4));
        return idStb.toString();
    }

    protected String getServerNo(){
        int serverNo = 1;
        if(serverNo < 0 || serverNo > 100){
            return "01";
        }else if(serverNo < 10){
            return "0" + serverNo;
        }else{
            return String.valueOf(serverNo);
        }
    }

}
