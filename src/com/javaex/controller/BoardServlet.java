package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		String url;
		
		if("list".equals(actionName)) {
			url = "/WEB-INF/views/board/list.jsp";
			WebUtil.forward(request, response, url);

		} else if("writeform".equals(actionName)) {
			url = "/WEB-INF/views/board/write.jsp";
			WebUtil.forward(request, response, url);

		} else if("write".equals(actionName)) {
			url = "board?a=list";
			WebUtil.redirect(request, response, url);
			
		} else if("view".equals(actionName)) {
			url = "/WEB-INF/views/board/view.jsp";
			WebUtil.forward(request, response, url);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
