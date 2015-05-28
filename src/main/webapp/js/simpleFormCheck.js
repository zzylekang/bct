/**
	һ���򵥵Ŀͻ�����֤�ű�
*/

/********************������*************************************/
var single_quotes ="\'";
var double_quotes ="\"";
var line_break ="\n";
var enter_mark = "\r";


/********************������ ����*************************************/

/*
�ַ�������ĳ��÷������ԣ�
1��length  �����ַ����ĳ��ȣ��ַ��ĸ������������ֽڳ��ȣ�������һ�����ֵĳ���Ϊ1��һ��Ӣ����ĸ�����ֵĳ���Ҳ��1
2��indexOf("xx") ��λ�ַ�����ĳһ��ָ�����ַ��״γ��ֵ�λ��
3��match("xx") �������ַ������ض����ַ�����������ҵ��Ļ����򷵻�����ַ�
4��replace() �������ַ�������ĳЩ�ַ��滻��һЩ�ַ���
*/

/*
javascriptȫ�ֺ�����
decodeURI() ����ĳ������� URI��
decodeURIComponent() ����һ������� URI �����
encodeURI() ���ַ�������Ϊ URI��
encodeURIComponent() ���ַ�������Ϊ URI �����
escape() ���ַ������б��롣
eval() ���� JavaScript �ַ�������������Ϊ�ű�������ִ�С�
getClass() ����һ�� JavaObject �� JavaClass��     
isFinite() ���ĳ��ֵ�Ƿ�Ϊ����������
isNaN() ���ĳ��ֵ�Ƿ������֡�
Number() �Ѷ����ֵת��Ϊ���֡�
parseFloat() ����һ���ַ���������һ����������
parseInt() ����һ���ַ���������һ��������
String() �Ѷ����ֵת��Ϊ�ַ����� 
unescape() ���� escape() ������ַ������н��롣
*/
/*
javascript�У�String ��������ͨ���ַ����ǲ�ͬ�ġ�
String�����������new String(s); ��String(s);
��ͨ���ַ�����var txt="xxx";

*/
/*
ȫ�ֶ��� �� this
*/
/*
* javascript�д���������java����ͬ��Ҳ���Խ���ǿת��������Boolean("null")
* javascript�г����Ķ����У�Ҳ��Ԥ�ö���  
* javascript�е���ν����ʵ���൱��java�е���
* Boolean ����߼������޳�ʼֵ������ֵΪ 0��-0��null��""��false��undefined ���� NaN����ô�����ֵΪ false��������ֵΪ true����ʹ���Ա���Ϊ�ַ��� "false" ʱ����
* Array
* Date
* Math
* Number
* String
* RegExp
* this   ȫ�ֶ���
* 
* 
*/
/*
* javascript���Բ�������������html Dom����
* ����������������£�
* Window ��ʾһ����������ڻ�һ����ܡ� ��������Ķ������Ҳ��ȫ�ֶ��󣬶������Ժͷ��������ÿ���ʡ��windowǰ׺�� ����Window�����һ��ʵ����Ҫ��window������
* Navigator �����ͻ������������Ϣ��
* Screen �����ͻ�����ʾ������Ϣ��
* History ��������������ڷ��ʹ��� URL��
* Location �����˵�ǰURL����Ϣ��
*/

/*
* һ��ѧϰjavascript��dom��õķ�����������ie��debug
*/
/*
 �������ƣ�trim
 ��������: ȥ���ַ���ͷ����β���Ŀո�
 ����������ַ�������
 ����������������Ӵ�
*/
function trim(str){
  return str.replace(/(^\s*)|(\s*$)/g, "");
}
/*
��str��src�滻Ϊto, Ĭ��src=,   to=""
���룺332,22.00
�����33222.00
*/
function repalce(str, src, to){
	if (src === "") {src = ",";}
	return str.replace(/\ + src + /g, to);
}

/*
	����javascript�Դ�����ʾ��
	
*/
function confirmx(str) {
	return confirm(str);
}
/*
������������ת��Ϊ���֣������������ָ����Ԫ����
v��Ҫת��������
t:����ת�������Ԫ��
*/
function chinesemoney(t,v){
	var tip = document.getElementById(t);
	var currencyDigits = v; 
	var maximum_number = 999999999999.99;
	var cn_zero = "��";
	var cn_one = "Ҽ";
	var cn_two = "��";
	var cn_three = "��";
	var cn_four = "��";
	var cn_five = "��";
	var cn_six = "½";
	var cn_seven = "��";
	var cn_eight = "��";
	var cn_nine = "��";
	var cn_ten = "ʰ";
	var cn_hundred = "��";
	var cn_thousand = "Ǫ";
	var cn_ten_thousand = "��";
	var cn_hundred_million = "��";
	var cn_symbol = "�����";
	var cn_dollar = "Բ";
	var cn_ten_cent = "��";
	var cn_cent = "��";
	var cn_integer = "��";

	var integral;
	var decimal; 
	var outputCharacters; 
	var parts;
	var digits, radices, bigRadices, decimals;
	var zeroCount;
	var i, p, d;
	var quotient, modulus;
	currencyDigits = currencyDigits.replace(/,/g, ""); 
	currencyDigits = currencyDigits.replace(/^0+/, ""); 
	
	if (Number(currencyDigits) > maximum_number) {
		tip.innerHTML="";
		return "";
	}
	parts = currencyDigits.split(".");
	if (parts.length > 1) {
		integral = parts[0];
		decimal = parts[1];
		decimal = decimal.substr(0, 2);
	} else {
		integral = parts[0];
		decimal = "";
	}
	
	digits = new Array(cn_zero, cn_one, cn_two, cn_three, cn_four, cn_five, cn_six, cn_seven, cn_eight,cn_nine);
	radices = new Array("", cn_ten, cn_hundred, cn_thousand);
	bigRadices = new Array("", cn_ten_thousand, cn_hundred_million);
	decimals = new Array(cn_ten_cent, cn_cent);
	outputCharacters = "";
	
	if (Number(integral) > 0) {
		zeroCount = 0;
		for (i = 0; i < integral.length; i++) {
			p = integral.length - i - 1;
			d = integral.substr(i, 1);
			quotient = p / 4;
			modulus = p % 4;
			if (d == "0") {
				zeroCount++;
			}else {
				if (zeroCount > 0){
					outputCharacters += digits[0];
				}
				zeroCount = 0;
				outputCharacters += digits[Number(d)] + radices[modulus];
			}
			if (modulus == 0 && zeroCount < 4) {
				outputCharacters += bigRadices[quotient];
			}
		}
		outputCharacters += cn_dollar;
	}
	if (decimal != "") {
		for (i = 0; i < decimal.length; i++) {
			d = decimal.substr(i, 1);
			if (d != "0") {
				outputCharacters += digits[Number(d)] + decimals[i];
			}
		}
	}
	if (outputCharacters == "") {
		outputCharacters = cn_zero + cn_dollar;
	}
	if (decimal == "") {
		outputCharacters += cn_integer;
	}
	tip.innerHTML=outputCharacters;
}