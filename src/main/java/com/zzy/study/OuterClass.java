package com.zzy.study;

public class OuterClass {
	int index=66;
	public OuterClass() { 
		InterClass ic = new InterClass(); 
		System.out.println("OuterClass Create"); 
	}
	
	private class InterClass { 
		public InterClass() { 
			System.out.println("InterClass Create");
			System.out.println(index);
		} 
	}

	public static void main(String[] args) {
		//OuterClass ic = new OuterClass();
		OuterClass oc = new OuterClass(); 
		//InnerClass ic = new InnerClass();
	} 
}



