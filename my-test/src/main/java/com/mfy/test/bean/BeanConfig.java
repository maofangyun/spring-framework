package com.mfy.test.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.mfy.test.bean")
@EnableAspectJAutoProxy
public class BeanConfig {

//	@Bean
//	public User getUser(){
//		return new User("小明","男");
//	}
}
