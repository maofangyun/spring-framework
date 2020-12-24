package com.mfy.test.tx;

import org.springframework.transaction.support.TransactionSynchronization;

public class TransactionSynchronizationHandler implements TransactionSynchronization {

	@Override
	public void suspend() {
		System.out.println("-------挂起当前事务-------");
	}

	@Override
	public void resume() {
		System.out.println("-------恢复当前事务-------");
	}

	@Override
	public void beforeCommit(boolean readOnly) {
		System.out.println("-------beforeCommit-------");
	}

	@Override
	public void beforeCompletion() {
		System.out.println("-------beforeCompletion-------");
	}

	@Override
	public void afterCommit() {
		System.out.println("-------afterCommit-------");
	}

	@Override
	public void afterCompletion(int status) {
		System.out.println("-------afterCompletion:"+status+"-------");
	}
}
