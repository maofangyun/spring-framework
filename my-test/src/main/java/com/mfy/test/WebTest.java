package com.mfy.test;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class WebTest {

	public static void main(String[] args) {
		// 创建Tomcat容器
		Tomcat tomcatServer = new Tomcat();
		// 端口号设置
		tomcatServer.setPort(9090);
		// 读取项目路径 加载静态资源
		StandardContext ctx = (StandardContext) tomcatServer.addWebapp("/", new File("my-test/src/main").getAbsolutePath());
		// 禁止重新载入
		ctx.setReloadable(false);
		// class文件读取地址
		File additionWebInfClasses = new File("my-test/build/classes");
		// 创建WebRoot
		WebResourceRoot resources = new StandardRoot(ctx);
		// tomcat内部读取Class执行
		resources.addPreResources(
				new DirResourceSet(resources, "/my-test/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		try {
			tomcatServer.start();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
		// 异步等待请求执行
		tomcatServer.getServer().await();
	}
}
