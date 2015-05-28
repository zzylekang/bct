package com.zzy.dev.project.web;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zzy.dev.project.web.job.PrepaidFeeJob;

public class JobLoadListener  implements ServletContextListener{
	private java.util.Timer prePaidFeetimer = null;
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("�������رգ�Ԥ�Ƹ��ѵĶ�ʱ��������ܵ�Ӱ��");
		prePaidFeetimer.cancel();
	}
	public void contextInitialized(ServletContextEvent sce) {
		//��ʱ����ʼ
		prePaidFeetimer = new java.util.Timer(true);
		
		//ÿ������23��ִ��
		Calendar c  = Calendar.getInstance();
		Date systime = c.getTime(); //ϵͳ��ǰʱ��
		
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 15);
		c.set(Calendar.SECOND, 0);
		Date firstTime = c.getTime();
		prePaidFeetimer.scheduleAtFixedRate(new PrepaidFeeJob(), firstTime, 1000*60*60*24);//ÿ������23��15��ִ��
		System.out.println("������������Ԥ�Ƹ��ѵĶ�ʱ���񱻼���");
	}
}
