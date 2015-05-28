package com.zzy.dev.comm.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	private FilterConfig config = null;
	private String defaultCharset = "utf-8";

	// Filter被释放时的回调方法
	public void destroy() {
	}

	// FilterConfig接口实例中封装了这个Filter的初始化参数
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	// 过滤方法
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String charset = config.getInitParameter("charset");
		if (charset == null) {
			charset = defaultCharset;
		}
		request.setCharacterEncoding(charset);
		// 有时候response不用设置编码，因为servlet一般不做输出，
		// 输出交由jsp来做，所以只要jsp页面设定编码即可
		resp.setCharacterEncoding(charset);
		resp.setContentType("text/html;charset=" + charset);
		chain.doFilter(req, resp);

	}
}
