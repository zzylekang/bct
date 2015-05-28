<%@page import="com.zzy.dev.comm.util.StringUtil"%>
<%@page import="com.zzy.dev.project.base.component.pager.bean.Page"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>

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
/*.positionre{ position:relative;width:0;height:0;}*/
.zwmxzy{display:none;position:absolute;width:477px;left:-300px;*left:-400px;top:0px;z-index:100;
		padding:0px;background-color:#FFFFFF;border:1px solid #ccc; text-align: center;line-height:28px;}
.zwmxzy .zhmxtable{border-top: 1px solid #ccc; border-right: 1px solid #ccc;padding:0px;background-color:#FFFFFF;width:100%}
.zwmxzy .zhmxtable tr.bgaa{ background:#eee}
.zwmxzy .zhmxtable td{border-left: 1px solid #ccc; border-bottom: 1px solid #ccc;padding:0px;}
</style>

<%--页面脚本 --%>
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
	
});
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
				<td class="tableTitle dtd">日期（yyyy-MM-dd）</td>
				<td class="tableTitle dtd">日期（yyyy-MM-dd HH:mm:ss）</td>
				<td class="tableTitle dtd">文本</td>
				<td class="tableTitle dtd">数字（两位小数）</td>
				<td class="tableTitle dtd">数字（四位小数）</td>
				<td class="tableTitle dtd">链接</td>
				<td class="tableTitle dtd">状态</td>
				<td class="tableTitle dtd">展开明细</td>
				<td class="tableTitle dtd">展开明细(弹出层)</td>
			</tr>
			<%--表头 结束 --%>
			
			<% if(pageRecords == null || pageRecords.size() == 0 ){ %>
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
					<td class="dtd ar"><%=StringUtil.formatFour(666.55) %>&nbsp;</td>
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
					<td class="dtd ac">
						<span>
							<a onclick="changeDetail('detail_div<%=i %>', 'ico<%=i %>', this);" style="cursor:hand;" class="cBlue"><span>展开明细</span>
								<img id="ico<%=i%>" src="images/xxico.gif" align="absmiddle" /></a>
						</span>
						
						<%--弹出层开始 --%>
						<div class="positionre">
	              			<div class="zwmxzy" id="detail_div<%=i %>">
								<table cellpadding="0" cellspacing="0" class="zhmxtable">
									<tr class="b bgaa">  
										<td>信托计划编号<br /><br /></td>
										<td>起始日<br /><br /></td>
										<td>到期日<br /><br /></td>
										<td>赎货保证金(元)<br /><br /></td></tr>
									<tr>  
										<td>值1<br /><br /></td>
										<td>值2<br /><br /></td>
										<td>值3<br /><br /></td>
										<td>值4<br /><br /></td></tr>
								</table>
							</div>
						</div><%--弹出层 结束 --%>
					</td>
				</tr>
				
				<%--明细 --%>
				<tr id="detail<%=i %>" class="show_none">
					<td colspan="明细要跨的列数">
						<%-- 此处放置表格 --%>
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
	</div>
	<%--内容区 结束 --%>
</body>
</html>