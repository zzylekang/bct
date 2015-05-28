package com.zzy.dev.comm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileMD5 {

	/**
	 * 
	 * HMAC算法
	 */

	private static final String		ALGORITHM_SHA1		= "SHA1";

	private static final String		ALGORITHM_MD5		= "MD5";

	private static final String		ALGORITHM_MAC		= "HmacMD5";

	private static final String		ALGORITHM_MAC_SHAR1	= "HmacSHA1";

	protected static char			hexDigits[]			= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	protected static MessageDigest	messagedigest		= null;
	static {
		try {
			messagedigest = MessageDigest.getInstance(ALGORITHM_SHA1); // ALGORITHM_MD5  ALGORITHM_SHA1
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private static byte[] base64StrToByteArray(String str) {
		return str.getBytes();
	}

	/**
	 * 
	 * 将base64编码后的密钥字符串转换成密钥对象
	 * 
	 * @param key
	 *            密钥字符串
	 * 
	 * @param algorithm
	 *            加密算法
	 * 
	 * @return 返回密钥对象
	 */

	private static Key toKey(String key, String algorithm) {

		SecretKey secretKey = new SecretKeySpec(base64StrToByteArray(key), algorithm);

		return secretKey;

	}

	public static String getFileMACString(String fileName) throws IOException {

		String result = null;
		
		try {
			messagedigest = MessageDigest.getInstance(ALGORITHM_SHA1); // ALGORITHM_MD5  ALGORITHM_SHA1
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		File file = new File(fileName);
		
		FileInputStream fileInputStream = new FileInputStream(file);
		//MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		byte[] byteFileArray = new byte[6553500];
		
		int length = fileInputStream.read(byteFileArray);
		
		/*byte[] byteFileArray2 = Arrays.copyOf(byteFileArray, length);
		
		messagedigest.update(byteFileArray2);*/
		
		messagedigest.update(byteFileArray, 0, length);
		byte[] byteArrays = messagedigest.digest();
	
		//ch.close();
		fileInputStream.close();
		
		result = bufferToHex(byteArrays).toUpperCase();
		return result;
	}

	public static String getFileMD5StringMAC(File file, String keyStr) throws IOException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

		Key k = toKey(keyStr, ALGORITHM_MAC_SHAR1);
		try {
			//Mac mac = Mac.getInstance(k.getAlgorithm());
			Mac mac = Mac.getInstance(ALGORITHM_MAC_SHAR1);
			mac.init(k);
			mac.update(byteBuffer);
			return bufferToHex(mac.doFinal());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());
	}

	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	public static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();

		String FileName = "D:/logs/C14022343646_借款合同.pdf";
		String keyStr = "dddddddddddd";

		String testStr1 = "8142E88EE77D917943064833F792FD739E0039DA";

		String testStr2 = "";

		File bigFile = new File(FileName);

		String md5 = getFileMACString(FileName);

		//String md5 = getFileMD5StringMAC(big, keyStr); 

		long end = System.currentTimeMillis();
		System.out.println(" now:" + md5.toUpperCase());
		System.out.println("last1:" + testStr1);
		System.out.println("last2:" + testStr2);
		System.out.println("time:" + ((end - begin) / 1000) + "s");

	}
}
