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
		
		System.out.println("******" + Timeutil.getCurrentTime() + ":更新信息请求******");
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
			int ID = reqArray.getJSONObject(0).getInt("ID");
			String field = reqArray.getJSONObject(0).getString("FIELD");
			String updateContent = reqArray.getJSONObject(0).getString("CONTENT");
			
			switch (UserService.updateInformation(field, updateContent, ID)) {
			
			case 1:   // 修改昵称
				System.out.println("**********修改用户名成功");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","1"));
				break;
				
			case 2:   // 修改密码
				System.out.println("**********修改登陆密码成功");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","2"));
				break;
				
			case 3:   // 修改电话
				System.out.println("**********修改手机号码成功");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","3"));
				break;
				
			case 4:   // 修改楼栋
				System.out.println("**********修改楼栋成功");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","4"));
				break;
				
			case 5:   // 修改门号
				System.out.println("**********修改门号成功");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","5"));
				break;
			
			case 6:   // 修改开门密码成功
				System.out.println("**********修改开门密码成功");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","6"));
				break;
				
			case 7:   // 修改密码  旧密码不正确
				System.out.println("**********修改密码 失败 旧密码不正确");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","7"));
				break;
				
			case -1:   // 修改失败
				System.out.println("**********修改失败");
				respArray = new JSONArray().put(new JSONObject().put("RESULT","-1"));
				break;
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
