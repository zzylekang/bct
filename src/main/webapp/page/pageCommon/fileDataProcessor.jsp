<?xml version="1.0" encoding="GBK" ?>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<%@include file="jspJava.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>�ļ��������</title>
<%
	String path = request.getContextPath();
	Map<String , String > hmnote = new HashMap<String, String>();
	hmnote.put("replace", "find=Ҫ���ҵ�����&replace=Ҫ�滻������&place=Ҫ�滻��λ�ã�first:last:all��&regular=������ʽ");
	hmnote.put("keyValueCode", "��Ҫ����ָ���������ʽΪ<b>\t</b>&nbsp;&nbsp;<b>,</b>");
	hmnote.put("recordToFileCopy", "ȷ���ļ���ÿ����¼����ȷ��");
%>

<link href="<%=path%>/css/adminstyle.css" rel="stylesheet" type="text/css" /><%--ȫ����ʽ --%>

<%--��������ؼ� --%>
<link href="<%=path%>/css/InputDate.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=path%>/js/InputDate.js"></script>

<%-- ����� ��ͨ���� --%>
<script language="javascript" src="<%=path%>/js/checkInput.js"></script>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<%--jQuery --%>
<script language="Javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript">
/**
 * ����ĳ�ʼ��
 */
$(document).ready(function(){
	//����������԰󶨴ӷ������ش����������ݵ���ѯ��
	
});

</script>

</head>
<body>
<form action="<%=path %>/test">
		<input type="hidden" name="action" value="fileDataProcessor" />
		
		<%--һ����� --%>
		<table  id="mainTable" cellspacing="0" cellpadding="0">
			<%--��ͷ --%>
			<tr>
				<td class="tableTitle dtd">����˳��</td>
				<td class="tableTitle dtd">ѡ��������</td>
				<td class="tableTitle dtd">�Ƿ����ɴ�����</td>
				<td class="tableTitle dtd">����ѡ��</td>
			</tr>
			<%--��ͷ ���� --%>
			
			<%--������ --%>
			<%
				for (int i = 0; i < hm.size() + 5; i++) {
				%>
					<tr>
						<td class="dtd ac"><%=i %></td>
						<td class="dtd al">
							<select name="pkName">
								<option value="" >��ѡ���������</option>
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
				<td>���롢�����Ϣ</td>
				<td class="dtd al" >
					����Ҫ������ļ�(�ļ���)ȫ·����<input type="text" name="input" style="width: 400px"/></td>
				<td class="dtd al" >
					���봦�����洢���ļ����ļ��У�ȫ·����<input type="text" name="output" value="D:/DataProcessCenter/02output/result.txt" style="width: 400px"/>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="dtd ac">��ǰ����ʹ��˵����</td>
				<td class="dtd al" colspan="3">
					<span id="readme"></span>
				</td>
			</tr>
		</table>
		<%--һ����� ����--%>
		<input type="submit" />
	</form>
</body>
</html>