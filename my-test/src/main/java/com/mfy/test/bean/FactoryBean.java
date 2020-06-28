package com.mfy.test.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class FactoryBean {

    public Object factoryMethod(String id,@Autowired Son son) {
        System.out.println("=========factoryMethod=========");
        return new PropertyClass();
    }
}
