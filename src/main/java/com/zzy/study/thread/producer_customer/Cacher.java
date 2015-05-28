package com.zzy.study.thread.producer_customer;

public class Cacher {
	private String name = "unkown";
	private String sex = "unkown";
	private boolean bFull = false;//true:��
	
	public synchronized void push(String pName, String pSex){
		
		if (bFull) {//��
			try{
				System.out.println("push wait.");
				this.wait();
			}catch(Exception e){
				e.printStackTrace();
			}
		} //else {//��
			//System.out.println("push start.....");
			this.name = pName;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.sex = pSex;
			bFull = true;//��Ϊ��
			this.notify();
			//System.out.println("push end.....");
		//}

		
	}
	
	public synchronized void get(){
		/**
		 * ע��������߼�һ������д��if....else,
		 * д��if...else�Ǵ���ġ�
		 * �߳��ڵȴ������������Ӧ�ü���ִ�С�
		 * ���д��if...else����ִ����wait()��,�����ͽ����ˣ�
		 * ProducerThread.run()�еĽ���push()�ͻ����
		 */
		
		if (bFull == false) {//��
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
			bFull = false;//��Ϊ��
			this.notify();
			//System.out.println("get end.");
		//}
	}
}
