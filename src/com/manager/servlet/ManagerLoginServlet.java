package com.manager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neo.service.UserService;
import com.neo.util.Timeutil;

/**
 * 后台系统
 * 管理员账号登陆
 * @author Administrator
 *
 */
@WebServlet("/ManagerLogin")
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		//获取登陆账号和密码
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		if(UserService.managerLogin(userName, passWord)){//验证密码
			//登陆成功
			System.out.println("******" + Timeutil.getCurrentTime() + ":登陆后台管理系统:" + request.getLocalAddr() + "******");
			//设置登陆状态
			request.getSession().setAttribute("loginSign", "true");
			//设置登陆结果，
			request.setAttribute("loginresult", "true");
			//页面跳转，跳转到公告列表页面
			response.sendRedirect(request.getContextPath() + "/announcementList.action");
		} else{
			//登陆失败，设置登陆结果，在登陆页面显示登陆结果
			request.setAttribute("loginresult", "false");
			//跳转到登陆页面
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
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
