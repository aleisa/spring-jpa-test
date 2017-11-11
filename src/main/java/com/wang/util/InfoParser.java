/**
 * 
 */
package com.wang.util;





import com.wang.entity.BaseEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析实体类，将实体类的属性对应的setter、getter方法解析出来
 * @author hzwei
 *
 */ 
public class InfoParser<T extends BaseEntity>
{ 
    private Map<String, Method> getMethodMap = new HashMap<String, Method>();
    private Map<String, Method> setMethodMap = new HashMap<String, Method>();
    private Map<String, Class> fieldTypeMap = new HashMap<String, Class>();
    private List<String> fieldNames = new ArrayList<String>();
     
    public InfoParser(Class<T> cls){
        destroy(); 
        init(cls);
        Class superCls = cls.getSuperclass();
        while (!superCls.getName().equals("java.lang.Object")){
            init(superCls);
            superCls = superCls.getSuperclass();
        } 
    }
     
    private void init(Class<? super T> cls){
        Field[] fields =  cls.getDeclaredFields();
        Method[] methods = cls.getMethods();
       
        if (fields == null || fields.length <= 0 
            || methods == null || methods.length <= 0){
            return;
        }
        
        String fieldName = null;
        String methodName = null;
          
        int methodCount = 0;
        for (Field field: fields){
            fieldName = field.getName();
            if ("serialVersionUID".equalsIgnoreCase(fieldName)){
                continue;
            }
            
            methodCount = 0;
            fieldNames.add(fieldName);
            fieldTypeMap.put(fieldName.toUpperCase(), field.getType());
            for (Method method: methods){
                if (methodCount == 2){
                    break;  //找到了field的set与get方法，退出，进行下一次查找
                }
                
                methodName = method.getName();
                if (fieldName.equalsIgnoreCase(methodName.substring(3))){
                    if (methodName.startsWith("get")){
                        getMethodMap.put(fieldName.toUpperCase(), method);
                        methodCount ++;
                        continue;
                    }
                    
                    if (methodName.startsWith("set")){
                        setMethodMap.put(fieldName.toUpperCase(), method);
                        methodCount ++;
                        continue;
                    }
                }else if (fieldName.equalsIgnoreCase(methodName.substring(2))){
                    if (methodName.startsWith("is")){
                        getMethodMap.put(fieldName.toUpperCase(), method);
                        methodCount ++;
                        break;
                    }
                }
            }
        }
    }
    
    public List<String> getFieldNames(){
        return fieldNames;
    }
    
    public Class getFieldType(String fieldName){
        if (fieldName == null || fieldName.trim().length() <= 0){
            return null;
        }
        
        fieldName = fieldName.trim().toUpperCase();
        return fieldTypeMap.get(fieldName);
    }
    
    public Method getSetterMethod(String fieldName){
        if (fieldName == null || fieldName.trim().length() <= 0){
            return null;
        }
        
        fieldName = fieldName.trim().toUpperCase();
        return setMethodMap.get(fieldName);
    }
    
    public Method getGetterMethod(String fieldName){
        if (fieldName == null || fieldName.trim().length() <= 0){
            return null;
        }
        
        fieldName = fieldName.trim().toUpperCase();
        return getMethodMap.get(fieldName);
    }
    
    public void destroy(){
        getMethodMap.clear();
        setMethodMap.clear();
        fieldNames.clear();
        fieldTypeMap.clear();
    }
    
    public static void main(String[] args){
        
    }
}
