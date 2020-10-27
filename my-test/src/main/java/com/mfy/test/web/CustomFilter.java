package com.mfy.test.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "customFilter",		//过滤器的名称
		urlPatterns = "/*",		//过滤器的拦截路径
		// REQUEST：当用户直接访问页面时，Web容器将会调用过滤器。如果目标资源是通过RequestDispatcher的include()或forward()方法访问时，那么该过滤器就不会被调用。
		// INCLUDE：如果目标资源是通过RequestDispatcher的include()方法访问时，那么该过滤器将被调用。除此之外，该过滤器不会被调用。
		// FORWARD：如果目标资源是通过RequestDispatcher的forward()方法访问时，那么该过滤器将被调用，除此之外，该过滤器不会被调用。
		// ERROR：如果目标资源是通过声明式异常处理机制调用时，那么该过滤器将被调用。除此之外，过滤器不会被调用。
		dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST},
		initParams = {@WebInitParam(name = "user", value = "root")}		//初始化参数,如无必要可不配置
		)
public class CustomFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String user = filterConfig.getInitParameter("user");
		System.out.println(user);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("进行过滤操作......");
		chain.doFilter(request, response);	//放行
	}

	@Override
	public void destroy() {

	}
}
