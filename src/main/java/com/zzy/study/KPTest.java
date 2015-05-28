package com.zzy.study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 疑难知识点测试
 * @author Administrator
 *
 */
public class KPTest {
	
	public static void main(String [] args) throws IOException {
		//KPTest.testReturn("bbb");
		//KPTest.otherTest();
	}
	
	private static String readAline() throws IOException {
		String rd = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入：");
		rd = br.readLine();
		
		return rd;
	}
	
	/*
	 * 静态常量测试
	 * 
	 */
	private static void staticConstTest(){
		System.out.println(Const.XX);
	}
	
	private static void bigDecimalTest() {
		System.out.println(new BigDecimal(1515151).toString());
		
		BigDecimal feeRate = new BigDecimal("1.041");
		BigDecimal actualPay = new BigDecimal("10");
		BigDecimal commissionAmount = actualPay.multiply(feeRate);//佣金
		System.out.println(commissionAmount);
		System.out.println(commissionAmount.setScale(0, BigDecimal.ROUND_HALF_UP));
	}
	
	/**
	 * finally 测试
	 * 
	 * 
	 */
	public static void finallyTest() {
		System.out.println("return value of getValue(): " + getValue());
	}
	
	/**
	 * finally 测试
	 * 
	 * 
	 */
	public static void finallyTest2() {
		System.out.println(f(2));
	}
	
	public static int f(int n)
	{
		try
		{
			int r = n * n;
			return r;
		}
		finally
		{
			if (n == 2)
			{
				return 0;
			}
		}
	}
	
	private static int getValue() {
		int i = 1;
		try {
			return i;
		} finally {
			System.out.println("i :  finally");
			++i;
		}
	}
    
    /**
     * 赋值、加减运算测试
     * 
     */
    public static void operationTest() {
		int x = 0;
		int y = x++;//先将x赋值给y，再将x自加1   该语句等价于==>  y=x=x+1;
		System.out.println(y);
		y = ++x;//先将x自加1，再将x赋值给y    该语句等价于==>  y=(x=x+1);
		System.out.println(y);
		
		int a = 3;
		a += a -= a*a;//java的赋值语句的结合性是从左向右的（c语言不是这样的，结合性为从右向左的，turboC的测试结果为a=-12）
		System.out.println("a=" + a);
		
		int m,n1,a1,b1;
		m=n1=a1=b1=1;
		
		long n = 1;
		float f = n;
		System.out.println(f);
		System.out.println(15/2);
		System.out.println(15f/2);
		System.out.println(n + f);
		
		String value1="cooks";
		String value2="ceeol";
		char o='o';
		char e='e';
		int ii = o - e;
		System.out.println(ii);
		int i=value1.compareTo(value2);
		System.out.println(i);
		int arr[][]=new int[10][10];
		int [][]brr=new int[10][10];
		int mm=11;
		int nn=3;
		System.out.println(mm/nn);
		System.out.println(mm%nn);
    }
    
    /**
     * 逻辑运算符测试
     * &（与） 、|（或）、~（非）、^（异或）：位运算符
     * 
     * 由于boolean类型在内存中也是二进制表示，因此也可以进行位操作，异或除外
     */
    public static void logicOperatorTest() {
		boolean a = true;
		boolean b = false;
		if (a & b) {
			System.out.println("a & b= true");
		} else {
			System.out.println("a & b=false");
		}

		System.out.println(a);
		System.out.println(a & b);
		System.out.println(a | b);
		System.out.println(a ^ b);
		System.out.println();
		
		System.out.println("-----------------------");
		System.out.println(a && b);
		System.out.println(a || b);
    }
    
    /**
     * 其它测试
     * @throws IOException 
     */
    public static void otherTest() throws IOException {
		int z,k;
		for (z =1,k=1; k<3 || z <3;z++,k++) {
			
		}
		InputStream is = KPTest.class.getResourceAsStream("/readme");
		
		int len = is.available();
		byte [] bytes = new byte[len];
		is.read(bytes);
		String fileStr = new String(bytes, "GBK");
		System.out.println(fileStr);
		System.out.println(KPTest.class.getResource(""));
		System.out.println(KPTest.class.getResource("/"));
    }
    
