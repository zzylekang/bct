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
 * ���ݸ�����Ŀ¼���ļ������ո������߼�������д���
 * @author Administrator
 *
 */
public class FileProcess {

	/**
	 * @param args ���磺java FileProcess -f �ļ�ȫ·�� -d Ŀ¼
	 */
	public static void main(String[] args) {
		String dir = "E:\\wd\\soft_dev\\all jars\\";
		FileProcess fp = new FileProcess();
		Set<String> fdSet = fp.getAllFD(dir);
		
		System.out.println("����ǰ�ļ��б�");
		System.out.println(fdSet);
		
		Iterator<String> it = fdSet.iterator();

		while(it.hasNext()) {
			String fileName = it.next();
			File f = new File(dir + fileName);
			System.out.println("��������ļ�Ϊ"  + f.getAbsolutePath());
			if (f.isFile()) {
				fp.doProcess1(dir, fileName, 2, fdSet);
			}
		}
		
		fdSet = fp.getAllFD(dir);
		System.out.println("������ļ��б�");
		System.out.println(fdSet);
		System.exit(0);
	}
	
	/**
	 * �ڸ����ļ����У��ݹ�����뵱ǰ�������ļ�(ȫ·��)��ָ����ϵ����С��ͬ���ļ���
	 * ִ�п�����ɾ������
	 */
	public void doProcess1(String filePath, String fileName, int i, Set<String> fdSet) {
		String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) 
								+ " (" + i + ")" + fileName.substring(fileName.lastIndexOf("."));
		
		System.out.println("�����Ŀ���ļ�Ϊ:"  + newFileName);
		
		if (fdSet.contains(newFileName)) {
			File f = new File(filePath + fileName);
			File newf = new File(filePath + newFileName);
			
			if (f.length() == newf.length() ) {
				
				//���Ƶ�ǰ�ļ�����ǰĿ¼�µ�   "copy+��ǰ����"
				String todir = "copy_" + "20120318";
				String toFullFilename = filePath + todir + "\\" + newFileName;
				System.out.println("������Ŀ��Ϊ"  + toFullFilename);
				this.copyFile(filePath + newFileName, toFullFilename);
				
				//ɾ��newf
				newf.delete();
			}
			i++;
			this.doProcess1(filePath, fileName, i, fdSet);
		}
	}
	/**
	 * copyָ���ļ���ָ��Ŀ¼ָ���ļ�
	 * @param srcFileName ����Դ
	 * @param toFileName ����Ŀ��
	 * @throws FileNotFoundException 
	 */
	public void copyFile(String srcFileName, String toFileName){
		System.out.println("����Դ"  + srcFileName);
		System.out.println("����Ŀ��"  + toFileName);
		
		byte [] b = new byte[512];
		InputStream is = null;
		OutputStream os = null;
		try {
			File f = new File(toFileName.substring(0, toFileName.lastIndexOf("\\")));
			
			//����Ŀ¼
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
				System.out.println("�ر��ļ����쳣");
				e.printStackTrace();
			}
			
		}
	}
	
	
	/**
	 * windows�����ļ����п���ʱ���������ͬ���ļ���ϵͳ���������
	 * �����飺��һ���滻���������߶�����������ȡ������
	 * 
	 * �������ԣ����������������һ��������������
	 *     �������ļ����ֱ�Ϊ��xsu12.jar��xsu12 (2).jar
	 *     ����������ļ����ļ���ȥ��" (2)"��" (3)"�Ⱥ���ͬ�����Ҵ�СҲ��ͬ������Ϊ�������ļ���ͬһ���ļ�
	 *     ɾ����׺Ϊ(2)��(3)���ļ�
	 *     
	 *     
	 */
	/**
	 * ���ָ��Ŀ¼�����е��ļ����ļ��У����������HashSet�з��ء�
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
