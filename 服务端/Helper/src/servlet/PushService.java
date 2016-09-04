package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import push.PushMessage;
import push.PushServiceReq;
import util.JsonUtil;
import dao.PushServiceDao;
import daoimpl.PushServiceDaoImpl;

public class PushService extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PushService() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		//��ȡpost���� 
		StringBuffer sb = new StringBuffer() ; 
		InputStream is = request.getInputStream(); 
		InputStreamReader isr = new InputStreamReader(is);   
		BufferedReader br = new BufferedReader(isr); 
		String s = "" ; 
		while((s=br.readLine())!=null){ 
		sb.append(s) ; 
		} 
		String str =sb.toString();
		System.out.println(str);
		
		PushServiceReq pushMessageReq= (PushServiceReq)JsonUtil.fromRequest(str, PushServiceReq.class);
		PushServiceDao pushserviceDao=new PushServiceDaoImpl();    
		
		List<String> PushModeList = pushserviceDao.getPushMode(pushMessageReq.getUserCode(),pushMessageReq.getRepCreateDate());
		
		String resultjson = JsonUtil.toJson(PushModeList);
		System.out.println(resultjson);
		out.write(resultjson);
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
