package com.zzy.study;

public class TestSychroMain {
	public final synchronized static void main(String []args){
		System.gc();
		long i=Math.round(11.5);
		long j=Math.round(-11.5);
		System.out.print(i+","+j);
		int a[]={1,5,6,8};
		System.out.println(" "+a.length);
		int[][]  aaa=new int[9][5];
		int aa=aaa.length;
	}
}
