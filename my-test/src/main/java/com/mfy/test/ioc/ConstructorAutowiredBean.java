package com.mfy.test.ioc;

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

    //@Autowired
    private Student student;

	//@Autowired
	private User user;

	@Autowired(required = false)
    public ConstructorAutowiredBean(Student student) {
        this.student = student;
    }

	@Autowired(required = false)
	public ConstructorAutowiredBean(User user,Student student) {
		this.user = user;
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
