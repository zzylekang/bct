<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="" %>
<%
	//此处添加java代码
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>主页面（页面框架）</title>
<link href="<%=path%>/css/adminstyle.css" rel="stylesheet" type="text/css" /><%--全局样式 --%>
</head>

<body scroll="no" style="width: 100%;height:100%">
<table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%" scroll="no">
		<!--top显示区-->
		<tr>
			<td colspan="3">
				<div id="headtip">
					<table id="head" cellspacing="0" cellpadding="0">
						<tr>
							<td class="w220"><img src="<%=path%>/images/fts_logo.gif" /></td>
							<td>
								<table class="showrole" cellpadding="0" cellspacing="0">
									<tr>
										<td class="h30">您好! <a
											href="<%=path%>/admin/staffinfo/changePassword.jsp" title="点击修改密码"
											target="frmRight">XXX</a>
											系统管理员2</td>
										<td class="h30 ar"><a href="http://scf.315.com.cn"
											title="进入在线融资系统" target="_blank">在线融资系统</a> <span
											class="hline">|</span> <a href="#" title="系统帮助">帮助</a> <span
											class="hline">|</span> <a href="sltEXadmin?action=logout"
											title="安全退出系统">安全退出</a>&nbsp;&nbsp;</td>
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
		<!--top显示区结束-->

		<tr>
			<!--菜单栏显示区-->
			<td align="left" valign="top" nowrap id="frmmenu"
				style="height:518px; width: 127px;"
				name="frmmenu">
				<%--对应left.jsp，其中的<a>元素通过指定target属性使得目标页在指定的位置显示，
						形如：<a href="estoreservlet?action=checkFee" target="frmRight"> --%>
				<iframe id="boardtitle" name="boardtitle"
					style="height:100%; width: 100%;" align="top"
					marginwidth=0 marginheight=0 frameborder="0" scrolling="auto"
					src="<%=path%>/staffinfoservlet?action=functionTree"> </iframe></td>
			<!--菜单栏显示区结束-->
			
			<td style="background-color: #f4f4f4; width: 5px">&nbsp;</td>
			
			<!--内容显示区-->
			<td height="518px" valign="top" align="left">
			<%--对应indexBank.jsp --%>
			<iframe	id="frmRight" name="frmRight"
					style="height: 100%; width: 100%;"
					frameborder=0 src="<%=path%>/sltEXadmin?action=goodst&act=indexbank"></iframe>
			</td>
			<!--内容显示区结束-->
		</tr>
		<tr>
			<td colspan="3"><div id="copyright">
					金银岛(北京)网络科技股份有限公司 版权所有 2005-2008 京ICP许可证040465号<br />
				</div></td>
		</tr>
	</table>
</body>
</html>