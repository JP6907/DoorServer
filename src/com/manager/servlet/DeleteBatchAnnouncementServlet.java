package com.manager.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neo.service.NoticeService;
import com.neo.util.Timeutil;

/*
 * ����ɾ����������
 */
@WebServlet("/DeleteBatchAnnouncementServlet")
public class DeleteBatchAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBatchAnnouncementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//���ñ����ʽ
		System.out.println("******" + Timeutil.getCurrentTime() + ":����ɾ������******");
		request.setCharacterEncoding("utf-8");
		//��ȡҳ��ֵ
		String[] ids = request.getParameterValues("id");
		//ɾ��
		NoticeService.deleteBatchAnnouncement(ids);
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
