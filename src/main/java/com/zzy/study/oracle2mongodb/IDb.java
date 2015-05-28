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
	 * 获得数据库的连接池
	 * @return
	 */
	//public Mongo getConnectionPool(String ip, Integer port) 
			//throws UnknownHostException, MongoException;
	
	/**
	 * 获得数据库的连接池(授权方式)
	 * @return
	 */
	//public Mongo getConnectionPool()
			//throws UnknownHostException, MongoException ;
	
	/**
	 * 获得指定的Db
	 * @return
	 */
	//public DB getDb(String dbName, String username, String password);
	
	/**
	 * 获得指定的Db
	 * @return
	 */
	//public DB getDb(String dbName);
	
	/**
	 * 获得指定的数据集（Collection）
	 */
	public DBCollection getCollection(DB db, String collectionName) ;
	
	/**
	 * 向数据集中插入文档
	 * @param doc
	 * @return
	 */
	public boolean insert(String doc);
	
	/**
	 * 根据指定的查询串，查询document
	 */
	public List<DBObject> query(String json);
}
