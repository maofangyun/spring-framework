package com.mfy.test.aop;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
/**
 * ScopedProxyMode.TARGET_CLASS,表示通过cglib生成一个动态代理的对象
 * ScopedProxyMode.INTERFACES,表示通过JDK生成一个动态代理的对象
 * 使用场景:针对@Autowired的自动装配过程,若proxyMode = NO,则每次调用ScopedProxyBean的code()时,打印出来的哈希码都是一样的
 * 想要实现原型的效果,即每次调用code()时,打印对象哈希码都不一样,必须使用proxyMode = ScopedProxyMode.TARGET_CLASS或者proxyMode = ScopedProxyMode.INTERFACES
 * */
@Scope(value = DefaultListableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ScopedProxyBean {

    public void code() {
        System.out.println(this.hashCode());
    }
}
