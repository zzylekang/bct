<%@page import="com.zzy.dev.comm.util.StringUtil"%>
<%@page import="com.zzy.dev.project.base.component.pager.bean.Page"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>

<%@ page import="" %>

<%
//�˴����java����
String path = request.getContextPath();
Page curPage = (Page)request.getAttribute("page");//tab1��Ӧ��һ������
List pageRecords = curPage.getPageRecords();
Page curPage2 = (Page)request.getAttribute("page2");//tab2��Ӧ��һ������
List pageRecords2 = curPage2.getPageRecords();
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
���ػ���ʾÿ�ж�Ӧ����ϸ
detail:��ϸ�ж�Ӧ��id
ico:ͼ��
a:��ǰ���������
*/
function changeDetail(detail, ico, a){
	if ($("#"+detail).attr("class").indexOf("show_none") != -1) {//��ϸ���صĳ���
		$("#"+detail).removeClass("show_none");//��ʾ��ϸ
		$("#"+ico).attr("src", "images/xxico02.gif");
		$(a).find("span").html("������ϸ");
	} else {
		$("#"+detail).addClass("show_none");//������ϸ
		$("#"+ico).attr("src", "images/xxico.gif");
		$(a).find("span").html("չ����ϸ");
	}
}

/**
 * ����ĳ�ʼ��
 */
