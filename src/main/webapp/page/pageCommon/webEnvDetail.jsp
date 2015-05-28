<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>

<%
//�˴����java����
String path = request.getContextPath();

Properties pageRecords = System.getProperties();
Map<String, String> hm  = new HashMap<String, String>();
hm.put("java.version","Java ����ʱ�����汾");
hm.put("java.vendor","Java ����ʱ������Ӧ��");
hm.put("java.vendor.url","Java ��Ӧ�̵� URL");
hm.put("java.home","Java ��װĿ¼");
hm.put("java.vm.specification.version","Java ������淶�汾");
hm.put("java.vm.specification.vendor","Java ������淶��Ӧ��");
hm.put("java.vm.specification.name","Java ������淶����");
hm.put("java.vm.version","Java �����ʵ�ְ汾");
hm.put("java.vm.vendor","Java �����ʵ�ֹ�Ӧ��");
hm.put("java.vm.name","Java �����ʵ������");
hm.put("java.specification.version","Java ����ʱ�����淶�汾");
hm.put("java.specification.vendor","Java ����ʱ�����淶��Ӧ��");
hm.put("java.specification.name","Java ����ʱ�����淶����");
hm.put("java.class.version","Java ���ʽ�汾��");
hm.put("java.class.path","Java ��·��");
hm.put("java.library.path","���ؿ�ʱ������·���б�");
hm.put("java.io.tmpdir","Ĭ�ϵ���ʱ�ļ�·��");
hm.put("java.compiler","Ҫʹ�õ� JIT ������������");
hm.put("java.ext.dirs","һ��������չĿ¼��·��");
hm.put("os.name","����ϵͳ������");
hm.put("os.arch","����ϵͳ�ļܹ�");
hm.put("os.version","����ϵͳ�İ汾");
hm.put("file.separator","�ļ��ָ������� UNIX ϵͳ���ǡ�/����");
hm.put("path.separator","·���ָ������� UNIX ϵͳ���ǡ�:����");
hm.put("line.separator","�зָ������� UNIX ϵͳ���ǡ�/n����");
hm.put("user.name","�û����˻�����");
hm.put("user.home","�û�����Ŀ¼");
hm.put("user.dir","�û��ĵ�ǰ����Ŀ¼");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>���web��������ϵͳ����</title>

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
	
});
</script>
</head>
<body>
	<%--������ --%>
	<div id="r">
		<p>���web��������ϵͳ����</p>
		<%--һ����� --%>
		<table  id="mainTable" cellspacing="0" cellpadding="0">
			<%--��ͷ --%>
			<tr>
				<td class="tableTitle dtd">����key</td>
				<td class="tableTitle dtd">����ֵ</td>
				<td class="tableTitle dtd">����˵��</td>
			</tr>
			<%--��ͷ ���� --%>
			
			<% if(pageRecords.isEmpty()){ %>
				<tr><td colspan="2"><div class="red ac">û�в�ѯ��������������Ϣ!</div></td></tr>
			<%} else {
				Iterator<Object> keys =  pageRecords.keySet().iterator();
				for (int i = 0; keys.hasNext(); i++) {//for_1
					String key = (String)keys.next();
					
			%>
				<%--������ --%>
				<tr>
					<td class="dtd al"><%=key %>&nbsp;</td>
					<td class="dtd al"><%=pageRecords.get(key) %>&nbsp;</td>
					<td class="dtd al"><%=hm.get(key) %>&nbsp;</td>
					
				</tr>
				
				<%}%><%--for_1 ѭ������ --%>
			<%} %>
		</table>
		<%--һ����� ����--%>
	</div>
	<%--������ ���� --%>
</body>
</html>