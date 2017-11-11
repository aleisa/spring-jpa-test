/**
 * 
 */
package com.wang.entity;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.wang.util.InfoParser;
import org.springframework.beans.BeanUtils;


import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Huangzw@jsecode.com
 * 
 */
public class BaseEntity implements Serializable,JacksonAnyGetterSupport {
    private static final long serialVersionUID = -6468420899701162073L;
    protected Map<Object, Object> extraMap ;
    
    private String token;
    
    @JsonIgnore
    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }
    @Override
    public BaseEntity clone(){
        BaseEntity clone;
        try{
            clone = this.getClass().newInstance();
            BeanUtils.copyProperties(this, clone);

            return clone;
        }catch(Exception e){ 
            throw new RuntimeException("The "+ this.getClass().getName() + " instance call clone method exception", e);
        }
    }
    
    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public String toString(){
        InfoParser parser = null;
        try{
            parser = new InfoParser(this.getClass());
            List<String> fieldNames = parser.getFieldNames();
            if(fieldNames.isEmpty()){
                return super.toString();
            }
            
            Method method = null;
            Object value = null;
            Object[] empty = new Object[]{};
            ToStringHelper tsh = MoreObjects.toStringHelper(this);
            for(String field : fieldNames){
                method = parser.getGetterMethod(field);
                if(method == null){
                    continue;
                }
                
                try{
                    value = method.invoke(this, empty);
                    if (value != null){
                        if (value.getClass().isArray()){
                            Object[] arr = (Object[])value;
                            ToStringHelper item = MoreObjects.toStringHelper("Array");
                            for (Object o:arr){
                                item.addValue(o);
                            }
                            tsh.add(field, item.toString());
                        }else if(value instanceof List){
                             List arr = (List)value;
                             ToStringHelper item = MoreObjects.toStringHelper("List");
                             for (int i=0;i<arr.size();i++){
                                 item.addValue(arr.get(i));
                             } 
                             tsh.add(field, item.toString());
                        }else if (value instanceof Map){
                            Map map = (Map)value;
                            Set<Map.Entry> set = map.entrySet();
                            ToStringHelper item = MoreObjects.toStringHelper("Map");
                            for (Map.Entry e:set){
                                item.addValue(e.getKey()+":"+e.getValue()); 
                            }
                            tsh.add(field, item.toString());
                        }else if (value instanceof Set){
                            Set set = (Set)value;
                            ToStringHelper item = MoreObjects.toStringHelper("Set");
                            for (Object o: set){
                                item.addValue(o);
                            }
                            tsh.add(field, item.toString());
                        }else {
                            tsh.add(field, value);
                        }
                    } 
                }catch(Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
             
            return tsh.toString();
        }catch(Exception we){
            we.printStackTrace();
            return this.getClass().getSimpleName() + ".toString: " + we.toString();
        }finally{
            if(parser != null){
                parser.destroy();
            }
        }
    }
    @Override
    @JsonAnyGetter
    public Map<Object, Object> getExtraMap() {
        return extraMap;
    }

    @Override
    public void putExtraProperty(Object name, Object value) {
        if(name == null || value == null)return;
        if(extraMap == null)
            extraMap = new java.util.HashMap<Object, Object>();
        extraMap.put(name,value);
    }
}
