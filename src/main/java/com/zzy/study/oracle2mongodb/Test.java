package com.zzy.study.oracle2mongodb;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Test {
	public static void main(String[] args) throws JSONException {
		Db dbserv = null;
		try {
			dbserv = new Db("test", "test");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject();
		json.put("name", "zhangzy");
		json.put("age", 20);
		
		JSONObject lan = new JSONObject();
		lan.put("x", 12);
		lan.put("y", 18);
		json.put("info", lan);
		
		JSONArray arr = new JSONArray();
		arr.put("1");
		arr.put("2");
		arr.put("3");
		json.put("arr", arr);

		dbserv.insert(json.toString());//≤Â»Î
		
		List<DBObject> list = dbserv.query(json.toString());
		
		//[0]	BasicDBObject  (id=52)	
			//hashMap
			//array value	BasicDBList  (id=121)	
			//BasicDBObject

		//System.out.println(json.toString());
		Test test = new Test();
		test.parseList(list);
	}
	
	public void parseList(List<DBObject> list) {
		Iterator<DBObject> it = list.iterator();
		while(it.hasNext()) {
			parseDBObject(it.next());
		}
	}
	private void parseDBObject(DBObject dbObject) {
		Set<String> sets = dbObject.keySet();
		Iterator it = sets.iterator();
		String key = null;
		Object o = null;
		while(it.hasNext()) {
			key = (String)it.next();
			System.out.print(key + ":\t");
			o = dbObject.get(key);
			if (o instanceof DBObject) {
				parseDBObject((DBObject)o);
			} else if (o instanceof BasicDBList) {
				BasicDBList arr = (BasicDBList)o;
				for (int i = 0; i < arr.size(); i++) {
					System.out.print(arr.get(i));
				}
			} else {
				System.out.print(o);
			}
			
			System.out.println();
			
		}
	}
}
