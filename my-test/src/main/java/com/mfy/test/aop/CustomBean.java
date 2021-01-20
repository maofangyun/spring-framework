package com.mfy.test.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname MyBean
 * @Description TODO
 * @Author Jack
 * Date 2021/1/13 18:19
 * Version 1.0
 */
@Component
public class CustomBean {

    @Autowired
    private ScopedProxyBean scopedProxyBean;

    public void test() {
        scopedProxyBean.code();
    }
}
