package com.zzy.dev.project.base.component.pager3;

import java.util.List;
/**
 * ��ҳ��˼��
 * ���ݵ�ȡ�÷�ʽ:
 * 			��1����session��ȡ�ã�һ����ȡ��ȫ���򲿷����ݣ��������ݻ��浽session�У�֮��ÿ�δ�session��ȡ��
 * 			��2��ÿ�ζ���DB��ȡ����
 * ��ҳȡ�õ�����Ϊ��ʵʱ�Եģ���Ϊ�п����²������������������ԭ����������Ѿ�������ҳ�С�
 * ���⣺��ʵ�ʵ�Ӧ���У��п������ݲ�����Դ��һ����������Դ�ڶ�����������ǵ�dao���ǽ����ڵ�������ϵķ�װ��Ϊ�˽�����
 * ���⣬������Ҫ�����ݿ��н���һ��View��������ʵ���˶������ݲ�ѯ��
 * @author Administrator
 *
 */
public class pageBean {
	private List currentPageData;//��ǰҳ������
	private List pageData;//�������������
	private int cacheCnt;//Ҫ�����ҳ��
	private int currentPageNo;//��ǰҳ
	private int maxPage;//���ҳ��
	private int count;//�ܹ��ļ�¼����
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
