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
 * ��ȡ������Ϣ
 * @author Administrator
 * ͨ��xml��ʽ������������
 * ����3���ڵ���Ϣ
 * time����ȡ������Ϣ��ʱ���
 * flag���ж��ǻ�ȡ�µ�����i��Ϣ���Ǿɵ�������Ϣ
 * deletedId���ͻ��˻�����������ӵ�id  ��ʽΪ�� "id1,id2,id3..."
 * 
 * ���flag Ϊnew
 * 		��ȡ���µ�10�����ӣ�����µ�������������10�����򲻼��deletedId������-1��ɾ�����пͻ��˻���
 * 						����µ������������ڵ���10��������deletedId�����ط��������Ѿ���ɾ����id
 * ���flagΪ  old
 * 		��ȡʱ���֮ǰ�����ʱ����10�����ݣ�deletedId���� null
 *
 *
 * deletedId �������������	
 *		1.��   ��ɾ������
 *		2.�м�������  ɾ��ָ������
 *		3.-1   ɾ�����л���
 *
 *
 *	��ȡ�����������ȡ����������ͬ
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
		System.out.println("******" + time + ":��ȡ��������******");
		response.setContentType("text/xml;charset=utf-8");
		
		PrintWriter out = response.getWriter();		
		
		String datetime = "";    //�ͻ��˱��ػ����ʱ���
		String flag = "";		//��ȡ���ݱ�־λ
		String deletedId = "";   //�ͻ��˻������ݵ�����id
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
		System.out.println("�ͻ�����������: " + datetime);
		
		List<PostProfile> postProfileList = new ArrayList<PostProfile>();
		try {
			//���������ӵ�����
			int count = BBSService.countNewPostprofile(datetime);
			//����ʱ����ȡ����
			postProfileList = BBSService.GetPostProfiles(flag, datetime);
			//��鱾�ػ���������Ƿ��ڷ������ϱ�ɾ����
			deletedId = BBSService.checkDeletedId(flag, deletedId, count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String respMessage = XmlHandle.packPostDada(postProfileList, time ,deletedId);
		//System.out.println("bianma:" + getEncoding(postProfileList.get(0).getPost_text()));
		System.out.println("[���ر���]:" + respMessage);
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