$(document).ready(function(){
	//����������԰󶨴ӷ������ش����������ݵ���ѯ��
	
	//��ʼ��tab
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
	
	//tab�л��Ĵ�����
	$(".tabli").click(function(this){
		var clickid = $(this).attr("id");//ȡ�õ�����Tab��ID
		
		//���������е�һ��
		$("#tab1_list").addClass("show_none");
		$("#tab2_list").addClass("show_none");
		
		if (clickid == "tab1") {//tab1����ʱ
			$("#tab1_list").removeClass("show_none");
		} else if (clickid == "tab2") {
			$("#tab2_list").removeClass("show_none");
		}
	});
});
</script>
</head>
<body>
	<%--������ --%>
	<div id="r">
		<%--
			˵���������tab�л�ʱ�������̨�������󣬳�ʼ��ʱ��ѯ����tab������
			����һ��tab��ҳʱ��Ҫͬʱ��������tabҳ�е�ҳ����
			������������pageNo1��pageNo2��Ϊurl�еĲ������ɣ�pageNo1��pageNo2��ֵ��Page��ȥά����
		 --%>
		<%--tab ����ʼ --%>
		<div class="cxglbox1">
		   <div class="menuaa">
		     <ul style="margin:0px; padding:0px;">
		       <li id="tab1" class="tabli">tab1</li>
		       <li id="tab2" class="tabli">tab2</li>
		     </ul>
		   </div>
		 </div>
		 <%--tab ������ --%>
		 
		 <div id="tab1_list"><%--tab1 һ������ --%>
			<%--һ����� --%>
			<table  id="mainTable" cellspacing="0" cellpadding="0">
				<%--��ͷ --%>
				<tr>
					<td class="tableTitle dtd">���ڣ�yyyy-MM-dd��</td>
					<td class="tableTitle dtd">���ڣ�yyyy-MM-dd HH:mm:ss��</td>
					<td class="tableTitle dtd">�ı�</td>
					<td class="tableTitle dtd">���֣���λС����</td>
					<td class="tableTitle dtd">���֣���λС����</td>
					<td class="tableTitle dtd">����</td>
					<td class="tableTitle dtd">״̬</td>
					<td class="tableTitle dtd">չ����ϸ</td>
				</tr>
				<%--��ͷ ���� --%>
				
				<% if(pageRecords == null || pageRecords.size() > 0 ){ %>
					<tr><td colspan="��������"><div class="red ac">û�в�ѯ��������������Ϣ!</div></td></tr>
				<%} else {
					java.util.Iterator iter = pageRecords.iterator();
					for (int i = 0;iter.hasNext(); i++) {//for_1
				%>
					<%--������ --%>
					<tr>
						<td class="dtd ac"><%=StringUtil.format(new Date()) %>&nbsp;</td>
						<td class="dtd ac"><%=StringUtil.formatLongDate(new Date()) %>&nbsp;</td>
						<td class="dtd al"><%=StringUtil.formatNull("�ı�") %>&nbsp;</td>
						<td class="dtd ar"><%=StringUtil.format(666.55) %>&nbsp;</td>
						<td class="dtd ar"><%=StringUtil.format(666.55) %>&nbsp;</td>
						<td class="dtd al"><a href="<%=path%>/servletNameXXX?action=XXX&key=<%="value"%>" title=""></a>&nbsp;</td>
						<%--����״̬����ʾ�������ַ�ʽ
							һ����״̬��key:valueѹ��һ��HashMap,�˴���map��ȡ��value
							�������ж�Ӧ�����ݶ�������ӷ������˴����ã��Ƽ���
						 --%>
						<td class="dtd al">״̬</td>
						<td class="dtd ac">
							<a onclick="changeDetail('detail<%=i %>', 'ico<%=i %>', this);" style="cursor:hand;" class="cBlue"><span>չ����ϸ</span>
								<img id="ico<%=i%>" src="images/xxico.gif" align="absmiddle" /></a>
						</td>
					</tr>
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
		 </div><%--tab1 һ������ ���� --%>
		 
		 <div id="tab2_list"><%--tab2 һ������ --%>
			<%--һ����� --%>
			<table  id="mainTable" cellspacing="0" cellpadding="0">
				<%--��ͷ --%>
				<tr>
					<td class="tableTitle dtd">���ڣ�yyyy-MM-dd��</td>
					<td class="tableTitle dtd">���ڣ�yyyy-MM-dd HH:mm:ss��</td>
					<td class="tableTitle dtd">�ı�</td>
					<td class="tableTitle dtd">���֣���λС����</td>
					<td class="tableTitle dtd">���֣���λС����</td>
					<td class="tableTitle dtd">����</td>
					<td class="tableTitle dtd">״̬</td>
					<td class="tableTitle dtd">չ����ϸ</td>
				</tr>
				<%--��ͷ ���� --%>
				
				<% if(pageRecords2 == null || pageRecords2.size() > 0 ){ %>
					<tr><td colspan="��������"><div class="red ac">û�в�ѯ��������������Ϣ!</div></td></tr>
				<%} else {
					java.util.Iterator iter = pageRecords2.iterator();
					for (int i = 0;iter.hasNext(); i++) {//for_1
				%>
					<%--������ --%>
					<tr>
						<td class="dtd ac"><%=StringUtil.format(new Date()) %>&nbsp;</td>
						<td class="dtd ac"><%=StringUtil.formatLongDate(new Date()) %>&nbsp;</td>
						<td class="dtd al"><%=StringUtil.formatNull("�ı�") %>&nbsp;</td>
						<td class="dtd ar"><%=StringUtil.format(666.55) %>&nbsp;</td>
						<td class="dtd ar"><%=StringUtil.format(666.55) %>&nbsp;</td>
						<td class="dtd al"><a href="<%=path%>/servletNameXXX?action=XXX&key=<%="value"%>" title=""></a>&nbsp;</td>
						<%--����״̬����ʾ�������ַ�ʽ
							һ����״̬��key:valueѹ��һ��HashMap,�˴���map��ȡ��value
							�������ж�Ӧ�����ݶ�������ӷ������˴����ã��Ƽ���
						 --%>
						<td class="dtd al">״̬</td>
						<td class="dtd ac">
							<a onclick="changeDetail('detail<%=i %>', 'ico<%=i %>', this);" style="cursor:hand;" class="cBlue"><span>չ����ϸ</span>
								<img id="ico<%=i%>" src="images/xxico.gif" align="absmiddle" /></a>
						</td>
					</tr>
					<%}%><%--for_1 ѭ������ --%>
				<%} %>
			</table>
			<%--һ����� ����--%>
			
			<%--��ҳ�� ��ʼ --%>
			<div id="nav2">
			<%if (pageRecords2.size() > 0) {%>
				<%=curPage2.getNavigator()%>
			<%}%>
			</div><%--��ҳ�� ���� --%>
		 </div><%--tab1 һ������ ����--%>
	</div>
	<%--������ ���� --%>
</body>
</html>