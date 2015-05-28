package com.zzy.study.timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ReplyListener implements ServletContextListener{
	private ReplyTimer rt = null;
	public void contextDestroyed(ServletContextEvent event) {
		String status = "[SYS] SMS reply listener stop .";
		event.getServletContext().log(status);
		System.out.println(status);
		if (rt != null) {
			rt.stop();
		}
	}

	public void contextInitialized(ServletContextEvent event) {
		String status = "[SYS] SMS reply listener start .";
		event.getServletContext().log(status);
		System.out.println(status);
		rt = new ReplyTimer(1);
		rt.start();
	}

}
