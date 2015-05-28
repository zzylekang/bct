<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>struts2ฒโสิาณ</title>
<%
String path = request.getContextPath();
System.out.println("[BCT/src/main/webapp/struts2Test.jsp][contextPath]" + path);
%>
</head>
<body>
 	<form action="<%=path%>/demo/demo.do">
			<div>ำรปงร๛ฃบ<input type="text" name="username"/></div>
			<div>รÜย๋ฃบ<input type="password" name="password"/></div>
	</form>
</body>
</html>