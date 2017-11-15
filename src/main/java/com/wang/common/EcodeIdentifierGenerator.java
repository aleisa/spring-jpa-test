/*
 * com.jsecode.platform.spring3.hibernate3.EcodeIdentifierGenerator.java
 * @CopyRight 江苏亿科达科技发展有限公司
 */
package com.wang.common;

import com.wang.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 自定义的主键生成器，扩展hibernate的主键生成规则。<br>
 * 规则为： 17位日期时间(精确到毫秒) + 2位服务器编号(config.properties文件中配置的ServerNo) + 4位随机码数字
 *
 * @author huangzw
 * @date 2013-3-26 下午4:54:28
 * @version 1.0
 */
public class EcodeIdentifierGenerator implements IdentifierGenerator, Configurable {
    private String entityName;
    private String tblkey;
    private static Object lock = new Object();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // 格式化当前系统日期
    public static final String TBL_KEY = "tblKey";

      //hibernate5.2.0的写法
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException{
//        Serializable id = session.getEntityPersister(entityName, object).getIdentifier(object, session);
//        if (id == null || id.toString().trim().length()==0){
//            id = createPKID();
//        }
//        
//        return id;
//    }
     
    //hibernate5.1.0的写法
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        Serializable id = session.getEntityPersister(entityName, object).getIdentifier(object, session);
        if (id == null || id.toString().trim().length()==0){
            id = createPKID();
        }
        
        return id;
    }
    
    protected Serializable createPKID(){
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
 
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        entityName = params.getProperty(ENTITY_NAME);
        tblkey = params.getProperty(TBL_KEY);
        if(entityName == null){
            throw new MappingException("no entity name");
        }
    }
    
    public static String getStrategyName(){
        return "ecodeIdentifierGenerator";
    }
}
