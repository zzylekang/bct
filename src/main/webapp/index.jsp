<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ include file="/page/pageCommon/path.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>BCT主页</title>
<%
System.out.println("[BCT/src/main/webapp/index.jsp][contextPath]" + path);
%>
</head>
<body>
<ul>
	<li><a href="<%=path %>/page/pageTest/test.jsp">一般测试</a></li>
	<li><a href="test?action=pageAction">分页测试</a></li>
	<li><a href="test?action=leftToAction">项目左右移动测试</a></li>
	<li><a href="test?action=writeFileInWebContainer">在web容器环境下，写文件</a></li>
	<li><a href="page/windowTest/windowTest.html">windowTest</a></li>
	<li><a href="page/pageCommon/webEnvDetail.jsp">获得web服务器的系统环境</a></li>
	<li><a href="page/pageCommon/fileDataProcessor.jsp">文件数据处理</a></li>
	<li><a href="<%=path%>/demo/demo.do">struts2 测试页</a></li>
	<li><a href="<%=path%>/page/uploadfile/uploadfile.jsp">文件上传测试</a></li>
</ul>
</body>
</html>