package com.zzy.dev.comm.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>���Ӧ�ó��������Ļ�����Ϣ������Ӧ����Ϣ</pre>
 * @author Administrator
 *
 */
public class ApplicationFilter implements Filter{
	
	private ServletContext ctx;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse rep,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("====================== Ӧ�ó���Filter��ʼ====================");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)rep;
		//HttpSession session = request.getSession();
		//System.out.println("session.getId()" + "=" + session.getId());
		System.out.println("====================== request ��Ϣ�����ʼ====================");
		/*System.out.println("====================== request getAttribute()��ʼ====================");
		Enumeration enums = request.getAttributeNames();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			String val = (String)request.getAttribute(key);
			System.out.println(key + "=" + val);
		}
		System.out.println("====================== request getAttribute()����====================");*/
		System.out.println("request.getCharacterEncoding()" + "=" + request.getCharacterEncoding());
		System.out.println("request.getLocalAddr()" + "=" + request.getLocalAddr());
		System.out.println("request.getContentType()" + "=" + request.getContentType());
		System.out.println("request.getLocalName()" + "=" + request.getLocalName());
		System.out.println("request.getLocalPort()" + "=" + request.getLocalPort());
		System.out.println("request.getProtocol()" + "=" + request.getProtocol());
		System.out.println("request.getRemoteAddr()" + "=" + request.getRemoteAddr());
		System.out.println("request.getRemoteHost()" + "=" + request.getRemoteHost());
		System.out.println("request.getRemotePort()" + "=" + request.getRemotePort());
		System.out.println("request.getScheme()" + "=" + request.getScheme());
		System.out.println("request.getServerName()" + "=" + request.getServerName());
		System.out.println("request.getServerPort()" + "=" + request.getServerPort());
		System.out.println("request.getLocale()" + "=" + request.getLocale());
		System.out.println("request.getLocales()" + "=" + request.getLocales());
		System.out.println("request.getPathInfo()" + "=" + request.getPathInfo());
		System.out.println("request.getPathTranslated()" + "=" + request.getPathTranslated());
		System.out.println("request.getRequestURI()" + "=" + request.getRequestURI());
		System.out.println("request.getRequestURL()" + "=" + request.getRequestURL().toString());
		
		System.out.println("====================== request getParameter()��ʼ====================");
		Enumeration enums = request.getParameterNames();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			String val = (String)request.getParameter(key);
			System.out.println(key + "=" + val);
		}
		System.out.println("====================== request getParameter()����====================");
		
		
		System.out.println("====================== request ��Ϣ�������====================");
		System.out.println("");
		System.out.println("====================== servletcotext ��Ϣ�����ʼ====================");
		
		//System.out.println("ctx.getContextPath()" + "=" + ctx.getContextPath()); weblogic 92��֧�֣��÷�����servlet2.5��������
		System.out.println("ctx.getInitParameterNames()" + "=" + ctx.getInitParameterNames());
		System.out.println("ctx.getMajorVersion()" + "=" + ctx.getMajorVersion());
		System.out.println("ctx.getMimeType('D:/config_para.properties')" + "=" + ctx.getMimeType("D:/config_para.properties"));
		System.out.println("ctx.getMinorVersion()" + "=" + ctx.getMinorVersion());
		System.out.println("ctx.getRealPath('fileDataProcessor.jsp')" + "=" + ctx.getRealPath("fileDataProcessor.jsp"));
		System.out.println("ctx.getServerInfo()" + "=" + ctx.getServerInfo());
		System.out.println("ctx.getServletContextName()" + "=" + ctx.getServletContextName());
		System.out.println("ctx.getContext('/iftjh')" + "=" + ctx.getContext("/iftjh"));
		
		System.out.println("====================== ctx.getInitParameterNames()��ʼ====================");
		Enumeration enums2 = ctx.getInitParameterNames();
		while (enums2.hasMoreElements()) {
			String key = (String) enums2.nextElement();
			String val = (String)ctx.getInitParameter(key);
			System.out.println(key + "=" + val);
		}
		System.out.println("====================== ctx.getInitParameterNames()����====================");
		
		System.out.println("ctx.getNamedDispatcher()" + "=" + ctx.getNamedDispatcher("testServlet"));
		System.out.println("ctx.getRequestDispatcher('/index.jsp')" + "=" + ctx.getRequestDispatcher("/index.jsp"));
		System.out.println("ctx.getResource('/index.jsp')" + "=" + ctx.getResource("/index.jsp"));
		System.out.println("ctx.getResourcePaths('/')" + "=" + ctx.getResourcePaths("/"));
		ctx.log("servlet log");
		System.out.println("ctx.log('servlet log')");
		System.out.println("====================== servletcotext ��Ϣ�������====================");
		
		chain.doFilter(request, response);

		System.out.println("====================== Ӧ�ó���Filter����====================");
	}

	public void init(FilterConfig config) throws ServletException {
		ctx = config.getServletContext();
	}

}
