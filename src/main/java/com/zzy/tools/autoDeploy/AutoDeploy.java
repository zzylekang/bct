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
 * 按照给定文件中提供的路径信息，经过一定规则的转换，
 * 并按照转换后的文件名称及路径，将指定的文件复制到另一个文件夹中，
 * 被用于项目部署包的制作。
 * @author Administrator
 *
 */
public class AutoDeploy {
	private static String config = "";//属性文件路径
	private static String path_head_new = null;//要拷贝到哪的路径
	private static String svnpath = null;//svn log文件路径
	private static String logpath = null;//log输出文件路径
	private static String project = null;//项目信息
	private static String runpath = null;
	private static String logProject = null;
	private static Map<String, String> projectMap = null;//项目信息
	private static Map<String, String> projectLocMap = null;//项目位置信息
	private static Map<String, String> logProjectMap = null;//日志中project名转换为正确project名
	
	private static LogWriter lw = null;
	
	/**
	 * 初始化部署所需参数
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
			throw new Exception("error:" + config + "文件，不存在！");
		}
		
		try {
			props.load(fis);
		} catch (IOException e) {
			throw new Exception("error:" + "加载" + config + "文件，失败！");
		}
		
		//拷贝源
		String path_head_old = props.getProperty("file_from");
		
		if (isEmpty(path_head_old)) {
			throw new Exception("error:" + "file_from不能为空！");
		} else {

			String [] strArr = path_head_old.split(",");
			projectLocMap = new HashMap<String, String>();
			for (int i = 0; i < strArr.length; i++) {
				projectLocMap.put(strArr[i].split("=")[0], strArr[i].split("=")[1]);
			}
		
		}
		
		//#拷贝目的地
		path_head_new = props.getProperty("file_to");
		if (isEmpty(path_head_new)) {
			//throw new Exception("error:" + "file_to为空！");
			System.out.println("info:" + "file_to为空！");
			path_head_new = "D:/AutoDeploy/" + "";
			System.out.println("info:" + "file_to默认为：" + path_head_new);
		}
		
		//#log文件路径
		logpath = props.getProperty("log_path");
		if (isEmpty(logpath)) {
			//throw new Exception("error:" + "log_path为空！");
			System.out.println("info:" + "log_path为空！");
			logpath = runpath + "log.txt";
			System.out.println("info:" + "log_path默认为：" + logpath);
		}
		
		//#svn log文件路径
		svnpath = props.getProperty("svn_log_path");
		if (isEmpty(svnpath)) {
			//throw new Exception("error:" + "svn_log_path为空！");
			System.out.println("info:" + "svn_log_path为空！");
			svnpath = runpath + "copy.txt";
			System.out.println("info:" + "svn_log_path默认为：" + svnpath);
		}
		
		//值的格式为：工程名1@对应webroot1
		project = props.getProperty("project");
		if (isEmpty(project)) {
			throw new Exception("error:" + "project为空！");
		} else {
			String [] strArr = project.split(",");
			projectMap = new HashMap<String, String>();
			for (int i = 0; i < strArr.length; i++) {
				projectMap.put(strArr[i].split("@")[0], strArr[i].split("@")[1]);
			}
		}
		
		//#日志中的项目带有路径的情况
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
			
			if (old_file.isDirectory()) {//当前路径为目录
				System.out.println(old_Path + "为目录，不进行处理！");
				lw.log(old_Path + "为目录，不进行处理！");
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
				System.out.println(old_Path + "不存在");
				lw.log(old_Path + "不存在");
			}
		} catch (Exception e) {
			System.out.println("写出错" + e.getMessage() + ":" + old_Path);
			lw.log("写出错" + e.getMessage() + ":" + old_Path);
		}
	}

	private static void read() throws IOException {
		try {
			FileInputStream fis = new FileInputStream(svnpath);//文件字节流
			InputStreamReader isr = new InputStreamReader(fis, "GBK");
			BufferedReader br = new BufferedReader(isr);
			System.out.println(svnpath + ":编码方式为：" + isr.getEncoding());
			
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
            	file_from = AutoDeploy.copyPre(read_line);//取得拷贝源路径
            	
            	file_to = AutoDeploy.getToPath(read_line);
            	
            	if (read_line != null && read_line.length() > 0) {
            		 write(file_from, file_to);
            	} else {
            		System.out.println("拷贝源路径为空");
            		lw.log("拷贝源路径为空");
            	}
            	System.out.println("------------------------------------------");
            	lw.log("------------------------------------------");
            }
            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
        	System.out.println("读出错" + e.getMessage());
        	lw.log("读出错" + e.getMessage());
        	
        	throw e;
        }
    }
	
	/**
	 * 获得当前记录对应的项目
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
			throw new Exception(path + "路径没有对应的项目");
		}
		
		return ret;
	}
	/**
	 * 获取工程的webroot
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
	 * 对copy的源路径做预处理
	 * @param path 旧路径
	 * @return 新路径
	 * @throws IOException 
	 */
	private static String copyPre(String path) throws IOException {
		
		String newPath = "";

		System.out.println("【拷贝源】转换前的路径为：" + path);
		lw.log("【拷贝源】转换前的路径为：" + path);
		
		String projectName = null;
		String webRoot = null;
		try {
			projectName = getProjectName(path);
			webRoot = getWebroot(projectName);
		} catch (Exception e) {
			System.out.println("warn:" + path + "：为未知项目路径");
		}
		String indStr = "/" + projectName + "/";
		newPath = path.substring(path.indexOf(indStr) + indStr.length() - 1);//去除路径中多余的前缀
		
		//去掉日志中多余路径
		if (logProjectMap != null && logProjectMap.containsKey(projectName)) {
			String logPath = logProjectMap.get(projectName);//要替换的
			if (newPath.indexOf(logPath) != -1) {
				newPath = newPath.replaceFirst(logPath, "/");
			}
		}
		
		if (newPath.indexOf(".java") != -1){//java文件
			newPath = newPath.replaceFirst("/src/", "/" + webRoot + "/WEB-INF/classes/");
			newPath = newPath.replaceFirst(".java", ".class");
		}
		
		newPath = projectLocMap.get(projectName) + newPath;//添加正确的路径前缀
		
		System.out.println("【拷贝源】转换后的路径为：" + newPath);
		lw.log("【拷贝源】转换后的路径为：" + newPath);
		return newPath;
    }
	/**
	 * 根据拷贝源路径生成目标路径
	 * @param path 旧路径
	 * @return 新路径
	 * @throws IOException 
	 */
	private static String getToPath(String path) throws IOException {
		String newPath = "";
		
		System.out.println("【拷贝目标】转换前的路径为：" + path);
		lw.log("【拷贝目标】转换前的路径为：" + path);
		
		
		String projectName = null;
		String webRoot = null;
		try {
			projectName = getProjectName(path);
			webRoot = getWebroot(projectName);
		} catch (Exception e) {
			System.out.println("warn:" + path + "：为未知项目路径");
		}
		String indStr = "/" + projectName + "/";
		newPath = path.substring(path.indexOf(indStr));
		
		//替换日志中多余路径
		if (logProjectMap != null && logProjectMap.containsKey(projectName)) {
			String logPath = logProjectMap.get(projectName);//要替换的
			if (newPath.indexOf(logPath) != -1) {
				newPath = newPath.replaceFirst(logPath, "/");
			}
		}
		
		//src目录
		String srcpath = "/" + projectName + "/src/";
		
		if (newPath.indexOf(srcpath) != -1) {
			newPath = newPath.replaceFirst("/src/", "/WEB-INF/classes/");
			newPath = newPath.replaceFirst(".java", ".class");
		} else {//web根目录
			String webrootPath = "/"+ webRoot +"/";
			newPath = newPath.replaceFirst(webrootPath, "/");
		}
		
		//转换日志中多余路径
		
		newPath = path_head_new + newPath;
		
		System.out.println("【拷贝目标】转换后的路径为：" + newPath);
		lw.log("【拷贝目标】转换后的路径为：" + newPath);
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
	 * 删除部署包中的打包文件夹
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
			
			/*if (file.exists() && !deleteDir(file)){//删除失败
				throw new Exception("error:删除【" + path + "】失败！");
			}*/
		}
	}
	
    /*** 递归删除目录下的所有文件及子目录下所有文件     
     * * @param dir 将要删除的文件目录     
     * * @return boolean Returns "true" if all deletions were successful.     
     * *                 If a deletion fails, the method stops attempting to     
     * *                 delete and returns "false".     
     * */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			//递归删除目录中的子目录下
			for (int i=0; i<children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}            
			}        
		}
		
		// 目录此时为空，可以删除
		return dir.delete();
	}

	
	public static void main(String[] args) {
		try {
			System.out.println("处理开始！");
			
			runpath = AutoDeploy.class.getResource("").getPath().replaceFirst("/", "");//去掉盘符前的“/”
			System.out.println("当前运行目录：" + runpath);
			
			if (args == null || args.length == 0) {
				config = runpath + "deploy_config.properties";
				System.out.println("命令行【配置文件位置】为空！");
				System.out.println("默认配置文件为：" + config);
			} else {
				config = args[0];
				System.out.println(config);
			}
			
			init();//初审化应用
			deletExistDeploy();//删除已经存在的部署包
			
			lw = new LogWriter(true, logpath);//获得日志
			
			read();//读取svn log文件，逐行处理
			lw.getOutputStream().close();
			System.out.println("处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

