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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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

	function fileChange(obj) {
		var file_data = $(obj).prop('files')[0];
		var file_name = file_data.name;
		$(obj).parent().find('span').text(file_name);
		$(obj).next().val(file_name);
	}
</script>
</head>
<body>
	<div class="wrapper">
		<h1>雞雞機車行</h1>
		<nav class="navbar navbar-expand-sm bg-light">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/">零件管理</a></li>
			<li class="nav-item"><a class="nav-link" href="/show">展示</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link 3</a></li>
		</ul>
		</nav>
		<br> <br>
		<form name="index" method="post" enctype="multipart/form-data"
			action="/">
			<div align="center">
				商品名：<input type="text" name="name" required /> 金額：<input
					type="number" name="price" maxlength="5" /> 存貨數：<input
					type="number" name="store" /> 檔案：<input type="file" name="file" />
			</div>
			<br />
			<div align="center">
				<button type="button" class="btn btn-primary" onclick="goInsert()">新增</button>
				<button type="button" class="btn btn-secondary" onclick="goUpdate()">修改</button>
				<button type="button" class="btn btn-dark" onclick="goDelete()">刪除</button>
				<button type="button" class="btn btn-success" onclick="goSearch()">查詢</button>
			</div>
			<br />
			<table align="center">
				<tr>
					<th><input type="checkbox" name="selAll" value="N"
						onclick="selectAll(this);"></th>
					<th>商品名</th>
					<th>存貨</th>
					<th>金額</th>
					<th>檔案</th>
				</tr>
				<c:if test="${list=='[]'}">
					<tr>
						<td>No data!
						<td>
					</tr>
				</c:if>
				<c:forEach items="${list}" var="Item" varStatus="varStatus">
					<tr>
						<td><input type="checkbox" name="selchk_${varStatus.count}"
							value="N" onclick="checkBox(this);" /></td>
						<td><input type="hidden" name="T01_ID_${varStatus.count}"
							value="${Item.T01_ID}" /> <input type="text"
							name="T01_NAME_${varStatus.count}" value="${Item.T01_NAME}" /></td>
						<td><input type="number" name="T01_STORE_${varStatus.count}"
							value="${Item.T01_STORE}" /></td>
						<td><input type="number" name="T01_PRICE_${varStatus.count}"
							value="${Item.T01_PRICE}" /></td>
						<td><label for="T01_PIC_${varStatus.count}"> <span
								id="text${varStatus.count}"> <c:choose>
										<c:when test="${Item.T01_PICNAME!=''}">${Item.T01_PICNAME}</c:when>
										<c:otherwise>未選擇任何檔案</c:otherwise>
									</c:choose>
							</span>
						</label> <input type="file" onchange="fileChange(this);"
							id="T01_PIC_${varStatus.count}" name="T01_PIC_${varStatus.count}"
							style="display: none" /> <input type="hidden"
							value="${Item.T01_PICNAME}" name="T01_PICNAME_${varStatus.count}">
						</td>

						<c:if test="${varStatus.last}">
							<input type="hidden" name="listCount" value="${varStatus.count}">
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
	<footer>
	<p>WebDesigneer 網頁設計</p>
	</footer>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>