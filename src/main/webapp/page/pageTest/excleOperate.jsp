<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="common.pager.bean.Page" %>
<%@ page import="test.pagerTest.bean.FCTRADE_FEEbean" %>

<%
//此处添加java代码
String path = request.getContextPath();

//主意这里的变量不能定义为：“page”，会与”《%@ page--%》“中的”page“相冲突
Page pager = (Page)request.getAttribute("page");

List pageRecords = pager.getPageRecords();
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
/*.positionre{ position:relative;width:0;height:0;}*/
.zwmxzy{display:none;position:absolute;width:477px;left:-300px;*left:-400px;top:0px;z-index:100;
		padding:0px;background-color:#FFFFFF;border:1px solid #ccc; text-align: center;line-height:28px;}
.zwmxzy .zhmxtable{border-top: 1px solid #ccc; border-right: 1px solid #ccc;padding:0px;background-color:#FFFFFF;width:100%}
.zwmxzy .zhmxtable tr.bgaa{ background:#eee}
.zwmxzy .zhmxtable td{border-left: 1px solid #ccc; border-bottom: 1px solid #ccc;padding:0px;}
</style>

<%--页面脚步 --%>
<script language="javascript">

/**
 * 画面的初始化
 */
$(document).ready(function(){
	//在这里你可以绑定从服务器回传回来的数据到查询表单
	alert($("#abc"));
	alert($("#abc").length);
});
</script>
</head>
<body>
	<%--内容区 --%>
	<div id="r">
		<%--一览表格 --%>
		<table  id="mainTable" cellspacing="0" cellpadding="0">
			<%--表头 --%>
			<tr>
				<td class="tableTitle dtd">日期（yyyy-MM-dd）</td>
				<td class="tableTitle dtd">日期（yyyy-MM-dd HH:mm:ss）</td>
				<td class="tableTitle dtd">文本</td>
				<td class="tableTitle dtd">数字（两位小数）</td>
				<td class="tableTitle dtd">数字（四位小数）</td>
			</tr>
			<%--表头 结束 --%>
			
			<% if(pageRecords == null || pageRecords.size() == 0 ){ %>
				<tr><td colspan="5"><div class="red ac">没有查询到符合条件的信息!</div></td></tr>
			<%} else {
				java.util.Iterator iter = pageRecords.iterator();
				FCTRADE_FEEbean fc = null;
				for (int i = 0;iter.hasNext(); i++) {//for_1
					fc = (FCTRADE_FEEbean)iter.next();
			%>
				<%--数据行 --%>
				<tr>
					<td class="dtd ac"><%=fc.ACTUAL_CHARGE_DATE %>&nbsp;</td>
					<td class="dtd ac"><%=fc.FEE_TIME %>&nbsp;</td>
					<td class="dtd al"><%=fc.FEE_ID %>&nbsp;</td>
					<td class="dtd ar"><%=fc.ACTUAL_CHARGE %>&nbsp;</td>
					<td class="dtd ar"><%=fc.FEE_BLANCE %>&nbsp;</td>
				</tr>
				
				<%}%><%--for_1 循环结束 --%>
			<%} %>
		</table>
		<%--一览表格 结束--%>
		
		<%--分页器 开始 --%>
		<div id="nav">
		<%if (pageRecords.iterator().hasNext()) {%>
			<%=pager.getNavigator()%>
		<%}%>
		</div><%--分页器 结束 --%>
	</div>
	<%--内容区 结束 --%>
</body>
</html>