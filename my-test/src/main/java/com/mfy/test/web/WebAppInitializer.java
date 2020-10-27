package com.mfy.test.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 不需要加@Component注解，因为tomcat启动时，会寻找实现了WebApplicationInitializer接口的类，并实例化
 * @link SpringServletContainerInitializer
 * @HandlesTypes(WebApplicationInitializer.class)
 * */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //父容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SpringContainer.class};
    }

    //SpringMVC配置子容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcContainer.class};
    }

    //获取DispatcherServlet的映射信息
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
		CustomFilter customFilter = new CustomFilter();
		Filter[] filters = new Filter[1];
		filters[0] = customFilter;
		return filters;
	}
}
