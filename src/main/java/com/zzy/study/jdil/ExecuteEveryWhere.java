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
			System.out.println("���ó����̻����𣩽ӿڿ�ʼ");
			System.out.println("������BankId����" + 1);
			System.out.println("������UserId����" + 1);
			System.out.println("������Money����" + 1);
			System.out.println("������RequestId����" + 1);
			System.out.println("������Token����" + "");
			ro = m_hil_msg.OutMoney("", "", 1, "", "");
			System.out.println("���ó����̻����𣩽ӿڽ���");
			
			if (ro != null && "01".equals(ro.getResponse_type())) {//
				
			}
	    } catch (MalformedURLException e2) {
			System.out.println("����webserviceʧ�ܡ�");
			e2.printStackTrace();
	    }
	}*/
	
	/**
	 * ���ݲ�ͬ�Ĳ�����ò�ͬ���ݿ����ӡ� ���汾���ݿ�������Tomcat������
	 * 
	 * @param dsName
	 *            String ���ݿ���
	 * @return Connection ���ݿ�����Connection
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
			Context initCtx1 =new InitialContext(table) ;  //��ó�ʼ������(��ʼ������) 
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
