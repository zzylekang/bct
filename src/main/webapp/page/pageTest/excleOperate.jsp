<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="common.pager.bean.Page" %>
<%@ page import="test.pagerTest.bean.FCTRADE_FEEbean" %>

<%
//�˴����java����
String path = request.getContextPath();

//��������ı������ܶ���Ϊ����page�������롱��%@ page--%�����еġ�page�����ͻ
Page pager = (Page)request.getAttribute("page");

List pageRecords = pager.getPageRecords();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>��ҳ�棨ҳ���ܣ�</title>

<link href="<%=path%>/css/adminstyle.css" rel="stylesheet" type="text/css" /><%--ȫ����ʽ --%>

<%--��������ؼ� --%>
<link href="<%=path%>/css/InputDate.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=path%>/js/InputDate.js"></script>

<%-- ����� ��ͨ���� --%>
<script language="javascript" src="<%=path%>/js/checkInput.js"></script>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<%--jQuery --%>
<script language="Javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>

<%--ҳ���Զ�����ʽ --%>
<style type="text/css">
/*.positionre{ position:relative;width:0;height:0;}*/
.zwmxzy{display:none;position:absolute;width:477px;left:-300px;*left:-400px;top:0px;z-index:100;
		padding:0px;background-color:#FFFFFF;border:1px solid #ccc; text-align: center;line-height:28px;}
.zwmxzy .zhmxtable{border-top: 1px solid #ccc; border-right: 1px solid #ccc;padding:0px;background-color:#FFFFFF;width:100%}
.zwmxzy .zhmxtable tr.bgaa{ background:#eee}
.zwmxzy .zhmxtable td{border-left: 1px solid #ccc; border-bottom: 1px solid #ccc;padding:0px;}
</style>

<%--ҳ��Ų� --%>
<script language="javascript">

/**
 * ����ĳ�ʼ��
 */
$(document).ready(function(){
	//����������԰󶨴ӷ������ش����������ݵ���ѯ��
	alert($("#abc"));
	alert($("#abc").length);
});
</script>
</head>
<body>
	<%--������ --%>
	<div id="r">
		<%--һ����� --%>
		<table  id="mainTable" cellspacing="0" cellpadding="0">
			<%--��ͷ --%>
			<tr>
				<td class="tableTitle dtd">���ڣ�yyyy-MM-dd��</td>
				<td class="tableTitle dtd">���ڣ�yyyy-MM-dd HH:mm:ss��</td>
				<td class="tableTitle dtd">�ı�</td>
				<td class="tableTitle dtd">���֣���λС����</td>
				<td class="tableTitle dtd">���֣���λС����</td>
			</tr>
			<%--��ͷ ���� --%>
			
			<% if(pageRecords == null || pageRecords.size() == 0 ){ %>
				<tr><td colspan="5"><div class="red ac">û�в�ѯ��������������Ϣ!</div></td></tr>
			<%} else {
				java.util.Iterator iter = pageRecords.iterator();
				FCTRADE_FEEbean fc = null;
				for (int i = 0;iter.hasNext(); i++) {//for_1
					fc = (FCTRADE_FEEbean)iter.next();
			%>
				<%--������ --%>
				<tr>
					<td class="dtd ac"><%=fc.ACTUAL_CHARGE_DATE %>&nbsp;</td>
					<td class="dtd ac"><%=fc.FEE_TIME %>&nbsp;</td>
					<td class="dtd al"><%=fc.FEE_ID %>&nbsp;</td>
					<td class="dtd ar"><%=fc.ACTUAL_CHARGE %>&nbsp;</td>
					<td class="dtd ar"><%=fc.FEE_BLANCE %>&nbsp;</td>
				</tr>
				
				<%}%><%--for_1 ѭ������ --%>
			<%} %>
		</table>
		<%--һ����� ����--%>
		
		<%--��ҳ�� ��ʼ --%>
		<div id="nav">
		<%if (pageRecords.iterator().hasNext()) {%>
			<%=pager.getNavigator()%>
		<%}%>
		</div><%--��ҳ�� ���� --%>
	</div>
	<%--������ ���� --%>
</body>
</html>