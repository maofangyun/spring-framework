package com.mfy.test.aop;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @Classname SetGlobleAdvice
 * @Description TODO
 * @Author Jack
 * Date 2021/1/14 21:18
 * Version 1.0
 * postProcessAfterInitialization
 */
@Component
public class GlobleAdviceRegister implements BeanPostProcessor, PriorityOrdered {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof AnnotationAwareAspectJAutoProxyCreator) {
            AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator = (AnnotationAwareAspectJAutoProxyCreator)bean;
            annotationAwareAspectJAutoProxyCreator.setInterceptorNames("globleAdvice");
        }
        return bean;
    }

    @Override
    public int getOrder() {
        return 45;
    }
}
