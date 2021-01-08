package com.mfy.test.cglib;

import org.springframework.cglib.proxy.FixedValue;

public class FixedValueIntercepter implements FixedValue {
    @Override
    public Object loadObject(){
        System.out.println("==================loadObject value");
        return "my value 111";
    }
}
