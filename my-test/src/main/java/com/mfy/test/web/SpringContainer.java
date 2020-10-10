package com.mfy.test.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//不扫描有@Controller注解的类
@ComponentScan(value = "com.mfy.test.web",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
})
public class SpringContainer {
}
