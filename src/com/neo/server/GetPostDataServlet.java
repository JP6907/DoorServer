package com.neo.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.neo.bean.PostProfile;
import com.neo.service.BBSService;
import com.neo.util.Timeutil;
import com.neo.util.XmlHandle;


/**
 * 获取帖子信息
 * @author Administrator
 * 通过xml方式接收请求数据
 * 包含3个节点信息
 * time：获取帖子信息的时间点
 * flag：判断是获取新的帖子i信息还是旧的帖子信息
 * deletedId：客户端缓存的所有帖子的id  格式为： "id1,id2,id3..."
 * 
 * 如果flag 为new
 * 		获取最新的10条帖子，如果新的帖子数量超过10条，则不检查deletedId，返回-1，删除所有客户端缓存
 * 						如果新的帖子数量少于等于10条，则检查deletedId，返回服务器上已经被删除的id
 * 如果flag为  old
 * 		获取时间点之前，最靠近时间点的10条数据，deletedId返回 null
 *
 *
 * deletedId 返回有三种情况	
 *		1.空   不删除缓存
 *		2.有几个数字  删除指定缓存
 *		3.-1   删除所有缓存
 *
 *
 *	获取公告数据与获取帖子数据相同
 */
@WebServlet("/GetPostDataServlet")
public class GetPostDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String time = Timeutil.getCurrentTime();
		System.out.println("******" + time + ":获取帖子数据******");
		response.setContentType("text/xml;charset=utf-8");
		
		PrintWriter out = response.getWriter();		
		
		String datetime = "";    //客户端本地缓存的时间点
		String flag = "";		//获取数据标志位
		String deletedId = "";   //客户端缓存数据的所有id
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(request.getInputStream());
			NodeList list = document.getElementsByTagName("Information");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				NodeList childNodes = element.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						if ("time".equals(childNodes.item(j).getNodeName())) {
							datetime = childNodes.item(j).getFirstChild().getNodeValue();
						} else if ("flag".equals(childNodes.item(j).getNodeName())) {
							flag = childNodes.item(j).getFirstChild().getNodeValue();
						} else if ("deletedId".equals(childNodes.item(j).getNodeName())) {
							deletedId = (childNodes.item(j).getFirstChild().getNodeValue()).trim();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("time:" + datetime);
		System.out.println("flag:" + flag);
		System.out.println("deletedId:" + deletedId);
		System.out.println("客户端最新日期: " + datetime);
		
		List<PostProfile> postProfileList = new ArrayList<PostProfile>();
		try {
			//计算新帖子的数量
			int count = BBSService.countNewPostprofile(datetime);
			//根据时间点获取帖子
			postProfileList = BBSService.GetPostProfiles(flag, datetime);
			//检查本地缓存的数据是否在服务器上被删除了
			deletedId = BBSService.checkDeletedId(flag, deletedId, count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String respMessage = XmlHandle.packPostDada(postProfileList, time ,deletedId);
		//System.out.println("bianma:" + getEncoding(postProfileList.get(0).getPost_text()));
		System.out.println("[返回报文]:" + respMessage);
		out.write(respMessage);
		out.flush();
		out.close();
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
       encode = "UTF-8";
       try {      
           if (str.equals(new String(str.getBytes(encode), encode))) {      
                String s1 = encode;      
               return s1;      
            }      
        } catch (Exception exception1) {      
        }     
       encode = "GB2312";
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
