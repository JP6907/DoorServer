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
import org.json.JSONException;
import org.json.JSONObject;

import com.neo.bean.Reply;
import com.neo.service.BBSService;
import com.neo.util.Timeutil;

/**
 * ���ӻظ�
 * @author Administrator
 *
 */
@WebServlet("/PostReplyServlet")
public class BbsReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BbsReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");             // ������������Ϊjson
		String reqMessage;                                              // ������Ϣ
		String responMessage;                                             // ��Ӧ��Ϣ
		JSONArray reqArray = null;                                      // �������
		JSONArray respArray = null;     // ��Ӧ����
		int id = -1;   //������id
		String time = Timeutil.getCurrentTime();
		String result = "Falied";
		System.out.println("******" + time + ":�������ӻظ�******");
		
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
			Reply reply = new Reply();
			reply.setReply_postid(Integer.valueOf(reqArray.getJSONObject(0).getString("reply_postid")));
			reply.setReply_judge(Integer.valueOf(reqArray.getJSONObject(0).getString("reply_judge")));
			reply.setReply_datetime(time);
			reply.setReply_responder(reqArray.getJSONObject(0).getString("reply_responder"));
			reply.setReply_publisher(reqArray.getJSONObject(0).getString("reply_publisher")); 
			reply.setReply_floor(Integer.valueOf(reqArray.getJSONObject(0).getString("reply_floor")));
			reply.setReply_text(reqArray.getJSONObject(0).getString("reply_text"));
			//��ȡ����󷵻�ֵ
			id = BBSService.addReply(reply);
			result = "Success";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			result = "Failed";
		} finally {
			try {
				respArray = new JSONArray().put(new JSONObject().put("RESULT",result)
						.put("ID",id + ""));
			} catch (JSONException e) {
				System.out.println("JSON����쳣!");
				e.printStackTrace();
			}
			responMessage = respArray.toString();
        	System.out.println("���ر���:" + responMessage);
			PrintWriter out = response.getWriter();
			out.write(responMessage);
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
