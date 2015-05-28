package com.zzy.dev.project.base.component.pager.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;

import com.zzy.dev.project.base.dao.BaseDaoBean;


/**
 * 将客户端的输入数据、处理
 * 代表画面上的一页（分页） 其中至少包含以下要素： ①分页数据：具体的一条条的数据，以及每页显示有多少条
 * ②分页导航器：上一页、下一页、总共页数、总共记录数、页码（1 2 3 ...）、当前页
 * 
 * T 页面的数据bean
 * @author Administrator
 * 
 */
public class Page {
	
	/*==========输入开始==============*/
	int pageSize = 10;// 每页显示的记录数
	String sql = "";//分页的查询语句
	int currentPageNo = 0;// 当前页数
	String url = "";// 页链接的url，用于获得所需页的数据
	int cntPageHref = 3;// 页码个数
	Class cls = null;//Bean的class
	
	/**
	 * 构造一个page对象 pageRecords 当前页的数据 currentPageNo 当前是第几页 url 页链接地址 pageSize
	 * 每页的记录数 recordCnt 总共的记录数
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
		
		//获得最大记录数
		Connection conn = null;
		conn = BaseDaoBean.getConnection("fctrade_jh");
		this.recordCnt = BaseDaoBean.getIntData(this.sql, conn);
		
		//获得当前页的数据
		this.pageRecords = BaseDaoBean.getArrayListBean(this.getPagingQuery(), conn, this.cls);
		BaseDaoBean.close(conn);
		
		this.setMaxPageNo();// 设置最大页数
	}

	/**
	 * 构造一个page对象 pageRecords 当前页的数据 currentPageNo 当前是第几页 url 页链接地址 pageSize
	 * 每页的记录数 cntPageHref 分页导航中，页链接的个数
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
	/*==========输入结束==============*/
	
	
	/*==========输出开始==============*/
	List pageRecords = null;// 分页中的数据， T代表一个具体的数据bean
	ArrayList<HashMap<String, Object>> pageRecordsMap = null;//分页中的数据, 将一条数据封装成一个HashMap，让后放在ArrayList中
	int recordCnt = 0;// 总共的记录数
	int maxPageNo = 0;// 总共的页数
	
	/**
	 * 获得当页的数据
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
	 * 获得如下形式的导航：共5页 总49条 [ 首页 | 上一页 | 下一页 | 末页 ] “输入框：输入页数” 页  “按钮提交” 
	 * @return
	 */
	public String getNavigator() {
		StringBuffer pagebuffer = new StringBuffer();
		if (url.equals("")) {
			return "";
		}
		
		//检查url是否有参数
		if (url.indexOf("?") == -1) {//无参数
			url += "?1=1";
		}
		
		pagebuffer
				.append("<form name=frmPage method=get action="
						+ url
						+ " onSubmit='return goPage()'>"
						+ "<div id='nav' style='margin-top:5px;height:25px;text-align:right;padding:5px;line-height:25px;'>");
		
		//设置总页数、总记录数
		pagebuffer.append("共" + this.getMaxPageNo() + "页 总" + getRecordCnt() + "条&nbsp;");
		
		// 设置首页
		if ((this.currentPageNo > 1) && (this.maxPageNo > 1)) {
			pagebuffer.append("[&nbsp;<a href=\"" + url
					+ "&pageNo=1\" class=\"cBlue\">首页</a>] | ");
		} else {
			pagebuffer.append("[&nbsp;首页 | ");
		}
		
		// 设置前一页
		if (this.currentPageNo > 1) {
			pagebuffer.append("<a href=\"" + url + "&pageNo=" + this.getPrePageNo()
					+ "\" class=\"cBlue\">上一页</a> | ");
		} else {
			pagebuffer.append("上一页 | ");
		}
		
		
		// 设置下一页
		if (this.currentPageNo < this.maxPageNo) {
			pagebuffer.append("<a href=\"" + url + "&pageNo=" + this.getNextPageNo()
					+ "\" class=\"cBlue\">下一页</a> | ");
		} else {
			pagebuffer.append("下一页 | ");
		}
		
		
		// 设置最后一页
		if ((this.currentPageNo < this.maxPageNo) && (this.maxPageNo > 1)) {
			pagebuffer.append("<a href=\"" + url + "&pageNo=" + this.maxPageNo
					+ "\" class=\"cBlue\">末页</a>&nbsp;]");
		} else {
			pagebuffer.append("末页&nbsp;]");
		}
		
		pagebuffer.append("&nbsp;&nbsp;<input type=text name=pageNo size=3>&nbsp;页&nbsp;");
		pagebuffer.append("<input type=submit value=Go>&nbsp");
		pagebuffer.append("</div></form>");
		pagebuffer.append("\r\n<script language=javascript>\r\n");
		pagebuffer.append("\t\t var maxPage=");
		pagebuffer.append(this.maxPageNo);
		pagebuffer.append(";\r\n\t\t function goPage(){\r\n");
		pagebuffer.append("\r\n         var pageNo= frmPage.pageNo.value;");
		pagebuffer.append("\r\n         if(isNaN(pageNo) || pageNo<1 || pageNo>maxPage){    ");
		pagebuffer.append("\r\n         alert('请输入1--'+maxPage+'的整数');    ");
		pagebuffer.append("\r\n         }    ");
		pagebuffer.append("\t\t  		else{\r\n  window.location=\"");
		pagebuffer.append(url);
		pagebuffer.append("&pageNo=\"+frmPage.pageNo.value;\r\n");
		pagebuffer.append("}");
		pagebuffer.append("\t\t return false;  }\r\n ");
		pagebuffer.append("</script>\r\n");
		
		
		return pagebuffer.toString();
	}
	/*==========输出结束==============*/
	
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
	 * 获得前一页的页号
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
	 * 获得下一页的页号
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
	 * 获得记录总数
	 * @return
	 */
	private int getRecordCnt(){
		return this.recordCnt;
	}
	
	/**
	 * @Description:(查询SQL分页处理)
	 * @return
	 * @date Nov 28, 2011 4:38:55 PM
	 */
	private String getPagingQuery(){
		
		StringBuffer mQuery = new StringBuffer();
		
		int startRownum = 0;
		int endRownum = 0;
		
		startRownum = (this.currentPageNo - 1) * this.pageSize + 1;//
		endRownum = this.currentPageNo * this.pageSize;
		
		/** 改进：考虑到查询效率问题，用between and 查询效率高于用大于小于 */
		//注意 oracle的between and是前后都是闭区间，也就是说包含两个端的数
		mQuery.append("SELECT * FROM (\n");
		mQuery.append("	  SELECT ROWNUM AS PAGE_RN , A.*\n");
		mQuery.append("     FROM ( \n");
		mQuery.append(sql);		
		mQuery.append(		    ") A ) \n");
		mQuery.append("WHERE PAGE_RN   BETWEEN "+ startRownum +" AND "+ endRownum +"\n");
		
		return mQuery.toString();
	}
	
	/**
	 * @Description:(查询SQL Count计算sql)
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
