package com.zzy.study.thread;

public class SubThread implements Runnable{
	Bussiness buzz = null;
	Thread thread = null;
	public DiaoDu threadLock = null;
	SubThread(Bussiness buzz){
		this.buzz = buzz;
		//this.thread = thread;
	}

	public void run() {
		System.out.println("子线程 开始");
		buzz.loop(5, "子线程");
		System.out.println("子线程 结束");

	}

}
