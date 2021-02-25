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
	function goInsert() {
		$('[name=index]').attr('action', '/insert');
		$('[name=index]').submit();
	}

	function goUpdate() {
		$('[name=index]').attr('action', '/update');
		$('[name=index]').submit();
	}

	function goDelete() {
		$('[name=index]').attr('action', '/delete');
		$('[name=index]').submit();
	}

	function goSearch() {
		$('[name=index]').attr('action', '/search');
		$('[name=index]').submit();
	}

	function checkBox(obj) {
		var value = $(obj).is(':checked');
		if (value) {
			$(obj).val('Y');
		} else {
			$(obj).val('N');
		}
	}

	function selectAll(obj) {
		if ($(obj).is(':checked')) {
			$('[name^=selchk]').each(function() {
				$(this).val('Y');
				$(this).prop('checked', true);
			})
		} else {
			$('[name^=selchk]').each(function() {
				$(this).val('N');
				$(this).prop('checked', false);
			})
		}
	}
</script>
</head>
<body>
	<h1>雞雞機車行</h1>
	<nav class="navbar navbar-expand-sm bg-light">
	<ul class="navbar-nav">
		<li class="nav-item"><a class="nav-link" href="/">零件管理</a></li>
		<li class="nav-item"><a class="nav-link" href="/upload">展示</a></li>
		<li class="nav-item"><a class="nav-link" href="#">Link 3</a></li>
	</ul>
	</nav>
	<br>
	<br>
	<form name="index" method="post" action="/">
	<h1 align="center">Show Goods</h1>
		<div class="container">
			<div class="card-columns">
				<c:forEach items="${list}" var="Item" varStatus="varStatus">
					<div class="card">
						<img class="card-img-top" src="/image/${Item.T01_PICNAME}" alt="Card image"
							style="width: 100%">
						<div class="card-body">
							<h4 class="card-title">${Item.T01_NAME}</h4>

							<p class="card-text">
								品名：${Item.T01_NAME}<br/> 
								存貨：${Item.T01_STORE}<br/>
								金額：${Item.T01_PRICE}
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	</form>
</body>
</html>