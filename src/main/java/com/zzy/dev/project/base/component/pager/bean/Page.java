package com.zzy.dev.project.base.component.pager.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import com.zzy.dev.project.base.dao.BaseDaoBean;


/**
 * ���ͻ��˵��������ݡ�����
 * �������ϵ�һҳ����ҳ�� �������ٰ�������Ҫ�أ� �ٷ�ҳ���ݣ������һ���������ݣ��Լ�ÿҳ��ʾ�ж�����
 * �ڷ�ҳ����������һҳ����һҳ���ܹ�ҳ�����ܹ���¼����ҳ�루1 2 3 ...������ǰҳ
 * 
 * T ҳ�������bean
 * @author Administrator
 * 
 */
public class Page {
	
	/*==========���뿪ʼ==============*/
	int pageSize = 10;// ÿҳ��ʾ�ļ�¼��
	String sql = "";//��ҳ�Ĳ�ѯ���
	int currentPageNo = 0;// ��ǰҳ��
	String url = "";// ҳ���ӵ�url�����ڻ������ҳ������
	int cntPageHref = 3;// ҳ�����
	Class cls = null;//Bean��class
	
	/**
	 * ����һ��page���� pageRecords ��ǰҳ������ currentPageNo ��ǰ�ǵڼ�ҳ url ҳ���ӵ�ַ pageSize
	 * ÿҳ�ļ�¼�� recordCnt �ܹ��ļ�¼��
	 * @throws SQLException 
	 * @throws NamingException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Page(int currentPageNo, String url, int pageSize, String sql, Class cls) 
			throws NamingException, SQLException, InstantiationException, IllegalAccessException {
		
		this.pageSize = pageSize;
		this.currentPageNo = currentPageNo;
		this.url = url;
		this.sql = sql;
		this.cls = cls;
		
		//�������¼��
		Connection conn = null;
		conn = BaseDaoBean.getConnection("fctrade_jh");
		this.recordCnt = BaseDaoBean.getIntData(this.sql, conn);
		
		//��õ�ǰҳ������
		this.pageRecords = BaseDaoBean.getArrayListBean(this.getPagingQuery(), conn, this.cls);
		BaseDaoBean.close(conn);
		
		this.setMaxPageNo();// �������ҳ��
	}

	/**
	 * ����һ��page���� pageRecords ��ǰҳ������ currentPageNo ��ǰ�ǵڼ�ҳ url ҳ���ӵ�ַ pageSize
	 * ÿҳ�ļ�¼�� cntPageHref ��ҳ�����У�ҳ���ӵĸ���
	 */
	public Page(int currentPageNo, String url, int pageSize, int cntPageHref, String sql, Class cls) {
		this.pageSize = pageSize;
		this.currentPageNo = currentPageNo;
		this.url = url;
		this.cntPageHref = cntPageHref;
		this.sql = sql;
		this.cls = cls;
		this.setMaxPageNo();
	}
	/*==========�������==============*/
	
	
	/*==========�����ʼ==============*/
	List pageRecords = null;// ��ҳ�е����ݣ� T����һ�����������bean
	ArrayList<HashMap<String, Object>> pageRecordsMap = null;//��ҳ�е�����, ��һ�����ݷ�װ��һ��HashMap���ú����ArrayList��
	int recordCnt = 0;// �ܹ��ļ�¼��
	int maxPageNo = 0;// �ܹ���ҳ��
	
