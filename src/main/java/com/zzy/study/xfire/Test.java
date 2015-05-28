package com.zzy.study.xfire;

import java.net.MalformedURLException;
import java.net.URL;


import org.apache.commons.httpclient.params.HttpClientParams;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

public class Test {
	public static void main(String [] args){
		Test.testBookService2();
	}
	
	public static void testBookService() {

		XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
		URL url = null;
		Client client = null;
		BookService m_hil_msg = null;
		try {
			url = new URL("http://localhost:7001/bct/services/BookService");
			client = new Client(url, BookService.class);
			HttpClientParams params = new HttpClientParams();
			params.setParameter(HttpClientParams.USE_EXPECT_CONTINUE, Boolean.FALSE);
			params.setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, 1000);//连接超时
			params.setParameter(HttpClientParams.SO_TIMEOUT, 1000);//应答超时
			client.setProperty(CommonsHttpMessageSender.HTTP_CLIENT_PARAMS, params);
			m_hil_msg = (BookService)factory.create(client);
			
			System.out.println(m_hil_msg.getBookNames());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	public static void testBookService2() {

		
		String url = null;
		BookService m_hil_msg = null;
		try {
			url = "http://localhost:7001/bct/services/BookService";
			Service srvcModel = new ObjectServiceFactory().create(BookService.class); 
			XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
			m_hil_msg = (BookService) factory.create(srvcModel, url);
			
			System.out.println(m_hil_msg.getBookNames());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
}
