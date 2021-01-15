package com.mfy.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @Classname GirlAdvice
 * @Description TODO
 * @Author Jack
 * Date 2021/1/14 21:11
 * Version 1.0
 */
@Component
public class GlobleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("=======globleAdvice.invoke======");
        return invocation.proceed();
    }
}
