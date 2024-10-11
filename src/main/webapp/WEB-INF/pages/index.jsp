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





  <div class="container">
    <div class="row justify-content-around" style="height: 90vh;">
      <div class="row justify-content-around" style="">
        <div id="login-form" class=" d-flex justify-content-center align-items-cente" >
          <div class="d-flex align-items-center">
          	<div class="p-2"><h1>Welcome To My Spring Boot Playground</h1></div>	
          </div>
        </div>
      </div>
      <div class="row justify-content-around" style="">
        <div class="d-flex align-items-center">

	    	<% if(user != null && !user.equals("")) { %>
				<h2>歡迎您， ${ sessionScope.user }</h2>
				<h3><a href="${ pageContext.request.contextPath }/login/logout">&nbsp;登出</a></h3>
				<% } else { %>
				<h2>您還尚未登入，請先登入後才可進行遊戲。</h2>
				<h3><a href="<%= request.getContextPath() %>/login">登入</a></h3>
			<% } %>
			
        </div>
      </div>
      <div class="row justify-content-around">
      	<div class="d-flex align-items-center">
      		<p>	<h3><a href="${ pageContext.request.contextPath }/lottery">&nbsp;遊玩</a></h3> </p>
      	</div>
      	
      </div>
    </div>
  </div>
  
	<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>
</html>