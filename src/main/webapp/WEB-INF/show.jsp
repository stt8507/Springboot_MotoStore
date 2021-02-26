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

<title>雞雞機車行</title>
<script>
	function goDetail(obj) {
		$('[name=T01_CHOSENID]').val($(obj).find('[name=T01_ID]').val());
		$('[name=index]').attr('action', '/detail');
		$('[name=index]').submit();
	}
</script>
</head>
<body>
	<div class="wrapper">
		<h1>雞雞機車行</h1>
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
				<li class="nav-item active"><a class="nav-link" href="#">首頁</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">關於</a></li>
				<li class="nav-item"><a class="nav-link" href="#">登出</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">產品資料</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/">修改資料</a> <a
							class="dropdown-item" href="/show">產品總覽</a>
					</div></li>
			</ul>

			<form class="form-inline">
				<input type="text" class="form-control mr-1" placeholder="輸入關鍵字" />
				<button class="btn btn-outlight" type="submit">搜尋</button>
			</form>
		</div>
		</nav>
		<br> <br>
		<form name="index" method="post" action="/">
			<h1 align="center">Show Goods</h1>
			<div class="container">
				<div class="row">
					<c:forEach items="${list}" var="Item" varStatus="varStatus">
						<div class="card col-sm-3" onclick="goDetail(this);">
							<input type="hidden" name="T01_ID" value="${Item.T01_ID}">
							<img class="card-img-top" src="/image/${Item.T01_PICNAME}"
								alt="Card image" style="width: 100%">
							<div class="card-body">
								<h4 class="card-title">${Item.T01_NAME}</h4>

								<p class="card-text">
									品名：${Item.T01_NAME}<br /> 存貨：${Item.T01_STORE}<br />
									金額：${Item.T01_PRICE}
								</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- Hidden Column -->
			<input type="hidden" name="T01_CHOSENID">
		</form>
	</div>
	<footer>
	<p>WebDesigneer 網頁設計</p>
	</footer>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>