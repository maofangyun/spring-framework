package com.mfy.test.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

public class CglibBeanFactory {

    public static Object getInstance() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        CallbackFilter callbackFilter = new CglibCallbackFilter();
        enhancer.setCallbackFilter(callbackFilter);

        Callback callback1 = new CglibIntercepter1();
        Callback callback2 = new CglibIntercepter2();
        Callback callback3 = new CglibIntercepter3();
        //这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
        Callback noop = NoOp.INSTANCE;
        Callback fixdValueCallback = new FixedValueIntercepter();
        Callback[] callbacks = {callback1, callback2, callback3, noop, fixdValueCallback};
        enhancer.setCallbacks(callbacks);
        return enhancer.create();
    }
}
