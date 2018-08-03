package org.hxc.multithreading.synchronizedexample;

//��SynchronizedExample.java������͹�ϵ��һͬչ����synchronized�ؼ��ֵļ���ʹ�÷���
public class SynchronizedExample2 {
	
	private static final long SLEEP_INTERVAL_MS = 5000;
	
	private final Object lock = new Object();
	
	//�޸ĺ��synchronized��������1�в�ͬ
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
			//���ͬ����lock�ϣ����н������ʱ���룬����ͬ���ڲ�ͬ�����ϣ�������ʱ
			System.out.println("Inside bar");
		}
		//����synchronized�����еĲ���Ӱ�첢��ִ��
		System.out.println("Bar not synchronized");
	}
	
	//��synchronized�ؼ��� -- ����ֱ�ӷ��ʵ���
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