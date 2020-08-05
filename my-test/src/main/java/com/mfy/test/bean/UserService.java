package com.mfy.test.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = "session",proxyMode = ScopedProxyMode.INTERFACES)
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
