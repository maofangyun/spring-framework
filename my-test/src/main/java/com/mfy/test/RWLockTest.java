package com.mfy.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockTest {
	public static void main(String[] args) {
		ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
		ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
		Thread t1 = new Thread(() -> {
			writeLock.lock();
			readLock.lock();
			writeLock.lock();
			writeLock.unlock();
			readLock.unlock();
			writeLock.unlock();
		});
		t1.start();
	}
}
