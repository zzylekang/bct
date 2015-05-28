package com.zzy.dev.comm.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** ͨ�÷��������� */
public class GeneralHelper
{
	private static final String[] SHORT_DATE_PATTERN 				= {"yyyy-MM-dd", "yyyy/MM/dd", "yyyy\\MM\\dd", "yyyyMMdd"};
	private static final String[] LONG_DATE_PATTERN 				= {"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy\\MM\\dd HH:mm:ss", "yyyyMMddHHmmss"};
	private static final String[] LONG_DATE_PATTERN_WITH_MILSEC 	= {"yyyy-MM-dd HH:mm:ss.SSS", "yyyy/MM/dd HH:mm:ss.SSS", "yyyy\\MM\\dd HH:mm:ss.SSS", "yyyyMMddHHmmssSSS"};
	
	/** ���ַ��� */
	public static final String EMPTY_STRING				= "";
	/** ���ַ��� */
	public static final String DEFAULT_ENCODING			= "UTF-8";
	/** ��ǰ����ϵͳƽ̨ */
	public static final String OS_PLATFORM				= getOSName();
	/** ��ǰ����ϵͳƽ̨�Ƿ�Ϊ Windows */
	public static final boolean IS_WINDOWS_PLATFORM		= isWindowsPlatform();
	/** ��ǰ����ϵͳƽ̨�Ļ��з� */
	public static final String NEWLINE_CHAR				= IS_WINDOWS_PLATFORM ? "\r\n" : "\n";
	
	
	/** ����ַ�����Ϊ null ����ַ��� */
	public final static boolean isStrNotEmpty(String str)
	{
		return str != null && str.length() != 0;
	}

	/** ����ַ�����Ϊ null �����ַ�����ֻ�����ո� */
	public final static boolean isTrimStrNotEmpty(String str)
	{
		boolean result = isStrNotEmpty(str);
		return result ? isStrNotEmpty(str.trim()) : result;
	}

	/** ����ַ���Ϊ null ����ַ��� */
	public final static boolean isStrEmpty(String str)
	{
		return !isStrNotEmpty(str);
	}

	/** ����ַ���Ϊ null �����ַ�����ֻ�����ո� */
	public final static boolean isTrimStrEmpty(String str)
	{
		boolean result = isStrEmpty(str);
		return result ?  result : isStrEmpty(str.trim());
	}

	/** �Ѳ��� str ת��Ϊ��ȫ�ַ�������� str = null�������ת��Ϊ���ַ��� */
	public final static String safeString(String str)
	{
		if(str == null)
			str = "";
		
		return str;
	}

	/** �Ѳ��� str ת��Ϊ��ȫ�ַ�����ִ��ȥ��ǰ��ո���� str = null�������ת��Ϊ���ַ��� */
	public final static String safeTrimString(String str)
	{
		return safeString(str).trim();
	}

	/** ����ַ����Ƿ����������ʽ */
	public final static boolean isStrNumeric(String str)
	{
		return str.matches("^0$|^\\-?[1-9]+[0-9]*$");
	}

	/** ����ַ����Ƿ���ϵ����ʼ���ʽ */
	public final static boolean isStrEmailAddress(String str)
	{
		return str.matches("^[a-z0-9_\\-]+(\\.[_a-z0-9\\-]+)*@([_a-z0-9\\-]+\\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)$");
	}

	/** ����ַ����Ƿ���� IP ��ַ��ʽ */
	public final static boolean isStrIPAddress(String str)
	{
		return str.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
	}

	/** ����ַ����Ƿ���� HTML ������Ԫ�ظ�ʽ */
	public final static boolean isStrLink(String str)
	{
		return str.matches("<a[^>]*href=\\\"[^\\s\\\"]+\\\"[^>]*>[^<]*<\\/a>");
	}

	/** ����ַ����Ƿ���� URL ��ʽ */
	public final static boolean isStrURL(String str)
	{
		return str.matches("^((https?|ftp|news):\\/\\/)?([a-z]([a-z0-9\\-]*\\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)|(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))(\\/[a-z0-9_\\-\\.~]+)*(\\/([a-z0-9_\\-\\.]*)(\\?[a-z0-9+_\\-\\.%=&amp;]*)?)?(#[a-z][a-z0-9_]*)?$");
	}
	
