<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.GuestVo" %>
<%@ page import="com.javaex.vo.UserVo" %>
<%@ page import="java.util.List" %>
<% UserVo authUser = (UserVo)session.getAttribute("authUser"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>Guestbook</title>
</head>
<body>

	<div id="container">
		
		<jsp:include page = "/WEB-INF/views/includes/header.jsp" />
		
		<jsp:include page = "/WEB-INF/views/includes/navigation.jsp" />
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form action="guestbook" method="get">
						
						<table>
							<tr>
								<td>이름</td><td>
								<% if (authUser == null) { %>
									<input type="text" name="name" />
								<% } else { %>
								<%=authUser.getName()%>
									<input type="hidden" name="name" value="<%=authUser.getName()%>">
								<% } %>
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
						
							<% 
								List<GuestVo> gList = (List)request.getAttribute("gList");
								
								for(GuestVo gVo : gList){
									int no = gVo.getNo();
									String name = gVo.getName();
									String content = gVo.getContent();
									String date = gVo.getDate();
								
							%>
							
							<table>
								<tr>
									<td><%=no %></td>
									<td><%=name %></td>
									<td><%=date %></td>
									<td><a href="guestbook?a=deleteform&no=<%=no %>">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
										<%=content %>
									</td>
								</tr>
							</table>
							
							<%} %>
							
							<br>
						</li>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<jsp:include page = "/WEB-INF/views/includes/footer.jsp" />
		
	</div> <!-- /container -->

</body>
</html>