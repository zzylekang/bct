package com.zzy.study.pagerTest.bean;

import java.util.Date;

public class FCTRADE_FEEbean {
	public String FEE_ID ;
	public String FEE_TYPE ;
	public double FEE_BLANCE ;	//开始预收费用
	public Date FEE_TIME ;
	public String FEE_STATUS ;
	public String USERID ;
	public String FEE_TYPE_NAME ;
	public String MEMO ;
	public double ACTUAL_CHARGE ;//实际费用
	public String ACCOUNT_DJ_ID ;//与FCTRADE_CUS_DJ对应
	public String CHECK_STATE	;//NUMBER(1)	Y			0未对账，1对账无误，2对账数据异常
	public String CHARGE_TYPE	;//NUMBER(1)	Y	1		1，未提款，2，已提款
	public Date   CHECK_DATE	;//DATE	Y			
	public Date   CHARGE_DATE	;//DATE	Y
	public String FEE_NO_1 ;
	public String FEE_NO_2 ;
	public String ISJGUSER ="0" ;//是否是监管用户--孙超2010-7-9
	public String CHARGE_SQUARE = "1";
	public Date ACTUAL_CHARGE_DATE;//实际生成费用日期
	public String FLAG = "0";
	public String BANK_TYPE;//银行类型,工商,01建行02，华夏03，00以前建行线下的贷款
	public String FLOW_NO;//流水号
	public String BAK_STATUS;
	public Date END_DATE; //信托缴费截止日期
	public double FEE_BAK;
}

