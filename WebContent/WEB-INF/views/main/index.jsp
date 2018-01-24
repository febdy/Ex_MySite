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
		
		<jsp:include page = "/WEB-INF/views/includes/header.jsp" />
		
		<jsp:include page = "/WEB-INF/views/includes/navigation.jsp" />

		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img style="width: 150px" id="profile" src="/mysite/assets/images/profile.jpg">
					<h2>안녕하세요.<br> --의 mysite에 방문하신 것을<br/> 환영합니다.</h2>
					<p>
						--
						<br>
						<br>
						<a href="guestbook?a=list">방명록</a>에 글 남기기
						<br>
					</p>
				</div>
			</div>
		</div>
		
		<jsp:include page = "/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>