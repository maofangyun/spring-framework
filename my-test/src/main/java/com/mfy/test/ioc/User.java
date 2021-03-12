package com.mfy.test.ioc;

public class User {

	private String name;

	private String sex;

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", sex='" + sex + '\'' +
				'}';
	}

	public User() {
	}

	public User(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
