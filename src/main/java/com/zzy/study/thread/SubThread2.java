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
		System.out.println("���߳�2 ��ʼ");
		buzz.loop3(2, "���߳�2");
		System.out.println("���߳�2 ����");

	}

}
