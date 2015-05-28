package com.zzy.study;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzy.dev.comm.util.FileUploader;
import com.zzy.dev.comm.util.FileUploader.Result;
import com.zzy.dev.comm.util.LogWriter;
import com.zzy.dev.comm.util.StringUtil;
import com.zzy.dev.project.base.component.pager.bean.Page;
import com.zzy.study.pagerTest.bean.FCTRADE_FEEbean;


public class TestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    // �ϴ�·��
    private static final String UPLOAD_PATH        = "upload";
    // �ɽ��ܵ��ļ�����
    private static final String[] ACCEPT_TYPES    = {"txt", "pdf", "doc", ".Jpg", ".jpn", ".gif", "*.zip", "*.RAR"};
    // ���ϴ��ļ���С����
    private static final long MAX_SIZE            = 1024 * 1024 * 100;
    // �������ļ���С����
    private static final long MAX_FILE_SIZE        = 1024 * 1024 * 10;
    
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("TestServlet.service ��ʼ");
		String action = StringUtil.formatNull(request.getParameter("action"));
		
		if ("pageAction".equals(action)) {//��ҳ
			
			int pageNo = 0;
			try {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			} catch (NumberFormatException e) {
				pageNo = 1;
			}
			long start = System.currentTimeMillis();System.out.println("start===============");
			for(long i = 0; i < 1000000000L; i++) {
				/*for(int j = 0; j < 1; j++) {
					
				}*/
			}
			System.out.println("===============ʱ���(��):" + ((System.currentTimeMillis() - start)/1000));
			String url = request.getContextPath() + "/test?action=pageAction";
			int pageSize = 10;
			String sql = "select * from FCTRADE_FEE where 1=1";
			try {
				Page page = new Page(pageNo, url, pageSize, sql, FCTRADE_FEEbean.class);
				
				request.setAttribute("page", page);

				request.getRequestDispatcher("page/pageTest/searchList.jsp").forward(request, response);
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		else if ("leftToAction".equals(action)) {//�����ƶ�window
			request.getRequestDispatcher("page/pageCommon/leftRightMove.jsp").forward(request, response);
		}
		else if ("writeFileInWebContainer".equals(action)) {//

			LogWriter lw = new LogWriter(true, "logtest.txt");
			lw.log("1111111111111111111111111");
			System.out.println("�ļ�д��ϣ�");
			System.out.println("sss"+ System.getProperty("user.dir"));
			
			System.out.println(TestServlet.class.getResource("/test/TestServlet.class").getPath());
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else if ("fileDataProcessor".equals(action)) {//
			LogWriter lw = new LogWriter(true, "Log.log");
			//lw.log(req.getParameter("pk"));
			/** ���ύ�ı����ж��nameֵ��ͬ�ı�Ԫ��ʱ��Ҫ����request.getParameterValues("��Ԫ��nameֵ")*/
			System.out.println(request.getParameterValues("checkedItem"));
			request.getRequestDispatcher("page/pageCommon/fileDataProcessor.jsp").forward(request, response);
		}
		else if ("uploadFile".equals(action)) {//
			//�ͻ���
			//�ļ���
			String filePath = request.getParameter("imagefile");
			System.out.println(filePath);
			
			//��������
			//�洢·��
			String serverPath = System.getProperty("user.dir");
			System.out.println(serverPath);
			
			//�洢����
			//String.format(format, args)
			
			// ���� FileUploader ����
	        FileUploader fu = new FileUploader(UPLOAD_PATH, ACCEPT_TYPES, MAX_SIZE, MAX_FILE_SIZE);
	        
	        // ����ʵ��������ö������ԣ���ѡ��
	        /*
	        fu.setFileNameGenerator(new FileNameGenerator()
	        {
	            
	            @Override
	            public String generate(FileItem item, String suffix)
	            {
	                return String.format("%d_%d", item.hashCode(), item.get().hashCode());
	            }
	        });
	        
	        fu.setServletProgressListener(new ProgressListener()
	        {
	            
	            @Override
	            public void update(long pBytesRead, long pContentLength, int pItems)
	            {
	                System.out.printf("%d: length -> %d, read -> %d.\n", pItems, pContentLength, pBytesRead);
	            }
	        });
	        */
	        
	        // ִ���ϴ�����ȡ�������
	        Result result = fu.upload(request, response);
		}
		
		else if ("jdil".equals(action)) {
			String interfaceNo = request.getParameter("interfaceNo");
/*			BusinessLogic bl = new BusinessLogic();
			if ("3FC307".equalsIgnoreCase(interfaceNo)) {//
				
			}
			
			else if ("3FC102".equalsIgnoreCase(interfaceNo)) {
				
			}
			
			else if ("3FC316".equalsIgnoreCase(interfaceNo)) {
				
				JH_INTERFACE_3FC316 dao316 = new JH_INTERFACE_3FC316();
				String sqlWhere = " and REQUISITION_NO = '" + "C13091243226" + "'";
				List<Res3FC316bean> list = dao316.getList(sqlWhere, 1);
				Res3FC316bean res3fc316bean = list.get(0);
				bl.business3FC316(res3fc316bean);
			}*/
		}
		
		System.out.println("TestServlet.service ����");
	}
	
	
}
