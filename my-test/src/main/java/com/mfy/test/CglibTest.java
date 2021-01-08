package com.mfy.test;

import com.mfy.test.cglib.CglibBeanFactory;
import com.mfy.test.cglib.UserService;

public class CglibTest {
	public static void main(String[] args) {
		UserService userService = (UserService) CglibBeanFactory.getInstance();
		System.out.println(userService.doSomething1("Jack"));
	}
}
