package com.zzy.study.timer;

import java.util.Date;
import java.util.Timer;

public class ReplyTimer {
	private final Timer timer = new Timer();
	private final int min;
	public ReplyTimer(int minutes) {
		min = minutes;
	}
	
	public void start() {
		Date date = new Date();
		timer.schedule(new ReplyTask(), date, min*60*1000);
		
	}
	public void stop() {
		timer.cancel();
	}
}
