package com.neo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ��½������ ���˺�̨����ϵͳ
 * ������δ��½״̬��ҳ��������ת����½ҳ��
 * 
 * @author Administrator
 *
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

	private FilterConfig config;

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse respo = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		String isLogined = (String) session.getAttribute("loginSign");
		if (isLogined!=null && isLogined.equals("true")) {
			// ����Ѿ���½
			chain.doFilter(request, response);
		} else {
			// δ��½ ����ת�� ��½ҳ��
			respo.sendRedirect(req.getContextPath());
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		config = fConfig;
	}

}