package com.zzy.study.security;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.KeyStroke;

public class HttpsDownload {
	public static void main(String [] args){
		HttpsDownload.downLoad1();
	}
	/**
	 * 添加证书到受信任证书库方式下载
	 */
	public static void downLoad2() {

		String downSrc = "https://www1.test.95599.cn/tjbranch/market/MarketFileDownloadAct.ebf" +
				"?sGuid=594e89dbf5e342d7a044be3af20a54b9&MerchantID=211000005722E01";
		String downSrc2 = "https://www.google.com.hk/webhp?hl=zh-CN";
		String desSrc = "D:/logs/heihei.txt";
		URL url = null;
		HttpsURLConnection httpsConn = null;
		InputStream is = null;
		FileOutputStream fos = null;
		//System.setProperty("javax.net.sll.trustStore", "certsnew");
		TrustManagerFactory tmf = null;
		SSLContext sslCtx = null;
		KeyStore tks = null;
		byte[] buffer = new byte[1024];
		try {
			// SSL环境上下文
			sslCtx = SSLContext.getInstance("SSL");
			// 受信任证书管理工厂
			tmf = TrustManagerFactory.getInstance("SunX509");
			// 受信任证书库
			tks = tks.getInstance("JKS");
			
			tks.load(new FileInputStream("D:/appconfig/certsnew.jks"), "11111111".toCharArray());
			tmf.init(tks);
			sslCtx.init(null, tmf.getTrustManagers(), new SecureRandom());
		  	HostnameVerifier hostnameVerifier = new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
		  	};
		  	HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
			url = new URL(downSrc);
			httpsConn = (HttpsURLConnection) url.openConnection();
			httpsConn.setDoOutput(true);
			httpsConn.setDoInput(true);
			httpsConn.setRequestMethod("GET");
			httpsConn.setRequestProperty("Content-type", "text/xml;charset=GB2312");
			httpsConn.setSSLSocketFactory(sslCtx.getSocketFactory());
			is = httpsConn.getInputStream();
			fos = new FileOutputStream(desSrc);
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
			is.close();
			System.out.println("success");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	/**
	 * 自定义TrustManager方式下载
	 */
	public static void downLoad1() {

		String downSrc = "https://www1.test.95599.cn/tjbranch/market/MarketFileDownloadAct.ebf?sGuid=b8d108b2742044c1ad7b43c901f725a0&MerchantID=211000005722E01";
		String desSrc = "D:/logs/aabb.pdf";
		URL url = null;
		HttpsURLConnection httpsConn = null;
		InputStream is = null;
		FileOutputStream fos = null;
		TrustManagerFactory tmf = null;
		SSLContext sslCtx = null;
		byte[] buffer = new byte[1];//注意，byte数组的长度如果大于1会导致下载后的文件与原始文件的MAC值不同。
		try {
			HostnameVerifier hostnameVerifier = new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					System.out.println(arg0 + ":" + arg1.getPeerHost());
					return true;
				}
		  	};
		  	HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
			TrustManager[] trustAllCerts = {new MyX509TrustManager()};
			sslCtx = SSLContext.getInstance("SSL");
			sslCtx.init(null, trustAllCerts, new SecureRandom());
			url = new URL(downSrc);
			httpsConn = (HttpsURLConnection) url.openConnection();
			httpsConn.setDoOutput(true);
			httpsConn.setDoInput(true);
			httpsConn.setRequestMethod("GET");
			httpsConn.setRequestProperty("Content-type", "text/xml;charset=GB2312");
			httpsConn.setSSLSocketFactory(sslCtx.getSocketFactory());
			is = httpsConn.getInputStream();
			fos = new FileOutputStream(desSrc);
			int len = is.read(buffer);
			while (len != -1) {
				fos.write(buffer, 0, len);
				len = is.read(buffer);
			}
			fos.flush();
			fos.close();
			is.close();
			System.out.println("success");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/* catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} *//*catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
