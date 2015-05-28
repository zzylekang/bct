package com.zzy.study;
//通过debug，F5执行可知其执行过程，据此可知原理
 class TestClassLoadExtends {
	int index=30;
	TestClassLoadExtends(){
		//System.out.println(this.index);
		//this.show();
		show();//等价于this.show()，执行当前实例对应的方法，由于this是Test1的实例，因此执行Test1的show()
	}
	public void show(){
		System.out.println(index);
	}
}
 class Test1 extends TestClassLoadExtends{
	 int index =50;//属于实例，构造时才初始化，类加载时赋默认值，实例方法使用的是本类的成员变量
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