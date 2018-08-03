package org.hxc.multithreading.synchronizedexample;

//与SynchronizedExample.java的区别和关系，一同展现了synchronized关键字的几种使用方法
public class SynchronizedExample2 {
	
	private static final long SLEEP_INTERVAL_MS = 5000;
	
	private final Object lock = new Object();
	
	//修改后的synchronized方法，与1中不同
	public void foo() {
		synchronized (lock) {
			System.out.println("Inside foo");
			try {
				Thread.sleep(SLEEP_INTERVAL_MS);
			}catch(InterruptedException e) {
				//
			}
		}
	} 
	
	public void bar() {
		synchronized (this) {
			//如果同步在lock上，运行结果会延时五秒，现在同步在不同的锁上，不会延时
			System.out.println("Inside bar");
		}
		//不在synchronized语句块中的不受影响并行执行
		System.out.println("Bar not synchronized");
	}
	
	//无synchronized关键字 -- 可以直接访问调用
	public void foobar() {System.out.println("Inside foobar");}
	
	public static void main(String[] args) throws InterruptedException {
		final SynchronizedExample2 s = new SynchronizedExample2();
		
		Thread T1 = new Thread((Runnable) () -> {s.foo();});
		Thread T2 = new Thread((Runnable) () -> {s.bar();});
		Thread T3 = new Thread((Runnable) () -> {s.foobar();});
		
		T1.start();
		T2.start();
		T3.start();
		
		T1.join();
		T2.join();
		T3.join();
	}
}