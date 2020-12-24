package com.mfy.test.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.mfy.test.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
