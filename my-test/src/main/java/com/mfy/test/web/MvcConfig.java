package com.mfy.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private UserInterceptor userInterceptor;


	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("user/**");
		registry.addInterceptor(new UserInterceptor1()).addPathPatterns("/user/**").excludePathPatterns("");
        super.addInterceptors(registry);
    }
}
