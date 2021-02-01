package com.mfy.test.spi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * spi违反了双亲委派的原因分析
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
			/**
			 * 	在调用DriverManager.getConnection()时,会触发DriverManager的类加载流程,在初始化时,会执行如下代码
			 * 	static {
			 * 		loadInitialDrivers();
			 *  }
			 *  loadInitialDrivers()的代码流程:
			 *  	1. 找出所有加载的jar包下路径为META-INF.services/java.sql.Driver的文件
			 *  	2. 读取文件中的全限定类名称,放入一个集合
			 *  	3. 遍历集合,通过Class.forName()得到Class对象,再通过class.newInstance()得到实例对象(驱动)
			 *  	4. 实例化过程中,会将实例对象(驱动)注册到registeredDrivers中
			 * 	loadInitialDrivers()会调用如下代码:
			 * 		指定了要加载Driver.class,同时使用Thread.currentThread().getContextClassLoader()的类加载器(即AppClassLoader)
			 * 		ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
			 * 		Iterator<Driver> driversIterator = loadedDrivers.iterator();
			 * 	    while(driversIterator.hasNext()) {
			 * 	    	// 执行Driver子类的类加载流程,c = Class.forName(cn, false, loader),得到Class对象
			 * 	    	// 其中cn="com.mysql.cj.jdbc.Driver",false表示只进行类的加载,不进行初始化流程,loader=AppClassLoader,
			 * 	    	// 后续马上执行c.newInstance(),触发类的初始化流程,以mysql举例:
			 * 	    	//     static {
			 *         	//			try {
			 *          //				java.sql.DriverManager.registerDriver(new Driver());
			 *         	//			} catch (SQLException E) {
		 	 *          //   			throw new RuntimeException("Can't register driver!");
			 *         	//			}
			 *     		//	}
			 *     		// 	通过DriverManager.registerDriver()方法,将驱动注册到registeredDrivers中
			 *     		以上的这些操作,全部都在下面的一行代码中完成
			 *          driversIterator.next();
			 *     }
			 * 	这段代码,通过遍历的方式,将扫描项目加载所有jar的META-INF.services目录下属于Driver.Class的子类(SPI机制),
			 *	并创建出实例对象,完成getConnection()方法的调用
			 * */
			Connection connection = DriverManager.getConnection("jdbc:mysql://39.98.237.119:3307/detection", "root", "123456");
			System.out.println(connection.getClass().getClassLoader());
			// Connection自己是被Bootstrap ClassLoader加载的
			System.out.println(Connection.class.getClassLoader());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
