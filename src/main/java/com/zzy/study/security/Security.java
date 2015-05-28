package com.zzy.study.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.zzy.dev.comm.util.FileMD5;

public class Security {
	public static void main(String [] args){
		/*StringBuffer mac = new StringBuffer();
		Security.getDigest(mac);
		System.out.println("引用：" + mac);*/
		Security.getDigest2();
	}
	private static final void getDigest2() {

		String filePath = "D:/logs/aabb.pdf";

		try {
			
			System.out.println("文件摘要十六进制字符串：" + FileMD5.getFileMACString(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	private static final void getDigest(StringBuffer mac) {

		String sha = "SHA1";
		
		String filePath = "D:/logs/C14022343646_借款合同.pdf";
		String toFilePath = "D:/logs/C14022343646_借款合同.new.pdf";
		FileInputStream fis = null;
		DigestInputStream dis = null;
		FileOutputStream fos = null;
		String macFromBank = null;
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(filePath);
			MessageDigest md = MessageDigest.getInstance(sha);
			
			//dis = new DigestInputStream(fis, md);
			fos = new FileOutputStream(toFilePath);
			int len = 0;
			//dis.on(true);
			while ((len = fis.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, len);
				fos.write(buffer, 0, len);
			}
			/*dis.on(false);
			while ((len = dis.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, len);
				fos.write(buffer, 0, len);
			}*/
			
			byte[] macbyte = md.digest();
			System.out.println("文件摘要数组：" + macbyte);
			String macStr = Security.byte2hexString(macbyte);
			String macStr2 = FileMD5.bufferToHex(macbyte);
			BigInteger md5 = new BigInteger(macbyte);
			System.out.println("文件摘要十六进制字符串：" + macStr);
			System.out.println("文件摘要十六进制字符串3：" + macStr2);
			System.out.println("文件摘要十六进制字符串2：" + md5.toString(16));
			if (macStr.equals(macFromBank)) {
				System.out.println("文件未被篡改");
			}
			mac.append(macStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				fos.close();
				//dis.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	
	}
	private static final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
           if ( ((int) bytes[i] & 0xff) < 0x10) {
           		buf.append("0");
           }
           buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
    	return buf.toString().toUpperCase();
   }
}
