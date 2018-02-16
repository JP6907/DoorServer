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
import com.neo.service.BBSService;
import com.neo.service.NoticeService;
import com.neo.util.Timeutil;


/**
 * 获取公告数据
 * 详见  com.neo.server.GetPostDataServlet 类
 */
@WebServlet("/GetNoticeServlet")
public class GetNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNoticeServlet() {
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
		System.out.println("******" + time + ":获取公告数据******");	
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
			String clientTime = reqArray.getJSONObject(0).getString("time");
			String flag = reqArray.getJSONObject(0).getString("flag");
			String deletedId = (reqArray.getJSONObject(0).getString("deletedId")).trim();
			
			System.out.println("time:" + clientTime);
			System.out.println("flag:" + flag);
			System.out.println("deletedId:" + deletedId);
			
			int count = NoticeService.countNewNotice(clientTime);
			//根据时间点获取公告
			List<Announcement> AnnouncementList = NoticeService.getAnnouncements(flag, clientTime);
			
			deletedId = NoticeService.checkDeletedId(flag, deletedId, count);
			
			JSONObject jo1 = new JSONObject();
			jo1.put("time", time);
			jo1.put("deletedId", deletedId);
			ja.put(jo1);
			for(int i = AnnouncementList.size()-1 ;i >=0  ; i--){
				JSONObject jo = new JSONObject();
				jo.put("id", AnnouncementList.get(i).getId());
				jo.put("date", AnnouncementList.get(i).getDate());
				jo.put("content", AnnouncementList.get(i).getContent());
				jo.put("title", AnnouncementList.get(i).getTitle());
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
