package com.mfy.test.ioc;

import org.springframework.beans.factory.annotation.Autowired;

public class Son {

	@Autowired
	private ConstructorAutowiredBean constructorAutowiredBean;

    private String username;

    private int age;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
