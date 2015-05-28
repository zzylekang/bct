package com.zzy.dev.project.web;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zzy.dev.project.web.job.PrepaidFeeJob;

public class JobLoadListener  implements ServletContextListener{
	private java.util.Timer prePaidFeetimer = null;
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("服务器关闭，预计付费的定时任务可能受到影响");
		prePaidFeetimer.cancel();
	}
	public void contextInitialized(ServletContextEvent sce) {
		//定时任务开始
		prePaidFeetimer = new java.util.Timer(true);
		
		//每天晚上23点执行
		Calendar c  = Calendar.getInstance();
		Date systime = c.getTime(); //系统当前时间
		
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 15);
		c.set(Calendar.SECOND, 0);
		Date firstTime = c.getTime();
		prePaidFeetimer.scheduleAtFixedRate(new PrepaidFeeJob(), firstTime, 1000*60*60*24);//每天晚上23点15分执行
		System.out.println("服务器启动，预计付费的定时任务被加载");
	}
}
