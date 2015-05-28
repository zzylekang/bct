package com.zzy.study.thread.producer_customer;

public class ProducerThread implements Runnable {
	Cacher c = null;
	ProducerThread(Cacher c) {
		this.c = c;
	}
	public void run() {
		int i = 0;
		while(true) {
			
			if (i == 0) {
				//System.out.println("i: " + 0);
				c.push("zhangsan", "male");
			} else {
				//System.out.println("i: " + 1);
				c.push("gaohong", "female");
			}
			i = (i + 1)%2;
			
		}

	}

}
