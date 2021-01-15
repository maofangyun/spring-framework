package com.mfy.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockTest {
	public static void main(String[] args) {
		ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		Thread t1 = new Thread(() -> {
			rwLock.readLock().lock();
			System.out.println("t1获取读锁");
			rwLock.writeLock().lock();
			System.out.println("t1获取写锁");
			rwLock.writeLock().unlock();
			System.out.println("t1释放写锁");
			rwLock.readLock().unlock();
			System.out.println("t1释放读锁");
		});
		t1.start();
	}
}
