package com.zzy.study.pagerTest.bean;

import java.util.Date;

public class FCTRADE_FEEbean {
	public String FEE_ID ;
	public String FEE_TYPE ;
	public double FEE_BLANCE ;	//��ʼԤ�շ���
	public Date FEE_TIME ;
	public String FEE_STATUS ;
	public String USERID ;
	public String FEE_TYPE_NAME ;
	public String MEMO ;
	public double ACTUAL_CHARGE ;//ʵ�ʷ���
	public String ACCOUNT_DJ_ID ;//��FCTRADE_CUS_DJ��Ӧ
	public String CHECK_STATE	;//NUMBER(1)	Y			0δ���ˣ�1��������2���������쳣
	public String CHARGE_TYPE	;//NUMBER(1)	Y	1		1��δ��2�������
	public Date   CHECK_DATE	;//DATE	Y			
	public Date   CHARGE_DATE	;//DATE	Y
	public String FEE_NO_1 ;
	public String FEE_NO_2 ;
	public String ISJGUSER ="0" ;//�Ƿ��Ǽ���û�--�ﳬ2010-7-9
	public String CHARGE_SQUARE = "1";
	public Date ACTUAL_CHARGE_DATE;//ʵ�����ɷ�������
	public String FLAG = "0";
	public String BANK_TYPE;//��������,����,01����02������03��00��ǰ�������µĴ���
	public String FLOW_NO;//��ˮ��
	public String BAK_STATUS;
	public Date END_DATE; //���нɷѽ�ֹ����
	public double FEE_BAK;
}

