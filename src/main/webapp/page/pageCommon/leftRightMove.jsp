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
<title>ʵ�������ƶ������</title>
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
 * ����ĳ�ʼ��
 */
$(document).ready(function(){
	//����������԰󶨴ӷ������ش����������ݵ���ѯ��
	
	//���������ע���¼�
	leftopttext();
	
	resetCheckedAll('leftRelAll','leftdiv');		//���ȫѡ��ťע�ᵥ���¼�
	resetCheckedAll('rightRelAll','rightdiv');		//�Ҳ�ȫѡ��ťע�ᵥ���¼� 
	resetChecked('leftdiv', 'leftRelAll');
	resetChecked('rightdiv', 'rightRelAll');
});

//Ϊ�����������ı������focusIn��focusOut�¼�
function leftopttext(){
	var str='��������'; 
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

//ȫѡ-ȡ��ȫѡ����
function resetCheckedAll(bindId, changeId){
	jQuery('#'+bindId).click(function(){
		var obj = jQuery('#'+changeId).find('input:checkbox'); //���п�ѡ�� ��'ȫѡ/ȫ��ѡ'ѡ��
		jQuery('#'+bindId).attr('checked')?obj.attr('checked',true):obj.attr('checked',false);	//ִ��'ȫѡ/ȫ��ѡ'����
		
		if (bindId == "leftRelAll") {
			if (this.checked) {
				jQuery('#'+changeId).find('input:text').show();
			} else {
				jQuery('#'+changeId).find('input:text').hide();
			}
		}
	});	
	
}

//��ѡ��ť�����¼�
function resetChecked(divId, checkboxId){
	//��ȡ���󶨣���Ϊ�ڶ�������ƶ�ʱ�����ж����ͬ�¼�����
	jQuery('#'+divId).find('input:checkbox').unbind("change");
	
	//��ѡ����Ŀ��Ϊȫѡʱ,ȫѡ״̬Ϊ false,����Ϊtrue
	jQuery('#'+divId).find('input:checkbox').change(function(){
			var obj = jQuery('#' + divId);
			if(obj.find('input:checkbox').length > obj.find('input:checked').length){//δȫ��ѡ��ʱ
				jQuery('#' + checkboxId).attr('checked',false);
			} else {
				jQuery('#' + checkboxId).attr('checked',true);
			}
	});
}

//�������
function getminus(a,b){
	var c =parseFloat(b)-parseFloat(a);
	return c.toFixed(4);
}

/**
 * addId:���Ӽ�¼��DIV
 * k:��¼������
 * values:���������
 * flag:1Ϊ���ƣ�2Ϊ����
 */
function getconent(addId, k, values, flag){
	values = parseFloat(values).toFixed(4);//��ʽ�����ֵ���ʾ��ʽ

	if(flag==1){//����
		
		jQuery('#'+addId).append("<div id='record_right" + k + "'>"
				+"<div ><div>"
				+"<input name='check" + k + "'" + " id='checkbox1_right'" + k + " type='checkbox'" + " value='" + jQuery("#checkbox1_left" + k).val() 
						+ "' cindex='" + jQuery("#checkbox1_left" + k).attr('cindex') + "'"+ "/>"
				+"</div><div>"
				+"<span id='item1_right" + k + "'>" + jQuery("#item1_left" + k).val() + "</span>"
				+"</div></div>"
				
				+"<div >"
				+"������<span class='item2_right' id='item2_right" + k + "'" + values + "</span>"
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
				+"������<span class='item2_left'  id='item2_left" + k + "'>" + values + "</span>"
				+"</div><div>"
				+"<input type='text' id='input1_left" + k + "'" + "value='��������'" + "/>"
				+"</div></div>"
				
				+"</div>"
				)
	}
}
<%--
ʵ�������ƶ���˼·��
���������ƶ���Ϊ���������
	A��ȫ���ƶ�
		A1���Ҳ����и���
		A2���Ҳ�û�и���
	B�������ƶ�
		B1���Ҳ����и���
		B2���Ҳ�û�и���
���������ƶ�ֻ��һ�������
A��ȫ���ƶ�
	A1�������
	A2�������
--%>
function moveOptions(btnId,delId,addId){
	
	jQuery('#'+btnId).bind('click',function(){
		
		var e = jQuery('#'+delId).find('input:checked');//�������ѡ�еĸ�ѡ��
		
		if(btnId=='rightRel'){//����
			var ccbox = jQuery(this);//��ǰ��ѡ���
			
			//Ϊ�˽����ҵļ�¼��Ӧ�ϣ�����ʹ���ҵ���Ŀ��������ͬ��
			var k = ccbox.attr("cindex");
	
			e.each(function(j){
				
				//�������Ƿ����е�ǰ����Ŀ
				var exsistFlag = jQuery("#checkbox1_left" + k).length == 0 ? false : true; 
				
				var sumr = parseFloat(jQuery("#item2_right" + k).text());//�Ҳ������
				
				if(exsistFlag==true){
					var sumz = parseFloat(jQuery("#item2_left" + k).text());//�������
					jQuery("#item2_left" + k).text((sumz + sumr).toFixed(4));
				} else{
					jQuery("#item2_left" + k).text(sumr.toFixed(4));
				}
				
				jQuery('#record_right'+k).remove();//�Ҳ��Ƴ�
			});	

		} else {//����
		
			e.each(function(){
				var ccbox = jQuery(this);//��ǰ��ѡ���
				
				//Ϊ�˽����ҵļ�¼��Ӧ�ϣ�����ʹ���ҵ���Ŀ��������ͬ��
				var k = ccbox.attr("cindex");
				
				
				var values = parseFloat($('#input1_left'+k).val());//������������
				alert("���������" + values);
				var sum = parseFloat($('#item2_left'+k).text());//����ܵ�����
				
				if (isNaN(values)) {//����������������������ȫ������
					values = sum;
				}
				
				if (values > sum) {//������Ų����
					values = sum
				}
				
				var result = getminus(values, sum);
				
				//����Ҳ��Ƿ����е�ǰ����Ŀ
				var exsistFlag = jQuery("#checkbox1_right" + k).length == 0 ? false : true; 
				
				if(parseFloat(result)>0) {//�����ƶ�

					$('#item2_left'+k).text(result);//�޸���౻�ƶ���Ŀ��"����"
					
					if(exsistFlag==true){//�Ҳ����и���
						var sumr = parseFloat(jQuery("#item2_right" + k).text());
					
						//�޸��Ҳ��Ӧ��Ŀ������
						$('#item2_right' + k).text((sumr + values).toFixed(4));
					
					} else {//�Ҳ���û�и���
						getconent(addId,k,values,1);
					}
					
					ccbox.attr("checked",false);//ȥ��ѡ��״̬
				} else {//ȫ���ƶ�
					if(exsistFlag==true){//�Ҳ���
						//�޸��Ҳ��Ӧ��Ŀ������
						$('#item2_right' + k).text((values).toFixed(4));
					}else{//�Ҳ���
						getconent(addId,k,b,1);
						
					}
					jQuery('#record_left'+k).remove();//����Ƴ�
				}
			});	
		
		}
		
		//�����Ų�������Լ���Ų������
		var els = $("#rightdiv .item2_left");
		var amountr = 0;
		if (els != null) {
			for (var i = 0; i < els.length; i++) {
				amountr += parseFloat($(els[i]).text());
			}
		}
		$("#syzl").text(amountr.toFixed(4));//ʣ������
		
		var amountl = 0;
		els = $("#leftdiv .item2_right");
		if (els != null) {
			for (var i = 0; i < els.length; i++) {
				amountl += parseFloat($(els[i]).text());
			}
		}
		$("#yczl").text(amountl.toFixed(4));//�Ƴ�����
		
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
ʵ�ֵ�˼�ǣ�
��Ϊ���ܹ��õ����ݼ������Ǳ����Ԫ�����һ���������ʽ����ֵ��������ÿ�����ݣ�class="record_left"
	��ȻҲ���Ը�ÿ�������е�������������ԣ��Ӷ��õ�������ļ���

--%>
	<div>
		<div>
			<div style="width:753px;">
<!-- ���� -->
				<div class="klnkstorbgl">

					<div id="leftdiv" class="klnkstorbglb">
				<%
					int i = 0;
					double amount = 0;
					for(HashMap<String, Object> hm : list) {
						amount += (Double)hm.get("doubleField");
				%>
				<%--�ı�����Ϊ��item{index1}_left{index2}
					��ѡ��ť��checkbox{index1}_left{index2}
					�����input{index1}_left{index2}
					ÿ����¼��record_left{index2}
				 --%>
						<div id="record_left<%=i %>">

							<div >
								<div>
									<%--ѡ��� --%>
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
									������<span id="item2_left<%=i %>" class="item2_left"><%=(Double)hm.get("doubleField") %></span>��
								</div>
								<div>
									<input name="32" type="text" value="��������" id="input1_left<%=i %>"/>
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
						<div>ȫѡ</div>
					</div>

				</div>
<!-- ������� -->

<!-- �м� -->
				<div class="klnkstorbgm">
					
					<input type="button" id="leftRel" value="����&gt;&gt;"/>
					<div ></div>
					<input type="button" id="rightRel"  value="&lt;&lt;����"/>
				</div>
<!-- �м� ���� -->

<!-- ���� -->
				<div class="klnkstorbgr">

					<div id="rightdiv" class="klnkstorbglb">
					<%--�ı�����Ϊ��item{index1}_right{index2}
						��ѡ��ť��checkbox{index1}_right{index2}
						�����input{index1}_right{index2}
						ÿ����¼��record_right{index2}
					 --%>
					</div>
					<div class="clear"></div>
					<div>
						<div>
							<input name="12" type="checkbox" value="121" id="rightRelAll" />
						</div>
						<div >ȫѡ</div>
					</div>

					<div></div>
				</div>
<!-- ������� -->

				<div>
					<div class="klhhstorbglc left klhhwc klhhwapadd82">
						<div>
							<span>ʣ��������</span> <span id="syzl"><%=amount %></span> ��
						</div>
					</div>
					<div class="klhhstorbglc left klhhwc">
						<div>
							<span>�Ƴ�����:</span><span id="yczl">0</span> ��
						</div>
					</div>
				</div>

			</div>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>