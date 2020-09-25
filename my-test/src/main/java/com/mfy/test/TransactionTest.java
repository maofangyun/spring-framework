package com.mfy.test;

import com.mfy.test.tx.TransConfig;
import com.mfy.test.tx.UserBalanceService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class TransactionTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(TransConfig.class);
		ac.refresh();
		UserBalanceService ubs = ac.getBean(UserBalanceService.class);
		ubs.test();
		ubs.insert("张三","1000");
	}
}
