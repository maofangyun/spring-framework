package com.mfy.test.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.mfy.test.bean")
@EnableAspectJAutoProxy
public class BeanConfig {

	@Bean
	public User getUser(){
		getWoman();
		return new User();
	}

	@Bean
	public Woman getWoman(){
		System.out.println("测试@Configuration的类方法内部调用");
		return new Woman();
	}
}
