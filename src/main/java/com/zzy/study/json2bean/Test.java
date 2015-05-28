package com.zzy.study.json2bean;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.zzy.dev.comm.util.JsonUtil;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sbSql = new StringBuffer();
		

sbSql.append("{\r\n");
sbSql.append("  \"image\":[\r\n");
sbSql.append("\r\n");
sbSql.append("  ],\r\n");
sbSql.append("  \"jmInfo\":null,\r\n");
sbSql.append("  \"offerId\":\"B2013112703223\",\r\n");
sbSql.append("  \"order\":[\r\n");
sbSql.append("    {\r\n");
sbSql.append("      \"buyerId\":\"1000000000772\",\r\n");
sbSql.append("      \"buyerImgPath\":\"http:\\/\\/image.315.com.cn\\/imageManager\\/pub\\/images\\/2013\\/1118\\/13847609212525722_0.jpg\",\r\n");
sbSql.append("      \"buyerName\":\"升级企业5\",\r\n");
sbSql.append("      \"image\":[\r\n");
sbSql.append("\r\n");
sbSql.append("      ],\r\n");
sbSql.append("      \"orderId\":\"D2013112700168\",\r\n");
sbSql.append("      \"orderInfo\":\"的发生非盛大555的发生非盛大555的发生非盛大555的发生非盛大555的发生非盛大555的发生非盛大555的发生非盛大555的发生非盛大555的发生非盛大555\",\r\n");
sbSql.append("      \"orderTime\":\"2013-11-27 11:14:21\",\r\n");
sbSql.append("      \"orderVoice\":null,\r\n");
sbSql.append("      \"orderVoiceSecs\":null,\r\n");
sbSql.append("      \"status\":null,\r\n");
sbSql.append("      \"stautsName\":\"待卖家确认\"\r\n");
sbSql.append("    },\r\n");
sbSql.append("    {\r\n");
sbSql.append("      \"buyerId\":\"1000000000772\",\r\n");
sbSql.append("      \"buyerImgPath\":\"http:\\/\\/image.315.com.cn\\/imageManager\\/pub\\/images\\/2013\\/1118\\/13847609212525722_0.jpg\",\r\n");
sbSql.append("      \"buyerName\":\"升级企业5\",\r\n");
sbSql.append("      \"image\":[\r\n");
sbSql.append("        {\r\n");
sbSql.append("          \"id\":\"10721\",\r\n");
sbSql.append("          \"imagePath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220233170.jpg\",\r\n");
sbSql.append("          \"smallPath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220233170.jpg_200_150.jpg\"\r\n");
sbSql.append("        },\r\n");
sbSql.append("        {\r\n");
sbSql.append("          \"id\":\"10722\",\r\n");
sbSql.append("          \"imagePath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220239851.jpg\",\r\n");
sbSql.append("          \"smallPath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220239851.jpg_200_150.jpg\"\r\n");
sbSql.append("        },\r\n");
sbSql.append("        {\r\n");
sbSql.append("          \"id\":\"10726\",\r\n");
sbSql.append("          \"imagePath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220263195.jpg\",\r\n");
sbSql.append("          \"smallPath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220263195.jpg_200_150.jpg\"\r\n");
sbSql.append("        },\r\n");
sbSql.append("        {\r\n");
sbSql.append("          \"id\":\"10724\",\r\n");
sbSql.append("          \"imagePath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220252773.jpg\",\r\n");
sbSql.append("          \"smallPath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220252773.jpg_200_150.jpg\"\r\n");
sbSql.append("        },\r\n");
sbSql.append("        {\r\n");
sbSql.append("          \"id\":\"10725\",\r\n");
sbSql.append("          \"imagePath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220258084.jpg\",\r\n");
sbSql.append("          \"smallPath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220258084.jpg_200_150.jpg\"\r\n");
sbSql.append("        },\r\n");
sbSql.append("        {\r\n");
sbSql.append("          \"id\":\"10723\",\r\n");
sbSql.append("          \"imagePath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220245682.jpg\",\r\n");
sbSql.append("          \"smallPath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855220245682.jpg_200_150.jpg\"\r\n");
sbSql.append("        }\r\n");
sbSql.append("      ],\r\n");
sbSql.append("      \"orderId\":\"D2013112700167\",\r\n");
sbSql.append("      \"orderInfo\":\"数据库洗洗脑那些年那地方11数据库洗洗脑那些年那地方11数据库洗洗脑那些年那地方11数据库洗洗脑那些年那地方11数据库洗洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44洗脑那些年那地方44\",\r\n");
sbSql.append("      \"orderTime\":\"2013-11-27 11:13:46\",\r\n");
sbSql.append("      \"orderVoice\":null,\r\n");
sbSql.append("      \"orderVoiceSecs\":null,\r\n");
sbSql.append("      \"status\":null,\r\n");
sbSql.append("      \"stautsName\":\"待卖家确认\"\r\n");
sbSql.append("    },\r\n");
sbSql.append("    {\r\n");
sbSql.append("      \"buyerId\":\"1000000000772\",\r\n");
sbSql.append("      \"buyerImgPath\":\"http:\\/\\/image.315.com.cn\\/imageManager\\/pub\\/images\\/2013\\/1118\\/13847609212525722_0.jpg\",\r\n");
sbSql.append("      \"buyerName\":\"升级企业5\",\r\n");
sbSql.append("      \"image\":[\r\n");
sbSql.append("\r\n");
sbSql.append("      ],\r\n");
sbSql.append("      \"orderId\":\"D2013112700166\",\r\n");
sbSql.append("      \"orderInfo\":\"订单伈伈睍睍3333\",\r\n");
sbSql.append("      \"orderTime\":\"2013-11-27 11:12:32\",\r\n");
sbSql.append("      \"orderVoice\":null,\r\n");
sbSql.append("      \"orderVoiceSecs\":null,\r\n");
sbSql.append("      \"status\":null,\r\n");
sbSql.append("      \"stautsName\":\"待卖家确认\"\r\n");
sbSql.append("    },\r\n");
sbSql.append("    {\r\n");
sbSql.append("      \"buyerId\":\"1000000000772\",\r\n");
sbSql.append("      \"buyerImgPath\":\"http:\\/\\/image.315.com.cn\\/imageManager\\/pub\\/images\\/2013\\/1118\\/13847609212525722_0.jpg\",\r\n");
sbSql.append("      \"buyerName\":\"升级企业5\",\r\n");
sbSql.append("      \"image\":[\r\n");
sbSql.append("        {\r\n");
sbSql.append("          \"id\":\"10689\",\r\n");
sbSql.append("          \"imagePath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855209512831.jpg\",\r\n");
sbSql.append("          \"smallPath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855209512831.jpg_200_150.jpg\"\r\n");
sbSql.append("        },\r\n");
sbSql.append("        {\r\n");
sbSql.append("          \"id\":\"10688\",\r\n");
sbSql.append("          \"imagePath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855209501350.jpg\",\r\n");
sbSql.append("          \"smallPath\":\"http:\\/\\/static.315.com.cn:90\\/upload\\/trade\\/image\\/13855209501350.jpg_200_150.jpg\"\r\n");
sbSql.append("        }\r\n");
sbSql.append("      ],\r\n");
sbSql.append("      \"orderId\":\"D2013112700142\",\r\n");
sbSql.append("      \"orderInfo\":\"测试数据 商号2222\",\r\n");
sbSql.append("      \"orderTime\":\"2013-11-27 10:55:52\",\r\n");
sbSql.append("      \"orderVoice\":null,\r\n");
sbSql.append("      \"orderVoiceSecs\":null,\r\n");
sbSql.append("      \"status\":null,\r\n");
sbSql.append("      \"stautsName\":\"待卖家确认\"\r\n");
sbSql.append("    },\r\n");
sbSql.append("    {\r\n");
sbSql.append("      \"buyerId\":\"1000000000772\",\r\n");
sbSql.append("      \"buyerImgPath\":\"http:\\/\\/image.315.com.cn\\/imageManager\\/pub\\/images\\/2013\\/1118\\/13847609212525722_0.jpg\",\r\n");
sbSql.append("      \"buyerName\":\"升级企业5\",\r\n");
sbSql.append("      \"image\":[\r\n");
sbSql.append("\r\n");
sbSql.append("      ],\r\n");
sbSql.append("      \"orderId\":\"D2013112700141\",\r\n");
sbSql.append("      \"orderInfo\":\"测试数据 商号11\",\r\n");
sbSql.append("      \"orderTime\":\"2013-11-27 10:54:50\",\r\n");
sbSql.append("      \"orderVoice\":null,\r\n");
sbSql.append("      \"orderVoiceSecs\":null,\r\n");
sbSql.append("      \"status\":null,\r\n");
sbSql.append("      \"stautsName\":\"待卖家确认\"\r\n");
sbSql.append("    }\r\n");
sbSql.append("  ],\r\n");
sbSql.append("  \"productDesc\":\"报盘信息111\",\r\n");
sbSql.append("  \"releaseTime\":\"2013-11-27 10:12:10\",\r\n");
sbSql.append("  \"sellFrom\":null,\r\n");
sbSql.append("  \"sourceType\":\"1\",\r\n");
sbSql.append("  \"voice\":null,\r\n");
sbSql.append("  \"voiceSecs\":null\r\n");
sbSql.append("}"); 

		
		
		/*JSONObject jsonObj = JSONObject.fromObject(sbSql.toString());
		ResJydaz017 bean = (ResJydaz017)JSONObject.toBean(jsonObj, ResJydaz017.class );*/
		Gson gson = new Gson();
		ResJydaz017 bean = gson.fromJson(sbSql.toString(), ResJydaz017.class);

	}

}