    /**
     * 编码相关的测试
     * 
     * java使用Unicode进行编码，每个字符占用两个字节
     * char 类型占两个字节，最大值是65535
     */
    public static void encodeTest() {
		String greeting = "hello,未来！";
		int n = greeting.length();
		
		/*codePoint:码点，我译为“码位值”。
		 * 每个码位值实际上代表一个真正unicode字符。即unicode字符集上的码位值。
		 * 为什么要这些码位相关的方法?
		 * 源自1个java的char字符并不完全等于一个unicode的字符。
		 * char采用UCS-2编码是一种淘汰的UTF-16编码，最多65536种形态，也远少于当今unicode拥有11万字符的需求。
		 * java只好对后来新增的unicode字符用2个char拼出1个unicode字符。导致String中char的数量不等于unicode字符的数量。
		 */
		char ch = '呵';//java中的char占用两个字节
		int cpCount = greeting.codePointCount(0, n);
		char first = greeting.charAt(1);
		char last = greeting.charAt(4);
		int index = greeting.offsetByCodePoints(3, 3);//在index基础上，偏移codePointOffset个代码点的索引。
		int cp = greeting.codePointAt(index);
		char c = (char)cp;
		int maxCharv = (int)Character.MAX_VALUE;
		
		
        //下面对char型进行讨论
        char[] codeUnits;
        //基本多语言级别 basic multilingual plane
        //ASC2 code
        for(int i = 0 ; i < 128 ;i++){
            codeUnits = Character.toChars(i);
            if( codeUnits.length == 1 ){
                System.out.println(i+" "+(char)i);
            }
        }
        
		//汉字 '严'
        char yan = '严';
        codeUnits = Character.toChars(0x4e25);
        System.out.printf("汉字："+yan+" 代码点所占代码单元长度" + codeUnits.length + " (0x%x) ,它属于基本多语言级别。",
        		(int)codeUnits[0]);
        System.out.println();
        
       //辅助字符 supplementary character
        //代码点0x1d56b
        codeUnits = Character.toChars(0x1d56b);
        String str = new String(codeUnits);
        System.out.println(str);
        
        //判断代码单元的高低位
        System.out.printf("代码点0x%x 在UTF-16表示中被分解为两个代码单元 0x%x 0x%x ", 0x105600,(int)codeUnits[0],
        		(int)codeUnits[1]);
        System.out.println();
        System.out.printf("0x%x is HighSurrogate:"+Character.isHighSurrogate(codeUnits[0]),(int)codeUnits[0]);
        System.out.println();
        System.out.printf("0x%x is HighSurrogate:"+Character.isHighSurrogate(codeUnits[1]),(int)codeUnits[1]);
        System.out.println();
        System.out.printf("0x%x is LowSurrogate:"+Character.isLowSurrogate(codeUnits[0]), (int)codeUnits[0]);
        System.out.println();
        System.out.printf("0x%x is LowSurrogate:"+Character.isLowSurrogate(codeUnits[1]),(int)codeUnits[1]);
        System.out.println();

    }
    /**
     * 编码相关的测试
     * 
     */
    public static void encodeTest2() {

		try{
			
			
			String str = "字符测试";
			byte[] bs = str.getBytes("GB2312");
			FileOutputStream fos = new FileOutputStream("utf16.txt");
			fos.write(bs);
			fos.close();
			
			FileInputStream fis = new FileInputStream("utf16.txt");
			//InputStreamReader isr = new InputStreamReader(fis, "iso-8859-1");
			
			//char[] cs = new char[100];
			//isr.read(cs);
			//isr.close();
			
			//String sss = new String(cs);
			
			byte[] bs2 = new byte[fis.available()];
			fis.read(bs2);
			fis.close();
			String sss = new String(bs2,"iso-8859-1");
			String sss2 = new String(sss.getBytes("iso-8859-1"),"GB2312");
			
			System.out.println(sss);
			System.out.println(sss2);
			
			/*String str = "字符测试";
			byte[] bs = str.getBytes("Shift_JIS");*/
			
			
			/*
			FileInputStream fis = new FileInputStream("utf16.txt");
			InputStreamReader isr = new InputStreamReader(fis, "iso-8859-1");
			
			char[] cs = new char[100];
			fis.read(cs);
			fis.close;
			
			System.out.println(new String(cs));
			
			SortedMap<String,Charset> sm = Charset.availableCharsets();
			Iterator<Map.Entry<String,Charset>> it = sm.entrySet().iterator();
			while(it.hasNext()){  
				 Map.Entry<String,Charset> en = it.next();
				 System.out.println(en.getKey() +"----->" + en.getValue() ); 
			}
			*/
			
		} catch(Exception e){}
	
    }
    /**
     * 编码相关的测试
     * 
     */
    public static void encodeTest3() {

		try{
			
			String str = "字符测试";
			byte[] bs = str.getBytes("GB2312");
			FileOutputStream fos = new FileOutputStream("utf16.txt");
			fos.write(bs);
			fos.close();
			
			FileInputStream fis = new FileInputStream("utf16.txt");
			//InputStreamReader isr = new InputStreamReader(fis, "iso-8859-1");
			
			//char[] cs = new char[100];
			//isr.read(cs);
			//isr.close();
			
			//String sss = new String(cs);
			
			byte[] bs2 = new byte[fis.available()];
			fis.read(bs2);
			fis.close();
			String sss = new String(bs2,"iso-8859-1");
			String sss2 = new String(sss.getBytes("iso-8859-1"),"GB2312");
			
			System.out.println(sss);
			System.out.println(sss2);
			/*
			FileInputStream fis = new FileInputStream("utf16.txt");
			InputStreamReader isr = new InputStreamReader(fis, "iso-8859-1");
			
			char[] cs = new char[100];
			fis.read(cs);
			fis.close;
			
			System.out.println(new String(cs));
			
			SortedMap<String,Charset> sm = Charset.availableCharsets();
			Iterator<Map.Entry<String,Charset>> it = sm.entrySet().iterator();
			while(it.hasNext()){  
				 Map.Entry<String,Charset> en = it.next();
				 System.out.println(en.getKey() +"----->" + en.getValue() ); 
			}
			*/
			
		} catch(Exception e){}
	
    }
    /**
     * java 中boolean占用的字节数
     * 
     * 见：http://blog.csdn.net/lovexp2010/article/details/7839377
     */
    
    
    /**
     * switch 测试
     */
    public static void switchTest() {
		char t = 'a';
		long s = 1212;
		switch(t){//只接受类int类型，如:char、int、enum
		case 'c':
			System.out.println("c");
		case 'a':
			System.out.println("a");
			//break;
		case 'd':
			System.out.println("d");
			break;
		}
    }
    
