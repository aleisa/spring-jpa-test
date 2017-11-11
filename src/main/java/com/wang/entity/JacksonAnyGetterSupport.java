package com.wang.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;

/**
 * @author Huzl
 * @version 1.0.0
 */
public interface JacksonAnyGetterSupport {
    @JsonAnyGetter
    Map<Object, Object> getExtraMap();
    
    void putExtraProperty(Object name, Object value);
}
