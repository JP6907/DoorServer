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
		response.setContentType("text/json;charset=UTF-8");             // 设置内容类型为json
		String reqMessage;                                              // 请求信息
		String respMessage;                                             // 响应信息
		JSONArray reqArray = null;                                      // 请求对象
		JSONArray respArray = null;     // 响应对象
		System.out.println("******" + Timeutil.getCurrentTime() + ":登陆请求******");
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
			System.out.println("请求报文:" + reqMessage);
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
			System.out.println("返回报文:" + respMessage);
			PrintWriter out = response.getWriter();
			out.write(respMessage);
			out.flush();
			out.close();
		}
	}

}

