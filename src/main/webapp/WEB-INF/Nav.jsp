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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>機車行</title>
<script>
	
</script>
</head>
<body>
	<div class="wrapper">
		<br><br>
		<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"> <img
			alt="eHappy" src="/image/bs_logo.png" />
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navContent">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="/">首頁</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">關於</a></li>
				<li class="nav-item"><a class="nav-link" href="#">登出</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">產品資料</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/index">修改資料</a> <a
							class="dropdown-item" href="/show">產品總覽</a>
					</div></li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">工作紀錄</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/log">工作日誌</a> 
						<a class="dropdown-item" href="/myDoc">我的文件</a>
						<a class="dropdown-item" href="/WorkingList">工作清單</a> 
						<a class="dropdown-item" href="/addWork">新增工作</a>
					</div>
				</li>
			</ul>

			<form class="form-inline" action="/searchForAll">
				<input type="hidden" name="sourcePage" value="${sourcePage}">
				<input type="text" name="searchNameForAll" class="form-control mr-1" placeholder="輸入關鍵字" />
				<button class="btn btn-outlight" type="submit">搜尋</button>
			</form>
		</div>
		</nav>
		<br> <br>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>