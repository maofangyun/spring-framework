package com.mfy.test.bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class AspectBean {

	@Before("execution(* com.mfy.test.bean.IndexService.test(..))")
	public void before(JoinPoint join){
		//获取方法名
		String mathName=join.getSignature().getName();
		//获取参数列表
		List<Object> args = Arrays.asList(join.getArgs());

		System.out.println("前置通知---->before   方法名是:"+mathName+"\t参数列表是:"+args);
	}

	@After("execution(* com.mfy.test.bean.IndexService.test(..))")
	public void after(){
		System.out.println("后置通知---->after....");
	}
}
