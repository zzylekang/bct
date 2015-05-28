package com.zzy.dev.comm.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * HTTP ������
 *
 */
public class HttpHelper
{
	
	/** URL ��ַ�ָ��� */
	public static final String URL_PATH_SEPARATOR	= "/";
	/** HTTP URL ��ʶ */
	public static final String HTTP_SCHEMA			= "http";
	/** HTTPS URL ��ʶ */
	public static final String HTTPS_SCHEMA			= "https";
	/** HTTP Ĭ�϶˿� */
	public static final int HTTP_DEFAULT_PORT		= 80;
	/** HTTPS Ĭ�϶˿� */
	public static final int HTTPS_DEFAULT_PORT		= 443;
	/** Ĭ�ϻ�������С */
	private static final int DEFAULT_BUFFER_SIZE	= 4096;
	
	private static ServletContext servletContext;
	
	/** ��ȡ {@link ServletContext} */
	public static ServletContext getServletContext()
	{
		return servletContext;
	}

	/** ��ʼ��  {@link HttpHelper} ��  {@link ServletContext} ��ֻ�ܳ�ʼ��һ�Σ�ͨ����Ӧ�ó�������ʱִ�У�
	 * 
	 *  @param servletContext			: ȫ�� {@link ServletContext} ����
	 *  @throws IllegalStateException	: �ظ���ʼ��ʱ�׳�
	 *  @throws IllegalArgumentException: servletContext ����Ϊ null ʱ�׳�
	 * 
	 */
	synchronized public final static void initializeServletContext(ServletContext servletContext)
	{
		if(HttpHelper.servletContext != null)
			throw new IllegalStateException("'HttpHelper.servletContext' had been initialized before");
		else if(servletContext == null)
			throw new IllegalArgumentException("param 'servletContext' is null (not valid)");
		
		HttpHelper.servletContext = servletContext;
	}

	/** �ͷ�  {@link HttpHelper} �� {@link ServletContext} ��ͨ����Ӧ�ó���ر�ʱִ�У� */
	synchronized public final static void unInitializeServletContext()
	{
		HttpHelper.servletContext = null;
	}

	/** ��ȡ {@link HttpURLConnection} */
	public final static HttpURLConnection getHttpConnection(String url, KV<String, String> ... properties) throws IOException
	{
		return getHttpConnection(url, null, properties);
	}

	/** ��ȡ {@link HttpURLConnection} */
	public final static HttpURLConnection getHttpConnection(String url, String method, KV<String, String> ... properties) throws IOException
	{
		URL connUrl				= new URL(url);
		HttpURLConnection conn	= (HttpURLConnection)connUrl.openConnection();
		
		if(GeneralHelper.isStrNotEmpty(method))
			conn.setRequestMethod(method);
		
		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		for(KV<String, String> kv : properties)
			conn.setRequestProperty(kv.getKey(), kv.getValue());

		return conn;
	}

	/** ��ҳ������ı����� */
	public final static void writeString(HttpURLConnection conn, String content, String charsetName) throws IOException
	{
		writeString(conn.getOutputStream(), content, charsetName);
	}

	/** ��ҳ������ı����� */
	public final static void writeString(HttpServletResponse res, String content, String charsetName) throws IOException
	{
		writeString(res.getOutputStream(), content, charsetName);
	}

