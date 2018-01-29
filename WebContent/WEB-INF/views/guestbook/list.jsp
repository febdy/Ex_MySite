<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Guestbook</title>
</head>
<body>

	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form action="guestbook" method="post">
						
						<table>
							<tr>
								<td>이름</td><td>
								<c:choose>
									<c:when test="${empty authUser}">
										<input type="text" name="name" />
									</c:when>
									<c:otherwise>
										${authUser.name}
										<input type="hidden" name="name" value="${authUser.name}">
									</c:otherwise>
								</c:choose>
								</td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content" placeholder="내용을 입력해주세요."></textarea></td>
							</tr>
							<tr>
								<td><input type="hidden" name="a" value="add"></td>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>
					<ul>
						<li>							
							<c:forEach items="${gList}" var="gVo" varStatus="status">
								<table>
									<tr>
										<td>${gVo.no}</td>
										<td>${gVo.name}</td>
										<td>${gVo.date}</td>
										<td><a href="guestbook?a=deleteform&no=${gVo.no}">삭제</a></td>
									</tr>
									<tr>
										<td colspan=4>
											${gVo.content}
										</td>
									</tr>
								</table>
							</c:forEach>
							
							<br>
						</li>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div> <!-- /container -->

</body>
</html>