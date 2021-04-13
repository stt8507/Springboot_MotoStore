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
<title>我的文件</title>
<script>

</script>
</head>
<body>
	<jsp:include page="Nav.jsp"></jsp:include>
	<div class="container">
		<h1>我的文件</h1>
		<div class="row justify-content-end">
		<span class="col-sm-7">${errorMsg}</span>
		<div class="col-sm-5">
		<form action="/addNewDoc" method="post" enctype="multipart/form-data">
			<input type="file" name="newFile">
			<input type="submit" value="確認上傳">
		</form>
		</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>檔名</th>
					<th>類型</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${fileName}</td>
					<td>${fileType}</td>
				</tr>
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