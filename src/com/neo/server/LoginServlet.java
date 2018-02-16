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

import com.neo.bean.DoorUser;
import com.neo.service.UserService;
import com.neo.util.Timeutil;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");             // ������������Ϊjson
		String reqMessage;                                              // ������Ϣ
		String respMessage;                                             // ��Ӧ��Ϣ
		JSONArray reqArray = null;                                      // �������
		JSONArray respArray = null;     // ��Ӧ����
		System.out.println("******" + Timeutil.getCurrentTime() + ":��½����******");
		//response.getWriter().write("LoginServlet");
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			reqMessage = sb.toString();
			System.out.println("������:" + reqMessage);
			reqArray = new JSONArray(reqMessage);
			String phone = reqArray.getJSONObject(0).getString("PHONE");
			String password = reqArray.getJSONObject(0).getString("PASSWORD");
			DoorUser userInfo = UserService.doorUserLogin(phone, password);
			if(userInfo != null){
				respArray = new JSONArray().put(new JSONObject().put("ID", userInfo.getId())
																.put("USERNAME", userInfo.getdName())
																.put("PHONE", userInfo.getdPhone())
																.put("BUILDING", userInfo.getBuilding())
																.put("DOORID", userInfo.getDoorId())
																.put("PATH", userInfo.getdImagePath()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			respMessage = ( respArray == null ? "" : respArray.toString() );
			System.out.println("���ر���:" + respMessage);
			PrintWriter out = response.getWriter();
			out.write(respMessage);
			out.flush();
			out.close();
		}
	}

}

