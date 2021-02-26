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
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<title>雞雞機車行</title>
<script>
	
</script>
</head>
<body>
	<h1>雞雞機車行</h1>
	<nav class="navbar navbar-expand-sm bg-light">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="/">零件管理</a></li>
		<li class="nav-item"><a class="nav-link" href="/show">展示</a></li>
		<li class="nav-item"><a class="nav-link" href="#">Link 3</a></li>
	</ul>
	</nav>
	<br>
	<br>
	<form name="index" method="post" action="/">
		<h1 align="center">Show Detail</h1>
		<div class="container">
			<table align="center" class="table table-bordered">
				<thead>
					<tr>
						<th>商品名</th>
						<th>存貨</th>
						<th>金額</th>
						<th>檔案</th>
					</tr>
				</thead>
				<c:forEach items="${list}" var="Item" varStatus="varStatus">
					<tr>
						<td>${Item.T02_NAME}</td>
						<td>${Item.T02_STORE}</td>
						<td>${Item.T02_PRICE}</td>
						<td>${Item.T02_PICNAME}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>