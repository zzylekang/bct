package com.zzy.study.thread;

import java.util.LinkedList;

import cn.itcast.heima2.TraditionalThreadCommunication;

/**
 * ����ʵ�����¹���:���߳�ѭ��5��,�������߳�ѭ��10��,�����ֻص����߳�ѭ��5��,�����ٻص����߳�ѭ��10��,���ѭ��5��.
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

		System.out.println("���߳� ��ʼ");
		new ThreadCommunication().method1(buzz);//���߳�
		System.out.println("���߳� ����");
	}
	
	private static int counter = 1;
	
	////���߳�
	public void method1(Bussiness buzz) {
		for (int i=1; i <= 5; i++) {

			System.out.println("��" + i + "��ѭ��");
			
			
			buzz.threadLock = true;
			buzz.threadLock2 = false;
			buzz.threadLock3 = false;
			
			SubThread myrun = new SubThread(buzz);
			Thread subThread1 = new Thread(myrun);//���߳�
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
			buzz.loop2(10, "���߳�");
			//buzz.loop3(10, "���߳�2");
			while (true) {
				int j = 0;
				//System.out.println("while " + ++j);
				if (buzz.threadLock2 == false) {
					buzz.threadLock3 = true;
					
					break;
				} 
			}
			SubThread2 myrun2 = new SubThread2(buzz);
			Thread subThread2 = new Thread(myrun2);//���߳�
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

