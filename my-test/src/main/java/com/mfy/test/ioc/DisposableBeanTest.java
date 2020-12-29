package com.mfy.test.ioc;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.annotation.Order;

import java.util.List;

@Order(9)
public class DisposableBeanTest implements DisposableBean {

    /*
    * 这个方法需要进行业务功能增强，但是又不希望在原来基础上修改，可以用
    * replaced-method标签
    * */
    public void method(String param) {
        System.out.println("I am origin method! param = " + param);
    }

    public void method(List<String> param) {
        System.out.println("I am origin method! param = " + param);
    }

    @Override
    public void destroy(){
        System.out.println("=============DisposableBeanTest.DisposableBean");
    }
}
