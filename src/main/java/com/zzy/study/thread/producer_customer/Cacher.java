package com.zzy.study.thread.producer_customer;

public class Cacher {
	private String name = "unkown";
	private String sex = "unkown";
	private boolean bFull = false;//true:满
	
	public synchronized void push(String pName, String pSex){
		
		if (bFull) {//满
			try{
				System.out.println("push wait.");
				this.wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		} //else {//空
			//System.out.println("push start.....");
			this.name = pName;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.sex = pSex;
			bFull = true;//置为满
			this.notify();
			//System.out.println("push end.....");
		//}

		
	}
	
	public synchronized void get(){
		/**
		 * 注意这里的逻辑一定不能写成if....else,
		 * 写成if...else是错误的。
		 * 线程在等待后，如果被唤醒应该继续执行。
		 * 如果写成if...else，在执行完wait()后,方法就结束了，
		 * ProducerThread.run()中的交替push()就会错误
		 */
		
		if (bFull == false) {//空
			System.out.println("get wait.");
			try{
				this.wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		} //else {
			//System.out.println("get start.");
			System.out.print(this.name);
			System.out.println(" : " + this.sex);
			bFull = false;//置为空
			this.notify();
			//System.out.println("get end.");
		//}
	}
}
