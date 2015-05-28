/*
 * Copyright Bruce Liang (ldcsaa@gmail.com)
 *
 * Author	: Bruce Liang
 * Bolg		: http://www.cnblogs.com/ldcsaa
 * WeiBo	: http://weibo.com/u/1402935851
 * QQ Group	: http://qun.qq.com/#jointhegroup/gid/75375912
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zzy.dev.comm.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** ����롢�ӽ��ܰ����� */
public class CryptHelper
{
	/** AES �ܳ׳��� */
	public static final int AES_KEY_SIZE		= 128;
	/** DES �ܳ׳��� */
	public static final int DES_KEY_SIZE		= 56;
	/** ����ģʽ */
	public static final int ENCRYPT_MODE		= Cipher.ENCRYPT_MODE;
	/** ����ģʽ */
	public static final int DECRYPT_MODE		= Cipher.DECRYPT_MODE;
	/** Ĭ���ַ�����UTF-8�� */
	public static final String DEFAULT_ENCODING	= GeneralHelper.DEFAULT_ENCODING;
	/** ���ܷ�����MD5 */
	public static final String MD5				= "MD5";
	/** ���ܷ�����SHA */
	public static final String SHA				= "SHA";
	/** ���ܷ�����AES */
	public static final String AES				= "AES";
	/** ���ܷ�����DES */
	public static final String DES				= "DES";
	
	private static final String SEC_RAN_ALG		= "SHA1PRNG";

	/** byte[] -> ʮ�������ַ��� (Сд) */
	public final static String bytes2HexStr(byte[] bytes)
	{
		return bytes2HexStr(bytes, false);
	}
	
	/** byte[] -> ʮ�������ַ��� */
	public final static String bytes2HexStr(byte[] bytes, boolean capital)
	{
		StringBuilder sb = new StringBuilder();
		
		for(byte b : bytes)
			sb.append(byte2Hex(b, capital));
		
		return sb.toString();
	}

	/** byte -> ʮ������˫�ַ� (Сд) */
	public final static char[] byte2Hex(byte b)
	{
		return byte2Hex(b, false);
	}

	/** byte -> ʮ������˫�ַ� */
	public final static char[] byte2Hex(byte b, boolean capital)
	{
		byte bh	= (byte)(b >>> 4 & 0xF);
		byte bl	= (byte)(b & 0xF);

		return new char[] {halfByte2Hex(bh, capital), halfByte2Hex(bl, capital)};
	}
	
	/** �� byte -> ʮ�����Ƶ��ַ� (Сд) */
	public final static char halfByte2Hex(byte b)
	{
		return halfByte2Hex(b, false);
	}
	
	/** �� byte -> ʮ�����Ƶ��ַ� */
	public final static char halfByte2Hex(byte b, boolean capital)
	{
		return (char)(b <= 9 ? b + '0' : (capital ? b + 'A' - 0xA : b + 'a' - 0xA));
	}
	
	/** ʮ�������ַ��� -> byte[] */
	public final static byte[] hexStr2Bytes(String str)
	{
		int length = str.length();
		
		if(length % 2 != 0)
		{
			str = "0" + str;
			length = str.length();
		}
		
		byte[] bytes = new byte[length / 2];
		
		for(int i = 0; i < bytes.length; i++)
			bytes[i] = hex2Byte(str.charAt(2 * i), str.charAt(2 * i + 1));
		
		return bytes;
	}

	/** ʮ������˫�ַ� -> byte */
	public final static byte hex2Byte(char ch, char cl)
	{
		byte bh	= hex2HalfByte(ch);
		byte bl	= hex2HalfByte(cl);
		
		return (byte)((bh << 4) + bl);
	}
	
	/** ʮ�����Ƶ��ַ� -> �� byte */
	public final static byte hex2HalfByte(char c)
	{
		return (byte)(c <= '9' ? c - '0' : (c <= 'F' ? c - 'A' + 0xA : c - 'a' + 0xA));
	}
	
