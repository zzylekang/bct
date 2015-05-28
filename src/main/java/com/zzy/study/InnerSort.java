package com.zzy.study;

import java.util.*; 
class InsertSort { 
	ArrayList al; 
	public InsertSort(int num,int mod) { 
		al = new ArrayList(num); 
		Random rand = new Random(); 
		System.out.println("The ArrayList Sort Before:"); 
		for (int i=0;i<num;i++) { 
			al.add(new Integer(Math.abs(rand.nextInt()) % mod + 1)); 
			System.out.println("al["+i+"]="+al.get(i)); 
		} 
	} 
	public void SortIt() { 
		Integer tempInt; 
		int MaxSize=1; 
		for(int i=1;i<al.size();i++)  { 
			tempInt = (Integer)al.remove(i); 
			if(tempInt.intValue()>=((Integer)al.get(MaxSize-1)).intValue()){ 
				al.add(MaxSize,tempInt); 
				MaxSize++; 
				System.out.println(al.toString()); 
			} else { 
				for (int j=0;j<al.size();j++)  { 
					if (((Integer)al.get(j)).intValue()>=tempInt.intValue()) { 
						al.add(j,tempInt); 
						MaxSize++; 
						System.out.println(al.toString()); 
						break; 
					} 
				} 
			} 
		} 
		System.out.println("The ArrayList Sort After:"); 
		for(int i=0;i<al.size();i++) { 
			System.out.println("al["+i+"]="+al.get(i)); 
		} 
	} 
	public static void main(String[] args) { 
		InsertSort is = new InsertSort(10,100); 
		is.SortIt(); 
	} 
}

