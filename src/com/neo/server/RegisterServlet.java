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

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("******" + Timeutil.getCurrentTime() + ":注册请求******");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charset=UTF-8");
		String reqMsg;
		String respMsg;
		JSONArray reqArray = null;
		JSONArray respArray = null;
		try {
			// 读取用户请求
			BufferedReader br = new BufferedReader(new InputStreamReader(
					req.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while((temp = br.readLine()) != null){
				sb.append(temp);
			}
			br.close();
			reqMsg = sb.toString();
			System.out.println("请求报文:" + reqMsg);
			// 由请求信息生成JSONArray对象
			reqArray = new JSONArray(reqMsg);
			String phone = reqArray.getJSONObject(0).getString("PHONE");
			String password = reqArray.getJSONObject(0).getString("PASSWORD");
			if(UserService.doorUserRegister(phone, password)){
				respArray = new JSONArray().put(new JSONObject().put("RESULT","true"));
			}else{
				respArray = new JSONArray().put(new JSONObject().put("RESULT","false"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			respMsg = ( respArray == null ? "" : respArray.toString() );
			System.out.println("返回报文:" + respMsg);
			PrintWriter out = resp.getWriter();
			out.write(respMsg);
			out.flush();
			out.close();
		}
	}

}
