package com.neo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import com.neo.service.UserService;
import com.neo.util.Timeutil;

public class UpdateServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("******" + Timeutil.getCurrentTime() + ":������Ϣ����******");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charset=UTF-8");
		String reqMsg;
		String respMsg;
		JSONArray reqArray = null;
		JSONArray respArray = null;
		try {
			// ��ȡ�û�����
			BufferedReader br = new BufferedReader(new InputStreamReader(
					req.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while((temp = br.readLine()) != null){
				sb.append(temp);
			}
			br.close();
			reqMsg = sb.toString();
			System.out.println("������:" + reqMsg);
			// ��������Ϣ����JSONArray����
			reqArray = new JSONArray(reqMsg);
			int ID = reqArray.getJSONObject(0).getInt("ID");
			String field = reqArray.getJSONObject(0).getString("FIELD");
			String updateContent = reqArray.getJSONObject(0).getString("CONTENT");
			
			switch (UserService.updateInformation(field, updateContent, ID)) {
			
			case 1:   // �޸��ǳ�
				System.out.println("**********�޸��û����ɹ�");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","1"));
				break;
				
			case 2:   // �޸�����
				System.out.println("**********�޸ĵ�½����ɹ�");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","2"));
				break;
				
			case 3:   // �޸ĵ绰
				System.out.println("**********�޸��ֻ�����ɹ�");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","3"));
				break;
				
			case 4:   // �޸�¥��
				System.out.println("**********�޸�¥���ɹ�");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","4"));
				break;
				
			case 5:   // �޸��ź�
				System.out.println("**********�޸��źųɹ�");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","5"));
				break;
			
			case 6:   // �޸Ŀ�������ɹ�
				System.out.println("**********�޸Ŀ�������ɹ�");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","6"));
				break;
				
			case 7:   // �޸�����  �����벻��ȷ
				System.out.println("**********�޸����� ʧ�� �����벻��ȷ");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","7"));
				break;
				
			case -1:   // �޸�ʧ��
				System.out.println("**********�޸�ʧ��");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","-1"));
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			respMsg = ( respArray == null ? "" : respArray.toString() );
			System.out.println("���ر���:" + respMsg);
			PrintWriter out = resp.getWriter();
			out.write(respMsg);
			out.flush();
			out.close();
		}
	}
}
