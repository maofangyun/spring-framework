package com.mfy.test;

import com.mfy.test.aop.AopConfig;
import com.mfy.test.aop.CustomBean;
import com.mfy.test.aop.IndexService;
import com.mfy.test.aop.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AopConfig.class);
		ac.refresh();
		IndexService bean = ac.getBean(IndexService.class);
		UserService userService = bean.getUserService();
		System.out.println(userService);
		//UserService userService = bean.getUserService();
		//System.out.println(userService);
		//CustomBean bean = ac.getBean(CustomBean.class);
		//bean.test();
		//bean.test();
	}
}
