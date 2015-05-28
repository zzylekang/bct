package com.zzy.study;
class One {       
  synchronized void display(int num)  { 
       System.out.print(""+num);
       try {
          Thread.sleep(1000); 
       }   
      catch(InterruptedException e)  {
           System.out.println("ÖÐ¶Ï");
       }
       System.out.println(" Íê³É");
 }
}


class Two implements Runnable {
  int number; 
  One one;
  Thread t;
  public Two(One one_num, int n)   {
      one=one_num;  number=n;
      t=new Thread(this);
      t.start();
   }
   public void run() {
        one.display(number);
   }
}

class Synch {
	   public static void main(String args[]) {
	       One one=new One();
	       int digit=10;
	       Two s1=new Two(one,digit++);
	       Two s2=new Two(one,digit++);
	       Two s3=new Two(one,digit++);
	       
	       try {
			s1.t.join();
		    s2.t.join();
		    s3.t.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	  
	  }
	}
