package com.zzy.tools.autoDeploy;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.zzy.dev.comm.util.LogWriter;

/**
 * ���ո����ļ����ṩ��·����Ϣ������һ�������ת����
 * ������ת������ļ����Ƽ�·������ָ�����ļ����Ƶ���һ���ļ����У�
 * ��������Ŀ�������������
 * @author Administrator
 *
 */
public class AutoDeploy {
	private static String config = "";//�����ļ�·��
	private static String path_head_new = null;//Ҫ�������ĵ�·��
	private static String svnpath = null;//svn log�ļ�·��
	private static String logpath = null;//log����ļ�·��
	private static String project = null;//��Ŀ��Ϣ
	private static String runpath = null;
	private static String logProject = null;
	private static Map<String, String> projectMap = null;//��Ŀ��Ϣ
	private static Map<String, String> projectLocMap = null;//��Ŀλ����Ϣ
	private static Map<String, String> logProjectMap = null;//��־��project��ת��Ϊ��ȷproject��
	
	private static LogWriter lw = null;
	
	/**
	 * ��ʼ�������������
	 * @throws Exception 
	 */
	private static void init() throws Exception {
		Properties props = new Properties();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(config);
			//isr = new InputStreamReader(fis, "GBK");
		} catch (FileNotFoundException e) {
			throw new Exception("error:" + config + "�ļ��������ڣ�");
		}
		
		try {
			props.load(fis);
		} catch (IOException e) {
			throw new Exception("error:" + "����" + config + "�ļ���ʧ�ܣ�");
		}
		
		//����Դ
		String path_head_old = props.getProperty("file_from");
		
		if (isEmpty(path_head_old)) {
			throw new Exception("error:" + "file_from����Ϊ�գ�");
		} else {

			String [] strArr = path_head_old.split(",");
			projectLocMap = new HashMap<String, String>();
			for (int i = 0; i < strArr.length; i++) {
				projectLocMap.put(strArr[i].split("=")[0], strArr[i].split("=")[1]);
			}
		
		}
		
		//#����Ŀ�ĵ�
		path_head_new = props.getProperty("file_to");
		if (isEmpty(path_head_new)) {
			//throw new Exception("error:" + "file_toΪ�գ�");
			System.out.println("info:" + "file_toΪ�գ�");
			path_head_new = "D:/AutoDeploy/" + "";
			System.out.println("info:" + "file_toĬ��Ϊ��" + path_head_new);
		}
		
		//#log�ļ�·��
		logpath = props.getProperty("log_path");
		if (isEmpty(logpath)) {
			//throw new Exception("error:" + "log_pathΪ�գ�");
			System.out.println("info:" + "log_pathΪ�գ�");
			logpath = runpath + "log.txt";
			System.out.println("info:" + "log_pathĬ��Ϊ��" + logpath);
		}
		
		//#svn log�ļ�·��
		svnpath = props.getProperty("svn_log_path");
		if (isEmpty(svnpath)) {
			//throw new Exception("error:" + "svn_log_pathΪ�գ�");
			System.out.println("info:" + "svn_log_pathΪ�գ�");
			svnpath = runpath + "copy.txt";
			System.out.println("info:" + "svn_log_pathĬ��Ϊ��" + svnpath);
		}
		
		//ֵ�ĸ�ʽΪ��������1@��Ӧwebroot1
		project = props.getProperty("project");
		if (isEmpty(project)) {
			throw new Exception("error:" + "projectΪ�գ�");
		} else {
			String [] strArr = project.split(",");
			projectMap = new HashMap<String, String>();
			for (int i = 0; i < strArr.length; i++) {
				projectMap.put(strArr[i].split("@")[0], strArr[i].split("@")[1]);
			}
		}
		
