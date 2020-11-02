package com.mfy.test.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.io.Serializable;

public class CustomEvent<T> extends ApplicationContextEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private T data;

	public CustomEvent(ApplicationContext source) {
		super(source);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
