<%@page import="java.util.ArrayList"%>
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
	
	 <%--@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>
	<% ArrayList<int[]> result = (ArrayList<int[]>)request.getAttribute("result"); %>
	<% int count = 1; %>
	
	<div class="container d-flex justify-content-center align-items-cente">
		<div class="d-flex align-items-center">
			<form action="lottery" method="post">
				<input type="hidden" name="times" value="${ requestScope.times }" readonly="readonly"/>
				<input type="hidden" name="numbers" value="${ requestScope.excludeNums }" readonly="readonly"/>
				<table>
					<thead>
						<tr><th>總生成組數: ${ requestScope.times } 組</th></tr>
						<tr><th>排除的數字: ${ requestScope.excludeNums } </th></tr>
					</thead>
					<tbody>
						<% for(int[] nums : result) { %>
							<tr class="table">
								<td scope="row">第 <%= count %> 組</td>
								<td>
									<% for(int num : nums) { %>
										<%= num %>				
									<% } %>
								</td>
								
							</tr>
							<% count++; %>
						<% } %>
						
		<%--
					 	<c:forEach var="nums" items=${ requestScope.result }>
						 	<tr>
								<td>第 ${ count } 組</td>
								<td>
									<c:forEach var="num" items="${ nums }">
										${ num }
									</c:forEach>
								</td>
							</tr>	
					 	</c:forEach>
		--%>
						
						<tr>			
							<td><input class="btn btn-primary" type="submit" value="再來一次"/></td>
							<td><a href=""><input class="btn btn-primary" type="button" value="返回"/></a></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</body>
</html>