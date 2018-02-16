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

import com.neo.service.UserService;
import com.neo.util.OpenDoorUtil;
import com.neo.util.Timeutil;

/**
 * 下位机一直保持请求开门应答
 * 
 * @author Administrator
 *
 */
@WebServlet("/GprsServlet")
public class GprsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GprsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		System.out.println("******" + Timeutil.getCurrentTime() + ":开门请求 get******");
		PrintWriter out = response.getWriter();
		String reqMessage = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			reqMessage = sb.toString();
			System.out.println("请求报文:" + reqMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果请求开门
		if (reqMessage.equals("opendoor")) {
			//开门状态为真
			if (OpenDoorUtil.openDoorState) {
				out.print("open");
				System.out.println("******开门!******");
			} else {
				System.out.println("******拒绝!******");
				out.print("error");
			}
		} else {
			System.out.println("length:" + reqMessage.length());
			if (reqMessage.length() == 23) {
			System.out.println("[接收用户密码]:  " + reqMessage);
			String sign = reqMessage.substring(0, 5);
			String userPhone = reqMessage.substring(6,17);
			String passWord = reqMessage.substring(17, 23);
			System.out.println("sign:" + sign);
			System.out.println("phone:" + userPhone);
			System.out.println("password:" + passWord);
				// 如果验证密码
				if (sign.equals("check")) {
					System.out.println("检查密码: ");
					//检查开门密码
					boolean result = UserService.checkDoorPassword(userPhone, passWord);
					if (result) {
						// 开门
						new OpenDoorUtil().openDoor();
						out.print("open");
						System.out.println("密码正确！");
					} else {
						//密码错误
						out.print("error");
						System.out.println("密码错误！");
					}
				}
			} else{
				out.print("error");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("******开门请求 post******");
		// PrintWriter out = response.getWriter();
		// out.print("doPost method");
	}

}
