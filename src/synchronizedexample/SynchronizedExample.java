package org.hxc.multithreading.synchronizedexample;

public class SynchronizedExample {
	
	private static final long SLEEP_INTERVAL_MS = 5000;
	
	//��synchronized�ؼ��� -- ֻ��һ���߳���ͬʱ��������һ��method
	public synchronized void foo() {
		System.out.println("Inside foo");
		try {
			Thread.sleep(SLEEP_INTERVAL_MS);
		}catch(InterruptedException e) {
			//
		}
	} 
	
	public synchronized void bar() {System.out.println("Inside bar");}
	
	//��synchronized�ؼ��� -- ����ֱ�ӷ��ʵ���
	public void foobar() {System.out.println("Inside foobar");}
	
	public static void main(String[] args) throws InterruptedException {
		final SynchronizedExample s = new SynchronizedExample();
		
		Thread T1 = new Thread(new Runnable() {
			@Override
			public void run() {
				s.foo();
			}
		});
		
		Thread T2 = new Thread(new Runnable() {
			@Override
			public void run() {
				s.bar();
			}
		});	

		Thread T3 = new Thread(new Runnable() {
			@Override
			public void run() {
				s.foobar();
			}
		});
		
		T1.start();
		T2.start();
		T3.start();
		
		T1.join();
		T2.join();
		T3.join();
	}
}
