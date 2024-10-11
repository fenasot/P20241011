<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
<jsp:include page="/templates/header.jsp"></jsp:include>
</head>
<body>
	<div class="container d-flex justify-content-center align-items-cente" style="height: 100vh;">
		<div class="d-flex align-items-center">
			<form method="post" action="signup">
				<table class="tableset">
					<thead>
						<tr><th>建立帳號</th></tr>
						<tr><th><div class="text-danger">${ requestScope.errorMsg }</div></th></tr>
					</thead>
					<tbody>
						<tr>
							<td>帳號:</td>
							<td><input class="form-control" name="acc" value="${ requestScope.acc }" pattern="^[A-Za-z\d]{6,20}$" placeholder="請輸入6~20個字元" /></td>
						</tr>
						<tr>
							<td>密碼:</td>
							<td><input class="form-control" name="pwd" value="" type="password" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$" placeholder="包含英文數字的6~20個字元"/></td>
						</tr>
						<tr>
							<td><a href="${ pageContext.request.contextPath }/login">返回</a></td>
							<td><input class="btn btn-primary" type="submit" value="建立" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	
	<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>
</html>