package com.mfy.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class XMLACTest {
	public static void main(String[] args) {
		// new一个应用上下文的时候,会注册一些内部的BeanFactoryPostProcessor的beanDefinition
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		ac.refresh();
	}
}
