package com.zzy.study.jdil;


import java.net.MalformedURLException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ExecuteEveryWhere {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ExecuteEveryWhere.I3FC314();
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long now = 1378711710307l;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		System.out.println(now + " = " + formatter.format(calendar.getTime()));
	}
	
/*	private static void I3FC307() {

		BusinessLogic bl = new BusinessLogic();
		JH_INTERFACE_3FC307 ji3fc307 = new JH_INTERFACE_3FC307();
		String sqlWhere = " and business_status = 0 and id = '" + "2563979" + "'";
		List<Res3FC307bean> list_ji3fc307bean = ji3fc307.getList(sqlWhere, 1);
		Res3FC307bean res3fc307bean = list_ji3fc307bean.get(0);
		bl.business3FC307(res3fc307bean);
	
	}
	
	private static void I3FC314() {

		BusinessLogic bl = new BusinessLogic();
		JH_INTERFACE_3FC314 ji3fc314 = new JH_INTERFACE_3FC314();
		String sqlWhere = " and breach_order_num = '" + "D13082903562" + "'";
		List<Res3FC314bean> list_ji3fc314bean = ji3fc314.getList(sqlWhere, 1);
		Res3FC314bean res3fc307bean = list_ji3fc314bean.get(0);
		bl.business3FC314(res3fc307bean);
	
	}*/
/*	private static void hil() {
		Service srvcModel = new ObjectServiceFactory().create(HIL_MSG.class); 
	    XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
	    String url = "http://192.168.5.127:7001/HILService/services/HIL";
	    ResultObject ro = null;
	    try {
	    	HIL_MSG m_hil_msg = (HIL_MSG) factory.create( srvcModel, url);
			System.out.println("调用出金（商户发起）接口开始");
			System.out.println("参数（BankId）：" + 1);
			System.out.println("参数（UserId）：" + 1);
			System.out.println("参数（Money）：" + 1);
			System.out.println("参数（RequestId）：" + 1);
			System.out.println("参数（Token）：" + "");
			ro = m_hil_msg.OutMoney("", "", 1, "", "");
			System.out.println("调用出金（商户发起）接口结束");
			
			if (ro != null && "01".equals(ro.getResponse_type())) {//
				
			}
	    } catch (MalformedURLException e2) {
			System.out.println("调用webservice失败。");
			e2.printStackTrace();
	    }
	}*/
	
	/**
	 * 根据不同的参数获得不同数据库连接。 本版本数据库连接在Tomcat中配置
	 * 
	 * @param dsName
	 *            String 数据库名
	 * @return Connection 数据库连接Connection
	 */
/*	public static Connection getConnection(String dssName) throws DAOSysException
	{

		String dbsrc = dssName;
		// Properties p = getConfig("/log.properties");
		// String dsName = p.getProperty("dsname");
		// if(dsName!=null &&dsName.equals("")){
		// dbsrc=dsName;
		// }
		java.sql.Connection conn = null;
		try
		{
			//Weblogic10  

			Properties props = new Properties();  
			props.setProperty( "java.naming.factory.initial" , "weblogic.jndi.WLInitialContextFactory" );  
			props.setProperty( "java.naming.provider.url" , "t3://192.168.5.197:7001" );
			props.put(Context.SECURITY_PRINCIPAL, "weblogic");
			props.put(Context.SECURITY_CREDENTIALS,"00000000");

			InitialContext initCtx1 = new InitialContext(props);

			Hashtable table = new Hashtable();   
			table.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");   
			table.put(Context.PROVIDER_URL, "t3://localhost:7001");   
			Context initCtx1 =new InitialContext(table) ;  //获得初始化环境(初始上下文) 
			DataSource ds = (DataSource) initCtx1.lookup(dbsrc);
			conn = ds.getConnection();
		}
		catch (Exception e)
		{	
			e.printStackTrace();
			Log.fatalError(e, "open connection");
			//throw new DAOSysException("open connection err " + e.getMessage());
		}

		return conn;
	}*/
}