	/**
	 * ��õ�ҳ������
	 * @return
	 * @throws SQLException 
	 * @throws NamingException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public List getPageRecords() throws NamingException, SQLException, InstantiationException, IllegalAccessException {
		return pageRecords;
	}
	
	/**
	 * ���������ʽ�ĵ�������5ҳ ��49�� [ ��ҳ | ��һҳ | ��һҳ | ĩҳ ] �����������ҳ���� ҳ  ����ť�ύ�� 
	 * @return
	 */
	public String getNavigator() {
		StringBuffer pagebuffer = new StringBuffer();
		if (url.equals("")) {
			return "";
		}
		
		//���url�Ƿ��в���
		if (url.indexOf("?") == -1) {//�޲���
			url += "?1=1";
		}
		
		pagebuffer
				.append("<form name=frmPage method=get action="
						+ url
						+ " onSubmit='return goPage()'>"
						+ "<div id='nav' style='margin-top:5px;height:25px;text-align:right;padding:5px;line-height:25px;'>");
		
		//������ҳ�����ܼ�¼��
		pagebuffer.append("��" + this.getMaxPageNo() + "ҳ ��" + getRecordCnt() + "��&nbsp;");
		
		// ������ҳ
		if ((this.currentPageNo > 1) && (this.maxPageNo > 1)) {
			pagebuffer.append("[&nbsp;<a href=\"" + url
					+ "&pageNo=1\" class=\"cBlue\">��ҳ</a>] | ");
		} else {
			pagebuffer.append("[&nbsp;��ҳ | ");
		}
		
		// ����ǰһҳ
		if (this.currentPageNo > 1) {
			pagebuffer.append("<a href=\"" + url + "&pageNo=" + this.getPrePageNo()
					+ "\" class=\"cBlue\">��һҳ</a> | ");
		} else {
			pagebuffer.append("��һҳ | ");
		}
		
		
		// ������һҳ
		if (this.currentPageNo < this.maxPageNo) {
			pagebuffer.append("<a href=\"" + url + "&pageNo=" + this.getNextPageNo()
					+ "\" class=\"cBlue\">��һҳ</a> | ");
		} else {
			pagebuffer.append("��һҳ | ");
		}
		
		
		// �������һҳ
		if ((this.currentPageNo < this.maxPageNo) && (this.maxPageNo > 1)) {
			pagebuffer.append("<a href=\"" + url + "&pageNo=" + this.maxPageNo
					+ "\" class=\"cBlue\">ĩҳ</a>&nbsp;]");
		} else {
			pagebuffer.append("ĩҳ&nbsp;]");
		}
		
		pagebuffer.append("&nbsp;&nbsp;<input type=text name=pageNo size=3>&nbsp;ҳ&nbsp;");
		pagebuffer.append("<input type=submit value=Go>&nbsp");
		pagebuffer.append("</div></form>");
		pagebuffer.append("\r\n<script language=javascript>\r\n");
		pagebuffer.append("\t\t var maxPage=");
		pagebuffer.append(this.maxPageNo);
		pagebuffer.append(";\r\n\t\t function goPage(){\r\n");
		pagebuffer.append("\r\n         var pageNo= frmPage.pageNo.value;");
		pagebuffer.append("\r\n         if(isNaN(pageNo) || pageNo<1 || pageNo>maxPage){    ");
		pagebuffer.append("\r\n         alert('������1--'+maxPage+'������');    ");
		pagebuffer.append("\r\n         }    ");
		pagebuffer.append("\t\t  		else{\r\n  window.location=\"");
		pagebuffer.append(url);
		pagebuffer.append("&pageNo=\"+frmPage.pageNo.value;\r\n");
		pagebuffer.append("}");
		pagebuffer.append("\t\t return false;  }\r\n ");
		pagebuffer.append("</script>\r\n");
		
		
		return pagebuffer.toString();
	}
	/*==========�������==============*/
	
	private void setMaxPageNo() {
		if (this.recordCnt % this.pageSize == 0) {
			this.maxPageNo = this.recordCnt / this.pageSize;
		} else {
			this.maxPageNo = this.recordCnt / this.pageSize + 1;
		}
	}

	private int getMaxPageNo() {
		return this.maxPageNo;
	}

	/**
	 * ���ǰһҳ��ҳ��
	 * 
	 * @return
	 */
	private int getPrePageNo() {
		if (this.currentPageNo > 1) {
			return this.currentPageNo - 1;
		} else {
			return this.currentPageNo;
		}
	}

	/**
	 * �����һҳ��ҳ��
	 * 
	 * @return
	 */
	private int getNextPageNo() {
		if (this.currentPageNo >= this.maxPageNo) {
			return this.currentPageNo;
		} else {
			return this.currentPageNo + 1;
		}
	}
	
	/**
	 * ��ü�¼����
	 * @return
	 */
	private int getRecordCnt(){
		return this.recordCnt;
	}
	
	/**
	 * @Description:(��ѯSQL��ҳ����)
	 * @return
	 * @date Nov 28, 2011 4:38:55 PM
	 */
	private String getPagingQuery(){
		
		StringBuffer mQuery = new StringBuffer();
		
		int startRownum = 0;
		int endRownum = 0;
		
		startRownum = (this.currentPageNo - 1) * this.pageSize + 1;//
		endRownum = this.currentPageNo * this.pageSize;
		
		/** �Ľ������ǵ���ѯЧ�����⣬��between and ��ѯЧ�ʸ����ô���С�� */
		//ע�� oracle��between and��ǰ���Ǳ����䣬Ҳ����˵���������˵���
		mQuery.append("SELECT * FROM (\n");
		mQuery.append("	  SELECT ROWNUM AS PAGE_RN , A.*\n");
		mQuery.append("     FROM ( \n");
		mQuery.append(sql);		
		mQuery.append(		    ") A ) \n");
		mQuery.append("WHERE PAGE_RN   BETWEEN "+ startRownum +" AND "+ endRownum +"\n");
		
		return mQuery.toString();
	}
	
	/**
	 * @Description:(��ѯSQL Count����sql)
	 * @return
	 * @date Nov 29, 2011 8:55:01 AM
	 */
	private String getCountQuery() {
		StringBuffer cQuery = new StringBuffer();
		
		cQuery.append("SELECT COUNT(*) FROM (");
		cQuery.append(this.sql);
		cQuery.append(")");
		
		return cQuery.toString();
	}
}
