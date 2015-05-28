<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%
//将所有的处理压到HashMap中
Map<String , String > hm = new HashMap<String, String>();
hm.put("keyValueCode", "将文件内容生成为Key：value形式的代码");
hm.put("recordToFileCopy", "复制文件中每行数据对应的文件");
hm.put("replace", "替换文件内容");
%>