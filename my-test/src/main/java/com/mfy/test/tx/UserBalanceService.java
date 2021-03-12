package com.mfy.test.tx;

import com.mfy.test.ioc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserBalanceService {

	@Autowired
	private BalanceService balanceService;

	@Autowired
	private UserService userService;

	/**
	 * 这里的自身循环依赖,会导致报错,原因是@Async注解并没有对代理对象的循环依赖情况进行考虑,
	 * 所以@Autowired的代理对象和@Service的代理对象不相同,这是不被运行的
	 * */
	//@Autowired
	//private UserBalanceService userBalanceService;

	/**
	 * NESTED传播机制,如果子事务异常,父级可以捕获它的异常而不进行回滚,正常提交;
	 * 但是如果父级异常,子事务必然回滚,这就是和REQUIRES_NEW的区别
	 * */
	@Transactional
	//@Async
	public void insert(User user,String acount) throws SQLException {
		// 事务同步处理器只能在这里注册,因为synchronizations只有在事务开启后,才会初始化
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationHandler());
		userService.insertUser(user);
		try {
			balanceService.insertBalance(user.getName(), acount);
		}
		catch (RuntimeException e){
			e.printStackTrace();
		}
	}

}
