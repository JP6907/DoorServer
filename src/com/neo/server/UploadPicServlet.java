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
 * �ϴ�ͷ��
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
        System.out.println("******" + Timeutil.getCurrentTime() + ":����ͷ������******");
        if (isMultipart)  
            try {  
            	String phone = "";
            	String relativePath = "";
                FileItemFactory factory = new DiskFileItemFactory();  
                ServletFileUpload upload = new ServletFileUpload(factory);  
                List<FileItem> items = upload.parseRequest(request);  
                // �õ���������·��,��Tomcat
                File dir = null;
                for (FileItem item : items)  
                    if (item.isFormField()){  // ��ͨ�ı����ֶ�
                    	// �����û�������ͷ�񱣴�Ŀ¼
                    	phone = item.getString();
                    	relativePath = "Upload/HeadImage/" + phone;
                    	String absolutePath = request.getSession().getServletContext().getRealPath("/") + relativePath;
                    	System.out.println(absolutePath);
                        dir = new File(absolutePath); 
	                    //����Ŀ¼  
	                    if(!dir.exists()){
	                        dir.mkdirs();
	                    }   
	                    System.out.println("****"+"����Ŀ¼...");
                    }
                    else{                    // �ļ����ֶ�
                    	// д���ļ���
                        item.write(new File(dir,item.getName())); 
                        relativePath += "/" + item.getName();
                        // ��������ݿ�
                        UserService.savePic(phone, relativePath);
                        System.out.println("****"+"�����ļ�:" + relativePath + "...");
                    }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }   
    }  
}

