<%@page import="com.zzy.dev.comm.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>

<%@ page import="" %>

<%
//此处添加java代码
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>主页面（页面框架）</title>

<link href="<%=path%>/css/adminstyle.css" rel="stylesheet" type="text/css" /><%--全局样式 --%>

<%--日期输入控件 --%>
<link href="<%=path%>/css/InputDate.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=path%>/js/InputDate.js"></script>

<%-- 表单检查 共通函数 --%>
<script language="javascript" src="<%=path%>/js/checkInput.js"></script>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<%--jQuery --%>
<script language="Javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>

<%--页面自定义样式 --%>
<style type="text/css">

</style>

<%--页面脚步 --%>
<script language="javascript">


/**
 * 画面的初始化
 */
$(document).ready(function(){
	//在这里你可以绑定从服务器回传回来的数据到查询表单
});
</script>
</head>
<body>
	<%--内容区 --%>
	<div id="r">
	
		<%--两列详细表格 --%>
		<table  class="inputTable" cellspacing="0" cellpadding="0">

			<tr><td colspan="2" class="tableTitle al">片段信息头1</td></tr>
			<tr><td style="width:200px" class="ar">文本：</td><td><%=StringUtil.formatNull("文本") %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">数字（两位小数）：</td><td><%=StringUtil.format(666.55) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">数字(四位小数)：</td><td><%=StringUtil.formatFour(666.55) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">日期(yyyy-MM-dd)：</td><td><%=StringUtil.format(new Date()) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">日期(yyyy-MM-dd hh:mm:ss)：</td><td><%=StringUtil.formatLongDate(new Date()) %>&nbsp;</td></tr>
			
			<tr><td colspan="2" class="tableTitle al">片段信息头2</td></tr>
			<tr><td style="width:200px" class="ar">文本：</td><td><%=StringUtil.formatNull("文本") %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">数字（两位小数）：</td><td><%=StringUtil.format(666.55) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">数字(四位小数)：</td><td><%=StringUtil.formatFour(666.55) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">日期(yyyy-MM-dd)：</td><td><%=StringUtil.format(new Date()) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">日期(yyyy-MM-dd hh:mm:ss)：</td><td><%=StringUtil.formatLongDate(new Date()) %>&nbsp;</td></tr>
		</table>
		<%--两列表格 结束--%>
		
		<%--四列详细表格 --%>
		
		<%--四列详细表格 结束 --%>
	</div>
	<%--内容区 结束 --%>
</body>
</html>