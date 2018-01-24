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
		
		actionName = request.getParameter("a");
		
		if("joinform".equals(actionName)) {
			url = "/WEB-INF/views/user/joinform.jsp";
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
			url = "/WEB-INF/views/user/joinsuccess.jsp";
			WebUtil.forward(request, response, url);
			
		} else if("loginform".equals(actionName)) {
			url = "/WEB-INF/views/user/loginform.jsp";
			WebUtil.forward(request, response, url);
		
		} else if("login".equals(actionName)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserDao dao = new UserDao();
			UserVo userVo = dao.getUser(email, password);
			
			if(userVo == null) {
				System.out.println("Login failed.");
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
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
			
		} else if("modifyform".equals(actionName)) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			if(authUser == null) {
				WebUtil.redirect(request, response, "user?a=loginform");
				
			} else {
				int no = authUser.getNo();

				UserDao userDao = new UserDao();
				UserVo userVo = userDao.getUser(no);
				
				session.setAttribute("userVo", userVo);
				url = "/WEB-INF/views/user/modifyform.jsp";
				WebUtil.forward(request, response, url);
			}
			
		} else if("modify".equals(actionName)) {
			
			HttpSession session = request.getSession(); 
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			if(authUser == null) {
				WebUtil.redirect(request, response, "user?a=loginform");
				
			} else {	
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");
				System.out.println(gender);
				
				UserDao userDao = new UserDao();
				UserVo userVo = (UserVo)session.getAttribute("userVo");
			
				userVo.setName(name);
				if(password != "")
					userVo.setPassword(password);
				userVo.setGender(gender);
				userDao.update(userVo);
				
				authUser.setName(name); // authUser 이름 갱신

				url = "main";
				WebUtil.redirect(request, response, url);
			}
		
		}
		
	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
