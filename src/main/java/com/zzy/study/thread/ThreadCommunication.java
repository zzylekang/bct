package com.zzy.study.thread;

import java.util.LinkedList;

import cn.itcast.heima2.TraditionalThreadCommunication;

/**
 * 该类实现如下功能:子线程循环5次,接着主线程循环10次,接着又回到子线程循环5次,接着再回到主线程循环10次,如此循环5次.
 * 
 * @author Administrator
 *
 */
public class ThreadCommunication  {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bussiness buzz = new Bussiness();

		System.out.println("主线程 开始");
		new ThreadCommunication().method1(buzz);//主线程
		System.out.println("主线程 结束");
	}
	
	private static int counter = 1;
	
	////主线程
	public void method1(Bussiness buzz) {
		for (int i=1; i <= 5; i++) {

			System.out.println("第" + i + "次循环");
			
			
			buzz.threadLock = true;
			buzz.threadLock2 = false;
			buzz.threadLock3 = false;
			
			SubThread myrun = new SubThread(buzz);
			Thread subThread1 = new Thread(myrun);//子线程
			subThread1.start();
			
			while (true) {
				int j = 0;
				//System.out.println("while " + ++j);
				if (buzz.threadLock == false) {
					buzz.threadLock2 = true;
					
					break;
				} 
			}
			
			//Thread.currentThread().wait();
			buzz.loop2(10, "主线程");
			//buzz.loop3(10, "主线程2");
			while (true) {
				int j = 0;
				//System.out.println("while " + ++j);
				if (buzz.threadLock2 == false) {
					buzz.threadLock3 = true;
					
					break;
				} 
			}
			SubThread2 myrun2 = new SubThread2(buzz);
			Thread subThread2 = new Thread(myrun2);//子线程
			subThread2.start();
			while (true) {
				int j = 0;
				//System.out.println("while " + ++j);
				if (buzz.threadLock3 == false) {
					break;
				} 
			}
		}

	}
}

