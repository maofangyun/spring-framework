package com.mfy.test.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean
	public User getUser(){
		return new User("小明","男");
	}
}
