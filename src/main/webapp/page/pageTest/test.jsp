<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="common.pager.bean.Page" %>
<%@ page import="test.pagerTest.bean.FCTRADE_FEEbean" %>
<%@ include file="/page/pageCommon/path.jsp" %>
<%
//�˴����java����
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>��ҳ�棨ҳ���ܣ�</title>

<%-- <link href="<%=path%>/css/adminstyle.css" rel="stylesheet" type="text/css" />ȫ����ʽ

��������ؼ�
<link href="<%=path%>/css/InputDate.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=path%>/js/InputDate.js"></script>

����� ��ͨ����
<script language="javascript" src="<%=path%>/js/checkInput.js"></script>
<script language="javascript" src="<%=path%>/js/common.js"></script>
--%>
<script language="javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script> 

<%--ҳ���Զ�����ʽ --%>
<style type="text/css">

</style>

<%--ҳ��Ų� --%>
<script language="javascript">

/**
 * ����ĳ�ʼ��
 */
$(document).ready(function(){
	//���û�����ָ�Ұ�ʱ����ǰһ����δ���ʱ��ȡ����������
	var pflag = false;//true�������У�false:����
	
	//var moneyKeydownVal = $("#money").val();//keydown�¼�ʱ�����ֵ
	
	//����ؼ����¼�,�������0-9��keycode��48-57����С����0-9��keycode��96-105��
	//FireFox��event����ľ������ͨ����������������
	//IE:������Ϊ��������
 	$("#money").keydown(function (event) {
	 		if (pflag) {
	 			event.returnValue = false;
	 			return;
	 		} else {
	 			pflag = true;
	 			moneyKeydownVal = $("#money").val().replace(/,/g, "");//���ڻ���
				/* var v = String.fromCharCode(event.keyCode);
				if (event.keyCode == 49 || event.keyCode == 97) {
					//alert(this.value);
					event.returnValue = false;//ȡ���¼��������Ĭ����Ϊ���ı��򲻻ᱻ��ֵ
					return;
					
				} else {
					event.returnValue = true;
				} */
	 		}
		}
	);
	
	//����ؼ����¼�
 	 $("#money").keyup(function (event) {
 			
			//moneyKeydownVal = $("#money").val();
			//alert($("#money").val());
			//var v = String.fromCharCode(event.keyCode);
			//��^-$|^-?\d+[.]$|^-?\d+([.]\d+)?$
			//��^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$
			v = $("#money").val().replace(/,/g, "");
			if(v != "" && !v.match(/^-$|^-?\d+[.]$|^-?\d+([.]\d+)?$/)){
	        	$("#money").val(moneyKeydownVal);//����
	        }
	        
	        if(/\.\d\d\d+$/.test(v)){//��λ����������С��ʱ����
	        	$("#money").val(moneyKeydownVal);//����
	        }
	        pflag = false;
		}				
	);
	
 	//����ؼ����¼�
 	 $("#money").focusout(function (event) {
 		 	var oldval = $("#money").val().replace(/,/g, "");
			var newval = /* "��" +  */oldval.replace(
				/([\d]+?)(?=(?:\d{3}([.]\d+)?)+$)/g,
				
	            function($0,$1,$2,lastIndex,str){
	                if(str.substring(0,lastIndex+4).indexOf(".")!=-1)return $1;
	                return $1+",";
	            }
        	);
			
			$("#money").val(newval);
		}				
	);
 	
	//��������ı����id
/*     var obj=document.getElementById("money");
    var flg=false;
    
    obj.onfocus=function(){
    	flg=false;
    }; */
    /**
	��IE�����¼�event��������������ԣ�keyCode, altKey, ctrlKey, and shiftKey
	����keypress��keydown�¼��в����ֵ��������ǰ���µļ���keyup������ǰ����ֵ
	��http://msdn.microsoft.com/zh-cn/library/ff974342(v=VS.85).aspx
	��ִ��˳��keydown->keypress->keyup
	����Ӧ�ļ���ͬ
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
	<%--������ --%>
	<div id="r">
		<form name="form1" action="" method="post">
			��<input name="money" id="money" onkeydown="" />
		</form>
	</div>
	
	<div>
		<a href="javascript:void(0)" onclick="openUrl()">������</a>
	</div>
	
	<div>
	<form name="form2" action="<%=path%>/test?action=jdil" method="post">
		�ӿڱ�ţ�<input name="interfaceNo" id="interfaceNo" type="text" value="3FC"/>
		<input type="submit" value="�ύ"/>
	</form>
	
	
	</div>
	<%--������ ���� --%>
</body>
</html>