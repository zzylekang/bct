package com.zzy.study;

class SplitString { 
	String SplitStr; 
	int SplitByte; 
	public SplitString(String str,int bytes){ 
		SplitStr=str; 
		SplitByte=bytes; 
		System.out.println("The String is:��"+SplitStr+"��;SplitBytes="+SplitByte); 
	} 
	public void SplitIt() { 
		int loopCount; 
		loopCount=(SplitStr.length()%SplitByte==0)?(SplitStr.length()/SplitByte):(SplitStr.length()/SplitByte+1); 
		System.out.println("Will Split into "+loopCount); 
		for (int i=1;i<=loopCount ;i++ ) { 
			if (i==loopCount){ 
				System.out.println(SplitStr.substring((i-1)*SplitByte,SplitStr.length())); 
			} else { 
				System.out.println(SplitStr.substring((i-1)*SplitByte,(i*SplitByte))); 
			} 
		} 
	} 
	public static void main(String[] args) { 
		SplitString ss = new SplitString("test��dd��dsaf���д�3443n�й�43�й���0ewldfls=103",4); 
		ss.SplitIt(); 
	} 
}

