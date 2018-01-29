package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebUtil {
	
	public static void forward(HttpServletRequest request, 
							   HttpServletResponse response, 
							   String url) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	public static void redirect(HttpServletRequest request, 
								HttpServletResponse response, 
								String url) throws IOException {
		
		response.sendRedirect(url);
		
	}
	
	public static void setBeforePage(HttpServletRequest request, String currPage) {
		HttpSession session = request.getSession();
		session.setAttribute("beforePage", currPage);
	}
	
	public static String getBeforePage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		return (String) session.getAttribute("beforePage");
	}
	
}
