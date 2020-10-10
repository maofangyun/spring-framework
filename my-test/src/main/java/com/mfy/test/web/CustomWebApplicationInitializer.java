//package com.mfy.test.web;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRegistration;
//
///**
// * 不需要加@Component注解，因为tomcat启动时，会寻找实现了WebApplicationInitializer接口的类，并实例化
// * @link SpringServletContainerInitializer
// * @HandlesTypes(WebApplicationInitializer.class)
// * */
//public class CustomWebApplicationInitializer implements WebApplicationInitializer {
//	@Override
//	public void onStartup(ServletContext servletContext) {
//		//web.xml 中的 listener 初始化
//		AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
//		//listener中的加载spring.xml的配置文件内容
//		ac.register(MvcContainer.class);
//		//用于初始化spring配置文件 web项目可用可不用
//		ac.refresh();
//		//创建servlet容器
//		DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
//		//创建前端控制器去名字为dispatcherServlet  <servlet>标签
//		ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
//		//随服务器启动加载这个servlet
//		registration.setLoadOnStartup(1);
//		//<servlet-mapping>里面的url-patten
//		registration.addMapping("/");
//	}
//}