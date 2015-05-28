package com.zzy.study.oracle2mongodb;
import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
public interface IDb {
	/**
	 * ������ݿ�����ӳ�
	 * @return
	 */
	//public Mongo getConnectionPool(String ip, Integer port) 
			//throws UnknownHostException, MongoException;
	
	/**
	 * ������ݿ�����ӳ�(��Ȩ��ʽ)
	 * @return
	 */
	//public Mongo getConnectionPool()
			//throws UnknownHostException, MongoException ;
	
	/**
	 * ���ָ����Db
	 * @return
	 */
	//public DB getDb(String dbName, String username, String password);
	
	/**
	 * ���ָ����Db
	 * @return
	 */
	//public DB getDb(String dbName);
	
	/**
	 * ���ָ�������ݼ���Collection��
	 */
	public DBCollection getCollection(DB db, String collectionName) ;
	
	/**
	 * �����ݼ��в����ĵ�
	 * @param doc
	 * @return
	 */
	public boolean insert(String doc);
	
	/**
	 * ����ָ���Ĳ�ѯ������ѯdocument
	 */
	public List<DBObject> query(String json);
}
