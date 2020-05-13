package com.mfy.test.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	@Autowired
	private IndexService indexService;

	private String value = "value";

	public UserService(){
		System.out.println("UserService()构造方法执行");
	}

	public IndexService getIndexService() {
		return indexService;
	}
}
