package com.mfy.test.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(value = OnBeanCondition.class)
public @interface ConditionOnBean {

    Class<?>[] value() default {};

    String[] name() default {};

}
