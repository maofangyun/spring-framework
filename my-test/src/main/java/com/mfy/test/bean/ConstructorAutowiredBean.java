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

	//    @Resource
    @Autowired
    private Student student;

//    @Resource
//    @Autowired
//    private PropertyBean propertyBean;

  /*  @Autowired
    public ConstructorAutowiredBean(Student student) {
        this.student = student;
    }*/

//    @Resource
    @PostConstruct
    private void postConstruct() {
        System.out.println("=======ConstructorAutowiredBean.postConstruct========");
    }

//    @Resource
    @PreDestroy
    public void preDestroy() {
        System.out.println("=======ConstructorAutowiredBean.preDestroy========");
    }
}
