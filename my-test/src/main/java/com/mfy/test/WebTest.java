package com.mfy.test;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class WebTest {

	public static void main(String[] args) {
		String userDir = System.getProperty("user.dir")+File.separatorChar; // 项目目录
		String tomcatBaseDir = userDir + File.separatorChar + "tomcat";
		String webappDir = userDir +File.separatorChar+ "build";

		Tomcat tomcatServer = new Tomcat();
		tomcatServer.setBaseDir(tomcatBaseDir);

		Connector connector = new Connector();
		connector.setPort(9090);
		connector.setURIEncoding("UTF-8");
		tomcatServer.getService().addConnector(connector);

		tomcatServer.addWebapp("/", webappDir);
		try {
			tomcatServer.start();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
		// 异步等待请求执行
		tomcatServer.getServer().await();
	}
}
