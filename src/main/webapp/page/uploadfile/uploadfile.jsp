<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ include file="/page/pageCommon/path.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<script type="text/javascript" src="<%=path %>/js/jquery-1.4.4.min.js" ></script>
<title>文件上传！！！</title>
<script type="text/javascript">
	function uploadFile() {
		jQuery("#form3").submit();
	}
</script>
</head>
<body>
<p>简单上传（客户端--&gt;web服务器）</p>
<form method="post" name="form3" id="form3" action="<%=path %>/test?action=uploadFile">
	<!-- <input type="file" name="imagefile" class="button_file" size="50" /><br/>
	<input type="file" name="imagefile2" class="button_file" size="50" /><br/> -->
	<input type="text" name="username" size="20"/><br/>
	<input type="button" onclick="uploadFile()" value="上传"/>
		<iframe name="upload_frame" id="uploadFrame"
		style="display: none"></iframe>
													
</form>
<br/>
<p>简单上传（客户端--&gt;web服务器--&gt;图片服务器）</p>
</body>
</html>