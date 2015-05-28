package com.zzy.dev.project.base.component.pager3;

import java.util.List;
/**
 * 分页的思想
 * 数据的取得方式:
 * 			（1）从session中取得，一次性取得全部或部分数据，并将数据缓存到session中，之后每次从session中取得
 * 			（2）每次都从DB中取数据
 * 分页取得的数据为非实时性的，因为有可能新产生的数据由于排序等原因而出现在已经翻过的页中。
 * 问题：在实际的应用中，有可能数据不是来源于一个表，而是来源于多个表，但是我们的dao类是建立在单表基础上的封装，为了解决这个
 * 问题，我们需要在数据库中建立一个View，这样就实现了多表的数据查询。
 * @author Administrator
 *
 */
public class pageBean {
	private List currentPageData;//当前页的数据
	private List pageData;//缓存出来的数据
	private int cacheCnt;//要缓存的页数
	private int currentPageNo;//当前页
	private int maxPage;//最大页数
	private int count;//总共的记录条数
	private String url;
	
	public List getCurrentPageData() {
		return currentPageData;
	}
	public void setCurrentPageData(List currentPageData) {
		this.currentPageData = currentPageData;
	}
	public List getPageData() {
		return pageData;
	}
	public void setPageData(List pageData) {
		this.pageData = pageData;
	}
	public int getCacheCnt() {
		return cacheCnt;
	}
	public void setCacheCnt(int cacheCnt) {
		this.cacheCnt = cacheCnt;
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
