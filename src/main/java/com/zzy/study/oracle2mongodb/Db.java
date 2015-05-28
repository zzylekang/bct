package com.zzy.study.oracle2mongodb;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * ���ݿ���ʶ���(DAO)
 * @author Administrator
 *
 */
public class Db implements IDb{
	
	private DB connection = null;//���ݿ�,�����̳߳���,ָ�����ݿ�ľ�������ӣ�ʵ����
	private  Mongo connPool = null;//��ʵ��,�൱�����ӳ�,������ӳز�û��ָ������DB������̹߳��� �༶��
	private DBCollection coll = null;//��
	
	public Db(String dbName, String collName) throws Exception{
		connPool = ConnectionPool.getConnectionPool();
		connection = connPool.getDB(dbName);
		getCollection(connection, collName);
	}
	
	public Db(String dbName, String username, String password, String collName) throws Exception{
		connPool = ConnectionPool.getConnectionPool();
		connection = connPool.getDB(dbName);
		
		if (!connection.authenticate(username, password.toCharArray())) {
			connection = null;
			throw new Exception("username or password is wrong!connection failed!");
		}
		
		getCollection(connection, collName);
	}

	public DBCollection getCollection(DB db, String collectionName) {
		coll = db.getCollection(collectionName);
		return coll;
	}

	public boolean insert(String doc) {
		BasicDBObject bo = (BasicDBObject)JSON.parse(doc);
		coll.insert(bo);
		return true;
	}

	public List<DBObject> query(String json) {
		BasicDBObject bo = (BasicDBObject)JSON.parse(json);
		DBCursor cur = coll.find(bo);
		List<DBObject> li = new ArrayList<DBObject>();
		while(cur.hasNext()) {
			li.add(cur.next());
		}
		cur.close();
		return li;
	}
	
}
