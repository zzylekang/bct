package com.zzy.tools;

import java.io.File;

/**
 * ���ദ��Ķ������ļ�����Ϊ��������Լ��ļ��ڵ����ݴ���
 * ���룺һ�������ļ����ļ��У��Լ��Զ���ġ������߼�����˳��
 * 		ע�⣺������ļ����ļ����ޱ���·������֧���ϴ���Ĵ���
 * 			�Ժ���ܻ�����ļ��ϴ����ļ������ļ��������ϴ���������Ե�����ģ����ڣ�����ֻרע�����ݵĴ���
 * �����һ�������ļ����ļ���
 * �������������Զ���Ĵ����߼�
 * @author Administrator
 *
 */
public class FileDataProcessor {
	private String dirSep = File.pathSeparator;
	
	/*==========���뿪ʼ==============*/
	private String input = "D:/DataProcessCenter/01input/copy.txt";//�����·�����ļ���ȫ·����·��+�ļ�����
	private String output = "D:/DataProcessCenter/02output/result.txt";//����Ľ��
	
	//pk1...pkn�����߼��Ĵ���˳��
	private String[] pkarr = null;//�����key���飬����key�Ĳ�ͬ���ò�ͬ�Ĵ����߼�
	public FileDataProcessor(String inputp, String outputp, String[] pkarrp){
		this.input = inputp;
		this.output = outputp;
		this.pkarr = pkarrp;
	}
	/*==========�������==============*/
	
	/*==========�����ʼ==============*/
	
	/*==========�������==============*/
	
	/*==========�����߼���ʼ==============*/
	
	/*==========�����߼�����==============*/
}
