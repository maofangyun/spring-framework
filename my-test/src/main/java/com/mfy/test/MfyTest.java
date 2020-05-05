package com.mfy.test;

import com.mfy.test.bean.BeanConfig;
import com.mfy.test.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.mfy.test.bean")
public class MfyTest {
	public static void main(String[] args) {
		ApplicationContext ac =new AnnotationConfigApplicationContext(BeanConfig.class);
		User user = ac.getBean(User.class);
		System.out.println(user.toString());
	}
}
