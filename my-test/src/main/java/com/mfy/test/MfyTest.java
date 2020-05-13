package com.mfy.test;

import com.mfy.test.bean.BeanConfig;
import com.mfy.test.bean.IndexService;
import com.mfy.test.bean.User;
import com.mfy.test.bean.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


public class MfyTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(BeanConfig.class);
		ac.refresh();
		UserService userService = ac.getBean(UserService.class);
		userService.getIndexService().test();
	}
}
