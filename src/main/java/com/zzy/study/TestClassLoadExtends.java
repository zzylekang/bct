package com.zzy.study;
//ͨ��debug��F5ִ�п�֪��ִ�й��̣��ݴ˿�֪ԭ��
 class TestClassLoadExtends {
	int index=30;
	TestClassLoadExtends(){
		//System.out.println(this.index);
		//this.show();
		show();//�ȼ���this.show()��ִ�е�ǰʵ����Ӧ�ķ���������this��Test1��ʵ�������ִ��Test1��show()
	}
	public void show(){
		System.out.println(index);
	}
}
 class Test1 extends TestClassLoadExtends{
	 int index =50;//����ʵ��������ʱ�ų�ʼ���������ʱ��Ĭ��ֵ��ʵ������ʹ�õ��Ǳ���ĳ�Ա����
	 Test1(){
		 System.out.println(this.index);
		 this.index=100;
	 }
	 public void show(){
		System.out.println(index);
	}
	 public static void main(String []args){
		 TestClassLoadExtends t=new Test1();
		 t.show();
	 }
 }