package com.mfy.test.ioc;

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

	public User() {
	}

	private String sex;

	public User(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}
}
