package com.mfy.test.ioc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

//@Component
public class FactoryBeanTest implements FactoryBean<Son> {

	/**
	 * 手动调用beanFactory.getBean(Son.class)或者beanFactory.getBean("factoryBeanTest"),才会触发getObject()的执行
	 * */
	@Override
	public Son getObject() {
		return new Son();
	}

	@Override
	public Class<?> getObjectType() {
		return Son.class;
	}
}
