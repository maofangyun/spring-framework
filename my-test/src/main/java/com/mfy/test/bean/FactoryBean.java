package com.mfy.test.bean;

import org.springframework.beans.factory.annotation.Autowired;

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
