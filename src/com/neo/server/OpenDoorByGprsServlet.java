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
import com.neo.util.OpenDoorUtil;
import com.neo.util.Timeutil;

/**
 * GPRS��������
 * @author Administrator
 *
 */
public class OpenDoorByGprsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("******" + Timeutil.getCurrentTime() + ":GPRS��������******");
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charset=UTF-8");
		String reqMessage;                                              // ������Ϣ
		String respMessage;                                             // ��Ӧ��Ϣ
		JSONArray reqArray = null;                                      // �������
		JSONArray respArray = null;                                     // ��Ӧ����
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					req.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			reqMessage = sb.toString();
			System.out.println("������:" + reqMessage);
			reqArray = new JSONArray(reqMessage);
			String phone = reqArray.getJSONObject(0).getString("PHONE");
			String odPass = reqArray.getJSONObject(0).getString("OD_PASS");
			boolean result = UserService.checkDoorPassword(phone, odPass);
			if(result){
				//������ȷ
				/**
				 * ���ÿ���״̬����Ϊ true
				 * �����߳� 5s ��״̬������λ false
				 */
				new OpenDoorUtil().openDoor();
				respArray = new JSONArray().put(new JSONObject().put("RESULT", 1));
			}else {
				respArray = new JSONArray().put(new JSONObject().put("RESULT", 0));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			respMessage = respArray.toString();
			System.out.println("���ر���:" + respMessage);
			PrintWriter out = resp.getWriter();
			out.write(respMessage);
			out.flush();
			out.close();
		}
	}
}
