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

import com.neo.bean.Announcement;
import com.neo.bean.NoticeReply;
import com.neo.service.NoticeService;
import com.neo.util.Timeutil;

/**
 * 获取公告回复，目前不用
 * @author Administrator
 *
 */
@WebServlet("/GetNoticeReplyServlet")
public class GetNoticeReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNoticeReplyServlet() {
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
		System.out.println("******" + Timeutil.getCurrentTime() + ":获取公告回复数据******");
		//获取公告id
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");  
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
			int id = reqArray.getJSONObject(0).getInt("id");
			
			//根据公告id获取公告回复
			List<NoticeReply> l = NoticeService.getNoticeReplys(id);
			for(int i = l.size() - 1; i >= 0 ; i--){
				JSONObject jo = new JSONObject();
				jo.put("id", l.get(i).getReplyId());
				jo.put("datetime",l.get(i).getReplyDateTime());
				jo.put("floor", l.get(i).getReplyFloor());
				jo.put("judge",l.get(i).getReplyJudge().intValue());
				jo.put("noticeid", l.get(i).getReplyNoticeId());
				jo.put("publisher", l.get(i).getReplyPublisher());
				jo.put("responder",l.get(i).getReplyResponder());
				jo.put("text", l.get(i).getReplyText());
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
