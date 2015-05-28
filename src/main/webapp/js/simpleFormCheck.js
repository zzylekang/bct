/**
	一个简单的客户端验证脚本
*/

/********************常量区*************************************/
var single_quotes ="\'";
var double_quotes ="\"";
var line_break ="\n";
var enter_mark = "\r";


/********************常量区 结束*************************************/

/*
字符串对象的常用方法属性：
1：length  计算字符串的长度（字符的个数，而不是字节长度），中文一个汉字的长度为1，一个英文字母、数字的长度也是1
2：indexOf("xx") 定位字符串中某一个指定的字符首次出现的位置
3：match("xx") 来查找字符串中特定的字符，并且如果找到的话，则返回这个字符
4：replace() 方法在字符串中用某些字符替换另一些字符。
*/

/*
javascript全局函数：
decodeURI() 解码某个编码的 URI。
decodeURIComponent() 解码一个编码的 URI 组件。
encodeURI() 把字符串编码为 URI。
encodeURIComponent() 把字符串编码为 URI 组件。
escape() 对字符串进行编码。
eval() 计算 JavaScript 字符串，并把它作为脚本代码来执行。
getClass() 返回一个 JavaObject 的 JavaClass。     
isFinite() 检查某个值是否为有穷大的数。
isNaN() 检查某个值是否是数字。
Number() 把对象的值转换为数字。
parseFloat() 解析一个字符串并返回一个浮点数。
parseInt() 解析一个字符串并返回一个整数。
String() 把对象的值转换为字符串。 
unescape() 对由 escape() 编码的字符串进行解码。
*/
/*
javascript中：String 对象与普通的字符串是不同的。
String对象的声明：new String(s); 或String(s);
普通的字符串：var txt="xxx";

*/
/*
全局对象 ： this
*/
/*
* javascript中创建对象与java中相同，也可以进行强转操作，如Boolean("null")
* javascript中常见的对象有：也叫预置对象  
* javascript中的所谓对象，实际相当于java中的类
* Boolean 如果逻辑对象无初始值或者其值为 0、-0、null、""、false、undefined 或者 NaN，那么对象的值为 false。否则，其值为 true（即使当自变量为字符串 "false" 时）！
* Array
* Date
* Math
* Number
* String
* RegExp
* this   全局对象
* 
* 
*/
/*
* javascript可以操作浏览器对象和html Dom对象
* 其中浏览器对象如下：
* Window 表示一个浏览器窗口或一个框架。 是浏览器的顶层对象，也是全局对象，对其属性和方法的引用可以省略window前缀。 引用Window对象的一个实例，要用window来引用
* Navigator 包含客户端浏览器的信息。
* Screen 包含客户端显示屏的信息。
* History 包含了浏览器窗口访问过的 URL。
* Location 包含了当前URL的信息。
*/

/*
* 一个学习javascript或dom最好的方法，就是在ie下debug
*/
/*
 函数名称：trim
 函数功能: 去除字符串头部和尾部的空格
 传入参数：字符串变量
 传出结果：处理后的子串
*/
function trim(str){
  return str.replace(/(^\s*)|(\s*$)/g, "");
}
/*
将str中src替换为to, 默认src=,   to=""
输入：332,22.00
输出：33222.00
*/
function repalce(str, src, to){
	if (src === "") {src = ",";}
	return str.replace(/\ + src + /g, to);
}

/*
	调用javascript自带的提示框
	
*/
function confirmx(str) {
	return confirm(str);
}
/*
将阿拉伯数字转换为汉字，并将结果放在指定的元素上
v：要转换的数字
t:接收转换结果的元素
*/
function chinesemoney(t,v){
	var tip = document.getElementById(t);
	var currencyDigits = v; 
	var maximum_number = 999999999999.99;
	var cn_zero = "零";
	var cn_one = "壹";
	var cn_two = "贰";
	var cn_three = "叁";
	var cn_four = "肆";
	var cn_five = "伍";
	var cn_six = "陆";
	var cn_seven = "柒";
	var cn_eight = "捌";
	var cn_nine = "玖";
	var cn_ten = "拾";
	var cn_hundred = "佰";
	var cn_thousand = "仟";
	var cn_ten_thousand = "万";
	var cn_hundred_million = "亿";
	var cn_symbol = "人民币";
	var cn_dollar = "圆";
	var cn_ten_cent = "角";
	var cn_cent = "分";
	var cn_integer = "整";

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