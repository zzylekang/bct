<%@page import="com.zzy.dev.comm.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>

<%@ page import="" %>

<%
//�˴����java����
String path = request.getContextPath();
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

</style>

<%--ҳ��Ų� --%>
<script language="javascript">


/**
 * ����ĳ�ʼ��
 */
$(document).ready(function(){
	//����������԰󶨴ӷ������ش����������ݵ���ѯ��
});
</script>
</head>
<body>
	<%--������ --%>
	<div id="r">
	
		<%--������ϸ��� --%>
		<table  class="inputTable" cellspacing="0" cellpadding="0">

			<tr><td colspan="2" class="tableTitle al">Ƭ����Ϣͷ1</td></tr>
			<tr><td style="width:200px" class="ar">�ı���</td><td><%=StringUtil.formatNull("�ı�") %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">���֣���λС������</td><td><%=StringUtil.format(666.55) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">����(��λС��)��</td><td><%=StringUtil.formatFour(666.55) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">����(yyyy-MM-dd)��</td><td><%=StringUtil.format(new Date()) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">����(yyyy-MM-dd hh:mm:ss)��</td><td><%=StringUtil.formatLongDate(new Date()) %>&nbsp;</td></tr>
			
			<tr><td colspan="2" class="tableTitle al">Ƭ����Ϣͷ2</td></tr>
			<tr><td style="width:200px" class="ar">�ı���</td><td><%=StringUtil.formatNull("�ı�") %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">���֣���λС������</td><td><%=StringUtil.format(666.55) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">����(��λС��)��</td><td><%=StringUtil.formatFour(666.55) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">����(yyyy-MM-dd)��</td><td><%=StringUtil.format(new Date()) %>&nbsp;</td></tr>
			<tr><td style="width:200px" class="ar">����(yyyy-MM-dd hh:mm:ss)��</td><td><%=StringUtil.formatLongDate(new Date()) %>&nbsp;</td></tr>
		</table>
		<%--���б�� ����--%>
		
		<%--������ϸ��� --%>
		
		<%--������ϸ��� ���� --%>
	</div>
	<%--������ ���� --%>
</body>
</html>