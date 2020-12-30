package com.mfy.test.ioc;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(name = "mfy",value = "classpath:/${USERNAME}.properties")
@PropertySource(name = "mfy",value = "classpath:/test2.properties")
public class PropertySourceTest {
}