	/** ����������ʽ��ת���ַ����������� ignores �����а������ַ��� */
	public static final String escapeRegexChars(String str, char ... ignores)
	{
		final char ESCAPE_CHAR	 = '\\';
		final char[] REGEX_CHARS = {'.', ',', '?', '+', '-', '*', '^', '$', '|', '&', '{', '}', '[', ']', '(', ')', '\\'};
		
		char[] regex_chars = REGEX_CHARS;
		
		if(ignores.length > 0)
		{
			Set<Character> cs = new HashSet<Character>(REGEX_CHARS.length);
			
			for(int i = 0; i < REGEX_CHARS.length; i++)
				cs.add(REGEX_CHARS[i]);
			for(int i = 0; i < ignores.length; i++)
				cs.remove(ignores[i]);
				
			int i		= 0;
			regex_chars = new char[cs.size()];
			Iterator<Character> it = cs.iterator();
			
			while(it.hasNext())
				regex_chars[i++] = it.next();				
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			
			for(int j = 0; j < regex_chars.length; j++)
			{
				if(c == regex_chars[j])
				{
					sb.append(ESCAPE_CHAR);
					break;
				}
			}
			
			sb.append(c);
		}
		
		return sb.toString();
	}

	/** ���ָ��ַ������ָ����" \t\n\r\f,;"�� */
	public final static String[] splitStr(String str)
	{
		return splitStr(str, " \t\n\r\f,;");
	}
	
	/** ���ָ��ַ������ָ������ delim ����ָ���� */
	public final static String[] splitStr(String str, String delim)
	{
		StringTokenizer st	= new StringTokenizer(str, delim);
		String[] array		= new String[st.countTokens()];
		
		int i = 0;
		while(st.hasMoreTokens())
			array[i++] = st.nextToken();
		
		return array;
	}

