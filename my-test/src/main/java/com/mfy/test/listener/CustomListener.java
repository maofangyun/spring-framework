package com.mfy.test.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomListener {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * {@Async}会生成一个代理对象,加了@Async有可能执行不了,可能出现容器已经停止了,但是此线程的事件还没有执行完
	 * */
	//@Async
	@EventListener
	public void handleCustomEvent(CustomEvent<String> event) {
		System.out.println("监听到事件:"+event.getData());
	}

	//@Async
	@EventListener(value = {ContextStartedEvent.class})		// value中的值会覆盖入参的类型,即改变了监听器监听事件的类型
	public void handleStartedEvent(ApplicationEvent event) {
		System.out.println("监听到Started事件:"+event.toString());
	}

	//@Async
	@EventListener
	public ApplicationEvent handleRefreshedEvent(ContextRefreshedEvent event) {
		System.out.println("监听到Refreshed事件:"+event.toString());
		CustomEvent<String> customEvent = new CustomEvent<String>(applicationContext);
		customEvent.setData("测试监听器的返回值");
		return customEvent;
	}

	/**
	 * 非ApplicationEvent类型的返回值,将包装成PayloadApplicationEvent类型的事件
	 * */
	//@Async
	@EventListener
	public String handleStoppedEvent(ContextStoppedEvent event) {
		System.out.println("监听到Stopped事件:"+event.toString());
		return "测试返回字符串";
	}

	@EventListener
	public void handlePayloadEvent(PayloadApplicationEvent<String> event) {
		System.out.println("监听到Payload事件:"+event.getPayload());
	}

	/**
	 * 监听器的入参不一定是ApplicationEvent类型
	 * */
	@EventListener
	public void handleString(String str) {
		System.out.println("监听到String事件:"+str);
	}

	//@Async
	@EventListener
	public void handleClosedEvent(ContextClosedEvent event) {
		System.out.println("监听到Closed事件:"+event.toString());
	}
}
