package com.mfy.test;

import com.mfy.test.aop.AopConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AopConfig.class);
		ac.refresh();
	}
}
