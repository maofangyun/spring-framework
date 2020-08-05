package com.mfy.test.bean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class User {
	private String name;

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", sex='" + sex + '\'' +
				'}';
	}

	private String sex;
}
