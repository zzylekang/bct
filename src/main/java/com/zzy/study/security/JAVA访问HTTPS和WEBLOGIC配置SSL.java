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
public class JAVA����HTTPS��WEBLOGIC����SSL {
	
	 /**
	  * @param args
	  * @throws Exception 
	  */
	public static void main(String[] args) throws Exception {
		// SSL����������
		SSLContext sslContext = SSLContext.getInstance("SSL"); 
		
		// ˽Կ������
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		// ������֤�������
	  	TrustManagerFactory  tmf = TrustManagerFactory .getInstance("SunX509");
	  	// ��Կ��
	  	KeyStore ks = KeyStore.getInstance("JKS");
	  	ks.load(new FileInputStream("D:\\keytool\\store.jks"), "xq000391".toCharArray());
	  	kmf.init(ks, "xq000391".toCharArray());
	  	// ������֤���
	  	KeyStore tks = KeyStore.getInstance("JKS");
	  	tks.load(new FileInputStream("D:\\keytool\\PAICSTG.jks"), "xq000391".toCharArray());
	  	
	  	/**
	  	 * ����ļ�����  
	  	 * keytool -import -trustcacerts -alias www.zlex.org -file d:\zlex.p7b -keystore d:\zlex.jks -v
	  	 * 
	  	 * keytool -import  -alias paic -trustcacerts -file d:\keytool\PAICSTG.cer -keystore  d:\keytool\PAICSTG.jks
	  	 **/
	  	tmf.init(tks);
	  	// ��ʼ��SSL����������
		  
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
	  	
	  	  	
	  	// ������С��ITS�����·�
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

===========================================��֤����==========================================================================

public static void main(String args[]) {
		try {
			// ����SSL start
			// SSL����������
			SSLContext sslContext = SSLContext.getInstance("SSL");
			// ������֤����Ҫ�ͻ���֤���
			// ˽Կ������
			// KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			// ��Կ��
			// KeyStore ks = KeyStore.getInstance("JKS");
			// ks.load(new FileInputStream("D:\\chengang.jks"),
			// "123456".toCharArray());
			// kmf.init(ks, "123456".toCharArray());

			// ���ط��������֤��
			// ������֤�������
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance("SunX509");
			KeyStore tks = KeyStore.getInstance("JKS");
			tks.load(new FileInputStream("D:\\chengangtrust.jks"),
					"123456".toCharArray());
			tmf.init(tks);
			// ��ʼ��SSL����������
			sslContext.init(null, tmf.getTrustManagers(), null);
			HostnameVerifier hostnameVerifier = new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			};
			// ����SSL end
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
			 * Content-Type���ó�application/x-www-form-urlencodedʱ����˵�request������getParameter��ȡ���ݲ���
			 * ���ó�application/x-www-form-urlencodedʱ����Ҫ�Ѳ�������encoder����ת��
			 * Form��Ĭ���ύ���;���application/x-www-form-urlencoded��Ŀǰ��˾��ajax����(GET��POST)Ҳ��application/x-www-form-urlencoded
			 * 
			 * Content-Type���ó��������ͣ��磺text/html��text/xml��text/json��application/
			 * jsonʱ����request�� InputStream�л�ȡ����ֵ
			 */
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			// HTTPS��������SSL
			connection.setSSLSocketFactory(sslContext.getSocketFactory());
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			//���ò�����������encoder
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
 * ֤������
 * 
 * ���ɷ����֤��� 
 * keytool -genkey -keyalg RSA -keysize 1024 -validity 3650 -alias chengang -keystore d:\\chengang.jks -storepass 123456 -dname "CN=www.chengang.com, OU=chen, O=gang, L=ChengDu, ST=ChengHua, C=CN"
 * 
 * �ӷ����֤��⵼���跢���ͻ��˵�֤�� 
 * keytool -exportcert -alias chengang -keystore d:\\chengang.jks -storepass 123456 -file d:\\chengang.cer -rfc
 * 
 * �ͻ��˵������˸�����֤�鵽������֤����� 
 * Keytool -importcert -trustcacerts -alias chengang -file d:\\chengang.cer -keystore d:\\chengangtrust.jks -storepass 123456
 * 
 * 
 **/

 /**
 * webloigc  SSL ����
 *
 * Custom Trust Keystore(���������֤���)������֤ʱûʲô�ã����ó�Custom Identity Keystore(�����֤���)���У�
 * 
 * ֻ��Two Way Client Cert Behavior�ĳ����һ����˫����֤ʱ������
 * 
 **/