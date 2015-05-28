package com.zzy.study.thread.producer_customer;

public class CustomerThread implements Runnable{
	Cacher c = null;
	CustomerThread(Cacher c) {
		this.c = c;
	}
	public void run() {
		while(true) {
			c.get();
		}
	}
}
