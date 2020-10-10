package com.mfy.test;

public class ThreadTest {
	public static void main(String[] args) {
		Object object =  new Object();

		Thread t1 = new Thread(() -> {
			synchronized (object){
				for(int i=1; i<27; i++){
						object.notify();
						System.out.print(i);
						try {
							object.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			synchronized (object){
				for(char i=65; i<91; i++){
					object.notify();
					System.out.print(i);
					try {
						if( i < 90)
							object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t2.start();

	}
}
