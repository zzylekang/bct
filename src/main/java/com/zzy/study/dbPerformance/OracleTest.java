package com.zzy.study.dbPerformance;

import java.sql.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class OracleTest {
	private final long num=100;//测试数量,单位为万，num为100表示数据量为100w，num为500表示数据量为500w
	private static Logger log = Logger.getLogger(OracleTest.class);
	private static Connection con;
	private long st=0;//起始时间
	private long et=0;//结束时间
	public static void main(String[] args) {
		con= getcon();
		OracleTest jdbc=new OracleTest();
		//===================================
		jdbc.test(con);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void test(Connection conn){
		long start=System.currentTimeMillis();
		long[] res=new long[]{0l,0l,0l,0l,0l,0l,0l,0l};
		final int count=5;
		for (int i = 0; i < count; i++) {//循环多次取平均值
			res[0]+=testInsert(con);
		//===================================
			res[1]+=query(con);
		//===================================
			long id=getid(con);
			res[2]+=findions(con,id);
		//===================================
			res[3]+=update(con,id,"ss");
		//===================================
			res[4]+=setIndex(con,1);
		//===================================
			res[5]+=findions(con,id);
		//===================================
			res[6]+=update(con,id,"s");
		//===================================
			res[7]+=del(con);
			setIndex(con,0);//删除索引
		//===================================
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
	 * 建立数据库连接
	 * */
	private static Connection getcon(){
		String user = "system";
		String password = "cctest";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		}  catch (ClassNotFoundException e1) {
			log.info("数据库驱动不存在！");
			log.info(e1.toString());
		} catch (SQLException e2) {
			log.info("数据库存在异常！");
			log.info(e2.toString());
		} 
		return con;
	}
	/**
	 * 批量入库
	 * @author cc
	 * */
	public long  testInsert(Connection con)  {
		long batch=10000;
		long count=0l;
		long tmp=0;
		long tmps=0;
		long sum=0;//插入事件总计
		final long time=System.currentTimeMillis();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			StringBuffer sqlf=new StringBuffer();
			sqlf.append("INSERT INTO TEST_TABLE (SID,PE,ME," +
					"ED,EE,SD,AD,ET,SE," +
					"SP,F_1,F_2,F_3,F_4,F_5,F_6,F_7,F_8,F_9,F_10) values (" +
					"?,'MongoDB','MongoDB',1111111111,'1111111111'," +
					"1,?,'database',?,'192.168.16.88','F_1','F_2','F_3','F_4','F_5','F_6'" +
					",'F_7','F_8','F_9','F_10')");
			pst = con.prepareStatement(sqlf.toString());
			while(count<num){
				tmp=count*batch;
				tmps=(count+1)*batch;
				for (;tmp < tmps; tmp++) {
					st=System.currentTimeMillis();
					pst.setLong(1, time+tmp);
					pst.setLong(2, time);
					pst.setString(3, st+"");
					pst.addBatch();
				}
				log.info("第"+count+"次开始时间"+st);
				st=System.currentTimeMillis();
				pst.executeBatch();
				et=System.currentTimeMillis();
				log.info("第"+count+"次结束时间"+et);
				count++;
				sum+=(et-st);
			}
			log.info("总共用时"+sum+"ms,数据量为"+num*batch);
			return sum;
		}  catch (SQLException e2) {
			log.info("数据库存在异常！");
			log.info(e2.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				log.info(e.toString());
			}
		}
		return 0;
	}
	//查询
	private long query(Connection con){
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="SELECT * FROM TEST_TABLE";
		try {
			st=System.currentTimeMillis();
			pst = con.prepareStatement(sql.toString());
			rs=pst.executeQuery(sql);
			while(rs.next()){
				rs.getLong(1);rs.getString(11);
				rs.getString(2);rs.getString(12);
				rs.getString(3);rs.getString(13);
				rs.getLong(4);rs.getString(14);
				rs.getString(5);rs.getString(15);
				rs.getLong(6);rs.getString(16);
				rs.getLong(7);rs.getString(17);
				rs.getString(8);rs.getString(18);
				rs.getLong(9);rs.getString(19);
				rs.getString(10);rs.getString(20);
			}
			et=System.currentTimeMillis();
			 log.info(num+"万条记录遍历起始时间"+st+"|結束时间"+et+"总用时"+(et-st)+"ms");
			 log.info("速率"+num*10000*1000/(et-st)+"条/秒");//SID=1283742824711
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return et-st;
	}
	//得到中间id
	private long getid(Connection con){
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql="SELECT SID FROM TEST_TABLE";
		ArrayList<Long> al=new ArrayList<Long>();
		try {
			st=System.currentTimeMillis();
			pst = con.prepareStatement(sql.toString());
			rs=pst.executeQuery(sql);
			while(rs.next()){
				al.add(rs.getLong(1));
			}
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			log.info("SID="+al.get(al.size()/2));
			return al.get(al.size()/2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//设置索引
	/**
	 * 建立索引,删除索引
	 * */
	private long setIndex(Connection con,int type){
//		CREATE INDEX 索引名ON
		PreparedStatement pst = null;
		String sql="";
		if(type==1){
			 sql="CREATE INDEX sid_idx ON TEST_TABLE(SID)";
		}else{
			 sql="DROP INDEX sid_idx ";
		}
		try {
			st=System.currentTimeMillis();
			pst = con.prepareStatement(sql);
			pst.execute(sql);
			et=System.currentTimeMillis();
			log.info("开始时刻"+st+"结束时刻"+et);
			log.info(num+"百万条记录建立索引时间为:"+(et-st)+"ms");
			if (pst != null)
				pst.close();
			return et-st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//删除
	private long del(Connection con){
		PreparedStatement pst = null;
		String sql="TRUNCATE TABLE TEST_TABLE";
		st=System.currentTimeMillis();
		try {
			pst = con.prepareStatement(sql);
			pst.execute(sql);
			if (pst != null)
				pst.close();
			et=System.currentTimeMillis();
			log.info(st+"|"+et);
			log.info("删除时间为:"+(et-st)+"ms");
			return et-st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	
	}
	//修改
	private long update(Connection con,long id,String str){
		Statement smt = null;
		String strs="MongoDB"+str;
		String sql="UPDATE  TEST_TABLE  SET  PE='"+strs+"' , ME='"+strs+"'  WHERE  SID="+id;
		st=System.currentTimeMillis();
		try {
			smt = con.createStatement();
//			pst.setString(1, "MongoDB"+str);
			smt.executeUpdate(sql);
			if (smt != null)
				smt.close();
			et=System.currentTimeMillis();
			log.info("开始时刻"+st+"结束时刻"+et);
			log.info("修改1条记录时间为:"+(et-st)+"ms");
			return et-st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	
	}
	/**
	 * 根据条件查询1000条数据
	 * */
	private long findions(Connection con,long id){
		long count=0;
		Statement pst = null;
		ResultSet rs = null;
		String sql="SELECT * FROM TEST_TABLE FM WHERE　FM.SID BETWEEN "+id+" AND "+(id+999);
		try {
			st=System.currentTimeMillis();
			pst = con.createStatement();
			rs=pst.executeQuery(sql);
			while(rs.next()){
				count++;
			}
			et=System.currentTimeMillis();
			log.info("指针查找时间"+st+"|"+et);
			log.info("查询时间"+count+"条记录遍历起始时间:"+st+"|結束时间"+et+"总用时"+(et-st)+"ms");
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			return et-st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
