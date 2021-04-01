<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<title>工作日誌</title>
<script>

</script>
</head>
<body>
	<jsp:include page="Nav.jsp"></jsp:include>
	
	<div class="container">
		<form action="/searchWorkname">
		<div class="row">
			<div class="col-sm-9"><h1>工作日誌</h1></div>
			<div class="input-group mb-4 col-sm-3">
				<input type="text" class="form-control" placeholder="工作名稱" name="workName">
  				<div class="input-group-append">
    				<button class="btn" type="submit">搜尋文章</button>
  				</div>
			</div>
		</div>
		</form>
		<br>
		<h1 align="center">${title}</h1><br>
		<fmt:formatDate value="${cdate}" pattern="yyyy-MM-dd"/> ${status} 
		<c:forEach items="${tags}" var="tag" varStatus="varStatus">
		<c:choose>
			<c:when test='${varStatus.index %3 == 0}'><span class="badge badge-primary">${tag}</span> </c:when>
			<c:when test='${varStatus.index %3 == 1}'><span class="badge badge-secondary">${tag}</span> </c:when>
			<c:otherwise><span class="badge badge-success">${tag}</span> </c:otherwise>
		</c:choose>
		</c:forEach>
		<hr/>
		<p>${content}</p>
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