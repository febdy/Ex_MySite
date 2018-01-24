package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GuestBookDao dao;
		GuestVo vo;
		String url;
		String actionName = request.getParameter("a");

		if ("list".equals(actionName)) {
			dao = new GuestBookDao();
			List<GuestVo> gList = dao.getList();
			request.setAttribute("gList", gList);
			
			url = "/WEB-INF/views/guestbook/list.jsp";

			HttpSession session = request.getSession();
			session.setAttribute("beforePage", "guestbook?a=list");

			WebUtil.forward(request, response, url);
			
		} else if ("add".equals(actionName)) {			
			request.setCharacterEncoding("UTF-8");

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			dao = new GuestBookDao();
			vo = new GuestVo(1, name, password, content, "");

			dao.insert(vo);

			url = "guestbook?a=list";
			WebUtil.redirect(request, response, url);

		} else if ("deleteform".equals(actionName)) {
			int no = Integer.parseInt(request.getParameter("no"));
			request.setAttribute("no", no);
			url = "/WEB-INF/views/guestbook/deleteform.jsp";
			WebUtil.forward(request, response, url);
			
		} else if("delete".equals(actionName)){
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");

			dao = new GuestBookDao();
			dao.delete(no, password);
			
			url = "guestbook?a=list";
			WebUtil.redirect(request, response, url);
			
		} else {
			System.out.println("a is wrong value.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
