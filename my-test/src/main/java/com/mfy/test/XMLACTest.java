package com.mfy.test;

import com.mfy.test.bean.BeanConfig;
import com.mfy.test.bean.Son;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class XMLACTest {
	public static void main(String[] args) {
		// new一个应用上下文的时候,会注册一些内部的BeanFactoryPostProcessor的beanDefinition
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		Object factoryBean = ac.getBean("factoryBean");
	}
}
