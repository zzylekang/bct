package com.zzy.study.thread;

public class DiaoDu {
	private boolean threadLock = true;//线程调度
	public void setThreadLock(boolean v){
		this.threadLock = v;
	}
	public boolean getThreadLock(){
		return this.threadLock;
	}
}
