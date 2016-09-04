package servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.JsonUtil;

public class UploadFileServlet extends HttpServlet
{

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");  
		
		PrintWriter out = response.getWriter();

		// 创建文件项目工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 设置文件上传路径
		String upload = this.getServletContext().getRealPath("/upload/");
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		// 设置缓冲区大小为 10M
		factory.setSizeThreshold(1024 * 1024 * 10);
		// 设置临时文件夹为temp
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

		// 解析结果放在List中
		try
		{
			request.setCharacterEncoding("utf-8");
			List<FileItem> list = servletFileUpload.parseRequest(request);
			System.out.println("request:"+JsonUtil.toJson(list));

			for (FileItem item : list)
			{
				String name = item.getFieldName();
				InputStream is = item.getInputStream();

				if (name.contains("content"))
				{
					System.out.println(inputStream2String(is));
				} else if(name.contains("file"))
				{
					try
					{
						inputStream2File(is, upload + "\\" + item.getName());
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			
			out.write("success");
		} catch (FileUploadException e)
		{
			e.printStackTrace();
			out.write("failure");
		}

		out.flush();
		out.close();
	}

	// 流转化成字符串
	public static String inputStream2String(InputStream is) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1)
		{
			baos.write(i);
		}
		return baos.toString();
	}

	// 流转化成文件
	public static void inputStream2File(InputStream is, String savePath)
			throws Exception
	{
		System.out.println("文件保存路径为:" + savePath);
		File file = new File(savePath);
		InputStream inputSteam = is;
		BufferedInputStream fis = new BufferedInputStream(inputSteam);
		FileOutputStream fos = new FileOutputStream(file);
		byte[] buffer = new byte[65535]; // 每次读64K字节
		int count = 0;
		// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
		while ((count = is.read(buffer)) > 0)
		{
			fos.write(buffer, 0, count); // 向服务端文件写入字节流
			
		}
		fos.flush();
		fos.close();
		fis.close();
		inputSteam.close();
		
	}

}
