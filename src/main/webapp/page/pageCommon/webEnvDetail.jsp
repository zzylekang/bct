<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>

<%
//此处添加java代码
String path = request.getContextPath();

Properties pageRecords = System.getProperties();
Map<String, String> hm  = new HashMap<String, String>();
hm.put("java.version","Java 运行时环境版本");
hm.put("java.vendor","Java 运行时环境供应商");
hm.put("java.vendor.url","Java 供应商的 URL");
hm.put("java.home","Java 安装目录");
hm.put("java.vm.specification.version","Java 虚拟机规范版本");
hm.put("java.vm.specification.vendor","Java 虚拟机规范供应商");
hm.put("java.vm.specification.name","Java 虚拟机规范名称");
hm.put("java.vm.version","Java 虚拟机实现版本");
hm.put("java.vm.vendor","Java 虚拟机实现供应商");
hm.put("java.vm.name","Java 虚拟机实现名称");
hm.put("java.specification.version","Java 运行时环境规范版本");
hm.put("java.specification.vendor","Java 运行时环境规范供应商");
hm.put("java.specification.name","Java 运行时环境规范名称");
hm.put("java.class.version","Java 类格式版本号");
hm.put("java.class.path","Java 类路径");
hm.put("java.library.path","加载库时搜索的路径列表");
hm.put("java.io.tmpdir","默认的临时文件路径");
hm.put("java.compiler","要使用的 JIT 编译器的名称");
hm.put("java.ext.dirs","一个或多个扩展目录的路径");
hm.put("os.name","操作系统的名称");
hm.put("os.arch","操作系统的架构");
hm.put("os.version","操作系统的版本");
hm.put("file.separator","文件分隔符（在 UNIX 系统中是“/”）");
hm.put("path.separator","路径分隔符（在 UNIX 系统中是“:”）");
hm.put("line.separator","行分隔符（在 UNIX 系统中是“/n”）");
hm.put("user.name","用户的账户名称");
hm.put("user.home","用户的主目录");
hm.put("user.dir","用户的当前工作目录");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>获得web服务器的系统环境</title>

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
	
});
</script>
</head>
<body>
	<%--内容区 --%>
	<div id="r">
		<p>获得web服务器的系统环境</p>
		<%--一览表格 --%>
		<table  id="mainTable" cellspacing="0" cellpadding="0">
			<%--表头 --%>
			<tr>
				<td class="tableTitle dtd">属性key</td>
				<td class="tableTitle dtd">属性值</td>
				<td class="tableTitle dtd">属性说明</td>
			</tr>
			<%--表头 结束 --%>
			
			<% if(pageRecords.isEmpty()){ %>
				<tr><td colspan="2"><div class="red ac">没有查询到符合条件的信息!</div></td></tr>
			<%} else {
				Iterator<Object> keys =  pageRecords.keySet().iterator();
				for (int i = 0; keys.hasNext(); i++) {//for_1
					String key = (String)keys.next();
					
			%>
				<%--数据行 --%>
				<tr>
					<td class="dtd al"><%=key %>&nbsp;</td>
					<td class="dtd al"><%=pageRecords.get(key) %>&nbsp;</td>
					<td class="dtd al"><%=hm.get(key) %>&nbsp;</td>
					
				</tr>
				
				<%}%><%--for_1 循环结束 --%>
			<%} %>
		</table>
		<%--一览表格 结束--%>
	</div>
	<%--内容区 结束 --%>
</body>
</html>