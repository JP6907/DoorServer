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
import org.json.JSONException;
import org.json.JSONObject;

import com.neo.service.UserService;
import com.neo.util.Timeutil;
/**
 * 找回密码
 * 设置新密码
 * @author Administrator
 *
 */
public class FindPasswordServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("******" + Timeutil.getCurrentTime() + ":找回密码，设置新密码******");
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
			//是登陆密码还是开门密码
			String passwordType = reqArray.getJSONObject(0).getString("TYPE");
			//修改密码
			UserService.setNewPassword(phone, password, passwordType);
			respArray = new JSONArray().put(new JSONObject().put("RESULT","true"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
