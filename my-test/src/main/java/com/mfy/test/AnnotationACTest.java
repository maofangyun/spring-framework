package com.mfy.test;

import com.mfy.test.ioc.BeanConfig;
import com.mfy.test.ioc.Son;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnotationACTest {
	public static void main(String[] args) {
		// new一个应用上下文的时候,会注册一些内部的BeanFactoryPostProcessor的beanDefinition
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(BeanConfig.class);
		ac.refresh();
	}
}
