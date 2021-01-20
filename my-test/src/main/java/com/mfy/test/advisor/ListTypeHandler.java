package com.mfy.test.advisor;

import com.alibaba.fastjson.JSONArray;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @Classname ListTypeHandler
 * @Description TODO
 * @Author Jack
 * Date 2020/12/1 16:34
 * Version 1.0
 */
public class ListTypeHandler implements TypeHandler {
    @Override
    public boolean support(Type returnType) {
        return ((ParameterizedTypeImpl) returnType).getRawType().isAssignableFrom(List.class);
    }

    @Override
    public Object handler(Object result, Type returnType) {
        Type[] actualTypeArguments = ((ParameterizedTypeImpl) returnType).getActualTypeArguments();
        if (actualTypeArguments.length > 0) {
            return JSONArray.parseArray((String) result, actualTypeArguments);
        } else {
            return JSONArray.parseArray((String) result, Object.class);
        }
    }
}
