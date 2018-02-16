package com.neo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.neo.bean.Reply;
import com.neo.service.BBSService;
import com.neo.util.Timeutil;

/**
 * 获取帖子回复数据
 * @author Administrator
 *
 */
@WebServlet("/GetReplyDataServlet")
public class GetReplyDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReplyDataServlet() {
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
		System.out.println("******" + Timeutil.getCurrentTime() + ":获取帖子回复数据******");
			
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");             // 设置内容类型为json
		PrintWriter out = response.getWriter();		
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
			//获取帖子id
			int id = reqArray.getJSONObject(0).getInt("id");
			//根据帖子id获取帖子回复
			List<Reply> l = BBSService.getReply(id);
			for(int i = l.size() - 1; i >= 0 ; i--){
				JSONObject jo = new JSONObject();
				jo.put("id", l.get(i).getReply_id());
				jo.put("datetime",l.get(i).getReply_datetime());
				jo.put("floor", l.get(i).getReply_floor());//楼层
				jo.put("judge",l.get(i).getReply_judge());//是否回复楼主
				jo.put("postid", l.get(i).getReply_postid());//帖子的id
				jo.put("publisher", l.get(i).getReply_publisher());//帖子发布者
				jo.put("responder",l.get(i).getReply_responder());//回复者
				jo.put("text", l.get(i).getReply_text());//回复内容
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
}
