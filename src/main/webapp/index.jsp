<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ include file="/page/pageCommon/path.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>BCT��ҳ</title>
<%
System.out.println("[BCT/src/main/webapp/index.jsp][contextPath]" + path);
%>
</head>
<body>
<ul>
	<li><a href="<%=path %>/page/pageTest/test.jsp">һ�����</a></li>
	<li><a href="test?action=pageAction">��ҳ����</a></li>
	<li><a href="test?action=leftToAction">��Ŀ�����ƶ�����</a></li>
	<li><a href="test?action=writeFileInWebContainer">��web���������£�д�ļ�</a></li>
	<li><a href="page/windowTest/windowTest.html">windowTest</a></li>
	<li><a href="page/pageCommon/webEnvDetail.jsp">���web��������ϵͳ����</a></li>
	<li><a href="page/pageCommon/fileDataProcessor.jsp">�ļ����ݴ���</a></li>
	<li><a href="<%=path%>/demo/demo.do">struts2 ����ҳ</a></li>
	<li><a href="<%=path%>/page/uploadfile/uploadfile.jsp">�ļ��ϴ�����</a></li>
</ul>
</body>
</html>