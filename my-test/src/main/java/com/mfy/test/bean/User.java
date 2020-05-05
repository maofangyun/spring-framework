package com.mfy.test.bean;


public class User {
	private String name;

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", sex='" + sex + '\'' +
				'}';
	}

	public User(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}

	private String sex;
}
