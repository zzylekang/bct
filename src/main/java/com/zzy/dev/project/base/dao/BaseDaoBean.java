package com.zzy.dev.project.base.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDaoBean {
	/*==============输入开始： 这里的输入将以方法参数的形式传递进来================*/
	String sql = null;
	String dataSourceName = null;
	
	public BaseDaoBean(){
		
	}
	/*==============输入结束================*/
	
	/*==============输出开始================*/
	
	/**
	 * 通过JNDI获得连接，需要web容器的支持
	 * @return
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public static Connection getConnection(String dataSourceName) throws NamingException, SQLException{
		Context initCtx = new javax.naming.InitialContext();
		DataSource ds = (DataSource) initCtx.lookup(dataSourceName);
		Connection conn = ds.getConnection();
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 * @param curConn
	 * @throws SQLException 
	 */
	public static void close(Connection conn) throws SQLException{
		if(conn != null){
			conn.close();
			conn = null;
		}
	}
	
	/**
	 * 关闭数据库结果集
	 * @param curConn
	 * @throws SQLException 
	 */
	public static void close(ResultSet rs) throws SQLException{
		if(rs != null){
			rs.close();
			rs = null;
		}
	}
	
	/**
	 * 获得一个String类型的数据
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public static String getStringData(String sql, Connection conn) throws SQLException {
		String ret = null;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()){
			ret = rs.getString(1);
		}
		close(rs);
		return ret;
	}
	
	public static Integer getIntData(String sql, Connection conn) throws SQLException {
		Integer ret = 0;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()){
			ret = rs.getInt(1);
		}
		close(rs);
		return ret;
	}
	
	public static Date getDateData(String sql, Connection conn) throws SQLException{
		Date ret = null;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()){
			ret = rs.getDate(1);
		}
		close(rs);
		return ret;
	}
	
	/**
	 * 获得ArrayList<HashMap<String, Object>>形式是数据集
	 * @param sql
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static List<HashMap<String, Object>> getArrayListHashMap(String sql, Connection conn) throws SQLException{
		List<HashMap<String, Object>> ret = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ResultSetMetaData rsmd = rs.getMetaData(); //获得元数据
		int columnCount = rsmd.getColumnCount();   //获得查询的列数个数
		
		while (rs.next()){
			for(int i = 1 ; i < columnCount+1; i++){
				hm.put(rsmd.getColumnName(i), rs.getObject(i)); //key value
			}
			ret.add(hm);
		}
		close(rs);
		return ret;
	}
	
	/**
	 * 获得ArrayList<Bean>形式是数据集
	 * @param sql
	 * @param conn
	 * @param cls 
	 * @return
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static List getArrayListBean(String sql, Connection conn, Class cls) throws SQLException, InstantiationException, IllegalAccessException{
		List<Object> ret = new ArrayList<Object>();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		Object v = null;
		while (rs.next()){
			v = cls.newInstance();
			setValue(v, rs);
			ret.add(v);
		}
		close(rs);
		return ret;
	}
	
	//把数据库记录中的值转到类中
	private static void setValue(Object v,ResultSet rs) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Field [] fs=v.getClass().getFields();
		for (int i=0;i<fs.length;i++) {
			if (Modifier.isStatic(fs[i].getModifiers())) continue; //静态成员不处理
			int pos;
			try {
				pos=rs.findColumn(fs[i].getName());
			} catch (Exception e) {
				pos=-1;
			}
			
			if (pos>=0) {
				setFieldV(fs[i],v,rs);
			}
		}
	}
	
	//把记录中的值转成类的成员
	private static void setFieldV(Field f,Object v,ResultSet rs) throws SQLException, IllegalArgumentException, IllegalAccessException {
		Class cls=f.getType();

		if (cls.equals(java.util.Date.class)) {
			Date dd=rs.getTimestamp(f.getName());
			f.set(v,dd);
		} else if (cls.equals(java.lang.Long.class)) {
			Long ll;
			ll=new Long(rs.getLong(f.getName()));
			f.set(v,ll);
		} else if (cls.equals(long.class)) {
			long ll;
			ll=rs.getLong(f.getName());
			f.setLong(v,ll);
		} else if (cls.equals(java.lang.Integer.class)) {
			Integer ll;
			ll=new Integer(rs.getInt(f.getName()));
			f.set(v,ll);
		} else if (cls.equals(int.class)) {
			int ll;
			ll=rs.getInt(f.getName());
			f.setInt(v,ll);
		} else if (cls.equals(java.lang.Float.class)) {
			Float ll;
			ll=new Float(rs.getFloat(f.getName()));
			f.set(v,ll);
		} else if (cls.equals(float.class)) {
			float ll;
			ll=rs.getFloat(f.getName());
			f.setFloat(v,ll);
		} else if (cls.equals(java.lang.Double.class)) {
			Double ll;
			ll=new Double(rs.getDouble(f.getName()));
			f.set(v,ll);
		} else if (cls.equals(double.class)) {
			double ll;
			ll=rs.getDouble(f.getName());
			f.setDouble(v,ll);
		} else if (cls.equals(java.lang.StringBuffer.class)) {
			StringBuffer ll=null;
			Clob content=rs.getClob(f.getName());
			if (content!=null) {
			     String buf= content.getSubString(1,(int)content.length());
			     ll = new StringBuffer(buf);

			}
			f.set(v,ll) ;
		} else {
			f.set(v,rs.getString(f.getName()));
		} 
		
	}
	/*==============输出结束================*/
	
}
