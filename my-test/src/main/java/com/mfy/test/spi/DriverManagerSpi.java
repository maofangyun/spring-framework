package com.mfy.test.spi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 为什么spi违反了双亲委派,以及违反的原因
 * */
public class DriverManagerSpi {

	/**
	 * <h>原因:<h/>
	 * java.sql.Driver这个东西。JDK只能提供一个规范接口，而不能提供实现。
	 * 提供实现的是实际的数据库提供商，提供商的库总不能放JDK目录里吧。
	 * Java从1.6搞出了SPI就是为了优雅的解决这类问题——JDK提供接口，供应商提供服务。<p/>
	 *
	 * 编程人员编码时面向接口编程，然后JDK能够自动找到合适的实现，岂不是很爽？但是便利的同时也带来了困扰。
	 * 提供商提供的类不能放JDK里的lib目录下，所以也就没法用BootstrapClassLoader加载了。<br/>
	 * 所以当你代码写了<br/>
	 * Class clz = Class.forName("java.sql.Driver");<br/>
	 * Driver d = (Driver)clz.newInstance();<br/>
	 * 这个代码会用Bootstrap ClassLoader尝试去加载.问题是java.sql.Driver是个接口，无法真的实例化，就报错了。<p/>
	 *
	 * 没有SPI时，你可以现在classpath里加一个mysql-connector-java.jar，然后这样写<br/>
	 * Class clz = Class.forName("com.mysql.jdbc.Driver");<br/>
	 * Driver d = (Driver) clz.newInstance();<br/>
	 * 这就没问题了，这里用了Application Classloader加载了mysql-connector-java.jar的com.mysql.jdbc.Driver。<br/>
	 * 问题是你硬编码了一定要加载"com.mysql.jdbc.Driver"，不是很优雅，不能实现“用接口编程，自动实例化真的实现“的这种编码形式。
	 * */
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://39.98.237.119:3307/detection", "root", "123456");
			System.out.println(connection.getClass().getClassLoader());
			// Connection自己是被Bootstrap ClassLoader加载的
			System.out.println(Connection.class.getClassLoader());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
