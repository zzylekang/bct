package com.zzy.dev.comm.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class ServletRequestListenerImpl implements ServletRequestListener{

	public void requestDestroyed(ServletRequestEvent event) {
		System.out.println("request destroyed, request object hashCode is:" + event.getServletRequest().hashCode());
	}

	public void requestInitialized(ServletRequestEvent event) {
		System.out.println("request initialized, request object hashCode is:" + event.getServletRequest().hashCode());
	}

}
