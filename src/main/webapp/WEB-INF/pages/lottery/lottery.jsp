<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/templates/header.jsp"></jsp:include>
</head>
<body>
	<div class="container d-flex justify-content-center align-items-cente" style="height: 100vh;">
		<div class="d-flex align-items-center">
			<form method="post" action="lottery">
				<table>
					<thead>
						<tr><th><div class="text-danger">${ requestScope.errorMsg }</div></th></tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2">限制數字範圍為1~49，超出範圍將直接忽略，最多可限制42個數字，並用,分開，可能出現重複的結果，範例(3,5,9,6)</td>
						</tr>
						<tr>
							<td><label class="">請輸入欲限制的數字</label></td>
							<td><input class="form-control" name="numbers" value=""/></td>
						</tr>
						<tr>
							<td><label class="">請輸入要生成的組數，預設為2組</label></td>
							<td><input class="form-control" name="times" value=""></input></td>
						</tr>
						<tr>
							<td></td>
							<td><input class="btn btn-primary" type="submit" value="新增"/></td>
						</tr>
					</tbody>
				</table>
			</form>	
	
		</div>
	</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>
</html>