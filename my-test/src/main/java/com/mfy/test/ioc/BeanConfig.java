package com.mfy.test.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.mfy.test.ioc")
@EnableAspectJAutoProxy
public class BeanConfig {

	@Bean
	public User getUser(){
		this.getWoman();
		return new User();
	}

	@Bean("women")
	public Woman getWoman(){
		System.out.println("测试@Configuration的类方法内部调用");
		return new Woman();
	}
}