	/** ��ǰ�̵߳��� sleep() ������ͣ period ���� */
	public final static void waitFor(long period)
	{
		if(period > 0)
		{
			try
			{
				Thread.sleep(period);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
			Thread.yield();
	}

	/** ��ǰ�̵߳��� sleep() ������ͣ period �� unit ʱ�䵥Ԫ */
	public final static void waitFor(long period, TimeUnit unit)
	{
		if(period > 0)
		{
			try
			{
				unit.sleep(period);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
			Thread.yield();
	}

	/** String -> Integer�����ת�����ɹ��򷵻� null */
	public final static Integer str2Int(String s)
	{
		Integer returnVal;
		try {
			returnVal = Integer.decode(safeTrimString(s));
		} catch(Exception e) {
			returnVal = null;
		}
		return returnVal;
	}

	/** String -> int�����ת�����ɹ��򷵻�Ĭ��ֵ d */
	public final static int str2Int(String s, int d)
	{
		int returnVal;
		try {
			returnVal = Integer.parseInt(safeTrimString(s));
		} catch(Exception e) {
			returnVal = d;
		}
		return returnVal;
	}

	/** String -> int�����ת�����ɹ��򷵻� 0 */
	public final static int str2Int_0(String s)
	{
		return str2Int(s, 0);
	}

	/** String -> Short�����ת�����ɹ��򷵻� null */
	public final static Short str2Short(String s)
	{
		Short returnVal;
		try {
			returnVal = Short.decode(safeTrimString(s));
		} catch(Exception e) {
			returnVal = null;
		}
		return returnVal;
	}

	/** String -> short�����ת�����ɹ��򷵻�Ĭ��ֵ d */
	public final static short str2Short(String s, short d)
	{
		short returnVal;
		try {
			returnVal = Short.parseShort(safeTrimString(s));
		} catch(Exception e) {
			returnVal = d;
		}
		return returnVal;
	}

	/** String -> short�����ת�����ɹ��򷵻� 0 */
	public final static short str2Short_0(String s)
	{
		return str2Short(s, (short)0);
	}

	/** String -> Long�����ת�����ɹ��򷵻� null */
	public final static Long str2Long(String s)
	{
		Long returnVal;
		try {
			returnVal = Long.decode(safeTrimString(s));
		} catch(Exception e) {
			returnVal = null;
		}
		return returnVal;
	}

	/** String -> long�����ת�����ɹ��򷵻�Ĭ��ֵ d */
	public final static long str2Long(String s, long d)
	{
		long returnVal;
		try {
			returnVal = Long.parseLong(safeTrimString(s));
		} catch(Exception e) {
			returnVal = d;
		}
		return returnVal;
	}

	/** String -> long�����ת�����ɹ��򷵻� 0 */
	public final static long str2Long_0(String s)
	{
		return str2Long(s, 0L);
	}

	/** String -> Float�����ת�����ɹ��򷵻� null */
	public final static Float str2Float(String s)
	{
		Float returnVal;
		try {
			returnVal = Float.valueOf(safeTrimString(s));
		} catch(Exception e) {
			returnVal = null;
		}
		return returnVal;
	}

	/** String -> float�����ת�����ɹ��򷵻�Ĭ��ֵ d */
	public final static float str2Float(String s, float d)
	{
		float returnVal;
		try {
			returnVal = Float.parseFloat(safeTrimString(s));
		} catch(Exception e) {
			returnVal = d;
		}
		return returnVal;
	}

	/** String -> float�����ת�����ɹ��򷵻� 0 */
	public final static float str2Float_0(String s)
	{
		return str2Float(s, 0F);
	}

	/** String -> Double�����ת�����ɹ��򷵻� null */
	public final static Double str2Double(String s)
	{
		Double returnVal;
		try {
			returnVal = Double.valueOf(safeTrimString(s));
		} catch(Exception e) {
			returnVal = null;
		}
		return returnVal;
	}

	/** String -> double�����ת�����ɹ��򷵻�Ĭ��ֵ d */
	public final static double str2Double(String s, double d)
	{
		double returnVal;
		try {
			returnVal = Double.parseDouble(safeTrimString(s));
		} catch(Exception e) {
			returnVal = d;
		}
		return returnVal;
	}

	/** String -> double�����ת�����ɹ��򷵻� 0.0 */
	public final static double str2Double_0(String s)
	{
		return str2Double(s, 0D);
	}

	/** String -> Byte�����ת�����ɹ��򷵻� null */
	public final static Byte str2Byte(String s)
	{
		Byte returnVal;
		try {
			returnVal = Byte.decode(safeTrimString(s));
		} catch(Exception e) {
			returnVal = null;
		}
		return returnVal;
	}

	/** String -> byte�����ת�����ɹ��򷵻�Ĭ��ֵ d */
	public final static byte str2Byte(String s, byte d)
	{
		byte returnVal;
		try {
			returnVal = Byte.parseByte(safeTrimString(s));
		} catch(Exception e) {
			returnVal = d;
		}
		return returnVal;
	}

	/** String -> byte�����ת�����ɹ��򷵻� 0 */
	public final static byte str2Byte_0(String s)
	{
		return str2Byte(s, (byte)0);
	}

	/** String -> Character�����ת�����ɹ��򷵻� null */
	public final static Character str2Char(String s)
	{
		Character returnVal;
		try {
			returnVal = safeTrimString(s).charAt(0);
		} catch(Exception e) {
			returnVal = null;
		}
		return returnVal;
	}

	/** String -> char�����ת�����ɹ��򷵻�Ĭ��ֵ d */
	public final static char str2Char(String s, char d)
	{
		char returnVal;
		try {
			returnVal = safeTrimString(s).charAt(0);
		} catch(Exception e) {
			returnVal = d;
		}
		return returnVal;
	}

	/** String -> char�����ת�����ɹ��򷵻� 0 */
	public final static char str2Char_0(String s)
	{
		return str2Char(s, Character.MIN_VALUE);
	}

	/** String -> Boolean�����ת�����ɹ��򷵻� null */
	public final static Boolean str2Boolean(String s)
	{
		return Boolean.valueOf(safeTrimString(s));
	}

	/** String -> boolean�����ת�����ɹ��򷵻�Ĭ��ֵ d */
	public final static boolean str2Boolean(String s, boolean d)
	{
		s = safeTrimString(s);
		
		if(s.equalsIgnoreCase("true"))
			return true;
		else if(s.equalsIgnoreCase("false"))
			return false;
		
		return d;
	}

	/** String -> boolean�����ת�����ɹ��򷵻� 0 */
	public final static boolean str2Boolean_False(String s)
	{
		return str2Boolean(s, false);
	}

	/** String -> java.util.Date�� str �ĸ�ʽ�� format  ���� */
	public final static Date str2Date(String str, String format)
	{
		Date date = null;
		
		try
		{
			DateFormat df = new SimpleDateFormat(format);
			date = df.parse(safeTrimString(str));
		}
		catch(Exception e)
		{

		}

		return date;
	}

	/** String -> java.util.Date���ɺ��������ж� str �ĸ�ʽ */
	public final static Date str2Date(String str)
	{
		Date date = null;

		try
		{
			final char SEPARATOR	= '-';
			final String[] PATTERN	= {"yyyy", "MM", "dd", "HH", "mm", "ss", "SSS"};
			String[] values			= safeTrimString(str).split("\\D");
			String[] element		= new String[values.length];
			
			int length = 0;
			for(String e : values)
			{
				e = e.trim();
				if(e.length() != 0)
				{
					element[length++] = e;
					if(length == PATTERN.length)
						break;
				}
			}

			if(length > 0)
			{
	       		StringBuilder value	= new StringBuilder();

				if(length > 1)
				{
        			for(int i = 0; i < length; ++i)
        			{
        				value.append(element[i]);
        				value.append(SEPARATOR);
        			}
				}
				else
				{
					String src	= element[0];
					int remain	= src.length();
					int pos		= 0;
					int i		= 0;

					for(i = 0; remain > 0 && i < PATTERN.length; ++i)
					{
						int p_length	= PATTERN[i].length();
						int v_length	= Math.min(p_length, remain);
						String v 		= src.substring(pos, pos + v_length);
						pos			+= v_length;
						remain 		-= v_length;

						value.append(v);
						value.append(SEPARATOR);
					}

					length = i;
				}

     			StringBuilder format = new StringBuilder();

     			for(int i = 0; i < length; ++i)
				{
					format.append(PATTERN[i]);
					format.append(SEPARATOR);
				}

				date = str2Date(value.toString(), format.toString());
			}
		}
		catch(Exception e)
		{

		}

		return date;
	}
	
	/** String -> java.util.Date���� Patterns ָ�����ܵ����ڸ�ʽ */
	public final static Date str2Date(String str, String[] Patterns)
	{
		Date date = null;
		
		for(int i = 0; i < Patterns.length; ++i)
		{
			date = str2Date(str, Patterns[i]);

			if( date != null)
				break;
		}

		return date;
	}
	
	/** String -> java.util.Date���� GeneralHelper.SHORT_DATE_PATTERN ָ�����ܵ����ڸ�ʽ */
	public final static Date str2ShortDate(String str)
	{
		return str2Date(str, SHORT_DATE_PATTERN);
	}

	/** String -> java.util.Date���� GeneralHelper.LONG_DATE_PATTERN ָ�����ܵ����ڸ�ʽ */
	public final static Date str2LongDate(String str)
	{
		return str2Date(str, LONG_DATE_PATTERN);
	}

	/** String -> java.util.Date���� GeneralHelper.LONG_DATE_PATTERN_WITH_MILSEC ָ�����ܵ����ڸ�ʽ */
	public final static Date str2LongDateWithMilliSecond(String str)
	{
		return str2Date(str, LONG_DATE_PATTERN_WITH_MILSEC);
	}
	
	/** ����ת���������ӿ� */
	public static interface TypeHandler<T>
	{
		T handle(String v);
	}
	
	/** String -> Any���ַ���ת��Ϊ 8 �ֻ����������͡������װ�� {@link Date}�� �� {@link String} 
	 * 
	 * @param type	: Ŀ�����͵� {@link Class} ����
	 * @param v		: Ҫת�����ַ���
	 * @return		: ת����������ת�����ɹ����� null
	 * @throws 		: ���Ŀ�����Ͳ�֧���׳� {@link IllegalArgumentException}
	 * 
	 */
	public static final <T> T str2Object(Class<T> type, String v)
	{
		return str2Object(type, v, null);
	}
	
	/** String -> Any����� handler Ϊ null ����ַ���ת��Ϊ 8 �ֻ����������͡������װ�ࡢ {@link Date} �� {@link String}��
	 * 			      ��� handler ��Ϊ null ���� handler ִ��ת�� 
	 * 
	 * @param type	: Ŀ�����͵� {@link Class} ����
	 * @param v		: Ҫת�����ַ���
	 * @param handler	: ����ת��������
	 * @return		: ת����������ת�����ɹ����� null
	 * @throws 		: ���Ŀ�����Ͳ�֧���׳� {@link IllegalArgumentException}
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T str2Object(Class<T> type, String v, TypeHandler<T> handler)
	{
		Object param = null;
		
		if(handler != null)
			return handler.handle(v);
		
		if(type == String.class)
			param =  safeTrimString(v);
		else if(type == int.class)
			param =  str2Int_0(v);
		else if(type == long.class)
			param =  str2Long_0(v);
		else if(type == byte.class)
			param =  str2Byte_0(v);
		else if(type == char.class)
			param =  str2Char_0(v);
		else if(type == float.class)
			param =  str2Float_0(v);
		else if(type == double.class)
			param =  str2Double_0(v);
		else if(type == short.class)
			param =  str2Short_0(v);
		else if(type == boolean.class)
			param =  str2Boolean_False(v);
		else if(type == Integer.class)
			param =  str2Int(v);
		else if(type == Long.class)
			param =  str2Long(v);
		else if(type == Byte.class)
			param =  str2Byte(v);
		else if(type == Character.class)
			param =  str2Char(v);
		else if(type == Float.class)
			param =  str2Float(v);
		else if(type == Double.class)
			param =  str2Double(v);
		else if(type == Short.class)
			param =  str2Short(v);
		else if(type == Boolean.class)
			param =  str2Boolean(v);
		else if(Date.class.isAssignableFrom(type))
			param =  str2Date(v);
		else
			throw new IllegalArgumentException(String.format("object type '%s' not valid", type));
		
		return (T)param;
	}

	/** Any -> Object[] <br>
	 * 
	 *  obj == null					: ���� Object[] {null} <br>
	 *  obj Ϊ��������				: ǿ��ת��Ϊ Object[], ���������� <br>
	 *  obj Ϊ������������			: ���� Object[], ��Ԫ������Ϊ�������͵İ�װ�� <br>
	 *  obj Ϊ {@link Collection}	: ͨ�� toArray() �������� Object[] <br>
	 *  obj Ϊ {@link Iterable}		: ���� {@link Iterable}, �����ذ���������Ԫ�ص� Object[] <br>
	 *  obj Ϊ {@link Iterator}		: ���� {@link Iterator}, �����ذ���������Ԫ�ص� Object[] <br>
	 *  obj Ϊ {@link Enumeration}	: ���� {@link Enumeration}, �����ذ���������Ԫ�ص� Object[] <br>
	 *  obj Ϊ��ͨ����				: ���� Object[] {obj} <br>
	 * 
	 * @param obj	: �κζ���
	 * 
	 */
	public static final Object[] object2Array(Object obj)
	{
		Object[] array;
		
		if(obj == null)
			array = new Object[] {obj};
		else if(obj.getClass().isArray())
		{
			Class<?> clazz = obj.getClass().getComponentType();
			
			if(Object.class.isAssignableFrom(clazz))
				array = (Object[])obj;
			else
			{
				int length = Array.getLength(obj);
				
				if(length > 0)
				{
					array = new Object[length];
					
					for(int i = 0; i < length; i++)
						array[i] = Array.get(obj, i);
				}
				else
					array = new Object[0];
			}
		}
		else if(obj instanceof Collection<?>)
			array = ((Collection<?>)obj).toArray();
		else if(obj instanceof Iterable<?>)
		{
			List<Object> list = new ArrayList<Object>();
			Iterator<?> it = ((Iterable<?>)obj).iterator();
			
			while(it.hasNext())
				list.add(it.next());
			
			array = list.toArray();
		}
		else if(obj instanceof Iterator)
		{
			List<Object> list = new ArrayList<Object>();
			Iterator<?> it = (Iterator<?>)obj;
			
			while(it.hasNext())
				list.add(it.next());
			
			array = list.toArray();
		}
		else if(obj instanceof Enumeration<?>)
		{
			List<Object> list = new ArrayList<Object>();
			Enumeration<?> it = (Enumeration<?>)obj;
			
			while(it.hasMoreElements())
				list.add(it.nextElement());
			
			array = list.toArray();
		}
		else
			array = new Object[] {obj};
		
		return array;
	}

	/** ���� date ���� value �������ڣ����ʱ����Ϣ�� */
	public final static Date addDate(Date date, int value)
	{
		return addDate(date, value, true);
	}

	/** ���� date ���� value �������ڣ�trimTime ָ���Ƿ����ʱ����Ϣ */
	public final static Date addDate(Date date, int value, boolean trimTime)
	{
		return addTime(date, Calendar.DATE, value, trimTime);

	}

	/** ���� date ���� value �� field ʱ�䵥Ԫ������ڣ������ʱ����Ϣ�� */
	public final static Date addTime(Date date, int field, int value)
	{
		return addTime(date, field, value, false);
	}

	/** ���� date ���� value �� field ʱ�䵥Ԫ������ڣ�trimTime ָ���Ƿ�ȥ��ʱ����Ϣ */
	public final static Date addTime(Date date, int field, int value, boolean trimTime)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, value);

		if(trimTime)
		{
        		c.set(Calendar.HOUR, 0);
        		c.set(Calendar.MINUTE, 0);
        		c.set(Calendar.SECOND, 0);
        		c.set(Calendar.MILLISECOND, 0);
		}

		return c.getTime();

	}

	/** java.util.Date -> String��str �ĸ�ʽ�� format  ���� */
	public final static String date2Str(Date date, String format)
	{
		DateFormat df	= new SimpleDateFormat(format);
		return df.format(date);
	}

	/** ���� SQL ����ַ�����' -> ''����includeWidlcard ָ���Ƿ���Ǻź��ʺ���ת����* -> %, ? -> _�� */
	public static final String regularSQLStr(String str, boolean includeWidlcard)
	{
		str = str.replace("'", "''");

		if(includeWidlcard)
		{
			str = str.replace('*', '%');
			str = str.replace('?', '_');
		}

		return str;
	}
	
	/** ��ȡ clazz �� {@link ClassLoader} �������Ϊ null �򷵻ص�ǰ�̵߳� Context {@link ClassLoader} */
	public static final ClassLoader getClassLoader(Class<?> clazz)
	{
		ClassLoader loader = clazz.getClassLoader();
		
		if(loader == null)
			loader = Thread.currentThread().getContextClassLoader();
		
		return loader;
	}
	
	/** ��������Ϊ  className �� {@link Class} �����������ʧ���򷵻� null */
	public static final Class<?> loadClass(String className)
	{
		Class<?> clazz		= null;
		ClassLoader loader	= getClassLoader(GeneralHelper.class);
		
		try
		{
			clazz = loader.loadClass(className);
		}
		catch(ClassNotFoundException e)
		{
			
		}
		
		return clazz;
	}

	/** �� {@linkplain Class#forName(String)} ���� {@link Class} �����������ʧ���򷵻� null */
	public static final Class<?> classForName(String name)
	{
		Class<?> clazz = null;
		
		try
		{
			clazz = Class.forName(name);
		}
		catch(ClassNotFoundException e)
		{
			
		}
		
		return clazz;
	}

	/** �� {@linkplain Class#forName(String, boolean, ClassLoader)} ���� {@link Class} �����������ʧ���򷵻� null */
	public static final Class<?> classForName(String name, boolean initialize, ClassLoader loader)
	{
		Class<?> clazz = null;
		
		try
		{
			clazz = Class.forName(name, initialize, loader);
		}
		catch(ClassNotFoundException e)
		{
			
		}
		
		return clazz;
	}

	/** ��ȡ clazz ��Դ������ resPath ���·���� URL ���� */
	public static final URL getClassResource(Class<?> clazz, String resPath)
	{
		URL url = clazz.getResource(resPath);
		
		if(url == null)
		{
			ClassLoader loader = clazz.getClassLoader();
			if(loader != null) url = loader.getResource(resPath);
			
			if(url == null)
			{
				loader = Thread.currentThread().getContextClassLoader();
				if(loader != null) url = loader.getResource(resPath);
			}
		}

		return url;
	}

	/** ��ȡ clazz ��Դ������ resPath ���·���� URL �����б� */
	public static final List<URL> getClassResources(Class<?> clazz, String resPath)
	{
		List<URL> urlList		= new ArrayList<URL>();
		Enumeration<URL> urls	= null;
		
		try
		{
    		ClassLoader loader = clazz.getClassLoader();
    		if(loader != null) urls = loader.getResources(resPath);
    		
    		if(urls == null || !urls.hasMoreElements())
    		{
    			loader = Thread.currentThread().getContextClassLoader();
    			if(loader != null) urls = loader.getResources(resPath);
    		}
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
		if(urls != null)
		{
			while(urls.hasMoreElements())
				urlList.add(urls.nextElement());
		}

		return urlList;
	}

	/** ��ȡ clazz ��Դ������ resPath �� {@link InputStream} */
	public static final InputStream getClassResourceAsStream(Class<?> clazz, String resPath)
	{
		InputStream is = clazz.getResourceAsStream(resPath);
		
		if(is == null)
		{
			ClassLoader loader = clazz.getClassLoader();
			if(loader != null) is = loader.getResourceAsStream(resPath);
			
			if(is == null)
			{
				loader = Thread.currentThread().getContextClassLoader();
				if(loader != null) is = loader.getResourceAsStream(resPath);
			}
		}

		return is;
	}

	/** ��ȡ clazz ��Դ������ resPath ���·���� URL ����·���������ľ���·���� UTF-8 ���룩 */
	public static final String getClassResourcePath(Class<?> clazz, String resPath)
	{
		return getClassResourcePath(clazz, resPath, DEFAULT_ENCODING);
	}

	/** ��ȡ clazz ��Դ������ resPath ���·���� URL ����·���������ľ���·���� pathEnc ���룩 */
	public static final String getClassResourcePath(Class<?> clazz, String resPath, String pathEnc)
	{
		String path = null;

		try
		{
			URL url = getClassResource(clazz, resPath);
			
			if(url != null)
			{
				path = url.getPath();
				path = URLDecoder.decode(path, pathEnc);
			}
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}

		return path;
	}

	/** ��ȡ clazz ��Դ������ resPath ���·���� URL ����·���б������ľ���·���� UTF-8 ���룩 */
	public static final List<String> getClassResourcePaths(Class<?> clazz, String resPath)
	{
		return getClassResourcePaths(clazz, resPath, DEFAULT_ENCODING);
	}

	/** ��ȡ clazz ��Դ������ resPath ���·���� URL ����·���б������ľ���·���� pathEnc ���룩 */
	public static final List<String> getClassResourcePaths(Class<?> clazz, String resPath, String pathEnc)
	{
		List<String> pathList = new ArrayList<String>();

		try
		{
			List<URL> urlList = getClassResources(clazz, resPath);
			
			for(URL url : urlList)
			{
				String path = URLDecoder.decode(url.getPath(), pathEnc);
				pathList.add(path);
			}
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}

		return pathList;
	}

	/** ��ȡ clazz ��Դ�����ĵ�ǰ URL ����·�������صľ���·���� pathEnc ���룩 */
	public static final String getClassPath(Class<?> clazz)
	{
		return getClassResourcePath(clazz, ".");
	}

	/** ��ȡ resource ��Դ�� locale ���ػ��ļ�������Ϊ key ���ַ�����Դ�������� params ���� */
	public static final String getResourceMessage(Locale locale, String resource, String key, Object ... params)
	{
		ResourceBundle bundle = ResourceBundle.getBundle(resource, locale);
		String msg = bundle.getString(key);

		if(params != null && params.length > 0)
			msg = MessageFormat.format(msg, params);

		return msg;
	}

	/** ��ȡ resource ��Դ��Ĭ�ϱ��ػ��ļ�������Ϊ key ���ַ�����Դ�������� params ���� */
	public static final String getResourceMessage(String resource, String key, Object ... params)
	{
		return getResourceMessage(Locale.getDefault(), resource, key, params);
	}

	/** ��ȡ e �쳣�Ķ�ջ�б�����Ķ�ջ������ levels ָ�� */
	public static final List<String> getExceptionMessageStack(Throwable e, int levels)
	{
		List<String> list = new ArrayList<String>();

		if(levels == 0)
			levels = Integer.MAX_VALUE;

		for(int i = 0; i < levels; ++i)
		{
			StringBuilder sb = new StringBuilder();

			if(i > 0)
				sb.append("Caused by -> ");

			sb.append(e.getClass().getName());
			String msg = e.getLocalizedMessage();
			if(msg != null)
				sb.append(": ").append(msg);

			list.add(sb.toString());

			e = e.getCause();
			if(e == null)
				break;
		}

		return list;
	}

	/** ��ȡ e �쳣��������ջ�б� */
	public static final List<String> getExceptionMessageStack(Throwable e)
	{
		return getExceptionMessageStack(e, 0);
	}

	/** ��� e �쳣�� levels ���ջ�б� ps �� */
	public static final void printExceptionMessageStack(Throwable e, int levels, PrintStream ps)
	{
		List<String> list = getExceptionMessageStack(e, levels);

		for(String msg : list)
			ps.println(msg);
	}

	/** ��� e �쳣�� levels ���ջ�б���׼�������� */
	public static final void printExceptionMessageStack(Throwable e, int levels)
	{
		printExceptionMessageStack(e, levels, System.err);
	}

	/** ��� e �쳣��������ջ�б� ps �� */
	public static final void printExceptionMessageStack(Throwable e, PrintStream ps)
	{
		printExceptionMessageStack(e, 0, ps);
	}

	/** ��� e �쳣��������ջ�б���׼�������� */
	public static final void printExceptionMessageStack(Throwable e)
	{
		printExceptionMessageStack(e, 0);
	}
	
	/** ��Ԫ����ӵ� {@link Map} �У�����֤�̰߳�ȫ�����滻ԭֵ�� */
	public static final <K, V> boolean tryPut(Map<K, V> map, K key, V value)
	{
		return tryPut(map, key, value, false);
	}
	
	/** ��Ԫ����ӵ� {@link Map} �У�����֤�̰߳�ȫ */
	public static final <K, V> boolean tryPut(Map<K, V> map, K key, V value, boolean replace)
	{
		if(replace || !map.containsKey(key))
		{
			map.put(key, value);
			return true;
		}
		
		return false;
	}

	/** ��Ԫ����ӵ� {@link Map} �У�����֤�̰߳�ȫ�����滻ԭֵ�� */
	public static final <K, V> boolean syncTryPut(Map<K, V> map, K key, V value)
	{
		return syncTryPut(map, key, value, false);
	}
	
	/** ��Ԫ����ӵ� {@link Map} �У�����֤�̰߳�ȫ */
	public static final <K, V> boolean syncTryPut(Map<K, V> map, K key, V value, boolean replace)
	{
		synchronized(map)
		{
			return tryPut(map, key, value, replace);
		}
	}

	/** ��Ԫ����ӵ� {@link Map} �У�����֤�̰߳�ȫ�����滻ԭֵ�� */
	public static final <K, V> int tryPutAll(Map<K, V> map, Map<K, V> src)
	{
		return tryPutAll(map, src, false);
	}
	
	/** ��Ԫ����ӵ� {@link Map} �У�����֤�̰߳�ȫ */
	public static final <K, V> int tryPutAll(Map<K, V> map, Map<K, V> src, boolean replace)
	{
		if(replace)
		{
			map.putAll(src);
			return src.size();
		}
		
		int count = 0;
		Set<Map.Entry<K, V>> entries = src.entrySet();
		
		for(Map.Entry<K, V> e : entries)
		{
			if(!map.containsKey(e.getKey()))
			{
				map.put(e.getKey(), e.getValue());
				++count;
			}
		}
		
		return count;
	}

	/** ��Ԫ����ӵ� {@link Map} �У�����֤�̰߳�ȫ�����滻ԭֵ�� */
	public static final <K, V> int syncTryPutAll(Map<K, V> map, Map<K, V> src)
	{
		return syncTryPutAll(map, src, false);
	}
	
	/** ��Ԫ����ӵ� {@link Map} �У�����֤�̰߳�ȫ */
	public static final <K, V> int syncTryPutAll(Map<K, V> map, Map<K, V> src, boolean replace)
	{
		synchronized(map)
		{
			return tryPutAll(map, src, replace);
		}
	}

	/** �� {@link Map} ��ɾ��Ԫ�أ�����֤�̰߳�ȫ */
	public static final <K, V> boolean tryRemove(Map<K, V> map, K key)
	{
		if(map.containsKey(key))
		{
			map.remove(key);
			return true;
		}
		
		return false;
	}

	/** �� {@link Map} ��ɾ��Ԫ�أ�����֤�̰߳�ȫ */
	public static final <K, V> boolean syncTryRemove(Map<K, V> map, K key)
	{
		synchronized(map)
		{
			return tryRemove(map, key);
		}
	}

	/** ��� {@link Map}������֤�̰߳�ȫ */
	public static final <K, V> void tryClear(Map<K, V> map)
	{
		map.clear();
	}

	/** ��� {@link Map}������֤�̰߳�ȫ */
	public static final <K, V> void syncTryClear(Map<K, V> map)
	{
		synchronized(map)
		{
			tryClear(map);
		}
	}

	/** ��ȡ��ǰ JVM ���̵� ID */
	public static final int getProcessId()
	{
		return Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
	}

	/** ��ȡ��ǰ JVM ���̵� Java �汾 */
	public static final String getJavaVersion()
	{
		return System.getProperty("java.version");
	}

	/** ��ȡ��ǰ����ϵͳ������ */
	public static final String getOSName()
	{
		return System.getProperty("os.name");
	}

	/** ��鵱ǰ����ϵͳ�Ƿ�Ϊ Windows ϵ�� */
	public static final boolean isWindowsPlatform()
	{
		// return CURRENT_OS.toUpperCase().indexOf("WINDOWS") != -1;
		
		return File.pathSeparatorChar == ';';
	}

	/** ��ƴ��������ַ����Ƚ��� */
	public static class PinYinComparator implements Comparator<String>
	{
		public int compare(String o1, String o2)
		{
			java.text.Collator cmp = java.text.Collator.getInstance(java.util.Locale.CHINA);
			return cmp.compare(o1, o2);
		}
	}

	/** ���ļ����ƽ����ļ�ɸѡ���ļ������������캯������ name ָ���ļ�����������ʽ */
	public static class FileNameFileFilter implements FileFilter
	{
		protected static final int FLAGS = IS_WINDOWS_PLATFORM ? Pattern.CASE_INSENSITIVE : 0;

		Pattern pattern;

		public FileNameFileFilter(String name)
		{
			String exp = name;
			exp = exp.replace('.', '#');
			exp = exp.replaceAll("#", "\\\\.");
			exp = exp.replace('*', '#');
			exp = exp.replaceAll("#", ".*");
			exp = exp.replace('?', '#');
			exp = exp.replaceAll("#", ".?");
			exp = "^" + exp + "$";

			pattern = Pattern.compile(exp, FLAGS);
		}

		public boolean accept(File file)
		{
			Matcher matcher = pattern.matcher(file.getName());
			return matcher.matches();
		}
	}
}
