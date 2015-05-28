package com.zzy.study.sort;

public class Sort {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
        int[] testArr = {12,-45,0,15,16};
        testArr[0] = 10;
        testArr[1] = -41;
        testArr[2] = 66;
        testArr[3] = 101;
        testArr[4] = 1;
        Sort sort = new Sort();
        sort.sort(testArr);
        sort.sort4(testArr);
        System.out.println("____________________");
        sort.sort_improve(testArr);
	}
	
	/**
	 * @param arrint
	 */
	public void sort(int[] arrint){
		//int[] a = new int[100];
		System.out.print("----------------");
		// int array's length
		int len = arrint.length;

		// outer xunhuan's counter
		int cntOut = len - 1;
		int cntInner = cntOut - 1;
		int tmp = 0;
		for (int i = 0; i <= cntOut; i++ ) {
			for (int j = 0; j <= cntInner; j++) {
				if (arrint[j] > arrint[j+1]) {
					tmp = arrint[j];
					arrint[j] = arrint[j+1];
					arrint[j+1] = tmp;
				}
			}
		}

		System.out.println();
		for (int i = 0; i <= len - 1; i++) {
			System.out.println(arrint[i]);
		}
	}
	
	/**
	 * @param arrint
	 */
	public void sort_improve(int[] arrint){
		//int[] a = new int[100];
		System.out.print("-------------------");
		// int array's length
		int len = arrint.length;
		
		// outer xunhuan's controller
		int cntOut = len - 2;
		int cntInner = len - 2;
		int tmp = 0;
		for (int i = 0; i <= cntOut; i++ ) {
			for (int j = 0; j <= cntInner; j++) {
				if (arrint[j] > arrint[j+1]) {
					tmp = arrint[j];
					arrint[j] = arrint[j+1];
					arrint[j+1] = tmp;
				}
			}
			cntInner--;
		}
		
		System.out.println();
		for (int i = 0; i <= len -1; i++) {
			System.out.println(arrint[i]);
		}
	}
	
	public void sort2(int[] arrint) {
		System.out.println("xuan ze pai xu!");
		int len = arrint.length;
		int cntOut = len - 2;
		int cntInner = len - 1;
		int index = 0;
		int tmp = 0;
		//int startInner = 0;
		for (int i = 0; i <= cntOut; i++){
			tmp = arrint[i];
			index = i;
			for (int j = i + 1; j <= cntInner; j++){
				if (tmp > arrint[j]) {
					tmp = arrint[j];
					index = j;
				}
			}
			tmp = arrint[i];
			arrint[i] = arrint[index];
			arrint[index] = tmp;
		}
		
		System.out.println();
		for (int i = 0; i <= arrint.length - 1; i++) {
			System.out.println(arrint[i]);
		}
	}

	public void sort3(int[] obj) {
		int size = 1;
		int c = 0;
		while (size < obj.length){
			size++;
			c = obj[size - 1];
			for (int i = 0; i < size - 1; i++){
				if (c < obj[i]){
					// System.out.println(obj[i]);
					for (int j = size - 1; j - 1 >= i; j--){
						obj[j] = obj[j - 1];

					}
					obj[i] = c;
					break;
				}
			}
		}

		System.out.println();
		for (int i = 0; i <= obj.length - 1; i++) {
			System.out.println(obj[i]);
		}
	}
	
	public void sort4(int[] obj) {
		int len = obj.length;
		int cntOut = len - 1;
		int curData = 0;
		for (int i = 1; i <= cntOut; i++) {
			curData = obj[i];
			int j = i -1;
/*			for (; j >= 0; j--) {
				if (curData < obj[j]) {
					obj[j + 1] = obj[j];
				} else {
					break;
				}
			}*/
			while (j >= 0 && curData < obj[j]) {
				obj[j + 1] = obj[j];
				j--;
			}
			obj[j + 1] = curData;
		}
		
		System.out.println();
		for (int i = 0; i <= obj.length - 1; i++) {
			System.out.println(obj[i]);
		}
	}

}
