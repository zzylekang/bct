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
 * ����֪ʶ�����
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
		System.out.println("�����룺");
		rd = br.readLine();
		
		return rd;
	}
	
	/*
	 * ��̬��������
	 * 
	 */
	private static void staticConstTest(){
		System.out.println(Const.XX);
	}
	
	private static void bigDecimalTest() {
		System.out.println(new BigDecimal(1515151).toString());
		
		BigDecimal feeRate = new BigDecimal("1.041");
		BigDecimal actualPay = new BigDecimal("10");
		BigDecimal commissionAmount = actualPay.multiply(feeRate);//Ӷ��
		System.out.println(commissionAmount);
		System.out.println(commissionAmount.setScale(0, BigDecimal.ROUND_HALF_UP));
	}
	
	/**
	 * finally ����
	 * 
	 * 
	 */
	public static void finallyTest() {
		System.out.println("return value of getValue(): " + getValue());
	}
	
	/**
	 * finally ����
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
     * ��ֵ���Ӽ��������
     * 
     */
    public static void operationTest() {
		int x = 0;
		int y = x++;//�Ƚ�x��ֵ��y���ٽ�x�Լ�1   �����ȼ���==>  y=x=x+1;
		System.out.println(y);
		y = ++x;//�Ƚ�x�Լ�1���ٽ�x��ֵ��y    �����ȼ���==>  y=(x=x+1);
		System.out.println(y);
		
		int a = 3;
		a += a -= a*a;//java�ĸ�ֵ���Ľ�����Ǵ������ҵģ�c���Բ��������ģ������Ϊ��������ģ�turboC�Ĳ��Խ��Ϊa=-12��
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
     * �߼����������
     * &���룩 ��|���򣩡�~���ǣ���^����򣩣�λ�����
     * 
     * ����boolean�������ڴ���Ҳ�Ƕ����Ʊ�ʾ�����Ҳ���Խ���λ������������
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
     * ��������
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
     * ������صĲ���
     * 
     * javaʹ��Unicode���б��룬ÿ���ַ�ռ�������ֽ�
     * char ����ռ�����ֽڣ����ֵ��65535
     */
    public static void encodeTest() {
		String greeting = "hello,δ����";
		int n = greeting.length();
		
		/*codePoint:��㣬����Ϊ����λֵ����
		 * ÿ����λֵʵ���ϴ���һ������unicode�ַ�����unicode�ַ����ϵ���λֵ��
		 * ΪʲôҪ��Щ��λ��صķ���?
		 * Դ��1��java��char�ַ�������ȫ����һ��unicode���ַ���
		 * char����UCS-2������һ����̭��UTF-16���룬���65536����̬��ҲԶ���ڵ���unicodeӵ��11���ַ�������
		 * javaֻ�öԺ���������unicode�ַ���2��charƴ��1��unicode�ַ�������String��char������������unicode�ַ���������
		 */
		char ch = '��';//java�е�charռ�������ֽ�
		int cpCount = greeting.codePointCount(0, n);
		char first = greeting.charAt(1);
		char last = greeting.charAt(4);
		int index = greeting.offsetByCodePoints(3, 3);//��index�����ϣ�ƫ��codePointOffset��������������
		int cp = greeting.codePointAt(index);
		char c = (char)cp;
		int maxCharv = (int)Character.MAX_VALUE;
		
		
        //�����char�ͽ�������
        char[] codeUnits;
        //���������Լ��� basic multilingual plane
        //ASC2 code
        for(int i = 0 ; i < 128 ;i++){
            codeUnits = Character.toChars(i);
            if( codeUnits.length == 1 ){
                System.out.println(i+" "+(char)i);
            }
        }
        
		//���� '��'
        char yan = '��';
        codeUnits = Character.toChars(0x4e25);
        System.out.printf("���֣�"+yan+" �������ռ���뵥Ԫ����" + codeUnits.length + " (0x%x) ,�����ڻ��������Լ���",
        		(int)codeUnits[0]);
        System.out.println();
        
       //�����ַ� supplementary character
        //�����0x1d56b
        codeUnits = Character.toChars(0x1d56b);
        String str = new String(codeUnits);
        System.out.println(str);
        
        //�жϴ��뵥Ԫ�ĸߵ�λ
        System.out.printf("�����0x%x ��UTF-16��ʾ�б��ֽ�Ϊ�������뵥Ԫ 0x%x 0x%x ", 0x105600,(int)codeUnits[0],
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
     * ������صĲ���
     * 
     */
    public static void encodeTest2() {

		try{
			
			
			String str = "�ַ�����";
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
			
			/*String str = "�ַ�����";
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
     * ������صĲ���
     * 
     */
    public static void encodeTest3() {

		try{
			
			String str = "�ַ�����";
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
     * java ��booleanռ�õ��ֽ���
     * 
     * ����http://blog.csdn.net/lovexp2010/article/details/7839377
     */
    
    
    /**
     * switch ����
     */
    public static void switchTest() {
		char t = 'a';
		long s = 1212;
		switch(t){//ֻ������int���ͣ���:char��int��enum
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
     * ö�ٲ���
     * 
     */
    public static void enumTest() {
      for(Color s : Color.values()) {    
    	  //enum��values()����һ�����飬�������Seasons[]    
    	  System.out.println(s);    
      }
    }
    /**
     * ��ʵ��������ʱ����Ҫ��ִ�и��๹�췽����
     */
    
    /**
     * treeSet ����
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
     * switch ����
     */
    public static void switchTest2() {

		// TODO Auto-generated method stub
		int zb = 1;
		
		switch (zb) {
		
		case 1:
			if (zb == 1) {
				break;//�����ͬ��������switch��������
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
