package com.zzy.dev.comm.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

public class LogWriter {
	private boolean logFlag = true;
	private String logFileName = "/tmp/log.log";
	private OutputStream out = null;
	public LogWriter(boolean logFlag, String logFileName){
		this.logFileName = logFileName;
		this.logFlag = logFlag;
		try {
			this.out = getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void log(String info) throws IOException {
		//OutputStream out = getOutputStream();
		if (out instanceof FileOutputStream) {
			out.write( (StringUtil.formatLongDate(new Date())  + "\t").getBytes());
			out.write(info.getBytes("GBK"));
			out.write("\r\n".getBytes("GBK"));
		} else {
			PrintStream outp = (PrintStream)out;
			outp.print(StringUtil.formatLongDate(new Date())  + "\t");
			outp.println(info);
		}
		
		//System.out.println(System.getProperty("user.dir"));
	}
	
	public OutputStream getOutputStream() throws Exception {
		if (this.out != null) {
			return this.out;
		}
		if (logFlag) {
			File filePath = new File(logFileName.substring(0, logFileName.lastIndexOf("/")));
			File file = null;
			if (!filePath.exists()) {//Ŀ¼������
				if (filePath.mkdirs()) {//����Ŀ¼�ɹ�
					file = new File(logFileName);
					
					if (file.exists())file.delete();
					file.createNewFile();
				} else {
					throw new Exception("������־Ŀ¼ʧ�ܣ�--->" + logFileName.substring(0, logFileName.lastIndexOf("/")));
				}
			} else {
				file = new File(logFileName);
				
				if (file.exists())file.delete();
				file.createNewFile();
			}
			return new FileOutputStream(file, true);//����׷��д��ķ�ʽ
		} else {
			return System.out;
		}
	}

}
