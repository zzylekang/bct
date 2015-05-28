package com.zzy.dev.comm.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class StringUtil {	
	/**
	 * ��ʽ������
	 * @param num
	 * @param formatStr �磺#,##0.0000  #,##0.00
	 * @return
	 */
    public static String format(double num,String formatStr) {
        String str = "0.00";
        java.text.DecimalFormat dFormat = new DecimalFormat(formatStr);
        str = dFormat.format(0.00);
        
        if ((Math.abs(num - 0.0) < 0.00000001) || Double.isNaN(num)) {//����̫С�������֣��򷵻�0.00
            return str;
        }
        str = dFormat.format(num);

        return str;
    }
    
	/**
	 * ��ʽ������(�������룬������λС��)
	 * @param num
	 * @return
	 */
    public static String format(double num) {
        String str = "0.00";
        java.text.DecimalFormat dFormat = new DecimalFormat("#,##0.00");
        str = dFormat.format(0.00);
        
        if ((Math.abs(num - 0.0) < 0.00000001) || Double.isNaN(num)) {//����̫С�������֣��򷵻�0.00
            return str;
        }
        str = dFormat.format(num);

        return str;
    }
    
	/**
	 * ��ʽ������(�������룬������λС��)
	 * @param num
	 * @return
	 */
    public static String formatFour(double num) {
        String str = "0.0000";
        java.text.DecimalFormat dFormat = new DecimalFormat("#,##0.0000");
        str = dFormat.format(0.00);
        
        if ((Math.abs(num - 0.0) < 0.00000001) || Double.isNaN(num)) {//����̫С�������֣��򷵻�0.00
            return str;
        }
        str = dFormat.format(num);

        return str;
    }
    /**
     * null -> ""
     * @param str
     * @return
     */
    public static String formatNull(String str) {
        if (str == null) 
            return "";
         else
            return str;
    }
    
    /**
     * Date -> "YYYY-MM-dd"
     */
    public static String format(java.util.Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }
    
    /**
     * Date -> "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatLongDate(java.util.Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (time == null) {
            return "";
        } else {
            return formatter.format(time);
        }
    }
    
    /**
     * ���ܣ���ʽ���ַ���  
     * ���룺1212121212  --> �����121 212 121 2
     * 	str Ҫ��ʽ�����ַ���
     * 	sep �ָ���
     *  len �ָ�����
     */
    public static String formatStrBySeprator(String str, String sep, int len) {
		String str_new = "";
        if (str == null) {
        	return str_new;
        } else {
        	int start = 0;
        	int end = 0;
        	int loopCnt = 0;
        	if ((str.length())%len == 0 || str.length() < len) {
        		loopCnt = (str.length())/len;
        	} else {
        		loopCnt = (str.length())/len + 1;
        	}
        	if (str.length() < len) {//Ҫ��ʽ�����ַ��� ����С�� �ָ��
        		str_new = str + sep;
        	} else {
        		str_new = str.substring(0, len) + sep;
        	}
        	for (int i=0; i < loopCnt; i++) {
        		start = i*len;
        		end = (i+1)*len > str.length() ? str.length():(i+1)*len;
        		str_new = str_new + str.substring(start, end) + sep;
        	}
        	str_new = str_new.substring(0, str_new.length() - 1);
        }

        return str_new;
    }
}
