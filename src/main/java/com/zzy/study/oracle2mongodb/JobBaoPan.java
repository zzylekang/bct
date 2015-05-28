package com.zzy.study.oracle2mongodb;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

/**
 * ���ױ�������(TR_OFFER��):oracle->mongoDB
 * @author Administrator
 *
 */
public class JobBaoPan implements Job {

	public void execute(JobExecutionContext jctx) throws JobExecutionException {/*
		Scheduler sc = jctx.getScheduler();//���Schedulerʵ��
		
		TR_OFFER tr = new TR_OFFER();//���ݿ��DAO
		
		List list = null;
		try {
			list = tr.getListStr("and 1 = 1");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//���mongodbʵ��
		Db dbserv = null;
		try {
			dbserv = new Db("test", "TR_OFFER");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//oracle��õ����ݣ����뵽mongodb��
		for (int i = 0; i < list.size(); i++) {
			try {
				dbserv.insert((String)list.get(i));//����
			} catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		List<DBObject> list2 = dbserv.query("");
		
		this.parseList(list2);
	*/}
	
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
