<%@page import="com.zzy.dev.comm.util.StringUtil"%>
<%@page import="com.zzy.dev.project.base.component.pager.bean.Page"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>

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
/*.positionre{ position:relative;width:0;height:0;}*/
.zwmxzy{display:none;position:absolute;width:477px;left:-300px;*left:-400px;top:0px;z-index:100;
		padding:0px;background-color:#FFFFFF;border:1px solid #ccc; text-align: center;line-height:28px;}
.zwmxzy .zhmxtable{border-top: 1px solid #ccc; border-right: 1px solid #ccc;padding:0px;background-color:#FFFFFF;width:100%}
.zwmxzy .zhmxtable tr.bgaa{ background:#eee}
.zwmxzy .zhmxtable td{border-left: 1px solid #ccc; border-bottom: 1px solid #ccc;padding:0px;}
</style>

<%--ҳ��ű� --%>
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
	
});
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
				<td class="tableTitle dtd">���ڣ�yyyy-MM-dd��</td>
				<td class="tableTitle dtd">���ڣ�yyyy-MM-dd HH:mm:ss��</td>
				<td class="tableTitle dtd">�ı�</td>
				<td class="tableTitle dtd">���֣���λС����</td>
				<td class="tableTitle dtd">���֣���λС����</td>
				<td class="tableTitle dtd">����</td>
				<td class="tableTitle dtd">״̬</td>
				<td class="tableTitle dtd">չ����ϸ</td>
				<td class="tableTitle dtd">չ����ϸ(������)</td>
			</tr>
			<%--��ͷ ���� --%>
			
			<% if(pageRecords == null || pageRecords.size() == 0 ){ %>
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
					<td class="dtd ar"><%=StringUtil.formatFour(666.55) %>&nbsp;</td>
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
					<td class="dtd ac">
						<span>
							<a onclick="changeDetail('detail_div<%=i %>', 'ico<%=i %>', this);" style="cursor:hand;" class="cBlue"><span>չ����ϸ</span>
								<img id="ico<%=i%>" src="images/xxico.gif" align="absmiddle" /></a>
						</span>
						
						<%--�����㿪ʼ --%>
						<div class="positionre">
	              			<div class="zwmxzy" id="detail_div<%=i %>">
								<table cellpadding="0" cellspacing="0" class="zhmxtable">
									<tr class="b bgaa">  
										<td>���мƻ����<br /><br /></td>
										<td>��ʼ��<br /><br /></td>
										<td>������<br /><br /></td>
										<td>�����֤��(Ԫ)<br /><br /></td></tr>
									<tr>  
										<td>ֵ1<br /><br /></td>
										<td>ֵ2<br /><br /></td>
										<td>ֵ3<br /><br /></td>
										<td>ֵ4<br /><br /></td></tr>
								</table>
							</div>
						</div><%--������ ���� --%>
					</td>
				</tr>
				
				<%--��ϸ --%>
				<tr id="detail<%=i %>" class="show_none">
					<td colspan="��ϸҪ�������">
						<%-- �˴����ñ�� --%>
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
	</div>
	<%--������ ���� --%>
</body>
</html>