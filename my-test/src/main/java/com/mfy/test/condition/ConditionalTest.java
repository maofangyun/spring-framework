package com.mfy.test.condition;

import com.mfy.test.ioc.Son;
import org.springframework.stereotype.Component;

/**
 * @Classname ConditionalBean
 * @Description TODO
 * @Author Jack
 * Date 2021/1/4 16:40
 * Version 1.0
 */
@Component
@ConditionOnBean(Son.class)
public class ConditionalTest {
}
