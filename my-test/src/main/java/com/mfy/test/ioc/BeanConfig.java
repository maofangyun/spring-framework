package com.mfy.test.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.mfy.test.ioc","com.mfy.test.condition"})
@Import({DeferredImportSelectorDemo.class})
public class BeanConfig {

	@Autowired
	private ApplicationContext applicationContext;

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
		// 通过spring的getBean(),可以保证多次调用都是同一个对象,虽然getObject()返回的对象并不在spring容器中管理
		// 如果没有拦截的话,多次调用getObject(),会返回不同的Son对象,导致不是单例的问题
		Son son1 = factoryBeanTest.getObject();
		Son son2 = factoryBeanTest.getObject();
		// son1和son2是同一个对象
		System.out.println(son1);
		System.out.println(son2);
		FactoryBeanTest factoryBeanTest1 = (FactoryBeanTest)applicationContext.getBean("&getFactoryBeanTest");
		Son son3 = factoryBeanTest1.getObject();
		Son son4 = factoryBeanTest1.getObject();
		// son3和son4不是同一个对象
		System.out.println(son3);
		System.out.println(son4);
		return new User();
	}

	@Bean("son")
	public Son getSon(){
		System.out.println("测试@Configuration的类方法内部调用");
		return new Son();
	}
}
