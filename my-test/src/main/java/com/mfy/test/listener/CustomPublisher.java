package com.mfy.test.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CustomPublisher{

	@Autowired
	private ApplicationContext applicationContext;

	public void send(){
		CustomEvent<String> event = new CustomEvent<String>(applicationContext);
		event.setData("测试事件发布");
		applicationContext.publishEvent(event);
	}
}
