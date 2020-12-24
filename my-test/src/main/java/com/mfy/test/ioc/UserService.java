package com.mfy.test.ioc;

import com.mfy.test.aop.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
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
