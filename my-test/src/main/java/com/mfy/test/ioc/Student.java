package com.mfy.test.ioc;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

//@Lazy
@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class Student {
    private String username = "Jack";

    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
