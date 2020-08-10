package com.mfy.test;

import com.mfy.test.bean.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class XMLACTest {
	public static void main(String[] args) {
		// new一个应用上下文的时候,会注册一些内部的BeanFactoryPostProcessor的beanDefinition
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		//IndexService indexService = (IndexService)ac.getBean("indexService");
		//UserService userService = indexService.getUserService();
		//IndexService indexService1 = userService.getIndexService();
		Object factoryBean = ac.getBean("factoryBean");
	}
}
