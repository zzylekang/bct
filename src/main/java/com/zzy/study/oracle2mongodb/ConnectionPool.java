package com.zzy.study.oracle2mongodb;
import java.net.UnknownHostException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;
/**
 * 连接池
 * @author Administrator
 *
 */
public class ConnectionPool {

	private static String ip = "localhost";
	private static Integer port = 27017;
	private static Mongo m = null;//单实例,相当于连接池,这个连接池并没有指向具体的DB，多个线程共享， 类级别
	
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
