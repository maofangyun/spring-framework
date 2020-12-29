package com.mfy.test.ioc;

import org.springframework.context.annotation.PropertySource;

@PropertySource(name = "mfy",value = "classpath:/test1.properties")
@PropertySource(name = "mfy",value = "classpath:/test2.properties")
public class PropertySourceTest {
}
