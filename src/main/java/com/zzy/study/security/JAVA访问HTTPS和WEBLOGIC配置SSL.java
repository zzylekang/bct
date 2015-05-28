package com.zzy.study.security;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;
public class JAVA访问HTTPS和WEBLOGIC配置SSL {
	
	 /**
	  * @param args
	  * @throws Exception 
	  */
	public static void main(String[] args) throws Exception {
		// SSL环境上下文
		SSLContext sslContext = SSLContext.getInstance("SSL"); 
		
		// 私钥管理工厂
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		// 受信任证书管理工厂
	  	TrustManagerFactory  tmf = TrustManagerFactory .getInstance("SunX509");
	  	// 密钥库
	  	KeyStore ks = KeyStore.getInstance("JKS");
	  	ks.load(new FileInputStream("D:\\keytool\\store.jks"), "xq000391".toCharArray());
	  	kmf.init(ks, "xq000391".toCharArray());
	  	// 受信任证书库
	  	KeyStore tks = KeyStore.getInstance("JKS");
	  	tks.load(new FileInputStream("D:\\keytool\\PAICSTG.jks"), "xq000391".toCharArray());
	  	
	  	/**
	  	 * 打包文件命令  
	  	 * keytool -import -trustcacerts -alias www.zlex.org -file d:\zlex.p7b -keystore d:\zlex.jks -v
	  	 * 
	  	 * keytool -import  -alias paic -trustcacerts -file d:\keytool\PAICSTG.cer -keystore  d:\keytool\PAICSTG.jks
	  	 **/
	  	tmf.init(tks);
	  	// 初始化SSL环境上下文
		  
	  	sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
	  	
	  	HostnameVerifier hostnameVerifier = new HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
	  	};
	  	HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
	  	URL url = new URL("https://10.36.192.51:8007");
	  	HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
	  	urlCon.setDoOutput(true);
	  	urlCon.setDoInput(true);
	  	urlCon.setRequestMethod("POST");
	  	urlCon.setRequestProperty("Content-type", "text/xml;charset=GB2312");
	  	urlCon.setSSLSocketFactory(sslContext.getSocketFactory());
	  	
	  	OutputStream os = urlCon.getOutputStream();
	  	
	  	  	
	  	// 新渠道小消ITS名单下发
	  	FileInputStream fis = new FileInputStream("D:\\keytool\\xix_its_apply_req.xml");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] bytes = new byte[100];
		int len = -1;
		while ((len = bis.read(bytes)) != -1) {
			os.write(bytes, 0, len);
		}
		os.flush();
		bis.close();
		fis.close();
		os.close();
		
		InputStream is = urlCon.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		is.close();
		System.out.println(URLDecoder.decode(sb.toString(), "UTF-8"));
		urlCon.disconnect();
	}
}

===========================================验证代码==========================================================================

public static void main(String args[]) {
		try {
			// 设置SSL start
			// SSL环境上下文
			SSLContext sslContext = SSLContext.getInstance("SSL");
			// 单向认证不需要客户端证书库
			// 私钥管理工厂
			// KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			// 密钥库
			// KeyStore ks = KeyStore.getInstance("JKS");
			// ks.load(new FileInputStream("D:\\chengang.jks"),
			// "123456".toCharArray());
			// kmf.init(ks, "123456".toCharArray());

			// 加载服务端信任证书
			// 受信任证书管理工厂
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance("SunX509");
			KeyStore tks = KeyStore.getInstance("JKS");
			tks.load(new FileInputStream("D:\\chengangtrust.jks"),
					"123456".toCharArray());
			tmf.init(tks);
			// 初始化SSL环境上下文
			sslContext.init(null, tmf.getTrustManagers(), null);
			HostnameVerifier hostnameVerifier = new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			};
			// 设置SSL end
			HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
			URL postUrl = new URL(
					"https://localhost:7002/gcc/repeatCallSwitch.do");
			HttpsURLConnection connection = (HttpsURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			/**
			 * Content-Type设置成application/x-www-form-urlencoded时服务端的request才能用getParameter获取传递参数
			 * 设置成application/x-www-form-urlencoded时还需要把参数进行encoder编码转化
			 * Form的默认提交类型就是application/x-www-form-urlencoded，目前公司的ajax请求(GET和POST)也是application/x-www-form-urlencoded
			 * 
			 * Content-Type设置成其他类型，如：text/html、text/xml、text/json、application/
			 * json时须在request的 InputStream中获取参数值
			 */
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			// HTTPS连接设置SSL
			connection.setSSLSocketFactory(sslContext.getSocketFactory());
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			//设置参数，并进行encoder
			String content = "m="
					+ URLEncoder.encode("getRepeatCallAcceptInfo", "utf-8");
			content = content + "&callNo="
					+ URLEncoder.encode("13438094006", "utf-8");
			out.writeBytes(content);

			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			System.out.println("=============================");
			System.out.println("Contents of post request");
			System.out.println("=============================");
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("=============================");
			System.out.println("Contents of post request ends");
			System.out.println("=============================");
			reader.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
 * 证书生成
 * 
 * 生成服务端证书库 
 * keytool -genkey -keyalg RSA -keysize 1024 -validity 3650 -alias chengang -keystore d:\\chengang.jks -storepass 123456 -dname "CN=www.chengang.com, OU=chen, O=gang, L=ChengDu, ST=ChengHua, C=CN"
 * 
 * 从服务端证书库导出需发给客户端的证书 
 * keytool -exportcert -alias chengang -keystore d:\\chengang.jks -storepass 123456 -file d:\\chengang.cer -rfc
 * 
 * 客户端导入服务端给它的证书到受信任证书库中 
 * Keytool -importcert -trustcacerts -alias chengang -file d:\\chengang.cer -keystore d:\\chengangtrust.jks -storepass 123456
 * 
 * 
 **/

 /**
 * webloigc  SSL 配置
 *
 * Custom Trust Keystore(服务端信任证书库)单向认证时没什么用，设置成Custom Identity Keystore(服务端证书库)就行，
 * 
 * 只有Two Way Client Cert Behavior改成最后一项变成双向认证时才有用
 * 
 **/