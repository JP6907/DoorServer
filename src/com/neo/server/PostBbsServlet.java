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
		//获取当前时间
		String time = Timeutil.getCurrentTime();
		System.out.println("******" + time + ":发布帖子******");
		//初始化帖子对象
		PostProfile postProfile = new PostProfile();
		postProfile.post_replynum = 0; //回复数为0
		postProfile.post_publishdt = time; //发贴时间
		postProfile.post_newdt = time;  //最新回复时间
		long id = -1; //插入数据库id值
		//图片名列表
		List<String> picNames = new ArrayList<String>(); 
		
		PrintWriter dataOut = response.getWriter();
		String result = null;
		JSONArray respArray = null; // 响应对象
		
        //获得磁盘文件条目工厂。  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //获取文件上传需要保存的路径，upload文件夹需存在。  
        String path = request.getSession().getServletContext().getRealPath("/") + "Upload/BBS"; 
        File parentPath = new File(path);
        if(!parentPath.exists()){
        	parentPath.mkdirs();
        }
        System.out.println(path);
        //设置暂时存放文件的存储室，这个存储室可以和最终存储文件的文件夹不同。因为当文件很大的话会占用过多内存所以设置存储室。  
        factory.setRepository(new File(path));  
        //设置缓存的大小，当上传文件的容量超过缓存时，就放到暂时存储室。  
        factory.setSizeThreshold(1024*1024);  
        //上传处理工具类（高水平API上传处理？）  
        ServletFileUpload upload = new ServletFileUpload(factory);  
           
        try{  
        	int i = 0;
            //调用 parseRequest（request）方法  获得上传文件 FileItem 的集合list 可实现多文件上传。  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
            for(FileItem item:list){  
                //获取表单属性名字。  
                String name = item.getFieldName();  
                //如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。  
                if(item.isFormField()){  
                    //获取用户具体输入的字符串，
                    String value = item.getString();
                    ///!!!
                    ///编码格式不知为什么会变成 ISO-8859-1
                    ///!!!
                    value = new String(value.getBytes("ISO-8859-1"), "utf-8");
                    String dataname = item.getFieldName();
                    System.out.println("name:" + dataname);
                    System.out.print(getEncoding(value) + "   ");  //编码格式
                    System.out.println( "value:" + value);
                    request.setAttribute(name, value);  
                    if(dataname.equals("userPhone")){  //手机号码
                    	time = time.replaceAll("[:,\\s+,-]", "");
                    	path = path + "/" + value + "/" + time;    
                    	File fp = new File(path);   //创建图片保存目录
                    	if(!fp.exists()){
                    		fp.mkdirs();
                    	}
                    	System.out.println(path);
                    	postProfile.post_phone = value;
                    } else if(dataname.equals("userName")){  //用户名
                    	postProfile.post_publisher = value;
                    } else if(dataname.equals("title")){  //标题
                    	postProfile.post_title = value;
                    } else if(dataname.equals("content")){  //内容
                    	postProfile.post_text = value;
                    }
                }  
                //如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。  
                else{   
                    //获取路径名  
                    String value = item.getName();  
                    //取到最后一个反斜杠。  
                    int start = value.lastIndexOf("\\");  
                    //截取上传文件的 字符串名字。+1是去掉反斜杠。  
                    String filename =  (i++) + "" + value.substring(start+1);  
                    picNames.add(filename);
                    
                    request.setAttribute(name, filename);  
                       
                    /*第三方提供的方法直接写到文件中。 
                     * item.write(new File(path,filename));*/ 
                    //收到写到接收的文件中。  
                    OutputStream out = new FileOutputStream(new File(path,filename));  
                    InputStream in = item.getInputStream();  
                       
                    int length = 0;  
                    byte[] buf = new byte[1024];  
                    System.out.println("获取文件总量的容量:"+ item.getSize());  
                       
                    while((length = in.read(buf))!=-1){  
                        out.write(buf,0,length);  
                    }  
                    in.close();  
                    out.close();  
                }  
            }
            BBSService.addPic(postProfile, picNames);
            System.out.println("图片数量：" + picNames.size());
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
				System.out.println("JSON打包异常!");
				e.printStackTrace();
			}
        	String responMessage = respArray.toString();
        	System.out.println("返回报文:" + responMessage);
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
