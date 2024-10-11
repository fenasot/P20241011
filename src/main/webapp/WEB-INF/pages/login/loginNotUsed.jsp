<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
<jsp:include page="/templates/header.jsp"></jsp:include>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
</head>
<body>
	<form:form method="post" action="login">
		<table class="tableset">
			<tbody>
				<tr>
					<td><form:label path="acc">帳號:</form:label></td>
					<td><form:input path="acc"/></td>
				</tr>
				<tr>
					<td><form:label path="pwd">密碼:</form:label></td>
					<td><form:input path="pwd" type="password"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="登入" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
	<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>
</html>