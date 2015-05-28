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
	
	//threadLock �߳̿��Ʊ�����Ϊtrueʱ��loopִ��
	public synchronized void loop(int counter, String threadName) {
		System.out.println(threadName + " loop ��ʼ");
		while (threadLock == false) {
			try {
				System.out.println(threadName + " loop wait ��ʼ");
				this.wait();
				System.out.println(threadName + " loop wait ����");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for(int i=1; i <= counter; i++) {
			System.out.println(threadName + ",loop��" + i + "��ѭ��");
		}
		threadLock = false;
		//threadLock2 = true;
		//threadLock3 = false;
		this.notify();
		//Thread.currentThread().notify();
		System.out.println(threadName + " loop ����");
	}
	
	public synchronized void loop2(int counter, String threadName) {
		System.out.println(threadName + " loop2 ��ʼ");
		while (threadLock2 == false) {
			try {
				System.out.println(threadName + " loop2 wait ��ʼ");
				this.wait();
				System.out.println(threadName + " loop2 wait ����");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=1; i <= counter; i++) {
			System.out.println(threadName + ",loop2��" + i + "��ѭ��");
		}
		//threadLock = false;
		threadLock2 = false;
		//threadLock3 = true;
		this.notify();
		System.out.println(threadName + " loop2 ����");

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
			System.out.println(threadName + ",loop3��" + i + "��ѭ��");
		}
		//threadLock = false;
		//threadLock2 = false;
		threadLock3 = false;
		this.notify();

	}
}
