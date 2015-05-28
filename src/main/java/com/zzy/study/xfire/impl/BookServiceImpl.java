package com.zzy.study.xfire.impl;

import com.zzy.study.xfire.BookService;


public class BookServiceImpl implements BookService{

	public String getBookNames() {
		long timeout = 1000*60;
		try {
			System.out.println(System.currentTimeMillis());
			
			Thread.sleep(timeout);
			
			System.out.println(System.currentTimeMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "平凡的世界, 肖申克的救赎";
	}

}
