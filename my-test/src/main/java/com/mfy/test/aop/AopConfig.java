package com.mfy.test.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.mfy.test.aop")
/**
 * proxyTargetClass=true,表示使用cglib做动态代理;
 * proxyTargetClas=false,表示使用JDK做动态代理,但是碰到没有接口的被代理对象,还是会使用cglib;
 * exposeProxy=true,表示调用被代理方法时,会将代理对象,放入一个ThreadLocal中(AopContext.currentProxy),方便其他地方调用;
 * exposeProxy=false,表示不放入;
 * */
@EnableAspectJAutoProxy(proxyTargetClass = false, exposeProxy = true)
public class AopConfig {
}
