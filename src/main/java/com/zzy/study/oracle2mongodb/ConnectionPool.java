package com.zzy.study.oracle2mongodb;
import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
/**
 * ���ӳ�
 * @author Administrator
 *
 */
public class ConnectionPool {

	private static String ip = "localhost";
	private static Integer port = 27017;
	private static Mongo m = null;//��ʵ��,�൱�����ӳ�,������ӳز�û��ָ������DB������̹߳��� �༶��
	
	private ConnectionPool(){
		
	}
	
	public static Mongo getConnectionPool(String ip, Integer port) 
			throws UnknownHostException, MongoException {
		
		if (m != null) {
			return m;
		}
		m = new Mongo( ip , port );
		
		return m;
	}
	
	public static Mongo getConnectionPool() 
			throws UnknownHostException, MongoException {
		
		if (m != null) {
			return m;
		}
		m = new Mongo( ConnectionPool.ip , ConnectionPool.port );
		
		return m;
	}
}
