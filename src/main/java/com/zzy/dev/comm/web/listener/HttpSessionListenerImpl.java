package com.zzy.dev.comm.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionListenerImpl implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("session created, session object hashCode is:" + event.getSession().hashCode());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("session destroyed, session object hashCode is:" + event.getSession().hashCode());
	}

}
