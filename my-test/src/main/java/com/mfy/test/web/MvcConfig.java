package com.mfy.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Component
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/resources/main/WEB-INF/views/", ".jsp");
    }

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
	}

	/**
	 * 	addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
	 * 	registry.addViewController("请求路径").setViewName("请求页面文件路径")
	 * */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/view/ok").setViewName("ok");
	}

	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/user/**");
    }
}
