<?xml version="1.0" encoding="GB18030" ?>
<%@page import="com.zzy.tools.dataGenerator.DataGenerator"%>
<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.*"%>
<%@ include file="/page/pageCommon/path.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>实现左右移动的组件</title>
<script language="Javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script>
<style type="text/css">
	.klnkstorbgl{float:left;width:312px;border:1px solid #99BBE8;}
	.clear{clear:both}
	.klnkstorbglc{height:30px;padding:0px 10px 0px 10px;line-height:30px;border-top:1px solid #ECECEC; background:#F8F8F8}  
	.klnkstorbgm{float:left;width:78px;height:180px;padding:135px 0 0 9px;_padding:155px 0 0 9px;background:url(../../images/kljta.gif) no-repeat 6px 55px;_background:url(../../images/kljta.gif) no-repeat 6px 75px}
	.klnkstorbgr{float:left;width:320px;border:1px solid #99BBE8;}    
	.klnkstorbglb{height:242px;_height:283px;padding:0 10px 0px 10px;line-height:22px; background:#fff;overflow:auto}  
	.left{ float: left;}  
	.klhhwc{width:320px;}  
	.klhhwapadd82{padding-right:82px;}
</style>

<script type="text/javascript">
/**
 * 画面的初始化
 */
$(document).ready(function(){
	//在这里你可以绑定从服务器回传回来的数据到查询表单
	
	//数量输入框注册事件
	leftopttext();
	
	resetCheckedAll('leftRelAll','leftdiv');		//左侧全选按钮注册单击事件
	resetCheckedAll('rightRelAll','rightdiv');		//右侧全选按钮注册单击事件 
	resetChecked('leftdiv', 'leftRelAll');
	resetChecked('rightdiv', 'rightRelAll');
});

//为输入数量的文本框添加focusIn、focusOut事件
function leftopttext(){
	var str='输入数量'; 
	jQuery('#leftdiv').find('input:text').each(function(i){
		jQuery(this).focus(function(){
			if (jQuery.trim(jQuery(this).val()) == str) {
				jQuery(this).val('');
			}
		});
		
		jQuery(this).focusout(function(){
			if(jQuery.trim(jQuery(this).val()).length==0){
				jQuery(this).val(str);;
			}
		});
	
	});
}

//全选-取消全选操作
function resetCheckedAll(bindId, changeId){
	jQuery('#'+bindId).click(function(){
		var obj = jQuery('#'+changeId).find('input:checkbox'); //所有可选项 除'全选/全不选'选项
		jQuery('#'+bindId).attr('checked')?obj.attr('checked',true):obj.attr('checked',false);	//执行'全选/全不选'操作
		
		if (bindId == "leftRelAll") {
			if (this.checked) {
				jQuery('#'+changeId).find('input:text').show();
			} else {
				jQuery('#'+changeId).find('input:text').hide();
			}
		}
	});	
	
}

//单选按钮单击事件
function resetChecked(divId, checkboxId){
	//先取消绑定，因为在多次左右移动时，会有多个相同事件被绑定
	jQuery('#'+divId).find('input:checkbox').unbind("change");
	
	//当选定数目不为全选时,全选状态为 false,否则为true
	jQuery('#'+divId).find('input:checkbox').change(function(){
			var obj = jQuery('#' + divId);
			if(obj.find('input:checkbox').length > obj.find('input:checked').length){//未全部选择时
				jQuery('#' + checkboxId).attr('checked',false);
			} else {
				jQuery('#' + checkboxId).attr('checked',true);
			}
	});
}

//两数相差
function getminus(a,b){
	var c =parseFloat(b)-parseFloat(a);
	return c.toFixed(4);
}

/**
 * addId:增加记录的DIV
 * k:记录的索引
 * values:输入的数量
 * flag:1为右移，2为左移
 */
function getconent(addId, k, values, flag){
	values = parseFloat(values).toFixed(4);//格式化数字的显示格式

	if(flag==1){//右移
		
		jQuery('#'+addId).append("<div id='record_right" + k + "'>"
				+"<div ><div>"
				+"<input name='check" + k + "'" + " id='checkbox1_right'" + k + " type='checkbox'" + " value='" + jQuery("#checkbox1_left" + k).val() 
						+ "' cindex='" + jQuery("#checkbox1_left" + k).attr('cindex') + "'"+ "/>"
				+"</div><div>"
				+"<span id='item1_right" + k + "'>" + jQuery("#item1_left" + k).val() + "</span>"
				+"</div></div>"
				
				+"<div >"
				+"数量：<span class='item2_right' id='item2_right" + k + "'" + values + "</span>"
				+"</div>"
				
				+"</div>"
				)
	}else{
		jQuery('#'+addId).append("<div id='record_left" + k + "'>"
				+"<div ><div>"
				+"<input name='check" + k + "'" + " id='checkbox1_left'" + k + " type='checkbox'" + " value='" + jQuery("#checkbox1_right" + k).val() 
						+ "' cindex='" + jQuery("#checkbox1_right" + k).attr('cindex') + "'"+ "/>"
				+"</div><div>"
				+"<span id='item1_left" + k + "'" + jQuery("#item1_right" + k).val() + "</span>"
				+"</div></div>"
				
				+"<div ><div>"
				+"数量：<span class='item2_left'  id='item2_left" + k + "'>" + values + "</span>"
				+"</div><div>"
				+"<input type='text' id='input1_left" + k + "'" + "value='输入数量'" + "/>"
				+"</div></div>"
				
				+"</div>"
				)
	}
}
<%--
实现左右移动的思路：
由左向右移动分为两种情况：
	A：全部移动
		A1：右侧已有该项
		A2：右侧没有该项
	B：部分移动
		B1：右侧已有该项
		B2：右侧没有该项
由右向左移动只有一种情况：
A：全部移动
	A1：左侧有
	A2：左侧无
--%>
function moveOptions(btnId,delId,addId){
	
	jQuery('#'+btnId).bind('click',function(){
		
		var e = jQuery('#'+delId).find('input:checked');//获得所有选中的复选框
		
		if(btnId=='rightRel'){//左移
			var ccbox = jQuery(this);//当前的选择框
			
			//为了将左右的记录对应上，必须使左右的项目的索引相同。
			var k = ccbox.attr("cindex");
	
			e.each(function(j){
				
				//检查左侧是否已有当前的项目
				var exsistFlag = jQuery("#checkbox1_left" + k).length == 0 ? false : true; 
				
				var sumr = parseFloat(jQuery("#item2_right" + k).text());//右侧的数量
				
				if(exsistFlag==true){
					var sumz = parseFloat(jQuery("#item2_left" + k).text());//左侧数量
					jQuery("#item2_left" + k).text((sumz + sumr).toFixed(4));
				} else{
					jQuery("#item2_left" + k).text(sumr.toFixed(4));
				}
				
				jQuery('#record_right'+k).remove();//右侧移除
			});	

		} else {//右移
		
			e.each(function(){
				var ccbox = jQuery(this);//当前的选择框
				
				//为了将左右的记录对应上，必须使左右的项目的索引相同。
				var k = ccbox.attr("cindex");
				
				
				var values = parseFloat($('#input1_left'+k).val());//获得输入的数量
				alert("输入的数量" + values);
				var sum = parseFloat($('#item2_left'+k).text());//获得总的数量
				
				if (isNaN(values)) {//如果不输入数量的情况，就全部右移
					values = sum;
				}
				
				if (values > sum) {//超过可挪数量
					values = sum
				}
				
				var result = getminus(values, sum);
				
				//检查右侧是否已有当前的项目
				var exsistFlag = jQuery("#checkbox1_right" + k).length == 0 ? false : true; 
				
				if(parseFloat(result)>0) {//部分移动

					$('#item2_left'+k).text(result);//修改左侧被移动项目的"数量"
					
					if(exsistFlag==true){//右侧已有该项
						var sumr = parseFloat(jQuery("#item2_right" + k).text());
					
						//修改右侧对应项目的数量
						$('#item2_right' + k).text((sumr + values).toFixed(4));
					
					} else {//右侧已没有该项
						getconent(addId,k,values,1);
					}
					
					ccbox.attr("checked",false);//去掉选择状态
				} else {//全部移动
					if(exsistFlag==true){//右侧有
						//修改右侧对应项目的数量
						$('#item2_right' + k).text((values).toFixed(4));
					}else{//右侧无
						getconent(addId,k,b,1);
						
					}
					jQuery('#record_left'+k).remove();//左侧移除
				}
			});	
		
		}
		
		//计算可挪库总量以及已挪库总量
		var els = $("#rightdiv .item2_left");
		var amountr = 0;
		if (els != null) {
			for (var i = 0; i < els.length; i++) {
				amountr += parseFloat($(els[i]).text());
			}
		}
		$("#syzl").text(amountr.toFixed(4));//剩余总量
		
		var amountl = 0;
		els = $("#leftdiv .item2_right");
		if (els != null) {
			for (var i = 0; i < els.length; i++) {
				amountl += parseFloat($(els[i]).text());
			}
		}
		$("#yczl").text(amountl.toFixed(4));//移出总量
		
	});	
	
	
}
</script>

<%
DataGenerator dg = new DataGenerator();
List<HashMap<String, Object>> list = dg.getListMapData();
System.out.println(list);
%>
</head>
<body>
<%--
实现的思虑：
①为了能够拿到数据集，我们必须给元素添加一个虚拟的样式属性值，如左侧的每条数据：class="record_left"
	当然也可以给每条数据中的数据项添加属性，从而拿到数据项的集合

--%>
	<div>
		<div>
			<div style="width:753px;">
<!-- 左面 -->
				<div class="klnkstorbgl">

					<div id="leftdiv" class="klnkstorbglb">
				<%
					int i = 0;
					double amount = 0;
					for(HashMap<String, Object> hm : list) {
						amount += (Double)hm.get("doubleField");
				%>
				<%--文本命名为：item{index1}_left{index2}
					单选按钮：checkbox{index1}_left{index2}
					输入框：input{index1}_left{index2}
					每条记录：record_left{index2}
				 --%>
						<div id="record_left<%=i %>">

							<div >
								<div>
									<%--选择框 --%>
									<input name="check<%=i %>" id="checkbox1_left<%=i %>" type="checkbox"
										value="<%=(String)hm.get("StringField") %>" cindex="<%=i %>"
										 />
								</div>
								<div>
									<span id="item1_left<%=i %>"><%=(String)hm.get("StringField") %></span>
								</div>
							</div>

							<div>
								<div>
									数量：<span id="item2_left<%=i %>" class="item2_left"><%=(Double)hm.get("doubleField") %></span>吨
								</div>
								<div>
									<input name="32" type="text" value="输入数量" id="input1_left<%=i %>"/>
								</div>
							</div>
						</div>
						<%i++;} %>

					</div>

					<div class="clear"></div>

					<div >
						<div>
							<input name="12" type="checkbox" value="121" id="leftRelAll"/>
						</div>
						<div>全选</div>
					</div>

				</div>
<!-- 左面结束 -->

<!-- 中间 -->
				<div class="klnkstorbgm">
					
					<input type="button" id="leftRel" value="左移&gt;&gt;"/>
					<div ></div>
					<input type="button" id="rightRel"  value="&lt;&lt;右移"/>
				</div>
<!-- 中间 结束 -->

<!-- 右面 -->
				<div class="klnkstorbgr">

					<div id="rightdiv" class="klnkstorbglb">
					<%--文本命名为：item{index1}_right{index2}
						单选按钮：checkbox{index1}_right{index2}
						输入框：input{index1}_right{index2}
						每条记录：record_right{index2}
					 --%>
					</div>
					<div class="clear"></div>
					<div>
						<div>
							<input name="12" type="checkbox" value="121" id="rightRelAll" />
						</div>
						<div >全选</div>
					</div>

					<div></div>
				</div>
<!-- 右面结束 -->

				<div>
					<div class="klhhstorbglc left klhhwc klhhwapadd82">
						<div>
							<span>剩余总量：</span> <span id="syzl"><%=amount %></span> 吨
						</div>
					</div>
					<div class="klhhstorbglc left klhhwc">
						<div>
							<span>移出总量:</span><span id="yczl">0</span> 吨
						</div>
					</div>
				</div>

			</div>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>