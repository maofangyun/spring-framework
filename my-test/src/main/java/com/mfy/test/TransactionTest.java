package com.mfy.test;

import com.mfy.test.ioc.User;
import com.mfy.test.tx.TransConfig;
import com.mfy.test.tx.UserBalanceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class TransactionTest {

	public static void main(String[] args) throws SQLException {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(TransConfig.class);
		ac.refresh();
		UserBalanceService ubs = ac.getBean(UserBalanceService.class);
		User user = new User("李四","男");
		ubs.insert(user,"1000");
		System.out.println("main线程运行结束");
	}
}
