package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.JsonUtil;

import dao.UserInfoDao;
import daoimpl.UserInfoDaoImpl;

public class UploadHeadPhoto extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UploadHeadPhoto() {
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8"); // ���ô�����������ı����ʽ
		response.setContentType("text/html;charset=UTF-8"); // ����Content-Type�ֶ�ֵ
		PrintWriter out = response.getWriter();
		String loginname = null; //�����û���
		String headphoto = null; //�����ϴ���ͼƬ��
		try
		{	
			// ����Ĵ��뿪ʼʹ��Commons-UploadFile��������ϴ����ļ�����
			FileItemFactory factory = new DiskFileItemFactory(); // ����FileItemFactory����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// �������󣬲��õ��ϴ��ļ���FileItem����
			List<FileItem> items = upload.parseRequest(request);
			System.out.println("request:"+JsonUtil.toJson(items));
			// ��web.xml�ļ��еĲ����еõ��ϴ��ļ���·��
			// �����ļ��ϴ�·��
			String uploadPath = this.getServletContext().getRealPath("/User/HeadPhoto/");
			File file = new File(uploadPath);
			if (!file.exists())
			{
				file.mkdirs();
			}
			String filename = ""; // �ϴ��ļ����浽���������ļ���
			InputStream is = null; // ��ǰ�ϴ��ļ���InputStream����
			// ѭ�������ϴ��ļ�
			for (FileItem item : items)
			{
				// ������ͨ�ı���
				if (item.isFormField())					
				{
					if (item.getFieldName().equals("loginname")){//��ȡ�û�������
						loginname = JsonUtil.inputStream2String(item.getInputStream());
					} 
					continue;
				}
				// �����ϴ��ļ�
				else if (item.getName() != null && !item.getName().equals(""))
				{
					// �ӿͻ��˷��͹������ϴ��ļ�·���н�ȡ�ļ���
					filename = item.getName().substring(
							item.getName().lastIndexOf("\\") + 1);
					headphoto = filename;
					is = item.getInputStream(); // �õ��ϴ��ļ���InputStream����
				}
			
				// ��·�����ϴ��ļ�����ϳ������ķ����·��
				filename = uploadPath +"\\"+ filename;
				// ����������Ѿ����ں��ϴ��ļ�ͬ�����ļ����������ʾ��Ϣ
				if (new File(filename).exists())
				{
					new File(filename).delete();
				}
				// ��ʼ�ϴ��ļ�
				if (!filename.equals(""))
				{
					// ��FileOutputStream�򿪷���˵��ϴ��ļ�
					FileOutputStream fos = new FileOutputStream(filename);
					byte[] buffer = new byte[8192]; // ÿ�ζ�8K�ֽ�
					int count = 0;
					// ��ʼ��ȡ�ϴ��ļ����ֽڣ����������������˵��ϴ��ļ��������
					while ((count = is.read(buffer)) > 0)
					{
						fos.write(buffer, 0, count); // �������ļ�д���ֽ���
						
					}
					fos.close(); // �ر�FileOutputStream����
					is.close(); // InputStream����
					
					
					
				}
		    }
		} catch (FileUploadException e)
		{
			e.printStackTrace();
			out.write("fail");
		}
		
		//��ͷ��ĵ�ַ���浽���ݿ���
		UserInfoDao userInfoDao=new UserInfoDaoImpl();  
		if(userInfoDao.UpdateHeadPhoto(loginname, headphoto)){
			out.write("success");
		} else{
			out.write("fail");
		}
		
		
		out.flush();
		out.close();
	}


}
