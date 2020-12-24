package com.mfy.test.ioc;

public class FactoryBean implements org.springframework.beans.factory.FactoryBean<Woman> {

	@Override
	public Woman getObject() {
		return new Woman();
	}

	@Override
	public Class<?> getObjectType() {
		return Woman.class;
	}
}
