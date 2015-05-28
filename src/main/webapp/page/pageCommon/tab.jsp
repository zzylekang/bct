<%@page import="com.zzy.dev.comm.util.StringUtil"%>
<%@page import="com.zzy.dev.project.base.component.pager.bean.Page"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>

<%@ page import="" %>

<%
//此处添加java代码
String path = request.getContextPath();
Page curPage = (Page)request.getAttribute("page");//tab1对应的一览数据
List pageRecords = curPage.getPageRecords();
Page curPage2 = (Page)request.getAttribute("page2");//tab2对应的一览数据
List pageRecords2 = curPage2.getPageRecords();
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
隐藏或显示每行对应的明细
detail:明细行对应的id
ico:图标
a:当前点击的链接
*/
function changeDetail(detail, ico, a){
	if ($("#"+detail).attr("class").indexOf("show_none") != -1) {//明细隐藏的场合
		$("#"+detail).removeClass("show_none");//显示明细
		$("#"+ico).attr("src", "images/xxico02.gif");
		$(a).find("span").html("收起明细");
	} else {
		$("#"+detail).addClass("show_none");//隐藏明细
		$("#"+ico).attr("src", "images/xxico.gif");
		$(a).find("span").html("展开明细");
	}
}

/**
 * 画面的初始化
 */
