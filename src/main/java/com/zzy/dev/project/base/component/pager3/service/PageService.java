package com.zzy.dev.project.base.component.pager3.service;

import java.util.List;

public interface PageService {
	/**
	 * 获得页面导航控件
	 * 形如：首页 上一页 1 2 3 4 5 下一页 末页 至 XXX 页
	 */
	String getNavigator();
	
	/**
	 * 获得当前的页号
	 */
	int getPageNo();
	
	/**
	 * 前一页
	 */
	List getPreviousPage();
	
	/**
	 * 下一页
	 */
	List getNextPage();
	
	/**
	 * 获得最大页号
	 */
	int getMaxPageNo();
	
	/**
	 * 获得首页
	 */
	List getFirstPage();
	
	/**
	 * 末页
	 */
	List getLastPage();
	
	/**
	 * 获得指定页的数据
	 */
	List getPageByPageNo(int pageNo);
}
