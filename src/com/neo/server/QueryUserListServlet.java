package com.neo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.neo.bean.DoorUser;
import com.neo.service.UserService;
import com.neo.util.Timeutil;

/**
 * 查询多个用户信息
 * @author Administrator
 *
 */
@WebServlet("/QueryUserListServlet")
public class QueryUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryUserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String curtime = Timeutil.getCurrentTime();
				System.out.println("******" + curtime + ":查询多个用户信息******");
				PrintWriter out = response.getWriter();		
				request.setCharacterEncoding("UTF-8");
				String reqMessage;                                              // 请求信息
				JSONArray reqArray = null;                                      // 请求对象
				JSONArray ja = new JSONArray(); 
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
					//查询的数量
					int num = reqArray.length();
					System.out.println("查询数量:" + num);
					for(int i = 0;i<num;i++){
						String userPhone = reqArray.getJSONObject(i).getString("username");
						DoorUser user = UserService.queryUser(userPhone);
						JSONObject jo = new JSONObject();
						jo.put("phone", user.getdPhone());
						jo.put("imgPath",user.getdImagePath());
						jo.put("nickname", user.getdName());
						ja.put(jo);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String respMessage = ja.toString();
				System.out.println("[返回报文]:" + respMessage);
				out.write(respMessage);
				out.flush();
				out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
