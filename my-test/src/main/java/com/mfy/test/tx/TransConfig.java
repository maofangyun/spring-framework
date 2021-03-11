package com.mfy.test.tx;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 默认值为AdviceMode.PROXY,代理模式仅允许通过代理拦截呼叫,
 * 同一类中的本地调用无法以这种方式被拦截;在本地调用中，此类方法上的事务性注解将被忽略
 *
 * mode=AdviceMode.ASPECTJ:更高级的拦截模式,支持本地调用
 * */
@ComponentScan("com.mfy.test.tx")
@EnableTransactionManagement
public class TransConfig {
}
