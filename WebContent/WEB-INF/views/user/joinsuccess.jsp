<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.UserVo" %>
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser");
%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	<title>Join Success</title>
</head>
<body>
	
	<div id="container">
	
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<% 
					if(authUser == null) {
				%>
					<!-- 로그인 전 -->
					<li><a href="user?a=loginform">로그인</a></li>
					<li><a href="user?a=joinform">회원가입</a></li>
				<% } else { %>
					<!-- 로그인 후 -->					 
					<li><a href="">회원정보수정</a></li>
					<li><a href="user?a=logout">로그아웃</a></li> 
					<li> <%=authUser.getName() %>님 안녕하세요^^;</li>
				<% } %>
			</ul>
		</div> <!-- /header -->
				
		<div id="navigation">
			<ul>
				<li><a href="">--</a></li>
				<li><a href="">방명록</a></li>
				<li><a href="">게시판</a></li>
			</ul>
		</div> <!-- /navigation -->
		
		<div id="wrapper">
			<div id="content">
				<div id="user">
					
					<p class="jr-success">
						회원가입을 축하합니다.
						<br><br>
						<a href="user?a=loginform">로그인하기</a>
					</p>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<div id="footer">
			<p>(c)opyright 2015,2016,2017</p>
		</div> <!-- /footer -->
		
	</div> <!-- /container -->

</body>
</html>