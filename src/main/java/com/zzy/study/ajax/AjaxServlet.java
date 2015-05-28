/**
 * 
 */
package com.zzy.study.ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Administrator
 *
 */
public class AjaxServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
/*		InputStream stream=req.getInputStream();
		InputStreamReader isr=new InputStreamReader(stream);
		BufferedReader br=new BufferedReader(isr);
		String str=br.readLine();   
		System.out.println(str);

		str=URLDecoder.decode(str,"gb2312");
		System.out.println("ddd===" + str);
		br.close()*/;
		
		System.out.println("request contentType===" + req.getContentType());
		System.out.println("request RequestURI===" + req.getRequestURI());
		System.out.println("response CharacterEncoding===" + resp.getCharacterEncoding());
		
		req.setCharacterEncoding("gb2312");
		resp.setContentType("text/html; charset=gb2312");
		
		String name;
		
/*		name = (String)req.getAttribute("name");
		System.out.println("getAttribute()===" + name);
		
		name = req.getQueryString();
		System.out.println("getQueryString()===" + name);*/
		
		name = req.getParameter("name");
		//name = new String(req.getParameter("name").getBytes("iso-8859-1"), "utf-8");
		System.out.println("getParameter()===" + name);
		//name = URLDecoder.decode(name, "gb2312");
		//System.out.println("getParameter()===" + name);
		
		String [] names = {"zhangzhanyao", "zzy", "zhangzy", "zzy2000", "张占耀"};
		
		for (int i = 0; i < names.length; i++){
			if (names[i].equals(name)) {
				resp.getOutputStream().print("该用户已经存在！");
				resp.getOutputStream().close();
				/*PrintWriter out = resp.getWriter();
				out.write("该用户已经存在！");
				out.close();*/
				return;
			}
		}
		PrintWriter out = resp.getWriter();
		out.write("该用户名可以使用！");
		out.close();
		
	}
	
}
