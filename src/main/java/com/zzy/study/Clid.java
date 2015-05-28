package com.zzy.study;

public class Clid extends Parent{
	@Override
	public void parentm2(){
		System.out.println("Clid.parentm2");
	}
	
	public static void main(String [] args){
		Clid c = new Clid();
		c.parentm();
	}
}
