<%@page import="com.zzy.dev.project.base.component.pager.bean.Page"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="" %>

<%
//�˴����java����
String path = request.getContextPath();
Page curPage = (Page)request.getAttribute("page");
List pageRecords = curPage.getPageRecords();
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

function formSubmit(index){
	//����index,��ȡ�ñ����ݣ��Ա���м����ύ��
	$("#form"+index).submit();//�ύ��
}
</script>
</head>
<body>
	<%--������ --%>
	<div id="r">
	
		<%--��ѯ�� --%>
		<div id="search"  style="width:100%">
			<form name="f1" id="f1" method="post" action="servlet����">
				<input type="hidden" name="action" value="action����" />
				<%-- <input type="hidden" name="act" value="action�з�������" />--%>
				
				
				�ı���<input name="������" type="text" size="10" value="<%="�û�������ʱ�����ֵ"%>"/><%--�ı����ֵ������ֱ�Ӱ� --%>
				ѡ���������
					<select name="������"><%--�����ڿͻ��˳�ʼ��ʱ���û�ѡ���ֵ --%>
					<option value="">XXXX</option>
					<option value="���ݿ��ֶ�1">XXXX</option>
					</select>
				ѡ���ֵ���ͣ�
					<select name="������"><%--�����ڿͻ��˳�ʼ��ʱ���û�ѡ���ֵ --%>
					<option value="">��ѡ��XXX</option>
					<option value="ֵ1">XXXX</option>
					</select>
				����<input type="text" size=10 id="startdate" name="startdate" value="<%="�û�������ʱ�����ֵ"%>" />
				<IMG      onmouseup="_inputDate_toggleDatePicker( 'startdate' );"
				          id='startdateImg' style="CURSOR: hand" height=16 alt=��ѡ��ʼ����
				          src="<%=path%>/images/y.gif" width=16 align=absMiddle
				          cancelIcon="<%=path%>/images/InputDate/dateCancelButton.gif"
				          submitIcon="<%=path%>/images/InputDate/dateSubmitButton.gif"/> �� 
				<input size=10 type="text" id="enddate" name="enddate" value="<%="�û�������ʱ�����ֵ"%>" />
				<IMG      onmouseup="_inputDate_toggleDatePicker( 'queryenddate' );"
				          id='enddateImg' style="CURSOR: hand" height=16 alt=��ѡ���������
				          src="<%=path%>/images/y.gif" width=16 align=absMiddle
				          cancelIcon="<%=path%>/images/InputDate/dateCancelButton.gif"
				          submitIcon="<%=path%>/images/InputDate/dateSubmitButton.gif"/>
				          
				<input name="searchButton" id="searchButton" type="button" class="button" onclick="searchSubmit()" value="��ѯ"/>
			</form>
		</div>
		<%--��ѯ�� ���� --%>
		
		<%--һ����� --%>
		<table  id="mainTable" cellspacing="0" cellpadding="0">
			<%--��ͷ --%>
			<tr>
				<td class="tableTitle dtd">����</td>
				<td class="tableTitle dtd">�ı���</td>
				<td class="tableTitle dtd">�����б�</td>
				<td class="tableTitle dtd">��ť�ύ</td>
			</tr>
			<%--��ͷ ���� --%>
			
			<% if(pageRecords == null || pageRecords.size() == 0 ){ %>
				<tr><td colspan="��������"><div class="red ac">û�в�ѯ��������������Ϣ!</div></td></tr>
			<%} else {
				java.util.Iterator iter = pageRecords.iterator();
				for (int i = 0;iter.hasNext(); i++) {//for_1
			%>
				
				<%--formλ����Ҫȡ������Ҫ�ύ��Щ���ݣ� form��ʼ�������ı�Ԫ�ص�ֵ�ᱻ�ύ�������� --%>
				<form name="form<%=i %>" id="form<%=i %>" method="post" action="servlet����">
					<input type="hidden" name="action" value="action����" />
					<%-- <input type="hidden" name="act" value="action�з�������" />--%>
					
					<%--������ --%>
					<tr>
						<td class="dtd ac">
							<input id="date<%=i %>" name="date" ondblclick="javascript:doClear(this);" readonly="readonly" type="text" size="10"/>
			    			<img onmouseup="_inputDate_toggleDatePicker('date<%=i %>');" id='date<%=i %>Pos' 
				    			style="cursor: hand" height=16 alt="��ѡ��ʱ��" src="images/y.gif" 
				    			width="16" align="absMiddle"  cancelIcon="images/InputDate/dateCancelButton.gif" 
				    			submitIcon="images/InputDate/dateSubmitButton.gif"/></td>
						<td class="dtd ac">
							<input id="text<%=i %>" name="text" type="text"/></td>
						<td class="dtd al">
							<select name="select1" id="select<%=i%>">
							<option value="">XXXX</option>
							<option value="ֵ1">XXXX</option>
							</select></td>
						<td class="dtd ar">
							<input type="button" class="button" value="�ύ" onclick="formSubmit('<%=i %>')" name="inmoney"/></td>
						<td></td>
					</tr><%--������ ���� --%>
				</form>
				
				<%}%><%--for_1 ѭ������ --%>
			<%} %>
		</table>
		<%--һ����� ����--%>
		
		<%--��ҳ�� ��ʼ --%>
		<div id="nav">
		<%if (pageRecords.size() > 0) {%>
			<%=curPage.getNavigator()%>
		<%}%>
		</div><%--��ҳ�� ���� --%>
	</div>
	<%--������ ���� --%>
</body>
</html>