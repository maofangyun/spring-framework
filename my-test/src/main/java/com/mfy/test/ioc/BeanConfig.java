package com.mfy.test.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.mfy.test.ioc","com.mfy.test.condition"})
@Import({DeferredImportSelectorDemo.class})
public class BeanConfig {

	@Bean
	public FactoryBeanTest getFactoryBeanTest(){
		return new FactoryBeanTest();
	}

	@Bean
	public User getUser(){
		this.getSon();
		// 返回的是FactoryBeanTest的cglib代理对象
		FactoryBeanTest factoryBeanTest = this.getFactoryBeanTest();
		// getObject()会被拦截,最终调用beanFactory.getBean(beanName),beanName="getFactoryBeanTest"
		Son son = factoryBeanTest.getObject();
		System.out.println(son);
		return new User();
	}

	@Bean("son")
	public Son getSon(){
		System.out.println("测试@Configuration的类方法内部调用");
		return new Son();
	}
}
