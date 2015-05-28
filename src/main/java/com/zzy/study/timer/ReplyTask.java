package com.zzy.study.timer;

import java.util.TimerTask;

public class ReplyTask extends TimerTask{

	@Override
	public void run() {
		this.doSomething();
	}
	
	public void doSomething() {
		System.out.println("[SYS] SMS reply listener running ");
	}
}
