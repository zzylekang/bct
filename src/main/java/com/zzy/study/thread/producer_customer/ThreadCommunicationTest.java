package com.zzy.study.thread.producer_customer;

public class ThreadCommunicationTest {
	
	public static void main(String [] args) {
		Cacher c = new Cacher();
		ProducerThread pt = new ProducerThread(c);
		new Thread(pt).start();
		
		CustomerThread ct = new CustomerThread(c);
		new Thread(ct).start();
	}

}
