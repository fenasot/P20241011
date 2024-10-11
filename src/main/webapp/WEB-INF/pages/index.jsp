<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>遊樂場</title>
<jsp:include page="/templates/header.jsp"></jsp:include>
</head>
<body>
	<% String user = (String)session.getAttribute("user"); %>

	<div class="container d-flex justify-content-center align-items-cente" style="height: 100vh;">
		<div class="d-flex flex-row">	
			<div class="p-2"><h1>Welcome To My Spring Boot Playground</h1></div>	
		  <div class="p-2">
		  <div class="d-flex align-items-center">		
				<% if(user != null && !user.equals("")) { %>
				<h2>歡迎您， ${ sessionScope.user }</h2>
				<h3><a href="${ pageContext.request.contextPath }/login/logout">登出</a></h3>
				<% } else { %>
				<h2>您還尚未登入，請先登入後才可進行遊戲</h2>
				<h3><a href="<%= request.getContextPath() %>/login">登入</a></h3>
				<% } %>
			</div>		
			<h3><a href="${ pageContext.request.contextPath }/lottery">遊玩</a></h3>  
		  </div>
		</div>

	</div>
	

	
	<p><a href="<%= request.getContextPath() %>/lottery">大樂透</a></p>

	<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>
</html>