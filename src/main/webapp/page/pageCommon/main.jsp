<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
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
</head>

<body scroll="no" style="width: 100%;height:100%">
<table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%" scroll="no">
		<!--top��ʾ��-->
		<tr>
			<td colspan="3">
				<div id="headtip">
					<table id="head" cellspacing="0" cellpadding="0">
						<tr>
							<td class="w220"><img src="<%=path%>/images/fts_logo.gif" /></td>
							<td>
								<table class="showrole" cellpadding="0" cellspacing="0">
									<tr>
										<td class="h30">����! <a
											href="<%=path%>/admin/staffinfo/changePassword.jsp" title="����޸�����"
											target="frmRight">XXX</a>
											ϵͳ����Ա2</td>
										<td class="h30 ar"><a href="http://scf.315.com.cn"
											title="������������ϵͳ" target="_blank">��������ϵͳ</a> <span
											class="hline">|</span> <a href="#" title="ϵͳ����">����</a> <span
											class="hline">|</span> <a href="sltEXadmin?action=logout"
											title="��ȫ�˳�ϵͳ">��ȫ�˳�</a>&nbsp;&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" valign="bottom">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<!--top��ʾ������-->

		<tr>
			<!--�˵�����ʾ��-->
			<td align="left" valign="top" nowrap id="frmmenu"
				style="height:518px; width: 127px;"
				name="frmmenu">
				<%--��Ӧleft.jsp�����е�<a>Ԫ��ͨ��ָ��target����ʹ��Ŀ��ҳ��ָ����λ����ʾ��
						���磺<a href="estoreservlet?action=checkFee" target="frmRight"> --%>
				<iframe id="boardtitle" name="boardtitle"
					style="height:100%; width: 100%;" align="top"
					marginwidth=0 marginheight=0 frameborder="0" scrolling="auto"
					src="<%=path%>/staffinfoservlet?action=functionTree"> </iframe></td>
			<!--�˵�����ʾ������-->
			
			<td style="background-color: #f4f4f4; width: 5px">&nbsp;</td>
			
			<!--������ʾ��-->
			<td height="518px" valign="top" align="left">
			<%--��ӦindexBank.jsp --%>
			<iframe	id="frmRight" name="frmRight"
					style="height: 100%; width: 100%;"
					frameborder=0 src="<%=path%>/sltEXadmin?action=goodst&act=indexbank"></iframe>
			</td>
			<!--������ʾ������-->
		</tr>
		<tr>
			<td colspan="3"><div id="copyright">
					������(����)����Ƽ��ɷ����޹�˾ ��Ȩ���� 2005-2008 ��ICP���֤040465��<br />
				</div></td>
		</tr>
	</table>
</body>
</html>