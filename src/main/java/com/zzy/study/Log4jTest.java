package com.zzy.study;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4jTest {
	//static Logger log = 	Logger.getLogger("sss");
	//static Logger log = 	Logger.getRootLogger();
	public static void main(String[] args) {
		
		Logger log = 	Logger.getLogger(Log4jTest.class);//�ڲ����ڴ����Ƶ�logger������£���newһ�����ء����򷵻��Ѵ��ڵ�logger
		//BasicConfigurator.configure();
		log.debug("==============log test!=============");
	}
}
