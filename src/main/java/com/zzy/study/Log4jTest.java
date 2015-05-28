package com.zzy.study;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4jTest {
	//static Logger log = 	Logger.getLogger("sss");
	//static Logger log = 	Logger.getRootLogger();
	public static void main(String[] args) {
		
		Logger log = 	Logger.getLogger(Log4jTest.class);//在不存在此名称的logger的情况下，会new一个返回。否则返回已存在的logger
		//BasicConfigurator.configure();
		log.debug("==============log test!=============");
	}
}
