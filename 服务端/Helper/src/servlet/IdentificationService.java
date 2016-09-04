package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankCardDao;
import dao.HelperPayDao;
import dao.UserInfoDao;
import daoimpl.BankCardDaoImpl;
import daoimpl.HelperPayDaoImpl;
import daoimpl.UserInfoDaoImpl;

import util.JsonUtil;
import bean.BankCard;
import bean.HelperPay;
import bean.IdentificationReq;
import bean.UserInfo;

public class IdentificationService extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public IdentificationService() {
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
		
		String Json=request.getParameter("Json");
		System.out.println("Json:"+Json);
		
		IdentificationReq identificationReq = null;
		try {
			identificationReq = (IdentificationReq)JsonUtil.fromRequest(Json, IdentificationReq.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserInfoDao userInfoDao = new UserInfoDaoImpl();
		BankCardDao bankCardDao = new BankCardDaoImpl();
		HelperPayDao helperPayDao = new HelperPayDaoImpl();
		
		UserInfo userinfo = userInfoDao.GetUserInfoByLoginname(identificationReq.getLoginname());
		BankCard bankCard = bankCardDao.GetCardInfoByCardId(identificationReq.getBankCardId());
		String result = "";
		if(bankCard!=null){
			if(!identificationReq.getRealname().equals(bankCard.getName())){ //�����ʵ���������еĲ�һ��
				result += "��ʵ���������еĿ�������һ�£�\n";
			} 
			if(!identificationReq.getIdentitycard().equals(bankCard.getIdentitycard())){ //������֤��������ע������֤�Ų�һ��
				result += "���֤��������ע��Ĳ�һ�£�\n";
			} 
			if(!identificationReq.getBanktype().equals(bankCard.getBanktype())){ //��Ŀ������г���
				result += "����������д����\n";
			} 
			if(!identificationReq.getBankCardPassword().equals(bankCard.getPassword())){ //���������������
				result += "���������������\n";
			} 
		} else{
			result += "����������п������ڣ�\n";
		}
		
		if(result.equals("")){      //��֤ͨ��
			if(userInfoDao.UpdateIdentificationStatus(identificationReq.getLoginname(), identificationReq.getRealname(), identificationReq.getIdentitycard())){
				HelperPay helperPay = new HelperPay();
				helperPay.setUsername(userinfo.getLoginname());
				helperPay.setPassword(userinfo.getPassword());
				helperPay.setMoney(0);
				List<BankCard> bankcardlist = new ArrayList <BankCard> (); 
				bankcardlist.add(bankCard);
				helperPay.setBankcardlist(bankcardlist);
				if(helperPayDao.Register(helperPay)){
					result +="�����֤�ɹ���";
				} else{
					result += "���п���ʧ�ܣ�\n";
				}
			} else{
				result += "��֤��Ϣ����ʧ�ܣ�\n";
			}
		}
		
		out.write(result);
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
