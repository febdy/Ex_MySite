<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/main.css" rel="stylesheet" type="text/css">
	<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<!-- 로그인 전 -->
				<li><a href="user?a=loginform">로그인</a></li>
				<li><a href="user?a=joinform">회원가입</a></li>
				
				<!-- 로그인 후 -->
				<!-- 
				<li><a href="">회원정보수정</a></li>
				<li><a href="">로그아웃</a></li> 
				<li> --님 안녕하세요^^;</li>
				-->
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
				<div id="site-introduction">
					<img style="width: 150px" id="profile" src="/mysite/assets/images/profile.jpg">
					<h2>안녕하세요.<br> --의 mysite에 방문하신 것을<br/> 환영합니다.</h2>
					<p>
						--
						<br>
						<br>
						<a href="">방명록</a>에 글 남기기
						<br>
					</p>
				</div>
			</div>
		</div>
		
		<div id="footer">
			<p>(c)opyright 2015,2016,2017</p>
		</div> <!-- /footer -->
		
	</div>
</body>
</html>