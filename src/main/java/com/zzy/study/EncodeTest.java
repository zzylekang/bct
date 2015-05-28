package com.zzy.study;

import java.io.UnsupportedEncodingException;

public class EncodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "≤‚ ‘";
		try {
			byte[] barr = str.getBytes("GBK");
			System.out.println(barr);
			String strnew  = new String(barr, "GBK");
			System.out.println(strnew);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
