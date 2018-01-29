package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		String url;
		
		if("list".equals(actionName)) {
			WebUtil.setBeforePage(request, "board?a=list");
			
			BoardDao boardDao = new BoardDao();
			List<BoardVo> bList = boardDao.getList();
			
			request.setAttribute("bList", bList);
			url = "/WEB-INF/views/board/list.jsp";
			WebUtil.forward(request, response, url);

		} else if("writeform".equals(actionName)) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if(authUser == null) {
				url = "board?a=list";
				WebUtil.redirect(request, response, url);
			} else {
				url = "/WEB-INF/views/board/write.jsp";
				WebUtil.forward(request, response, url);
			}
			
		} else if("write".equals(actionName)) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			if(authUser == null) {
				url = "board?a=list";
				WebUtil.redirect(request, response, url);
			}
			else {
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				content = content.replace("\r\n", "<br>");
				
				BoardVo boardVo = new BoardVo();
				boardVo.setTitle(title);
				boardVo.setContent(content);
				boardVo.setHit(0);
				boardVo.setUserNo(authUser.getNo());
				boardVo.setName(authUser.getName());
				
				BoardDao boardDao = new BoardDao();
				boardDao.write(boardVo);
				
				url = "board?a=list";
				WebUtil.redirect(request, response, url);
			}
			
		} else if("view".equals(actionName)) {
			int no = Integer.valueOf(request.getParameter("no"));
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = boardDao.getArticle(no);
			boardDao.updateHit(no);
			
			WebUtil.setBeforePage(request, "board?a=view&no=" + no);

			request.setAttribute("boardVo", boardVo);
			url = "/WEB-INF/views/board/view.jsp";
			WebUtil.forward(request, response, url);
			
		} else if("modifyform".equals(actionName)) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");

			int no = Integer.valueOf(request.getParameter("no"));
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = boardDao.getArticle(no);
			
			if((authUser != null) && (authUser.getNo() == boardVo.getUserNo())) {
				request.setAttribute("boardVo", boardVo);
				url = "/WEB-INF/views/board/modify.jsp";				
				WebUtil.forward(request, response, url);
			} else {
				url = "board?a=list";
				WebUtil.redirect(request, response, url);
			}
			
		} else if("modify".equals(actionName)) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			int no = Integer.valueOf(request.getParameter("no"));
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = boardDao.getArticle(no);

			if((authUser != null) && (authUser.getNo() == boardVo.getUserNo())) {
				String newTitle = request.getParameter("title");
				String newContent = request.getParameter("content");
				newContent = newContent.replace("\r\n", "<br>");
				
				boardVo.setTitle(newTitle);
				boardVo.setContent(newContent);
				
				boardDao.modify(boardVo);
			} 
			
			url = "board?a=list";
			WebUtil.redirect(request, response, url);			
			
		} else if("delete".equals(actionName)) {
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			
			int no = Integer.parseInt(request.getParameter("no"));
			int userNo = Integer.parseInt(request.getParameter("userno"));
			
			if((authUser != null) && (authUser.getNo() == userNo)) {
				BoardDao boardDao = new BoardDao();
				boardDao.delete(no);
				
			}

			url = "board?a=list";
			WebUtil.redirect(request, response, url);
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
