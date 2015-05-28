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
 * <pre>输出应用程序容器的基本信息、请求及应答信息</pre>
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
		System.out.println("====================== 应用程序Filter开始====================");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)rep;
		//HttpSession session = request.getSession();
		//System.out.println("session.getId()" + "=" + session.getId());
		System.out.println("====================== request 信息输出开始====================");
		/*System.out.println("====================== request getAttribute()开始====================");
		Enumeration enums = request.getAttributeNames();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			String val = (String)request.getAttribute(key);
			System.out.println(key + "=" + val);
		}
		System.out.println("====================== request getAttribute()结束====================");*/
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
		
		System.out.println("====================== request getParameter()开始====================");
		Enumeration enums = request.getParameterNames();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			String val = (String)request.getParameter(key);
			System.out.println(key + "=" + val);
		}
		System.out.println("====================== request getParameter()结束====================");
		
		
		System.out.println("====================== request 信息输出结束====================");
		System.out.println("");
		System.out.println("====================== servletcotext 信息输出开始====================");
		
		//System.out.println("ctx.getContextPath()" + "=" + ctx.getContextPath()); weblogic 92不支持，该方法是servlet2.5中引进的
		System.out.println("ctx.getInitParameterNames()" + "=" + ctx.getInitParameterNames());
		System.out.println("ctx.getMajorVersion()" + "=" + ctx.getMajorVersion());
		System.out.println("ctx.getMimeType('D:/config_para.properties')" + "=" + ctx.getMimeType("D:/config_para.properties"));
		System.out.println("ctx.getMinorVersion()" + "=" + ctx.getMinorVersion());
		System.out.println("ctx.getRealPath('fileDataProcessor.jsp')" + "=" + ctx.getRealPath("fileDataProcessor.jsp"));
		System.out.println("ctx.getServerInfo()" + "=" + ctx.getServerInfo());
		System.out.println("ctx.getServletContextName()" + "=" + ctx.getServletContextName());
		System.out.println("ctx.getContext('/iftjh')" + "=" + ctx.getContext("/iftjh"));
		
		System.out.println("====================== ctx.getInitParameterNames()开始====================");
		Enumeration enums2 = ctx.getInitParameterNames();
		while (enums2.hasMoreElements()) {
			String key = (String) enums2.nextElement();
			String val = (String)ctx.getInitParameter(key);
			System.out.println(key + "=" + val);
		}
		System.out.println("====================== ctx.getInitParameterNames()结束====================");
		
		System.out.println("ctx.getNamedDispatcher()" + "=" + ctx.getNamedDispatcher("testServlet"));
		System.out.println("ctx.getRequestDispatcher('/index.jsp')" + "=" + ctx.getRequestDispatcher("/index.jsp"));
		System.out.println("ctx.getResource('/index.jsp')" + "=" + ctx.getResource("/index.jsp"));
		System.out.println("ctx.getResourcePaths('/')" + "=" + ctx.getResourcePaths("/"));
		ctx.log("servlet log");
		System.out.println("ctx.log('servlet log')");
		System.out.println("====================== servletcotext 信息输出结束====================");
		
		chain.doFilter(request, response);

		System.out.println("====================== 应用程序Filter结束====================");
	}

	public void init(FilterConfig config) throws ServletException {
		ctx = config.getServletContext();
	}

}
