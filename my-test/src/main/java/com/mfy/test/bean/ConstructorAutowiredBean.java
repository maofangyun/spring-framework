package com.mfy.test.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class ConstructorAutowiredBean {

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

    @Autowired
    private Student student;

    @Autowired
    private PropertyBean propertyBean;

	@Autowired(required = false)
    public ConstructorAutowiredBean(Student student) {
        this.student = student;
    }

	@Autowired(required = false)
	public ConstructorAutowiredBean(PropertyBean propertyBean,Student student) {
		this.propertyBean = propertyBean;
		this.student = student;
	}

	public ConstructorAutowiredBean(){}

    @PostConstruct
    private void postConstruct() {
        System.out.println("=======ConstructorAutowiredBean.postConstruct========");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("=======ConstructorAutowiredBean.preDestroy========");
    }

    @Autowired
	public void test(){
		System.out.println("test method @Autowired");
	}
}
