<%@page import="com.zzy.dev.project.base.component.pager.bean.Page"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="" %>

<%
//此处添加java代码
String path = request.getContextPath();
Page curPage = (Page)request.getAttribute("page");
List pageRecords = curPage.getPageRecords();
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

function formSubmit(index){
	//根据index,能取得表单数据，以便进行检查和提交表单
	$("#form"+index).submit();//提交表单
}
</script>
</head>
<body>
	<%--内容区 --%>
	<div id="r">
	
		<%--查询表单 --%>
		<div id="search"  style="width:100%">
			<form name="f1" id="f1" method="post" action="servlet名称">
				<input type="hidden" name="action" value="action名称" />
				<%-- <input type="hidden" name="act" value="action中方法名称" />--%>
				
				
				文本框<input name="参数名" type="text" size="10" value="<%="用户在请求时输入的值"%>"/><%--文本框的值，建议直接绑定 --%>
				选择框（条件）
					<select name="参数名"><%--建议在客户端初始化时绑定用户选择的值 --%>
					<option value="">XXXX</option>
					<option value="数据库字段1">XXXX</option>
					</select>
				选择框（值类型）
					<select name="参数名"><%--建议在客户端初始化时绑定用户选择的值 --%>
					<option value="">请选择XXX</option>
					<option value="值1">XXXX</option>
					</select>
				日期<input type="text" size=10 id="startdate" name="startdate" value="<%="用户在请求时输入的值"%>" />
				<IMG      onmouseup="_inputDate_toggleDatePicker( 'startdate' );"
				          id='startdateImg' style="CURSOR: hand" height=16 alt=请选择开始日期
				          src="<%=path%>/images/y.gif" width=16 align=absMiddle
				          cancelIcon="<%=path%>/images/InputDate/dateCancelButton.gif"
				          submitIcon="<%=path%>/images/InputDate/dateSubmitButton.gif"/> 到 
				<input size=10 type="text" id="enddate" name="enddate" value="<%="用户在请求时输入的值"%>" />
				<IMG      onmouseup="_inputDate_toggleDatePicker( 'queryenddate' );"
				          id='enddateImg' style="CURSOR: hand" height=16 alt=请选择结束日期
				          src="<%=path%>/images/y.gif" width=16 align=absMiddle
				          cancelIcon="<%=path%>/images/InputDate/dateCancelButton.gif"
				          submitIcon="<%=path%>/images/InputDate/dateSubmitButton.gif"/>
				          
				<input name="searchButton" id="searchButton" type="button" class="button" onclick="searchSubmit()" value="查询"/>
			</form>
		</div>
		<%--查询表单 结束 --%>
		
		<%--一览表格 --%>
		<table  id="mainTable" cellspacing="0" cellpadding="0">
			<%--表头 --%>
			<tr>
				<td class="tableTitle dtd">日期</td>
				<td class="tableTitle dtd">文本框</td>
				<td class="tableTitle dtd">下拉列表</td>
				<td class="tableTitle dtd">按钮提交</td>
			</tr>
			<%--表头 结束 --%>
			
			<% if(pageRecords == null || pageRecords.size() == 0 ){ %>
				<tr><td colspan="输入列数"><div class="red ac">没有查询到符合条件的信息!</div></td></tr>
			<%} else {
				java.util.Iterator iter = pageRecords.iterator();
				for (int i = 0;iter.hasNext(); i++) {//for_1
			%>
				
				<%--form位置主要取决于你要提交哪些内容， form开始到结束的表单元素的值会被提交到服务器 --%>
				<form name="form<%=i %>" id="form<%=i %>" method="post" action="servlet名称">
					<input type="hidden" name="action" value="action名称" />
					<%-- <input type="hidden" name="act" value="action中方法名称" />--%>
					
					<%--数据行 --%>
					<tr>
						<td class="dtd ac">
							<input id="date<%=i %>" name="date" ondblclick="javascript:doClear(this);" readonly="readonly" type="text" size="10"/>
			    			<img onmouseup="_inputDate_toggleDatePicker('date<%=i %>');" id='date<%=i %>Pos' 
				    			style="cursor: hand" height=16 alt="请选择时间" src="images/y.gif" 
				    			width="16" align="absMiddle"  cancelIcon="images/InputDate/dateCancelButton.gif" 
				    			submitIcon="images/InputDate/dateSubmitButton.gif"/></td>
						<td class="dtd ac">
							<input id="text<%=i %>" name="text" type="text"/></td>
						<td class="dtd al">
							<select name="select1" id="select<%=i%>">
							<option value="">XXXX</option>
							<option value="值1">XXXX</option>
							</select></td>
						<td class="dtd ar">
							<input type="button" class="button" value="提交" onclick="formSubmit('<%=i %>')" name="inmoney"/></td>
						<td></td>
					</tr><%--数据行 结束 --%>
				</form>
				
				<%}%><%--for_1 循环结束 --%>
			<%} %>
		</table>
		<%--一览表格 结束--%>
		
		<%--分页器 开始 --%>
		<div id="nav">
		<%if (pageRecords.size() > 0) {%>
			<%=curPage.getNavigator()%>
		<%}%>
		</div><%--分页器 结束 --%>
	</div>
	<%--内容区 结束 --%>
</body>
</html>