$(document).ready(function(){
	//在这里你可以绑定从服务器回传回来的数据到查询表单
	
	//初始化tab
	var tabFlag = '<%="1"%>';
	$("#tab1").removeClass("beginaa");
	$("#tab2").removeClass("beginaa");
	
	$("#tab1_list").addClass("show_none");
	$("#tab2_list").addClass("show_none");
	if (tabFlag == "1" || tabFlag == "") {
		$("#tab1").addClass("hoveraa");
		$("#tab1_list").removeClass("show_none");
	} else if (tabFlag == "2") {
		$("#tab2").addClass("hoveraa");
		$("#tab2_list").removeClass("show_none");
	}
	
	//tab切换的处理函数
	$(".tabli").click(function(this){
		var clickid = $(this).attr("id");//取得单击的Tab的ID
		
		//先隐藏所有的一览
		$("#tab1_list").addClass("show_none");
		$("#tab2_list").addClass("show_none");
		
		if (clickid == "tab1") {//tab1单击时
			$("#tab1_list").removeClass("show_none");
		} else if (clickid == "tab2") {
			$("#tab2_list").removeClass("show_none");
		}
	});
});
</script>
</head>
<body>
	<%--内容区 --%>
	<div id="r">
		<%--
			说明：这里的tab切换时，不向后台发送请求，初始化时查询两个tab的数据
			当在一个tab翻页时，要同时传递两个tab页中的页数。
			定义两个变量pageNo1、pageNo2做为url中的参数即可，pageNo1、pageNo2的值由Page类去维护。
		 --%>
		<%--tab 区开始 --%>
		<div class="cxglbox1">
		   <div class="menuaa">
		     <ul style="margin:0px; padding:0px;">
		       <li id="tab1" class="tabli">tab1</li>
		       <li id="tab2" class="tabli">tab2</li>
		     </ul>
		   </div>
		 </div>
		 <%--tab 区结束 --%>
		 
		 <div id="tab1_list"><%--tab1 一览数据 --%>
			<%--一览表格 --%>
			<table  id="mainTable" cellspacing="0" cellpadding="0">
				<%--表头 --%>
				<tr>
					<td class="tableTitle dtd">日期（yyyy-MM-dd）</td>
					<td class="tableTitle dtd">日期（yyyy-MM-dd HH:mm:ss）</td>
					<td class="tableTitle dtd">文本</td>
					<td class="tableTitle dtd">数字（两位小数）</td>
					<td class="tableTitle dtd">数字（四位小数）</td>
					<td class="tableTitle dtd">链接</td>
					<td class="tableTitle dtd">状态</td>
					<td class="tableTitle dtd">展开明细</td>
				</tr>
				<%--表头 结束 --%>
				
				<% if(pageRecords == null || pageRecords.size() > 0 ){ %>
					<tr><td colspan="输入列数"><div class="red ac">没有查询到符合条件的信息!</div></td></tr>
				<%} else {
					java.util.Iterator iter = pageRecords.iterator();
					for (int i = 0;iter.hasNext(); i++) {//for_1
				%>
					<%--数据行 --%>
					<tr>
						<td class="dtd ac"><%=StringUtil.format(new Date()) %>&nbsp;</td>
						<td class="dtd ac"><%=StringUtil.formatLongDate(new Date()) %>&nbsp;</td>
						<td class="dtd al"><%=StringUtil.formatNull("文本") %>&nbsp;</td>
						<td class="dtd ar"><%=StringUtil.format(666.55) %>&nbsp;</td>
						<td class="dtd ar"><%=StringUtil.format(666.55) %>&nbsp;</td>
						<td class="dtd al"><a href="<%=path%>/servletNameXXX?action=XXX&key=<%="value"%>" title=""></a>&nbsp;</td>
						<%--关于状态的显示，有两种方式
							一：将状态的key:value压入一个HashMap,此处从map中取得value
							二：在行对应的数据对象中添加方法，此处调用（推荐）
						 --%>
						<td class="dtd al">状态</td>
						<td class="dtd ac">
							<a onclick="changeDetail('detail<%=i %>', 'ico<%=i %>', this);" style="cursor:hand;" class="cBlue"><span>展开明细</span>
								<img id="ico<%=i%>" src="images/xxico.gif" align="absmiddle" /></a>
						</td>
					</tr>
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
		 </div><%--tab1 一览数据 结束 --%>
		 
		 <div id="tab2_list"><%--tab2 一览数据 --%>
			<%--一览表格 --%>
			<table  id="mainTable" cellspacing="0" cellpadding="0">
				<%--表头 --%>
				<tr>
					<td class="tableTitle dtd">日期（yyyy-MM-dd）</td>
					<td class="tableTitle dtd">日期（yyyy-MM-dd HH:mm:ss）</td>
					<td class="tableTitle dtd">文本</td>
					<td class="tableTitle dtd">数字（两位小数）</td>
					<td class="tableTitle dtd">数字（四位小数）</td>
					<td class="tableTitle dtd">链接</td>
					<td class="tableTitle dtd">状态</td>
					<td class="tableTitle dtd">展开明细</td>
				</tr>
				<%--表头 结束 --%>
				
				<% if(pageRecords2 == null || pageRecords2.size() > 0 ){ %>
					<tr><td colspan="输入列数"><div class="red ac">没有查询到符合条件的信息!</div></td></tr>
				<%} else {
					java.util.Iterator iter = pageRecords2.iterator();
					for (int i = 0;iter.hasNext(); i++) {//for_1
				%>
					<%--数据行 --%>
					<tr>
						<td class="dtd ac"><%=StringUtil.format(new Date()) %>&nbsp;</td>
						<td class="dtd ac"><%=StringUtil.formatLongDate(new Date()) %>&nbsp;</td>
						<td class="dtd al"><%=StringUtil.formatNull("文本") %>&nbsp;</td>
						<td class="dtd ar"><%=StringUtil.format(666.55) %>&nbsp;</td>
						<td class="dtd ar"><%=StringUtil.format(666.55) %>&nbsp;</td>
						<td class="dtd al"><a href="<%=path%>/servletNameXXX?action=XXX&key=<%="value"%>" title=""></a>&nbsp;</td>
						<%--关于状态的显示，有两种方式
							一：将状态的key:value压入一个HashMap,此处从map中取得value
							二：在行对应的数据对象中添加方法，此处调用（推荐）
						 --%>
						<td class="dtd al">状态</td>
						<td class="dtd ac">
							<a onclick="changeDetail('detail<%=i %>', 'ico<%=i %>', this);" style="cursor:hand;" class="cBlue"><span>展开明细</span>
								<img id="ico<%=i%>" src="images/xxico.gif" align="absmiddle" /></a>
						</td>
					</tr>
					<%}%><%--for_1 循环结束 --%>
				<%} %>
			</table>
			<%--一览表格 结束--%>
			
			<%--分页器 开始 --%>
			<div id="nav2">
			<%if (pageRecords2.size() > 0) {%>
				<%=curPage2.getNavigator()%>
			<%}%>
			</div><%--分页器 结束 --%>
		 </div><%--tab1 一览数据 结束--%>
	</div>
	<%--内容区 结束 --%>
</body>
</html>