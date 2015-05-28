package com.zzy.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * 根据给定的目录或文件，按照给定的逻辑步骤进行处理
 * @author Administrator
 *
 */
public class FileProcess {

	/**
	 * @param args 形如：java FileProcess -f 文件全路径 -d 目录
	 */
	public static void main(String[] args) {
		String dir = "E:\\wd\\soft_dev\\all jars\\";
		FileProcess fp = new FileProcess();
		Set<String> fdSet = fp.getAllFD(dir);
		
		System.out.println("处理前文件列表");
		System.out.println(fdSet);
		
		Iterator<String> it = fdSet.iterator();

		while(it.hasNext()) {
			String fileName = it.next();
			File f = new File(dir + fileName);
			System.out.println("被处理的文件为"  + f.getAbsolutePath());
			if (f.isFile()) {
				fp.doProcess1(dir, fileName, 2, fdSet);
			}
		}
		
		fdSet = fp.getAllFD(dir);
		System.out.println("处理后文件列表");
		System.out.println(fdSet);
		System.exit(0);
	}
	
	/**
	 * 在给定的集合中，递归查找与当前给定的文件(全路径)有指定关系、大小相同的文件。
	 * 执行拷贝、删除操作
	 */
	public void doProcess1(String filePath, String fileName, int i, Set<String> fdSet) {
		String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) 
								+ " (" + i + ")" + fileName.substring(fileName.lastIndexOf("."));
		
		System.out.println("处理的目标文件为:"  + newFileName);
		
		if (fdSet.contains(newFileName)) {
			File f = new File(filePath + fileName);
			File newf = new File(filePath + newFileName);
			
			if (f.length() == newf.length() ) {
				
				//复制当前文件到当前目录下的   "copy+当前日期"
				String todir = "copy_" + "20120318";
				String toFullFilename = filePath + todir + "\\" + newFileName;
				System.out.println("拷贝的目的为"  + toFullFilename);
				this.copyFile(filePath + newFileName, toFullFilename);
				
				//删除newf
				newf.delete();
			}
			i++;
			this.doProcess1(filePath, fileName, i, fdSet);
		}
	}
	/**
	 * copy指定文件到指定目录指定文件
	 * @param srcFileName 拷贝源
	 * @param toFileName 拷贝目标
	 * @throws FileNotFoundException 
	 */
	public void copyFile(String srcFileName, String toFileName){
		System.out.println("拷贝源"  + srcFileName);
		System.out.println("拷贝目标"  + toFileName);
		
		byte [] b = new byte[512];
		InputStream is = null;
		OutputStream os = null;
		try {
			File f = new File(toFileName.substring(0, toFileName.lastIndexOf("\\")));
			
			//创建目录
			if (!f.exists()){
				System.out.println(f.mkdir());
			}
			
			is = new FileInputStream(srcFileName);
			os = new FileOutputStream(new File(toFileName));
			
			while(is.read(b, 0, 512) != -1) {
				os.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				System.out.println("关闭文件流异常");
				e.printStackTrace();
			}
			
		}
	}
	
	
	/**
	 * windows中在文件进行拷贝时，如果存在同名文件，系统会给出如下
	 * 处理建议：（一）替换（二）两者都保留（三）取消拷贝
	 * 
	 * 本方法对（二）这种情况做进一步处理。举例如下
	 *     有两个文件，分别为：xsu12.jar、xsu12 (2).jar
	 *     如果这两个文件的文件名去掉" (2)"或" (3)"等后，相同，并且大小也相同，则认为这两个文件是同一个文件
	 *     删除后缀为(2)或(3)的文件
	 *     
	 *     
	 */
	/**
	 * 获得指定目录下所有的文件及文件夹，将结果放在HashSet中返回。
	 * 
	 */
	
	public Set<String> getAllFD(String dir) {
		Set<String> fdSet = null;
		File f = new File(dir);
		fdSet = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		fdSet.addAll(Arrays.asList(f.list()));
		
		return fdSet;
	}
}
