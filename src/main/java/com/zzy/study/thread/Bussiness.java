package com.zzy.study.thread;

public class Bussiness{
	int counter;
	String threadName = null;
	Bussiness(){}
	Bussiness(int counter, String threadName){
		this.counter = counter;
		this.threadName = threadName;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
	public boolean threadLock = true; 
	public boolean threadLock2 = true; 
	public boolean threadLock3 = true; 
	
	//threadLock 线程控制变量，为true时，loop执行
	public synchronized void loop(int counter, String threadName) {
		System.out.println(threadName + " loop 开始");
		while (threadLock == false) {
			try {
				System.out.println(threadName + " loop wait 开始");
				this.wait();
				System.out.println(threadName + " loop wait 结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for(int i=1; i <= counter; i++) {
			System.out.println(threadName + ",loop第" + i + "次循环");
		}
		threadLock = false;
		//threadLock2 = true;
		//threadLock3 = false;
		this.notify();
		//Thread.currentThread().notify();
		System.out.println(threadName + " loop 结束");
	}
	
	public synchronized void loop2(int counter, String threadName) {
		System.out.println(threadName + " loop2 开始");
		while (threadLock2 == false) {
			try {
				System.out.println(threadName + " loop2 wait 开始");
				this.wait();
				System.out.println(threadName + " loop2 wait 结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=1; i <= counter; i++) {
			System.out.println(threadName + ",loop2第" + i + "次循环");
		}
		//threadLock = false;
		threadLock2 = false;
		//threadLock3 = true;
		this.notify();
		System.out.println(threadName + " loop2 结束");

	}
	public synchronized void loop3(int counter, String threadName) {
		if (threadLock3 == false) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=1; i <= counter; i++) {
			System.out.println(threadName + ",loop3第" + i + "次循环");
		}
		//threadLock = false;
		//threadLock2 = false;
		threadLock3 = false;
		this.notify();

	}
}
