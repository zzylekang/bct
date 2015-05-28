package com.zzy.study.dbPerformance;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
public class MongoTest {
		private static Logger log = Logger.getLogger(MongoTest.class);
		private final long num=200;//测试数量,单位为万，100表示100w，500表示500w
		private long st=0;//起始时间
		private long et=0;//结束时间
		public static void main(String[] args) {
			try {
//				m = new Mongo( "192.168.16.88",27017 );
				MongoTest mt=new MongoTest();
				Mongo m = new Mongo( "127.0.0.1",27017 );
				DB db = m.getDB( "mongtest" );
				DBCollection coll = db.getCollection("mcl");
				//===================================
				mt.test(coll);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MongoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		private void test(DBCollection coll){
			long start=System.currentTimeMillis();
			long[] res=new long[]{0l,0l,0l,0l,0l,0l,0l,0l};
			final int count=3;
			for (int i = 0; i < count; i++) {
				res[0]+=testInsert(coll);
//				System.exit(0);
				long id =getSTDid(coll);
				//===================================
				res[1]+=query(coll,"无索引时");
				//===================================
				res[2]+=findnoin(coll,"无索引时",id);
				//===================================
				res[3]+=update(coll,"ss",id);
				//===================================
				res[4]+=setindex(coll, 1);
				//===================================
				if (getIndex(coll)>1){
					res[5]+=findnoin(coll,"建立索引后",id);
					//===================================
					res[6]+=update(coll,"s",id);
					//===================================
				}
				res[7]+=del(coll);
			}
			log.info("**********************************************************");
			log.info("插入"+num+"万条数据用时:"+(res[0]/count)+"ms");
			log.info("无索引时轮询"+num+"万条数据用时:"+(res[1]/count)+"ms");
			log.info("无索引在"+num+"万条数据中查找1000条记录用时:"+(res[2]/count)+"ms");
			log.info("无索引时更新1条记录"+num+"万条数据用时:"+(res[3]/count)+"ms");
			log.info(num+"万条数据创建索引用时:"+(res[4]/count)+"ms");
			log.info("插入"+num+"万条数据建立索引后查找1000条记:"+(res[5]/count)+"ms");
			log.info("插入"+num+"万条数据建立索引后修改1条记录用时:"+(res[6]/count)+"ms");
			log.info("插入"+num+"万条数据删除用时:"+(res[7]/count)+"ms");
			log.info("总用时"+(System.currentTimeMillis()-start)+"ms");
			log.info("**********************************************************");
		}
		/**
		 * 批量插入数据
		 * */
		private long  testInsert(DBCollection coll){
			List<DBObject> list=null;
			long var=0;
			String value="";
			long batch=10000;
			long count=0l;
			long tmp=0;
			long tmps=0;
			long sum=0;//插入事件总计
			BasicDBObject doc =null;
			final long time=System.currentTimeMillis();
			while(count<num){
				list=new ArrayList<DBObject>();
				tmp=count*batch;
				tmps=(count+1)*batch;
				for (;tmp < tmps; tmp++) {
					st=System.currentTimeMillis();
					doc = new BasicDBObject();
					value=st+"";
					doc.put("SID",time+tmp );
					doc.put("PE", "MongoDB");
					doc.put("ME", "MongoDB");
					doc.put("ED", 1111111111);
					doc.put("EE", "1111111111");
					doc.put("SD", 1);
					doc.put("AD", time);
					doc.put("ET", "database");
					doc.put("SE", value);
					doc.put("SP", "192.168.16.88");
					doc.put("F_1", "F_1");
					doc.put("F_2","F_2");
					doc.put("F_3", "F_3");
					doc.put("F_4", "F_4");
					doc.put("F_5", "F_5");
					doc.put("F_6", "F_6");
					doc.put("F_7", "F_7");
					doc.put("F_8", "F_8");
					doc.put("F_9", "F_9");
					doc.put("F_10", "F_10");
					list.add(doc);
				}
				log.info("第"+count+"次开始时间"+st);
				st=System.currentTimeMillis();
				coll.insert(list);
				et=System.currentTimeMillis();
				list.clear();
				log.info("第"+count+"次结束时间"+et);//+"速率"+(batch%(var))+"/ms"
				count++;
				sum+=(et-st);
			}
			log.info("总共用时"+sum+"ms,数据量为"+num*batch);//+"插入速率为"+num*batch%sum+"/ms"
			return sum;
		}
		/**
		 * 遍历集合
		 * */
		private long  query(DBCollection coll,String str){
			st=System.currentTimeMillis();
			DBCursor  cur = coll.find();
			while(cur.hasNext()) {
				cur.next();
		      }
			 et=System.currentTimeMillis();
			 log.info(str+coll.count()+"条记录遍历起始时间"+st+"|結束时间"+et+"总用时"+(et-st)+"ms");
			 log.info("速率"+coll.count()*1000/(et-st)+"条/秒");//SID=1283742824711
			 return et-st;
		}
		/**
		 * 查找数据
		 * */
		private long findnoin(DBCollection coll,String str,long id){
			BasicDBObject query = new BasicDBObject();//query.put("i", new BasicDBObject("$gt", 20).append("$lte", 30));  // 
			long count=0l;
			st=System.currentTimeMillis();
			query.put("SID", new BasicDBObject("$gt", id).append("$lte", id+1000));  //new BasicDBObject("$gt", 1283743824710l)
			DBCursor  cur = coll.find(query);
			while(cur.hasNext()) {
				cur.next();
				count++;
			}
			et=System.currentTimeMillis();
			log.info("指针查找时间"+st+"|"+et);
			log.info(str+"查询时间"+count+"条记录遍历起始时间:"+st+"|結束时间"+et+"总用时"+(et-st)+"ms");
//			log.info("速率"+count*1000/(et-st)+"条/秒");
			return et-st;
		}
		/**
		 * 建立索引,删除索引
		 * */
		private long setindex(DBCollection coll,int type){
			st=System.currentTimeMillis();
			if(type==1){
				coll.createIndex(new BasicDBObject("SID", 1));  // create index on "i", ascending
			}
			else{
				coll.dropIndex(new BasicDBObject("SID", 1));  // create index on "i", ascending
			}
			et=System.currentTimeMillis();
			log.info("开始时刻"+st+"结束时刻"+et);
			log.info(coll.count()+"条记录建立索引时间为:"+(et-st)+"ms");
//			log.info("速率"+coll.count()*1000/(et-st)+"条/s");
			return et-st;
		}
	
		private long getSTDid(DBCollection coll){
			int count=0;
			DBCursor  cur = coll.find();
			DBObject dbobj=new BasicDBObject();
			long mid=cur.count()/2;
			while(cur.hasNext()) {
				dbobj=cur.next();
				count++;
				if(count==mid){
					break;
				}
		    }
			long id=(Long)dbobj.get("SID");
			log.info("SID="+id);
			return id;
		}
		/***
		 * 删除数据
		 * */
		private long del(DBCollection coll ){
			st=System.currentTimeMillis();
			coll.drop();
			et=System.currentTimeMillis();
			log.info(st+"|"+et);
			log.info("删除时间为:"+(et-st)+"ms");
			return et-st;
		}
		/**
		 * 修改数据
		 * */
		private long update(DBCollection coll,String str,long id){
			BasicDBObject query = new BasicDBObject();//query.put("i", new BasicDBObject("$gt", 20).append("$lte", 30));  // 
			long count=0l;
			st=System.currentTimeMillis();
			query.put("SID", id);  //id
			DBCursor  cur = coll.find(query);
			DBObject doc=new BasicDBObject();
			while(cur.hasNext()) {
				doc=cur.next();
				count++;
			}
			if(count!=0){
				query.put("PE", "MongoDB"+str);
				query.put("ME", "MongoDB"+str);
				query.put("ED", 1111111111);
				query.put("EE", "1111111111");
				query.put("SD", 1);
				query.put("AD", (Long)doc.get("ALERTLEVELID"));
				query.put("ET", "database");
				query.put("SE", doc.get("STARTTIME"));
				query.put("SP", "192.168.16.88");
				query.put("F_1", "F_1");
				query.put("F_2","F_2");
				query.put("F_3", "F_3");
				query.put("F_4", "F_4");
				query.put("F_5", "F_5");
				query.put("F_6", "F_6");
				query.put("F_7", "F_7");
				query.put("F_8", "F_8");
				query.put("F_9", "F_9");
				query.put("F_10", "F_10");
				coll.update(doc,query);
				et=System.currentTimeMillis();
				log.info("开始时刻"+st+"结束时刻"+et);
				log.info("修改1条记录时间为:"+(et-st)+"ms");
//				log.info("速率"+1000/(et-st)+"条/s");
			}
			return et-st;
		}
		/**
		 * 得到最大和最小时间
		 * */
		private void print(DBCollection coll){
			DBCursor  cur = coll.find();
			long size=cur.count();
			log.info("================================指针数"+size);
			long count=0;
			while(cur.hasNext()) {
				if(count==0|count==1999999){
					DBObject db=new BasicDBObject();
					db=cur.next();
					log.info("===="+db.get("SID"));
				}else{
					 cur.next();
				}
				 count++;
		      }
		}
		/**
		 * 得到索引信息
		 * */
		private int getIndex(DBCollection coll){
			List<DBObject>list= coll.getIndexInfo();
			log.info(list.size());
			for (int i = 0; i < list.size(); i++) {
				DBObject dbo=new BasicDBObject();
				dbo=list.get(i);
				log.info(dbo.toString());
			}
			return list.size();
		}
}
