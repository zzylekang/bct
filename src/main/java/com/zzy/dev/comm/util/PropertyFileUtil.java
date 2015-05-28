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
     * 初始化属性文件
     * 
     * filePath：相对路径
     */  
    public PropertyFileUtil(String filePath)   {   
        propertie = new Properties();   
        URL cur = this.getClass().getResource(filePath);
        try {   
            inputFile = new FileInputStream(URLDecoder.decode(cur.getPath(), "utf-8"));   
            propertie.load(inputFile);   
            inputFile.close();   
        } catch (FileNotFoundException ex) {   
            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");   
            ex.printStackTrace();   
        } catch (IOException ex) {   
            System.out.println("装载文件--->失败!");   
            ex.printStackTrace();   
        }   
    }
    
    public static void main(String [] args) {
    	new PropertyFileUtil("/log4j.properties");
    	
    }
    
}
