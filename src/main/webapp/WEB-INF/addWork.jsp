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
<title>新增工作</title>
<script>
function addType(){
	
	var addTypeVal = $('[name=type]').val();
	var newType = '<span class="badge badge-primary">' + addTypeVal + '</span> ';
	$('[name=hasTag]').append(newType);
	var addTypeVal = $('[name=type]').val('');
	
}
</script>
</head>
<body>
	<jsp:include page="Nav.jsp"></jsp:include>
	<form>
	<div class="container">
		<h1>新增工作</h1>
		<div class="row">
			<div class="col-sm-12 form-group"><input class="form-control" type="text" name="title" placeholder="請輸入工作標題"></div>
		</div>
		<div name="hasTag"></div>
		<br>
		<div class="row">
			<div class="col-sm-12 form-group" id="typegroup">
				<input class="form-control" type="text" name="type" placeholder="請輸入標籤" onblur="addType();">
			</div>
		</div>
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