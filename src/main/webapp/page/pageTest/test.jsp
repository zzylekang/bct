<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="common.pager.bean.Page" %>
<%@ page import="test.pagerTest.bean.FCTRADE_FEEbean" %>
<%@ include file="/page/pageCommon/path.jsp" %>
<%
//此处添加java代码
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>主页面（页面框架）</title>

<%-- <link href="<%=path%>/css/adminstyle.css" rel="stylesheet" type="text/css" />全局样式

日期输入控件
<link href="<%=path%>/css/InputDate.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=path%>/js/InputDate.js"></script>

表单检查 共通函数
<script language="javascript" src="<%=path%>/js/checkInput.js"></script>
<script language="javascript" src="<%=path%>/js/common.js"></script>
--%>
<script language="javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script> 

<%--页面自定义样式 --%>
<style type="text/css">

</style>

<%--页面脚步 --%>
<script language="javascript">

/**
 * 画面的初始化
 */
$(document).ready(function(){
	//当用户多手指乱按时，在前一处理未完成时，取消后续操作
	var pflag = false;//true：处理中，false:闲置
	
	//var moneyKeydownVal = $("#money").val();//keydown事件时捕获的值
	
	//向金额控件绑定事件,（大键盘0-9的keycode：48-57）（小键盘0-9的keycode：96-105）
	//FireFox：event对象的句柄必须通过参数传到方法中
	//IE:不必作为参数传递
 	$("#money").keydown(function (event) {
	 		if (pflag) {
	 			event.returnValue = false;
	 			return;
	 		} else {
	 			pflag = true;
	 			moneyKeydownVal = $("#money").val().replace(/,/g, "");//用于回退
				/* var v = String.fromCharCode(event.keyCode);
				if (event.keyCode == 49 || event.keyCode == 97) {
					//alert(this.value);
					event.returnValue = false;//取消事件的浏览器默认行为，文本框不会被赋值
					return;
					
				} else {
					event.returnValue = true;
				} */
	 		}
		}
	);
	
	//向金额控件绑定事件
 	 $("#money").keyup(function (event) {
 			
			//moneyKeydownVal = $("#money").val();
			//alert($("#money").val());
			//var v = String.fromCharCode(event.keyCode);
			//①^-$|^-?\d+[.]$|^-?\d+([.]\d+)?$
			//②^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$
			v = $("#money").val().replace(/,/g, "");
			if(v != "" && !v.match(/^-$|^-?\d+[.]$|^-?\d+([.]\d+)?$/)){
	        	$("#money").val(moneyKeydownVal);//回退
	        }
	        
	        if(/\.\d\d\d+$/.test(v)){//三位（含）以上小数时回退
	        	$("#money").val(moneyKeydownVal);//回退
	        }
	        pflag = false;
		}				
	);
	
 	//向金额控件绑定事件
 	 $("#money").focusout(function (event) {
 		 	var oldval = $("#money").val().replace(/,/g, "");
			var newval = /* "￥" +  */oldval.replace(
				/([\d]+?)(?=(?:\d{3}([.]\d+)?)+$)/g,
				
	            function($0,$1,$2,lastIndex,str){
	                if(str.substring(0,lastIndex+4).indexOf(".")!=-1)return $1;
	                return $1+",";
	            }
        	);
			
			$("#money").val(newval);
		}				
	);
 	
	//金额输入文本框的id
/*     var obj=document.getElementById("money");
    var flg=false;
    
    obj.onfocus=function(){
    	flg=false;
    }; */
    /**
	①IE键盘事件event对象包含如下属性：keyCode, altKey, ctrlKey, and shiftKey
	②在keypress、keydown事件中捕获的值不包括当前按下的键，keyup包括当前键的值
	③http://msdn.microsoft.com/zh-cn/library/ff974342(v=VS.85).aspx
	④执行顺：keydown->keypress->keyup
	⑤响应的键不同
    */
/*     obj.onkeyup=function(){
        var v=String.fromCharCode(event.keyCode);
        v=this.value+v;
        flg=false;
        if(!v.match(/^-$|^-?\d+[.]$|^-?\d+([.]\d+)?$/)){
        	return false;
        }
        
        if(/\.\d\d$/.test(obj.value)){
        	event.returnValue=false;
        }
        flg=true;
    }
    
    obj.onblur=function(){
        if(!flg){
        	return;
        }
        this.value="$"+this.value.replace(/([\d]+?)(?=(?:\d{3}([.]\d+)?)+$)/g,
            function($0,$1,$2,lastIndex,str){
                if(str.substring(0,lastIndex+4).indexOf(".")!=-1)return $1;
                return $1+",";
            }
        );
    } */
    
    
});

function openUrl(){
	window.location.href="<%=path%>/page/uploadfile/uploadfile.jsp";
}

</script>
</head>
<body>
	<%--内容区 --%>
	<div id="r">
		<form name="form1" action="" method="post">
			金额：<input name="money" id="money" onkeydown="" />
		</form>
	</div>
	
	<div>
		<a href="javascript:void(0)" onclick="openUrl()">打开链接</a>
	</div>
	
	<div>
	<form name="form2" action="<%=path%>/test?action=jdil" method="post">
		接口编号：<input name="interfaceNo" id="interfaceNo" type="text" value="3FC"/>
		<input type="submit" value="提交"/>
	</form>
	
	
	</div>
	<%--内容区 结束 --%>
</body>
</html>