	/** ʹ��Ĭ���ַ������ַ���������ٽ��� MD5 ���� */
	public final static String md5(String input)
	{
		return md5(input, null);
	}
	
	/** ʹ��ָ���ַ������ַ���������ٽ��� MD5 ���� */
	public final static String md5(String input, String charset)
	{
		return encode(getMd5Digest(), input, charset);
	}
	
	/** MD5 ���� */
	public final static byte[] md5(byte[] input)
	{
		MessageDigest algorithm = getMd5Digest();
		return encode(algorithm, input);
	}
	
	/** ʹ��Ĭ���ַ������ַ���������ٽ��� SHA ���� */
	public final static String sha(String input)
	{
		return sha(input, null);
	}
	
	/** ʹ��ָ���ַ������ַ���������ٽ��� SHA ���� */
	public final static String sha(String input, String charset)
	{
		return encode(getShaDigest(), input, charset);
	}
	
	/** ʹ��Ĭ���ַ������ַ���������ٽ��� SHA-{X} ���ܣ����� {X} �� version ����ָ�� */
	public final static String sha(String input, int version)
	{
		return sha(input, null, version);
	}
	
	/** ʹ��ָ���ַ������ַ���������ٽ��� SHA-{X} ���ܣ����� {X} �� version ����ָ�� */
	public final static String sha(String input, String charset, int version)
	{
		return encode(getShaDigest(version), input, charset);
	}
	
	/** SHA���� */
	public final static byte[] sha(byte[] input)
	{
		MessageDigest algorithm = getShaDigest();
		return encode(algorithm, input);
	}
	
	/** SHA-{X} ���ܣ����� {X} �� version ����ָ�� */
	public final static byte[] sha(byte[] input, int version)
	{
		MessageDigest algorithm = getShaDigest(version);
		return encode(algorithm, input);
	}
	
	/** ʹ��ָ���㷨���ַ������� */
	public final static String encode(MessageDigest algorithm, String input)
	{
		return encode(algorithm, input, null);
	}
	
