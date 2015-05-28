<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@include file="jspJava.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>文件处理组件</title>
<%
	String path = request.getContextPath();
	Map<String , String > hmnote = new HashMap<String, String>();
	hmnote.put("replace", "find=要查找的内容&replace=要替换的内容&place=要替换的位置（first:last:all）&regular=正则表达式");
	hmnote.put("keyValueCode", "需要输入分隔符，其形式为<b>\t</b>&nbsp;&nbsp;<b>,</b>");
	hmnote.put("recordToFileCopy", "确保文件中每条记录的正确性");
%>

<link href="<%=path%>/css/adminstyle.css" rel="stylesheet" type="text/css" /><%--全局样式 --%>

<%--日期输入控件 --%>
<link href="<%=path%>/css/InputDate.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=path%>/js/InputDate.js"></script>

<%-- 表单检查 共通函数 --%>
<script language="javascript" src="<%=path%>/js/checkInput.js"></script>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<%--jQuery --%>
<script language="Javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript">
/**
 * 画面的初始化
 */
$(document).ready(function(){
	//在这里你可以绑定从服务器回传回来的数据到查询表单
	
});

</script>

</head>
<body>
<form action="<%=path %>/test">
		<input type="hidden" name="action" value="fileDataProcessor" />
		
		<%--一览表格 --%>
		<table  id="mainTable" cellspacing="0" cellpadding="0">
			<%--表头 --%>
			<tr>
				<td class="tableTitle dtd">处理顺序</td>
				<td class="tableTitle dtd">选择处理名称</td>
				<td class="tableTitle dtd">是否生成处理结果</td>
				<td class="tableTitle dtd">处理选项</td>
			</tr>
			<%--表头 结束 --%>
			
			<%--数据行 --%>
			<%
				for (int i = 0; i < hm.size() + 5; i++) {
				%>
					<tr>
						<td class="dtd ac"><%=i %></td>
						<td class="dtd al">
							<select name="pkName">
								<option value="" >请选择处理的名称</option>
								<%
									Set<String> keys = hm.keySet();
									Iterator<String> it = keys.iterator();
									while (it.hasNext()) {
										String key = it.next();
										String val = hm.get(key);
										%>
										<option value="<%=key %>" ><%=val %></option>
									<%}
								%>
							</select>
						</td>
						<td class="dtd al"><input type="text" name="pkOption" style="width: 400px"/></td>
						<td class="dtd al"><input type="checkbox" name="result" value="<%=i%>" checked="checked"/></td>
					</tr>
				<%}
			%>
			<tr>
				<td>输入、输出信息</td>
				<td class="dtd al" >
					输入要处理的文件(文件夹)全路径：<input type="text" name="input" style="width: 400px"/></td>
				<td class="dtd al" >
					输入处理结果存储的文件（文件夹）全路径：<input type="text" name="output" value="D:/DataProcessCenter/02output/result.txt" style="width: 400px"/>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="dtd ac">当前处理使用说明：</td>
				<td class="dtd al" colspan="3">
					<span id="readme"></span>
				</td>
			</tr>
		</table>
		<%--一览表格 结束--%>
		<input type="submit" />
	</form>
</body>
</html>