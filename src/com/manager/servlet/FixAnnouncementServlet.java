package com.manager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neo.dao.ManagerDao;
import com.neo.service.NoticeService;
import com.neo.util.Timeutil;

/**
 * �޸Ĺ���
 * ɾ��ԭ���Ĺ��棬�����µĹ���
 * @author Administrator
 *
 */
@WebServlet("/FixAnnouncementServlet")
public class FixAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FixAnnouncementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("******" + Timeutil.getCurrentTime() + ":�޸Ĺ���******");
		request.setCharacterEncoding("utf-8");
		//��ȡ�������
		String title = request.getParameter("title");
		//��ȡ��������
		String content = request.getParameter("content");
		//��ȡ����id
		String id = (String) request.getSession().getAttribute("id");
		System.out.println("�������:" + title);
		System.out.println("��������:" + content);
		//ɾ��ԭ����
		NoticeService.deleteOneAnnouncement(id);
		//�����¹���
		NoticeService.addAnnouncement(title, content);
		//ҳ����ת
		response.sendRedirect(request.getContextPath() + "/announcementList.action");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
