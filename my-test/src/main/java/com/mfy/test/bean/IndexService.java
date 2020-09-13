package com.mfy.test.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexService {

//	@Autowired
//	private UserService userService;

	private String key = "key";

	public IndexService(){
		System.out.println("IndexService()构造方法执行");
	}

//	public UserService getUserService() {
//		return userService;
//	}

	public void test(){
		System.out.println("indexService.....test()");
	}
}
