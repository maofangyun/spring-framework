package com.mfy.test.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(scopeName = "jack",proxyMode = ScopedProxyMode.INTERFACES)
public class Woman implements People {
    @Override
    public void showsix() {
        System.out.println("i am woman");
    }
}
