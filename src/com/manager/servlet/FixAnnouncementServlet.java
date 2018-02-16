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
 * 修改公告
 * 删除原来的公告，发布新的公告
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
		System.out.println("******" + Timeutil.getCurrentTime() + ":修改公告******");
		request.setCharacterEncoding("utf-8");
		//获取公告标题
		String title = request.getParameter("title");
		//获取公告内容
		String content = request.getParameter("content");
		//获取公告id
		String id = (String) request.getSession().getAttribute("id");
		System.out.println("公告标题:" + title);
		System.out.println("公告内容:" + content);
		//删除原公告
		NoticeService.deleteOneAnnouncement(id);
		//发布新公告
		NoticeService.addAnnouncement(title, content);
		//页面跳转
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