		//#��־�е���Ŀ����·�������
		logProject = props.getProperty("logproject");
		if (isEmpty(logProject)) {
			System.out.println("logproject is empty!");
		} else {
			String [] strArr = logProject.split(",");
			logProjectMap = new HashMap<String, String>();
			for (int i = 0; i < strArr.length; i++) {
				logProjectMap.put(strArr[i].split("=")[0], strArr[i].split("=")[1]);
			}
		}
	}
	public static void write(String old_Path, String new_path) throws IOException {
		try {
			int byte_sum = 0;
			int byte_read = 0;
			File old_file = new File(old_Path);
			
			if (old_file.isDirectory()) {//��ǰ·��ΪĿ¼
				System.out.println(old_Path + "ΪĿ¼�������д���");
				lw.log(old_Path + "ΪĿ¼�������д���");
				return;
			}
			
			if (old_file.exists()) {
				InputStream is = new FileInputStream(old_Path);
				(new File(new_path.substring(0, new_path.lastIndexOf("/")))).mkdirs();
				FileOutputStream fs = new FileOutputStream(new_path);
				byte[] buffer = new byte[1444];
				while ((byte_read = is.read(buffer)) != -1) {
					byte_sum += byte_read;
					fs.write(buffer, 0, byte_read);
				}
				is.close();
			} else {
				System.out.println(old_Path + "������");
				lw.log(old_Path + "������");
			}
		} catch (Exception e) {
			System.out.println("д����" + e.getMessage() + ":" + old_Path);
			lw.log("д����" + e.getMessage() + ":" + old_Path);
		}
	}

	private static void read() throws IOException {
		try {
			FileInputStream fis = new FileInputStream(svnpath);//�ļ��ֽ���
			InputStreamReader isr = new InputStreamReader(fis, "GBK");
			BufferedReader br = new BufferedReader(isr);
			System.out.println(svnpath + ":���뷽ʽΪ��" + isr.getEncoding());
			
            /*FileReader fr = new FileReader(svnpath);
            System.out.println(fr.getEncoding());
            BufferedReader br = new BufferedReader(fr);*/
            String read_line;
            String file_from;
            String file_to;
            while (br.ready()) {
            	read_line = br.readLine();
            	if (isEmpty(read_line)) {
            		continue;
            	}
            	file_from = AutoDeploy.copyPre(read_line);//ȡ�ÿ���Դ·��
            	
            	file_to = AutoDeploy.getToPath(read_line);
            	
            	if (read_line != null && read_line.length() > 0) {
            		 write(file_from, file_to);
            	} else {
            		System.out.println("����Դ·��Ϊ��");
            		lw.log("����Դ·��Ϊ��");
            	}
            	System.out.println("------------------------------------------");
            	lw.log("------------------------------------------");
            }
            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
        	System.out.println("������" + e.getMessage());
        	lw.log("������" + e.getMessage());
        	
        	throw e;
        }
    }
	
	/**
	 * ��õ�ǰ��¼��Ӧ����Ŀ
	 * @param path
	 * @return
	 * @throws Exception
	 */
	private static String getProjectName (String path) throws Exception {
		int idx = 0;
		int min = Integer.MAX_VALUE;
		String ret = null;
		
		Set<String> ks = projectMap.keySet();
		Iterator<String> it = ks.iterator();
		String key = null;
		while(it.hasNext()) {
			key = it.next();
			idx = path.indexOf("/" + key + "/");
			
			if (idx != -1 && idx < min) {
				min = idx;
				ret = key;
			}
		}

		if (ret == null) {
			throw new Exception(path + "·��û�ж�Ӧ����Ŀ");
		}
		
		return ret;
	}
	/**
	 * ��ȡ���̵�webroot
	 * @throws Exception 
	 */
	private static String getWebroot(String project) throws Exception {
		
		if (projectMap.containsKey(project)) {
			return projectMap.get(project);
			
		} else {
			return "";
		}

	}
	/**
	 * ��copy��Դ·����Ԥ����
	 * @param path ��·��
	 * @return ��·��
	 * @throws IOException 
	 */
	private static String copyPre(String path) throws IOException {
		
		String newPath = "";

		System.out.println("������Դ��ת��ǰ��·��Ϊ��" + path);
		lw.log("������Դ��ת��ǰ��·��Ϊ��" + path);
		
		String projectName = null;
		String webRoot = null;
		try {
			projectName = getProjectName(path);
			webRoot = getWebroot(projectName);
		} catch (Exception e) {
			System.out.println("warn:" + path + "��Ϊδ֪��Ŀ·��");
		}
		String indStr = "/" + projectName + "/";
		newPath = path.substring(path.indexOf(indStr) + indStr.length() - 1);//ȥ��·���ж����ǰ׺
		
		//ȥ����־�ж���·��
		if (logProjectMap != null && logProjectMap.containsKey(projectName)) {
			String logPath = logProjectMap.get(projectName);//Ҫ�滻��
			if (newPath.indexOf(logPath) != -1) {
				newPath = newPath.replaceFirst(logPath, "/");
			}
		}
		
		if (newPath.indexOf(".java") != -1){//java�ļ�
			newPath = newPath.replaceFirst("/src/", "/" + webRoot + "/WEB-INF/classes/");
			newPath = newPath.replaceFirst(".java", ".class");
		}
		
		newPath = projectLocMap.get(projectName) + newPath;//�����ȷ��·��ǰ׺
		
		System.out.println("������Դ��ת�����·��Ϊ��" + newPath);
		lw.log("������Դ��ת�����·��Ϊ��" + newPath);
		return newPath;
    }
	/**
	 * ���ݿ���Դ·������Ŀ��·��
	 * @param path ��·��
	 * @return ��·��
	 * @throws IOException 
	 */
	private static String getToPath(String path) throws IOException {
		String newPath = "";
		
		System.out.println("������Ŀ�꡿ת��ǰ��·��Ϊ��" + path);
		lw.log("������Ŀ�꡿ת��ǰ��·��Ϊ��" + path);
		
		
		String projectName = null;
		String webRoot = null;
		try {
			projectName = getProjectName(path);
			webRoot = getWebroot(projectName);
		} catch (Exception e) {
			System.out.println("warn:" + path + "��Ϊδ֪��Ŀ·��");
		}
		String indStr = "/" + projectName + "/";
		newPath = path.substring(path.indexOf(indStr));
		
		//�滻��־�ж���·��
		if (logProjectMap != null && logProjectMap.containsKey(projectName)) {
			String logPath = logProjectMap.get(projectName);//Ҫ�滻��
			if (newPath.indexOf(logPath) != -1) {
				newPath = newPath.replaceFirst(logPath, "/");
			}
		}
		
		//srcĿ¼
		String srcpath = "/" + projectName + "/src/";
		
		if (newPath.indexOf(srcpath) != -1) {
			newPath = newPath.replaceFirst("/src/", "/WEB-INF/classes/");
			newPath = newPath.replaceFirst(".java", ".class");
		} else {//web��Ŀ¼
			String webrootPath = "/"+ webRoot +"/";
			newPath = newPath.replaceFirst(webrootPath, "/");
		}
		
		//ת����־�ж���·��
		
		newPath = path_head_new + newPath;
		
		System.out.println("������Ŀ�꡿ת�����·��Ϊ��" + newPath);
		lw.log("������Ŀ�꡿ת�����·��Ϊ��" + newPath);
		return newPath;
    }
	
	private static boolean isEmpty(String str) {
		if (str == null || str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	private static boolean isEmptyUTF8(String str) {
		String empty  = null;
		try {
			empty  = new String("".getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (str == null || empty.equals(str)) {
			return true;
		} else {
			return false;
		}
	}
	private static boolean isNotEmpty(String str) {
		if (str == null || str.length() == 0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * ɾ��������еĴ���ļ���
	 * @throws Exception 
	 */
	private static void deletExistDeploy() throws Exception {
		Iterator<String> it = projectMap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String path = null;
			if (path_head_new.endsWith("/")) {
				path = path_head_new + key;
			} else {
				path = path_head_new + "/" +key;
			}
			
			File file = new File(path);
			String newPath = null;
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("yyyyMMddHHmmss");
			newPath = path + "_" + sdf.format(new Date(file.lastModified()));
			if (file.exists()) {
				file.renameTo(new File(newPath));
			}
			
			/*if (file.exists() && !deleteDir(file)){//ɾ��ʧ��
				throw new Exception("error:ɾ����" + path + "��ʧ�ܣ�");
			}*/
		}
	}
	
    /*** �ݹ�ɾ��Ŀ¼�µ������ļ�����Ŀ¼�������ļ�     
     * * @param dir ��Ҫɾ�����ļ�Ŀ¼     
     * * @return boolean Returns "true" if all deletions were successful.     
     * *                 If a deletion fails, the method stops attempting to     
     * *                 delete and returns "false".     
     * */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//�ݹ�ɾ��Ŀ¼�е���Ŀ¼��
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}            
			}        
		}
		
		// Ŀ¼��ʱΪ�գ�����ɾ��
		return dir.delete();
	}

	
	public static void main(String[] args) {
		try {
			System.out.println("����ʼ��");
			
			runpath = AutoDeploy.class.getResource("").getPath().replaceFirst("/", "");//ȥ���̷�ǰ�ġ�/��
			System.out.println("��ǰ����Ŀ¼��" + runpath);
			
			if (args == null || args.length == 0) {
				config = runpath + "deploy_config.properties";
				System.out.println("�����С������ļ�λ�á�Ϊ�գ�");
				System.out.println("Ĭ�������ļ�Ϊ��" + config);
			} else {
				config = args[0];
				System.out.println(config);
			}
			
			init();//����Ӧ��
			deletExistDeploy();//ɾ���Ѿ����ڵĲ����
			
			lw = new LogWriter(true, logpath);//�����־
			
			read();//��ȡsvn log�ļ������д���
			lw.getOutputStream().close();
			System.out.println("����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

