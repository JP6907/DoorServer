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
 * ��̨ϵͳ
 * ����Ա�˺ŵ�½
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
		//���ñ����ʽ
		request.setCharacterEncoding("utf-8");
		//��ȡ��½�˺ź�����
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		if(UserService.managerLogin(userName, passWord)){//��֤����
			//��½�ɹ�
			System.out.println("******" + Timeutil.getCurrentTime() + ":��½��̨����ϵͳ:" + request.getLocalAddr() + "******");
			//���õ�½״̬
			request.getSession().setAttribute("loginSign", "true");
			//���õ�½�����
			request.setAttribute("loginresult", "true");
			//ҳ����ת����ת�������б�ҳ��
			response.sendRedirect(request.getContextPath() + "/announcementList.action");
		} else{
			//��½ʧ�ܣ����õ�½������ڵ�½ҳ����ʾ��½���
			request.setAttribute("loginresult", "false");
			//��ת����½ҳ��
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
