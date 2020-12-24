package com.mfy.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class AspectBean {

	@Pointcut("execution(* com.mfy.test.aop.IndexService.*(..))")
	public void pc1(){}

	@Before("pc1()")
	public void beforeTest(JoinPoint join){
		//获取方法名
		String mathName=join.getSignature().getName();
		//获取参数列表
		List<Object> args = Arrays.asList(join.getArgs());

		System.out.println("前置通知---->before   方法名是:"+mathName+"\t参数列表是:"+args);
	}

	@After("pc1()")
	public void afterTest(){
		System.out.println("后置通知---->after....");
	}

	@Around("pc1()")
	public void aroundTest(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("环绕增强----> around  前置增强");
		pjp.proceed();
		System.out.println("环绕增强----> around  后置增强");
	}

	@AfterReturning("pc1()")
	public void afterReturningTest(JoinPoint joinPoint){
		System.out.println("返回增强 -----> afterReturning");
	}
}
