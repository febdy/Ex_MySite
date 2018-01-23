package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url, actionName;
		final String userDefaultUrl = "/WEB-INF/views/user";
		
		actionName = request.getParameter("a");
		
		if("joinform".equals(actionName)) {
			url = userDefaultUrl + "/joinform.jsp";
			WebUtil.forward(request, response, url);
			
		} else if("join".equals(actionName)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setPassword(password);
			userVo.setGender(gender);
			
			UserDao userDao = new UserDao();
			userDao.insert(userVo);
			
			url = "user?a=joinsuccess";
			WebUtil.forward(request, response, url);
			
		} else if("joinsuccess".equals(actionName)) {
			url = userDefaultUrl + "/joinsuccess.jsp";
			WebUtil.forward(request, response, url);
			
		} else if("loginform".equals(actionName)) {
			url = userDefaultUrl + "/loginform.jsp";
			WebUtil.forward(request, response, url);
		
		} else if("login".equals(actionName)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserDao dao = new UserDao();
			UserVo userVo = dao.getUser(email, password);
			
			if(userVo == null) {
				System.out.println("Login failed.");
			}
			else {
				System.out.println(userVo.getEmail() + " Login Success.");
				
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", userVo);
				
				url = "/mysite/main";
				WebUtil.redirect(request, response, url);
			}
			
		} else if("logout".equals(actionName)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate(); // 초기화
			
			url = "/mysite/main";
			WebUtil.redirect(request, response, url);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
