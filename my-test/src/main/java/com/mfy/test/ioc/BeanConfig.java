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
	public User getUser(){
		this.getSon();
		return new User();
	}

	@Bean("son")
	public Son getSon(){
		System.out.println("测试@Configuration的类方法内部调用");
		return new Son();
	}
}
