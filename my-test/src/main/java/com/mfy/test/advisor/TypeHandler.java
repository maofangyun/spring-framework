package com.mfy.test.advisor;

import java.lang.reflect.Type;

public interface TypeHandler {

    boolean support(Type returnType);

    Object handler(Object result,Type returnType);
}
