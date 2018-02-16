package com.neo.server;
 
import java.io.File;  
import java.io.IOException;  
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileItemFactory;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  

import com.neo.service.UserService;
import com.neo.util.Timeutil;

/**
 * 上传头像
 * @author Administrator
 *
 */
public class UploadPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	
    @Override  
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doPost(request, response);  
    }  
  
    @Override  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
        System.out.println("******" + Timeutil.getCurrentTime() + ":更换头像请求******");
        if (isMultipart)  
            try {  
            	String phone = "";
            	String relativePath = "";
                FileItemFactory factory = new DiskFileItemFactory();  
                ServletFileUpload upload = new ServletFileUpload(factory);  
                List<FileItem> items = upload.parseRequest(request);  
                // 得到服务器跟路径,即Tomcat
                File dir = null;
                for (FileItem item : items)  
                    if (item.isFormField()){  // 普通文本表单字段
                    	// 根据用户名创建头像保存目录
                    	phone = item.getString();
                    	relativePath = "Upload/HeadImage/" + phone;
                    	String absolutePath = request.getSession().getServletContext().getRealPath("/") + relativePath;
                    	System.out.println(absolutePath);
                        dir = new File(absolutePath); 
	                    //创建目录  
	                    if(!dir.exists()){
	                        dir.mkdirs();
	                    }   
	                    System.out.println("****"+"创建目录...");
                    }
                    else{                    // 文件表单字段
                    	// 写入文件里
                        item.write(new File(dir,item.getName())); 
                        relativePath += "/" + item.getName();
                        // 保存进数据库
                        UserService.savePic(phone, relativePath);
                        System.out.println("****"+"保存文件:" + relativePath + "...");
                    }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }   
    }  
}

