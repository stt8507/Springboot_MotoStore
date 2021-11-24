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
<title>產品介紹</title>
<script>
</script>
</head>
<body>
	<jsp:include page="Nav.jsp"></jsp:include>
	<form name="index" method="post" action="/">
			<h1 align="center">Show Detail</h1>
			<div class="container">
				<img src="/image/${MT01_PICNAME}" class="mx-auto d-block" style="width:50%">
				<div align="center" style="font-size: 25px; word-wrap: break-word; word-break:normal;">${MT01_COMMENT}</div>
			</div>
			
			<div class="container">
			<br>
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
							<td>${Item.MT02_NAME}</td>
							<td>${Item.MT02_STORE}</td>
							<td>${Item.MT02_PRICE}</td>
							<td>${Item.MT02_PICNAME}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
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