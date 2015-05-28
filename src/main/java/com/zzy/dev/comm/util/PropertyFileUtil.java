package com.zzy.dev.comm.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertyFileUtil {
    private Properties propertie;
    private FileInputStream inputFile; 
    
    /**
     * ��ʼ�������ļ�
     * 
     * filePath�����·��
     */  
    public PropertyFileUtil(String filePath)   {   
        propertie = new Properties();   
        URL cur = this.getClass().getResource(filePath);
        try {   
            inputFile = new FileInputStream(URLDecoder.decode(cur.getPath(), "utf-8"));   
            propertie.load(inputFile);   
            inputFile.close();   
        } catch (FileNotFoundException ex) {   
            System.out.println("��ȡ�����ļ�--->ʧ�ܣ�- ԭ���ļ�·����������ļ�������");   
            ex.printStackTrace();   
        } catch (IOException ex) {   
            System.out.println("װ���ļ�--->ʧ��!");   
            ex.printStackTrace();   
        }   
    }
    
    public static void main(String [] args) {
    	new PropertyFileUtil("/log4j.properties");
    	
    }
    
}
