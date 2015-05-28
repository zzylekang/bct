package com.zzy.tools;

import java.io.File;

/**
 * 该类处理的对象是文件（可为多个），以及文件内的数据处理。
 * 输入：一个或多个文件、文件夹，以及自定义的“处理逻辑”的顺序。
 * 		注意：输入的文件、文件夹限本地路径，不支持上传后的处理。
 * 			以后可能会添加文件上传、文件夹内文件的批量上传（这个会以单独的模块存在，这里只专注于数据的处理）
 * 输出：一个或多个文件、文件夹
 * 处理：根据需求，自定义的处理逻辑
 * @author Administrator
 *
 */
public class FileDataProcessor {
	private String dirSep = File.pathSeparator;
	
	/*==========输入开始==============*/
	private String input = "D:/DataProcessCenter/01input/copy.txt";//输入的路径或文件名全路径（路径+文件名）
	private String output = "D:/DataProcessCenter/02output/result.txt";//输出的结果
	
	//pk1...pkn就是逻辑的处理顺序
	private String[] pkarr = null;//处理的key数组，根据key的不同调用不同的处理逻辑
	public FileDataProcessor(String inputp, String outputp, String[] pkarrp){
		this.input = inputp;
		this.output = outputp;
		this.pkarr = pkarrp;
	}
	/*==========输入结束==============*/
	
	/*==========输出开始==============*/
	
	/*==========输出结束==============*/
	
	/*==========处理逻辑开始==============*/
	
	/*==========处理逻辑结束==============*/
}