	/** ʹ��ָ���ַ������ַ���������ٽ��� SHA-{X} ���ܣ��ַ����ı����� charset ����ָ�� */
	public final static String encode(MessageDigest algorithm, String input, String charset)
	{
		try
		{
			byte[] bytes	= input.getBytes(safeCharset(charset));
			byte[] output	= encode(algorithm, bytes);
			
			return bytes2HexStr(output);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/** ʹ��ָ���㷨�� byte[] ���� */
	public final static byte[] encode(MessageDigest algorithm, byte[] input)
	{
		return algorithm.digest(input);
	}
	
	/** ��ȡ MD5 ����ժҪ���� */
	public final static MessageDigest getMd5Digest()
	{
		return getDigest(MD5);
	}
	
	/** ��ȡ SHA ����ժҪ���� */
	public final static MessageDigest getShaDigest()
	{
		return getDigest(SHA);
	}
	
	/** ��ȡ SHA-{X} ����ժҪ�������� {X} �� version ����ָ�� */
	public final static MessageDigest getShaDigest(int version)
	{
		String algorithm = String.format("%s-%d", SHA, version);
		return getDigest(algorithm);
	}
	
	/** ���ݼ��ܷ������ƻ�ȡ����ժҪ���� */
	public final static MessageDigest getDigest(String algorithm)
	{
		try
		{
			return MessageDigest.getInstance(algorithm);
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}

	/** ���ݼ��ܷ������ƺ��ṩ�߻�ȡ����ժҪ���� */
	public final static MessageDigest getDigest(String algorithm, String provider)
	{
		try
		{
			return MessageDigest.getInstance(algorithm, provider);
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		catch(NoSuchProviderException e)
		{
			throw new RuntimeException(e);
		}
	}

	/** ���ݼ��ܷ������ƺ��ṩ�߻�ȡ����ժҪ���� */
	public final static MessageDigest getDigest(String algorithm, Provider provider)
	{
		try
		{
			return MessageDigest.getInstance(algorithm, provider);
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}

	/** URL���� ��ʹ��Ĭ���ַ����� */
	public final static String urlEncode(String url)
	{
		return urlEncode(url, null);
	}
	
	/** URL���� ��ʹ��ָ���ַ����� */
	public final static String urlEncode(String url, String charset)
	{
		try
		{
			return URLEncoder.encode(url, safeCharset(charset));
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}

	/** URL���� ��ʹ��Ĭ���ַ����� */
	public final static String urlDecode(String url)
	{
		return urlDecode(url, null);
	}
	
	/** URL���� ��ʹ��ָ���ַ����� */
	public final static String urlDecode(String url, String enc)
	{
		try
		{
			return URLDecoder.decode(url, safeCharset(enc));
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}

	/** base 64 ���� */
	public final static String base64Encode(byte[] bytes)
	{
		return new BASE64Encoder().encode(bytes);
	}

	/** ʹ��Ĭ���ַ������ַ������� base 64 ���� */
	public final static String base64Encode(String str)
	{
		return base64Encode(str, null);
	}

	/** ʹ��ָ���ַ������ַ������� base 64 ���� */
	public final static String base64Encode(String str, String charset)
	{
		try
		{
			byte[] bytes = str.getBytes(safeCharset(charset));
			return base64Encode(bytes);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/** base 64 ���� */
	public final static byte[] base64DecodeBytes(String str)
	{
		try
		{
			return new BASE64Decoder().decodeBuffer(str);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/** ʹ��Ĭ���ַ����� base 64 �ַ������н��� */
	public final static String base64Decode(String str)
	{
		return base64Decode(str, null);
	}

	/** ʹ��ָ���ַ����� base 64 �ַ������н��� */
	public final static String base64Decode(String str, String charset)
	{
		try
		{
			return new String(base64DecodeBytes(str), safeCharset(charset));
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/** ʹ��Ĭ���ַ������ַ���������ٽ��� AES ���� */
	public final static String aesEncrypt(String content, String password) throws GeneralSecurityException
	{
		return aesEncrypt(content, null, password);
	}
	
	/** ʹ��ָ���ַ������ַ���������ٽ��� AES ���ܣ��ַ����ı����� charset ����ָ�� */
	public final static String aesEncrypt(String content, String charset, String password) throws GeneralSecurityException
	{
		return encrypt(AES, AES_KEY_SIZE, content, charset, password);
	}
	
	/** AES ���� */
	public final static byte[] aesEncrypt(byte[] content, String password) throws GeneralSecurityException
	{
		return crypt(AES, ENCRYPT_MODE, AES_KEY_SIZE, content, password);
	}
	
	/** AES ���ܣ���ʹ��Ĭ���ַ������ɽ��ܺ���ַ��� */
	public final static String aesDecrypt(String content, String password) throws GeneralSecurityException
	{
		return aesDecrypt(content, null, password);
	}
	
	/** AES ���ܣ���ʹ��ָ���ַ������ɽ��ܺ���ַ������ַ����ı����� charset ����ָ�� */
	public final static String aesDecrypt(String content, String charset, String password) throws GeneralSecurityException
	{
		return decrypt(AES, AES_KEY_SIZE, content, charset, password);
	}

	/** AES ���� */
	public final static byte[] aesDecrypt(byte[] content, String password) throws GeneralSecurityException
	{
		return crypt(AES, DECRYPT_MODE, AES_KEY_SIZE, content, password);
	}

	/** ʹ��Ĭ���ַ������ַ���������ٽ��� DES ���� */
	public final static String desEncrypt(String content, String password) throws GeneralSecurityException
	{
		return desEncrypt(content, null, password);
	}
	
	/** ʹ��ָ���ַ������ַ���������ٽ��� DES ���ܣ��ַ����ı����� charset ����ָ�� */
	public final static String desEncrypt(String content, String charset, String password) throws GeneralSecurityException
	{
		return encrypt(DES, DES_KEY_SIZE, content, charset, password);
	}
	
	/** DES ���� */
	public final static byte[] desEncrypt(byte[] content, String password) throws GeneralSecurityException
	{
		return crypt(DES, ENCRYPT_MODE, DES_KEY_SIZE, content, password);
	}

	/** DES ���ܣ���ʹ��Ĭ���ַ������ɽ��ܺ���ַ��� */
	public final static String desDecrypt(String content, String password) throws GeneralSecurityException
	{
		return desDecrypt(content, null, password);
	}
	
	/** DES ���ܣ���ʹ��ָ���ַ������ɽ��ܺ���ַ������ַ����ı����� charset ����ָ�� */
	public final static String desDecrypt(String content, String charset, String password) throws GeneralSecurityException
	{
		return decrypt(DES, DES_KEY_SIZE, content, charset, password);
	}

	/** DES ���� */
	public final static byte[] desDecrypt(byte[] content, String password) throws GeneralSecurityException
	{
		return crypt(DES, DECRYPT_MODE, DES_KEY_SIZE, content, password);
	}

	/**
	 * �����ַ���
	 * 
	 * @param method	�����ܷ�����AES��DES��
	 * @param keysize	���ܳ׳���
	 * @param content	��Ҫ���ܵ�����
	 * @param charset	���������ݵı����ַ���
	 * @param password	������
	 * @return			���ӽ��ܽ��
	 * @throws GeneralSecurityException	����ʧ���׳��쳣
	 */
	public final static String encrypt(String method, int keysize, String content, String charset, String password) throws GeneralSecurityException
	{
		try
		{
			byte[] bytes	= content.getBytes(safeCharset(charset));
			byte[] output	= crypt(method, ENCRYPT_MODE, keysize, bytes, password);
			
			return bytes2HexStr(output);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �����ַ���
	 * 
	 * @param method	�����ܷ�����AES��DES��
	 * @param keysize	���ܳ׳���
	 * @param content	��Ҫ���ܵ�����
	 * @param charset	�����ܽ���ı����ַ���
	 * @param password	������
	 * @return			���ӽ��ܽ��
	 * @throws GeneralSecurityException	����ʧ���׳��쳣
	 */
	public final static String decrypt(String method, int keysize, String content, String charset, String password) throws GeneralSecurityException
	{
		try
		{
			byte[] bytes	= hexStr2Bytes(content);
			byte[] output	= crypt(method, DECRYPT_MODE, keysize, bytes, password);
			
			return new String(output, safeCharset(charset));
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��/����
	 * 
	 * @param method	����/���ܷ�����AES��DES��
	 * @param mode		��ģʽ������/���ܣ�
	 * @param keysize	���ܳ׳���
	 * @param content	��Ҫ��/���ܵ�����
	 * @param password	������
	 * @return			���ӽ��ܽ��
	 * @throws GeneralSecurityException	����ʧ���׳��쳣
	 */
	public final static byte[] crypt(String method, int mode, int keysize, byte[] content, String password) throws GeneralSecurityException
	{
			KeyGenerator kgen	= KeyGenerator.getInstance(method);
			SecureRandom secure	= SecureRandom.getInstance(SEC_RAN_ALG);
			String seed			= GeneralHelper.safeString(password);
			
			secure.setSeed(seed.getBytes());
			kgen.init(keysize, secure);

			SecretKey secretKey	= kgen.generateKey();
			byte[] enCodeFormat	= secretKey.getEncoded();
			SecretKeySpec key	= new SecretKeySpec(enCodeFormat, method);
			Cipher cipher		= Cipher.getInstance(method);
			
			cipher.init(mode, key);
			return cipher.doFinal(content);
	}
	
	private final static String safeCharset(String charset)
	{
		if(GeneralHelper.isStrEmpty(charset))
			charset = DEFAULT_ENCODING;
		
		return charset;
	}
}
