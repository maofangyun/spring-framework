package com.mfy.test.web;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
//@Component
public class AspectBean {

	@Pointcut("execution(* com.mfy.test.web.UserController.*(..))")
	public void pc(){}

	@Before("pc()")
	public void beforeTest(JoinPoint join){
		//获取方法名
		String mathName=join.getSignature().getName();
		//获取参数列表
		List<Object> args = Arrays.asList(join.getArgs());
		System.out.println("前置通知---->before   方法名是:"+mathName+"\t参数列表是:"+args);
	}

	@After("pc()")
	public void afterTest(){
		System.out.println("后置通知---->after....");
	}

}
