package servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UpLoadHttpFileServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpLoadHttpFileServlet() {
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

		request.setCharacterEncoding("UTF-8"); // ���ô�����������ı����ʽ
		response.setContentType("text/html;charset=UTF-8"); // ����Content-Type�ֶ�ֵ
		PrintWriter responseout = response.getWriter();
		  try {  
	            System.out.println("IP:" + request.getRemoteAddr());  
	            // 1�����������ࣺDiskFileItemFactory  
	            DiskFileItemFactory facotry = new DiskFileItemFactory();  
	            String tempDir = System.getProperty("java.io.tmpdir");
	            facotry.setRepository(new File(tempDir));//������ʱ�ļ����Ŀ¼  
	            // 2���������Ľ����ࣺServletFileUpload  
	            ServletFileUpload upload = new ServletFileUpload(facotry);  
	            upload.setHeaderEncoding("UTF-8");// ����ϴ����ļ�������  
	            upload.setFileSizeMax(1024 * 1024 * 1024);// �����ļ��ϴ����ֵ��1M  
	            upload.setSizeMax(2048 * 1024 * 1024);//�ļ��ϴ����ܴ�С����  
	  
	            // 3���ж��û��ı��ύ��ʽ�ǲ���multipart/form-data  
	            boolean bb = upload.isMultipartContent(request);  
	            if (!bb) {  
	                return;  
	            }  
	            // 4���ǣ�����request�������������List<FileItem>  
	            List<FileItem> items = upload.parseRequest(request);  
	            String uploadPath = this.getServletContext().getRealPath("/uploadHttpFile/");
	            System.out.println("�ϴ��������ĸ�Ŀ¼:" + uploadPath);  
	            File file = new File(uploadPath);
				if (!file.exists())
				{
					file.mkdir();
				}
	            for (FileItem item : items) {  
	                if (item.isFormField()) {  
	                    // 5���ж��Ƿ�����ͨ������ӡ����  
	                    String fieldName = item.getFieldName();// ���������  
	                    String fieldValue = item.getString("UTF-8");// �������ֵ  
	                    System.out.println(fieldName + "=" + fieldValue);  
	                } else {  
	                    // 6���ϴ������õ��������������ϴ������浽��������ĳ��Ŀ¼�У�����ʱ���ļ�����ɶ��  
	                    String fileName = item.getName();// �õ��ϴ��ļ������� C:\Documents  
	                                                        // and  
	                                                        // Settings\shc\����\a.txt  
	                                                        // a.txt  
	                    //����û�û��ѡ���ļ��ϴ������  
	                    if(fileName==null||fileName.trim().equals("")){  
	                        continue;  
	                    }  
	                    fileName = fileName  
	                            .substring(fileName.lastIndexOf("\\") + 1);  
	                    String newFileName =uploadPath +"\\"+ fileName;
	                    System.out.println("�ϴ����ļ����ǣ�" + fileName);
	                    if (new File(newFileName).exists())
	        			{
	        				new File(newFileName).delete();
	        				System.out.println("�ļ��Ѵ��ڣ�");
	        			}
	                    InputStream in = item.getInputStream();  
	                    /*String savePath = makeDir(storePath, fileName) + "\\"  
	                            + newFileName;  */
	                    OutputStream out = new FileOutputStream(newFileName);  
	                    byte b[] = new byte[1024];  
	                    int len = -1;  
	                    while ((len = in.read(b)) != -1) {  
	                        out.write(b, 0, len);  
	                    }  
	                    in.close();  
	                    out.close();  
	                    item.delete();//ɾ����ʱ�ļ�  
	                    responseout.write("success");
	                }  
	            }  
	        }catch(FileUploadBase.FileSizeLimitExceededException e){  
	           /* request.setAttribute("message", "�����ļ���С���ܳ���5M");  
	            request.getRequestDispatcher("/message.jsp").forward(request,  
	                    response);  */
	        	  responseout.write("failure");
	        }catch(FileUploadBase.SizeLimitExceededException e){  
	           /* request.setAttribute("message", "���ļ���С���ܳ���7M");  
	            request.getRequestDispatcher("/message.jsp").forward(request,  
	                    response);  */
	        	  responseout.write("failure");
	    }catch (Exception e) {  
	           /* e.printStackTrace();  
	            request.setAttribute("message", "�ϴ�ʧ��");  
	            request.getRequestDispatcher("/message.jsp").forward(request,  
	                    response);  */
	    	  responseout.write("failure");
	        }  
	    }  
	  
/*	    // WEB-INF/upload/1/3 ��ɢ�洢Ŀ¼  
	    private String makeDir(String storePath, String fileName) {  
	        int hashCode = fileName.hashCode();// �õ��ļ�����hashcode��  
	        int dir1 = hashCode & 0xf;// ȡhashCode�ĵ�4λ 0~15  
	        int dir2 = (hashCode & 0xf0) >> 4;// ȡhashCode�ĸ�4λ 0~15  
	        String path = storePath + "\\" + dir1 + "\\" + dir2;  
	        File file = new File(path);  
	        if (!file.exists())  
	            file.mkdirs();  
	        System.out.println("�洢·����"+path);  
	        return path;  
	}*/

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
