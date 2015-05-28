package com.zzy.dev.project.base.component.pager3.service;

import java.util.List;

public interface PageService {
	/**
	 * ���ҳ�浼���ؼ�
	 * ���磺��ҳ ��һҳ 1 2 3 4 5 ��һҳ ĩҳ �� XXX ҳ
	 */
	String getNavigator();
	
	/**
	 * ��õ�ǰ��ҳ��
	 */
	int getPageNo();
	
	/**
	 * ǰһҳ
	 */
	List getPreviousPage();
	
	/**
	 * ��һҳ
	 */
	List getNextPage();
	
	/**
	 * ������ҳ��
	 */
	int getMaxPageNo();
	
	/**
	 * �����ҳ
	 */
	List getFirstPage();
	
	/**
	 * ĩҳ
	 */
	List getLastPage();
	
	/**
	 * ���ָ��ҳ������
	 */
	List getPageByPageNo(int pageNo);
}