	/** ��ҳ������ı����� */
	public final static void writeString(OutputStream os, String content, String charsetName) throws IOException
	{
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, charsetName));
		
		pw.write(content);
		pw.flush();
		pw.close();
	}

	/** ��ҳ������ֽ����� */
	public final static void writeBytes(HttpURLConnection conn, byte[] content) throws IOException
	{
		writeBytes(conn.getOutputStream(), content);
	}

	/** ��ҳ������ֽ����� */
	public final static void writeBytes(HttpServletResponse res, byte[] content) throws IOException
	{
		writeBytes(res.getOutputStream(), content);
	}

	/** ��ҳ������ֽ����� */
	public final static void writeBytes(OutputStream os, byte[] content) throws IOException
	{
		BufferedOutputStream bos = new BufferedOutputStream(os);
		
		bos.write(content);
		bos.flush();
		bos.close();
	}

	/** ��ȡҳ��������ı����� */
	public final static String readString(HttpURLConnection conn, boolean escapeReturnChar, String charsetName) throws IOException
	{
		return readString(conn.getInputStream(), escapeReturnChar, charsetName);
	}

	/** ��ȡҳ��������ֽ����� */
	public final static String readString(HttpServletRequest request, boolean escapeReturnChar, String charsetName) throws IOException
	{
		return readString(request.getInputStream(), escapeReturnChar, charsetName);
	}

	/** ��ȡҳ��������ı����� */
	public final static String readString(InputStream is, boolean escapeReturnChar, String charsetName) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, charsetName));
		
		try
		{
    		if(escapeReturnChar)
    		{
        		for(String line = null; (line = rd.readLine()) != null;)
        			sb.append(line);
     		}
    		else
    		{
    			int count		= 0;
    			char[] array	= new char[DEFAULT_BUFFER_SIZE];
    			
    			while((count = rd.read(array)) != -1)
    				sb.append(array, 0, count);
    		}
		}
		finally
		{
			rd.close();
		}
		
		return sb.toString();
	}
	
	/** ��ȡҳ��������ֽ����� */
	public final static byte[] readBytes(HttpURLConnection conn) throws IOException
	{
		return readBytes(conn.getInputStream(), conn.getContentLength());
	}

	/** ��ȡҳ��������ֽ����� */
	public final static byte[] readBytes(HttpServletRequest request) throws IOException
	{
		return readBytes(request.getInputStream(), request.getContentLength());
	}
	
	/** ��ȡҳ��������ֽ����� */
	public final static byte[] readBytes(InputStream is) throws IOException
	{
		return readBytes(is, 0);
	}

	/** ��ȡҳ��������ֽ����� */
	public final static byte[] readBytes(InputStream is, int length) throws IOException
	{
		byte[] array = null;
		
		if(length > 0)
		{
			array = new byte[length];
		
			int read	= 0;
			int total	= 0;
			
			while((read = is.read(array, total, array.length - total)) != -1)
				total += read;
		}
		else
		{
			List<byte[]> list	= new LinkedList<byte[]>();
			byte[] buffer		= new byte[DEFAULT_BUFFER_SIZE];

			int read	= 0;
			int total	= 0;
			
			for(; (read = is.read(buffer)) != -1; total += read)
			{
				byte[] e = new byte[read];
				System.arraycopy(buffer, 0, e, 0, read);
				list.add(e);
			}
			
			array = new byte[total];
			
			int write = 0;
			for(byte[] e : list)
			{
				System.arraycopy(e, 0, array, write, e.length);
				write += e.length;
			}
		}
		
		return array;
	}
	
	/** ���ݵ�ַ�Ͳ������� URL */
	public final static String makeURL(String srcURL, KV<String, String> ... params)
	{
		return makeURL(srcURL, null, params);
	}

	/** ���ݵ�ַ�Ͳ������� URL������ָ���ַ����Ե�ַ���б��� */
	public final static String makeURL(String srcURL, String charset, KV<String, String> ... params)
	{
		StringBuilder sbURL = new StringBuilder(srcURL);
		
		char token = '&';
		char firstToken = srcURL.indexOf('?') != -1 ? token : '?';
		
		for(int i = 0; i < params.length; i++)
		{
			KV<String, String> kv = params[i];
			String key = kv.getKey();
			String val = kv.getValue();
			
			if(i > 0)
				sbURL.append(token);
			else
				sbURL.append(firstToken);
			
			sbURL.append(key);
			sbURL.append('=');
			if(GeneralHelper.isStrNotEmpty(val))
					sbURL.append(CryptHelper.urlEncode(val, charset));
			}
		
		return sbURL.toString();
	}

	/** �û������� XML �����ַ� */
	public final static String regulateXMLStr(String src)
	{
		String result = src;
		result = result.replaceAll("&", "&amp;");
		result = result.replaceAll("\"", "&quot;");
		result = result.replaceAll("'", "&apos;");
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll(">", "&gt;");
		
		return result;
	}

	/** �û������� HTML �����ַ� */
	public final static String regulateHtmlStr(String src)
	{
		String result = src;
		result = result.replaceAll("&", "&amp;");
		result = result.replaceAll("\"", "&quot;");
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll(">", "&gt;");
		result = result.replaceAll("\r\n", "<br/>");
		result = result.replaceAll(" ", "&nbsp;");
		
		return result;
	}
	
	/** ȷ�� URL ·����ǰ����� URL ·���ָ��� */
	public static final String ensurePath(String path, String defPath)
	{
		if(GeneralHelper.isStrEmpty(path))
			path = defPath;
		if(!path.startsWith(URL_PATH_SEPARATOR))
			path = URL_PATH_SEPARATOR + path;
		if(!path.endsWith(URL_PATH_SEPARATOR))
			path = path + URL_PATH_SEPARATOR;
		
		return path;
	}
	
	/** ��ȡ {@link HttpServletRequest} ��ָ������ֵ */
	@SuppressWarnings("unchecked")
	public final static <T> T getRequestAttribute(HttpServletRequest request, String name)
	{
		return (T)request.getAttribute(name);
	}

	/** ���� {@link HttpServletRequest} ��ָ������ֵ */
	public final static <T> void setRequestAttribute(HttpServletRequest request, String name, T value)
	{
		request.setAttribute(name, value);
	}
	
	/** ɾ�� {@link HttpServletRequest} ��ָ������ֵ */
	public final static void removeRequestAttribute(HttpServletRequest request, String name)
	{
		request.removeAttribute(name);
	}

	/** ��ȡ {@link HttpSession} ��ָ������ֵ */
	@SuppressWarnings("unchecked")
	public final static <T> T getSessionAttribute(HttpSession session, String name)
	{
		return (T)session.getAttribute(name);
	}

	/** ���� {@link HttpSession} ��ָ������ֵ */
	public final static <T> void setSessionAttribute(HttpSession session, String name, T value)
	{
		session.setAttribute(name, value);
	}
	
	/** ɾ�� {@link HttpSession} ��ָ������ֵ */
	public final static void removeSessionAttribute(HttpSession session, String name)
	{
		session.removeAttribute(name);
	}
	
	/** ���� {@link HttpSession} */
	public final static void invalidateSession(HttpSession session)
	{
		session.invalidate();
	}

	/** ��ȡ {@link ServletContext} ��ָ������ֵ */
	@SuppressWarnings("unchecked")
	public final static <T> T getApplicationAttribute(String name)
	{
		return (T)getApplicationAttribute(servletContext, name);
	}

	/** ��ȡ {@link ServletContext} ��ָ������ֵ */
	@SuppressWarnings("unchecked")
	public final static <T> T getApplicationAttribute(ServletContext servletContext, String name)
	{
		return (T)servletContext.getAttribute(name);
	}

	/** ���� {@link ServletContext} ��ָ������ֵ */
	public final static <T> void setApplicationAttribute(String name, T value)
	{
		setApplicationAttribute(servletContext, name, value);
	}

	/** ���� {@link ServletContext} ��ָ������ֵ */
	public final static <T> void setApplicationAttribute(ServletContext servletContext, String name, T value)
	{
		servletContext.setAttribute(name, value);
	}
	
	/** ɾ�� {@link ServletContext} ��ָ������ֵ */
	public final static void removeApplicationAttribute(String name)
	{
		removeApplicationAttribute(servletContext, name);
	}
	
	/** ɾ�� {@link ServletContext} ��ָ������ֵ */
	public final static void removeApplicationAttribute(ServletContext servletContext, String name)
	{
		servletContext.removeAttribute(name);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ����ȥ��ǰ��ո� */
	public final static String getParam(HttpServletRequest request, String name)
	{
		String param = getParamNoTrim(request, name);
		if(param != null) return param = param.trim();
		
		return param;
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ */
	public final static String getParamNoTrim(HttpServletRequest request, String name)
	{
		return request.getParameter(name);
	}

	/** ��ȡ {@link HttpServletRequest} �Ĳ������Ƽ��� */
	public final static List<String> getParamNames(HttpServletRequest request)
	{
		List<String> names		= new ArrayList<String>();
		Enumeration<String> en	= request.getParameterNames();
		
		while(en.hasMoreElements())
			names.add(en.nextElement());
		
		return names;
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ���� */
	public final static List<String> getParamValues(HttpServletRequest request, String name)
	{
		String[] values = request.getParameterValues(name);
		return values != null ? Arrays.asList(values) : null;
	}
	
	/** ��ȡ {@link HttpServletRequest} �����в������ƺ�ֵ */
	public final static Map<String, String[]> getParamMap(HttpServletRequest request)
	{
		return request.getParameterMap();
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Boolean getBooleanParam(HttpServletRequest request, String name)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Boolean(s);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ���Ĭ��ֵ */
	public final static boolean getBooleanParam(HttpServletRequest request, String name, boolean def)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Boolean(s, def);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Byte getByteParam(HttpServletRequest request, String name)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Byte(s);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ���Ĭ��ֵ */
	public final static byte getByteParam(HttpServletRequest request, String name, byte def)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Byte(s, def);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Character getCharParam(HttpServletRequest request, String name)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Char(s);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ���Ĭ��ֵ */
	public final static char getCharParam(HttpServletRequest request, String name, char def)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Char(s, def);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Short getShortParam(HttpServletRequest request, String name)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Short(s);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ���Ĭ��ֵ */
	public final static short getShortParam(HttpServletRequest request, String name, short def)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Short(s, def);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Integer getIntParam(HttpServletRequest request, String name)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Int(s);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ���Ĭ��ֵ */
	public final static int getIntParam(HttpServletRequest request, String name, int def)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Int(s, def);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Long getLongParam(HttpServletRequest request, String name)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Long(s);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ���Ĭ��ֵ */
	public final static long getLongParam(HttpServletRequest request, String name, long def)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Long(s, def);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Float getFloatParam(HttpServletRequest request, String name)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Float(s);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ���Ĭ��ֵ */
	public final static float getFloatParam(HttpServletRequest request, String name, float def)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Float(s, def);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Double getDoubleParam(HttpServletRequest request, String name)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Double(s);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ���Ĭ��ֵ */
	public final static double getDoubleParam(HttpServletRequest request, String name, double def)
	{
		String s = getParam(request, name);
		return GeneralHelper.str2Double(s, def);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Date getDateParam(HttpServletRequest request, String name)
	{
		String str = getParam(request, name);
		return GeneralHelper.str2Date(str);
	}

	/** ��ȡ {@link HttpServletRequest} ��ָ���������ֵ��ʧ�ܷ��� null */
	public final static Date getDateParam(HttpServletRequest request, String name, String format)
	{
		String str = getParam(request, name);
		return GeneralHelper.str2Date(str, format);
	}
	
	/** ʹ�ñ�Ԫ�ش��� Form Bean (��Ԫ�ص����ƺ� Form Bean ���Ի��Ա��������ȫһ��) */
	public final static <T> T createFormBean(HttpServletRequest request, Class<T> clazz)
	{
		return createFormBean(request, clazz, null);
	}

	/** ʹ�ñ�Ԫ�ش��� Form Bean (�� keyMap ӳ�����Ԫ�����Ʋ���Ӧ�� Form Bean ���Ի��Ա����) */
	public final static <T> T createFormBean(HttpServletRequest request, Class<T> clazz, Map<String, String> keyMap)
	{
		Map<String, String[]> valueMap = getParamMap(request);
		return BeanHelper.createBean(clazz, valueMap, keyMap);
	}
	
	/** ʹ�ñ�Ԫ�ش��� Form Bean (��Ԫ�ص����ƺ� Form Bean ��������ȫһ��) */
	public final static <T> T createFormBeanByProperties(HttpServletRequest request, Class<T> clazz)
	{
		return createFormBeanByProperties(request, clazz, null);
	}

	/** ʹ�ñ�Ԫ�ش��� Form Bean (�� keyMap ӳ�����Ԫ�����Ʋ���Ӧ�� Form Bean ����) */
	public final static <T> T createFormBeanByProperties(HttpServletRequest request, Class<T> clazz, Map<String, String> keyMap)
	{
		Map<String, String[]> properties = getParamMap(request);
		return BeanHelper.createBeanByProperties(clazz, properties, keyMap);
	}
	
	/** ʹ�ñ�Ԫ�ش��� Form Bean (��Ԫ�ص����ƺ� Form Bean ���Ի��Ա��������ȫһ��) */
	public final static <T> T createFormBeanByFieldValues(HttpServletRequest request, Class<T> clazz)
	{
		return createFormBeanByFieldValues(request, clazz, null);
	}

	/** ʹ�ñ�Ԫ�ش��� Form Bean (�� keyMap ӳ�����Ԫ�����Ʋ���Ӧ�� Form Bean ���Ի��Ա����) */
	public final static <T> T createFormBeanByFieldValues(HttpServletRequest request, Class<T> clazz, Map<String, String> keyMap)
	{
		Map<String, String[]> values = getParamMap(request);
		return BeanHelper.createBeanByFieldValues(clazz, values, keyMap);
	}
	
	/** ʹ�ñ�Ԫ����� Form Bean (��Ԫ�ص����ƺ� Form Bean ��������ȫһ��) */
	public final static <T> void fillFormBeanProperties(HttpServletRequest request, T bean)
	{
		fillFormBeanProperties(request, bean, null);
	}
	
	/** ʹ�ñ�Ԫ����� Form Bean (�� keyMap ӳ�����Ԫ�����Ʋ���Ӧ�� Form Bean ����) */
	public final static <T> void fillFormBeanProperties(HttpServletRequest request, T bean, Map<String, String> keyMap)
	{
        Map<String, String[]> properties = getParamMap(request);
        BeanHelper.setProperties(bean, properties, keyMap);
	}

	/** ʹ�ñ�Ԫ����� Form Bean (��Ԫ�ص����ƺ� Form Bean ��Ա��������ȫһ��) */
	public final static <T> void fillFormBeanFieldValues(HttpServletRequest request, T bean)
	{
		fillFormBeanFieldValues(request, bean, null);
	}
	
	/** ʹ�ñ�Ԫ����� Form Bean (�� keyMap ӳ�����Ԫ�����Ʋ���Ӧ�� Form Bean ��Ա����) */
	public final static <T> void fillFormBeanFieldValues(HttpServletRequest request, T bean, Map<String, String> keyMap)
	{
        Map<String, String[]> values = getParamMap(request);
        BeanHelper.setFieldValues(bean, values, keyMap);
	}

	/** ʹ�ñ�Ԫ����� Form Bean (��Ԫ�ص����ƺ� Form Bean ���Ի��Ա��������ȫһ��) */
	public final static <T> void fillFormBeanPropertiesOrFieldValues(HttpServletRequest request, T bean)
	{
		fillFormBeanPropertiesOrFieldValues(request, bean, null);
	}
	
	/** ʹ�ñ�Ԫ����� Form Bean (�� keyMap ӳ�����Ԫ�����Ʋ���Ӧ�� Form Bean ���Ի��Ա����) */
	public final static <T> void fillFormBeanPropertiesOrFieldValues(HttpServletRequest request, T bean, Map<String, String> keyMap)
	{
        Map<String, String[]> valueMap = getParamMap(request);
        BeanHelper.setPropertiesOrFieldValues(bean, valueMap, keyMap);
	}

	/** ��ȡ {@link HttpSession} �������û������д����� */
	public final static HttpSession getSession(HttpServletRequest request)
	{
		return getSession(request, true);
	}

	/** ��ȡ {@link HttpSession} �������û������ݲ��������Ƿ񴴽��� */
	public final static HttpSession getSession(HttpServletRequest request, boolean create)
	{
		return request.getSession(create);
	}

	/** ���� {@link HttpSession} ��������Ѵ����򷵻�ԭ���� */
	public final static HttpSession createSession(HttpServletRequest request)
	{
		return getSession(request);
	}

	/** ��ȡ���� {@link Cookie} */
	public final static Cookie[] getCookies(HttpServletRequest request)
	{
		return request.getCookies();
	}
	
	/** ��ȡָ�����Ƶ� {@link Cookie} */
	public final static Cookie getCookie(HttpServletRequest request, String name)
	{
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals(name))
				{
					cookie = c;
					break;
				}
			}
		}
		
		return cookie;
	}

	/** ��ȡָ�����Ƶ� {@link Cookie} ֵ��ʧ�ܷ��� null */
	public final static String getCookieValue(HttpServletRequest request, String name)
	{
		String value = null;
		Cookie cookie = getCookie(request, name);
		
		if(cookie != null)
			value = cookie.getValue();
		
		return value;
	}

	/** ��� {@link Cookie} */
	public final static void addCookie(HttpServletResponse response, Cookie cookie)
	{
		response.addCookie(cookie);
	}

	/** ��� {@link Cookie} */
	public final static void addCookie(HttpServletResponse response, String name, String value)
	{
		addCookie(response, new Cookie(name, value));
	}
	
	/** ��ȡ URL ��  BASE ·�� */
	public final static String getRequestBasePath(HttpServletRequest request)
	{
		String scheme		= request.getScheme();
		int serverPort		= request.getServerPort();
		StringBuilder sb	= new StringBuilder(scheme).append("://").append(request.getServerName());
		
		if	(!(
				(scheme.equals(HTTP_SCHEMA) && serverPort == HTTP_DEFAULT_PORT) ||
				(scheme.equals(HTTPS_SCHEMA) && serverPort == HTTPS_DEFAULT_PORT) 
			))
				sb.append(":").append(request.getServerPort());
		
			sb.append(request.getContextPath()).append("/");

		return sb.toString();
	}
	
	/** ��ȡ URL ��ַ���ļ�ϵͳ�ľ���·��,
	 * 
	 * Servlet 2.4 ����ͨ�� request.getServletContext().getRealPath() ��ȡ,
	 * Servlet 2.4 ����ͨ�� request.getRealPath() ��ȡ��
	 *  
	 */
	@SuppressWarnings("deprecation")
	public final static String getRequestRealPath(HttpServletRequest request, String path)
	{
		if(servletContext != null)
			return servletContext.getRealPath(path);
		else
		{
			try
			{
				Method m = request.getClass().getMethod("getServletContext");
				ServletContext sc = (ServletContext)m.invoke(request);
				return sc.getRealPath(path);
			}
			catch(Exception e)
			{
				return request.getRealPath(path);
			}
		}
	}
	
	/** ��ȡ��������Ŀͻ�����������ڵĲ���ϵͳƽ̨ */
	public final static String getRequestUserAgentPlatform(HttpServletRequest request)
	{
		int index		= 1;
		String platform	= null;
		String agent	= request.getHeader("user-agent");
		
		if(GeneralHelper.isStrNotEmpty(agent))
		{
			int i				= 0;
			StringTokenizer st	= new StringTokenizer(agent, ";");
			
			while(st.hasMoreTokens())
			{
				String token = st.nextToken();
				
				if(i == 0)
				{
					if(token.toLowerCase().indexOf("compatible") != -1)
						index = 2;
				}
				else if(i == index)
				{
					int sep = token.indexOf(")");
					
					if(sep != -1)
						token = token.substring(0, sep);
					
					platform = GeneralHelper.safeTrimString(token);
					
					break;
				}
				
				++i;
			}
		}

		return platform;
	}
	
	/** ���� HTTP �� 'Content-Type' ��Ӧͷ */
	public final static void setContentType(HttpServletResponse response, String contentType, String encoding)
	{
		StringBuilder sb = new StringBuilder(contentType);
				
		if(encoding != null)
			sb.append(";charset=").append(encoding);
		
		response.setContentType(sb.toString());
	}
	
	/** ��ֹ��������浱ǰҳ�� */
	public final static void setNoCacheHeader(HttpServletResponse response)
	{
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}
	
	/** ��������Ƿ����Է� Windows ϵͳ������� */
	public final static boolean isRequestNotComeFromWidnows(HttpServletRequest request)
	{
		String agent = request.getHeader("user-agent");
		
		if(GeneralHelper.isStrNotEmpty(agent))
			return agent.toLowerCase().indexOf("windows") == -1;
		
		return false;
	}
}