    private enum Color {red, blue, green}//there is not a ";"
    
    /**
     * 枚举测试
     * 
     */
    public static void enumTest() {
      for(Color s : Color.values()) {    
    	  //enum的values()返回一个数组，这里就是Seasons[]    
    	  System.out.println(s);    
      }
    }
    /**
     * 在实例化子类时，需要先执行父类构造方法。
     */
    
    /**
     * treeSet 测试
     */
    public static void treeSetTest() {

		TreeSet<String> set=new TreeSet<String>();
		set.add("wang");
		set.add("zhang");
		set.add("wang");
		Iterator<String> it=set.iterator();
		while(it.hasNext()){
			System.out.print(it.next());
		}
	
    }
    
    /**
     * switch 测试
     */
    public static void switchTest2() {

		// TODO Auto-generated method stub
		int zb = 1;
		
		switch (zb) {
		
		case 1:
			if (zb == 1) {
				break;//该语句同样起到跳出switch语句的作用
			}
			System.out.println("after if break ");
			break;
		case 2:
			System.out.println("case 2 ");
			break;
		default:
			System.out.println("default ");
		}
		System.out.println("after switch ");
	
    }
    
    public static String testReturn(String a) {
    	String b = "bbb";
    	if (b.equals(a)) {
    		return "equals";
    	}
    	
    	return null;
    }
}
