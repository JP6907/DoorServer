package com.neo.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.neo.bean.PostProfile;
import com.neo.service.BBSService;
import com.neo.util.Timeutil;


/**
 * Servlet implementation class UploadFileServlet
 */
public class PostBbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostBbsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//��ȡ��ǰʱ��
		String time = Timeutil.getCurrentTime();
		System.out.println("******" + time + ":��������******");
		//��ʼ�����Ӷ���
		PostProfile postProfile = new PostProfile();
		postProfile.post_replynum = 0; //�ظ���Ϊ0
		postProfile.post_publishdt = time; //����ʱ��
		postProfile.post_newdt = time;  //���»ظ�ʱ��
		long id = -1; //�������ݿ�idֵ
		//ͼƬ���б�
		List<String> picNames = new ArrayList<String>(); 
		
		PrintWriter dataOut = response.getWriter();
		String result = null;
		JSONArray respArray = null; // ��Ӧ����
		
        //��ô����ļ���Ŀ������  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //��ȡ�ļ��ϴ���Ҫ�����·����upload�ļ�������ڡ�  
        String path = request.getSession().getServletContext().getRealPath("/") + "Upload/BBS"; 
        File parentPath = new File(path);
        if(!parentPath.exists()){
        	parentPath.mkdirs();
        }
        System.out.println(path);
        //������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�  
        factory.setRepository(new File(path));  
        //���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�  
        factory.setSizeThreshold(1024*1024);  
        //�ϴ��������ࣨ��ˮƽAPI�ϴ�������  
        ServletFileUpload upload = new ServletFileUpload(factory);  
           
        try{  
        	int i = 0;
            //���� parseRequest��request������  ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
            for(FileItem item:list){  
                //��ȡ���������֡�  
                String name = item.getFieldName();  
                //�����ȡ�ı���Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�����ʽ���������ַ�����  
                if(item.isFormField()){  
                    //��ȡ�û�����������ַ�����
                    String value = item.getString();
                    ///!!!
                    ///�����ʽ��֪Ϊʲô���� ISO-8859-1
                    ///!!!
                    value = new String(value.getBytes("ISO-8859-1"), "utf-8");
                    String dataname = item.getFieldName();
                    System.out.println("name:" + dataname);
                    System.out.print(getEncoding(value) + "   ");  //�����ʽ
                    System.out.println( "value:" + value);
                    request.setAttribute(name, value);  
                    if(dataname.equals("userPhone")){  //�ֻ�����
                    	time = time.replaceAll("[:,\\s+,-]", "");
                    	path = path + "/" + value + "/" + time;    
                    	File fp = new File(path);   //����ͼƬ����Ŀ¼
                    	if(!fp.exists()){
                    		fp.mkdirs();
                    	}
                    	System.out.println(path);
                    	postProfile.post_phone = value;
                    } else if(dataname.equals("userName")){  //�û���
                    	postProfile.post_publisher = value;
                    } else if(dataname.equals("title")){  //����
                    	postProfile.post_title = value;
                    } else if(dataname.equals("content")){  //����
                    	postProfile.post_text = value;
                    }
                }  
                //���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���  
                else{   
                    //��ȡ·����  
                    String value = item.getName();  
                    //ȡ�����һ����б�ܡ�  
                    int start = value.lastIndexOf("\\");  
                    //��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�  
                    String filename =  (i++) + "" + value.substring(start+1);  
                    picNames.add(filename);
                    
                    request.setAttribute(name, filename);  
                       
                    /*�������ṩ�ķ���ֱ��д���ļ��С� 
                     * item.write(new File(path,filename));*/ 
                    //�յ�д�����յ��ļ��С�  
                    OutputStream out = new FileOutputStream(new File(path,filename));  
                    InputStream in = item.getInputStream();  
                       
                    int length = 0;  
                    byte[] buf = new byte[1024];  
                    System.out.println("��ȡ�ļ�����������:"+ item.getSize());  
                       
                    while((length = in.read(buf))!=-1){  
                        out.write(buf,0,length);  
                    }  
                    in.close();  
                    out.close();  
                }  
            }
            BBSService.addPic(postProfile, picNames);
            System.out.println("ͼƬ������" + picNames.size());
            id = BBSService.addPostProfile(postProfile);
            result = "Success";
        }catch(Exception e){  
        	result = "Failed";
            e.printStackTrace();
        } finally{
        	try {
				respArray = new JSONArray().put(new JSONObject().put("RESULT",result)
						.put("TIME", time)
						.put("ID",id + ""));
			} catch (JSONException e) {
				System.out.println("JSON����쳣!");
				e.printStackTrace();
			}
        	String responMessage = respArray.toString();
        	System.out.println("���ر���:" + responMessage);
        	dataOut.write(responMessage);
        	dataOut.flush();
        	dataOut.close();
        }
	}
    public static String getEncoding(String str) {      
        String encode = "ISO-8859-1";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s = encode;      
               return s;      
            }      
        } catch (Exception exception) {      
        }      
        encode = "GB2312";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s1 = encode;      
               return s1;      
            }      
        } catch (Exception exception1) {      
        }      
        encode = "UTF-8";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s2 = encode;      
               return s2;      
            }      
        } catch (Exception exception2) {      
        }      
        encode = "GBK";      
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s3 = encode;      
               return s3;      
            }      
        } catch (Exception exception3) {      
        }      
       return "";      
    }      

}
