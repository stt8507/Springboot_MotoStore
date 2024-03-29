<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!--Custom CSS-->
<link rel="stylesheet" href="/css/stylesheet.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<title>工作清單</title>
<script>
</script>
</head>
<body>
	<jsp:include page="Nav.jsp"></jsp:include>
	<div class="container">
		<h1>工作清單</h1>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>工作標題</th>
					<th>迫切性</th>
					<th>負責人</th>
					<th>截止日</th>
					<th>作業狀態</th>
				</tr>
			</thead>	
			<tbody>
				<c:forEach items="${list}" var="Item" varStatus="varStatus">
					<tr>
						<td>${Item.title}</td>
						<td>${Item.urgency}</td>
						<td>${Item.owner}</td>
						<td>${Item.dday}</td>
						<td>${Item.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>