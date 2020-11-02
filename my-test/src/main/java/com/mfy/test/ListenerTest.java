package com.mfy.test;

import com.mfy.test.listener.BeanConfig;
import com.mfy.test.listener.CustomPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ListenerTest {
	public static void main(String[] args) {
		// new一个应用上下文的时候,会注册一些内部的BeanFactoryPostProcessor的beanDefinition
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(BeanConfig.class);
		ac.refresh();
		//ac.start();
		CustomPublisher publisher = ac.getBean(CustomPublisher.class);
		publisher.send();
		ac.stop();
		ac.close();
	}
}
