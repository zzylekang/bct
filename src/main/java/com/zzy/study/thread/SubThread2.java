package com.zzy.study.thread;

public class SubThread2 implements Runnable{
	Bussiness buzz = null;
	Thread thread = null;
	public DiaoDu threadLock = null;
	SubThread2(Bussiness buzz){
		this.buzz = buzz;
		//this.thread = thread;
	}

	public void run() {
		System.out.println("子线程2 开始");
		buzz.loop3(2, "子线程2");
		System.out.println("子线程2 结束");

	}

}
