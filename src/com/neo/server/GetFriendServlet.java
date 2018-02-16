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

import com.neo.bean.FriendInfo;
import com.neo.service.UserService;
import com.neo.util.Timeutil;

/**
 * ��ȡ������Ϣ
 * ���� �ֻ�����    �û���    ��ע��    ͷ������·��
 * @author Administrator
 *
 */
@WebServlet("/GetFriendServlet")
public class GetFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFriendServlet() {
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
		String curtime = Timeutil.getCurrentTime();
		System.out.println("******" + curtime + ":��ȡ�����б�******");
		PrintWriter out = response.getWriter();		
		request.setCharacterEncoding("UTF-8");
		String reqMessage;                                              // ������Ϣ
		JSONArray reqArray = null;                                      // �������
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
			System.out.println("������:" + reqMessage);
			reqArray = new JSONArray(reqMessage);
			//���ѵ�id��Ϣ
			String userId = reqArray.getJSONObject(0).getString("userId");
			//��ѯ�û��������ݣ���ȡ���к��ѵ�id
			List<FriendInfo> friendList = UserService.searchFriends(userId);
			for(int i = 0 ;i < friendList.size()  ; i++){
				JSONObject jo = new JSONObject();
				jo.put("phone", friendList.get(i).getPhone());
				jo.put("imgPath", friendList.get(i).getImgPath());
				jo.put("nickname", friendList.get(i).getNickname());
				jo.put("remarkName", friendList.get(i).getRemarkName());
				ja.put(jo);
			}
			JSONObject jo = new JSONObject();
			ja.put(jo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String respMessage = ja.toString();
		System.out.println("[���ر���]:" + respMessage);
		out.write(respMessage);
		out.flush();
		out.close();
	}
}
