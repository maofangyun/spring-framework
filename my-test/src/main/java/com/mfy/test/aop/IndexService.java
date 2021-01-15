package com.mfy.test.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexService {

	@Autowired
	private UserService userService;

	private String key = "key";

	public IndexService(){
		System.out.println("IndexService()构造方法执行");
	}

	public UserService getUserService() {
		return userService;
	}

	public void test(){
		// 解决目标方法调用同对象中其他方法时,其他方法的切面逻辑无法执行的问题(途径之一)
		IndexService indexService = (IndexService)AopContext.currentProxy();
		indexService.test1();
		System.out.println("indexService.....test()");
	}

	public void test1(){
		System.out.println("测试方法test1");
		this.test2();
	}

	public void test2(){
		System.out.println("测试方法test2");
	}
}
