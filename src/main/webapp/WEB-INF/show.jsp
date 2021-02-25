<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
<title>雞雞機車行</title>
<script>
	function goInsert(){
		$('[name=index]').attr('action','/insert');
		$('[name=index]').submit();
	}
	
	function goUpdate(){
		$('[name=index]').attr('action','/update');
		$('[name=index]').submit();
	}
	
	function goDelete(){
		$('[name=index]').attr('action','/delete');
		$('[name=index]').submit();
	}
	
	function goSearch(){
		$('[name=index]').attr('action','/search');
		$('[name=index]').submit();
	}

	function checkBox(obj){
		var value=$(obj).is(':checked');
		if(value){
			$(obj).val('Y');
		}else{
			$(obj).val('N');
		}
	}

	function selectAll(obj){
		if($(obj).is(':checked')){
			$('[name^=selchk]').each(function(){
				$(this).val('Y');
				$(this).prop('checked',true);
			})
		}else{
			$('[name^=selchk]').each(function(){
				$(this).val('N');
				$(this).prop('checked',false);
			})
		}
	}
</script>
</head>
<body>
	<h1>雞雞機車行</h1>
	<nav class="navbar navbar-expand-sm bg-light">
  	<ul class="navbar-nav">
    	<li class="nav-item">
      		<a class="nav-link" href="#">零件管理</a>
    	</li>
    	<li class="nav-item">
      		<a class="nav-link" href="upload.jsp">上傳</a>
    	</li>
    	<li class="nav-item">
      		<a class="nav-link" href="#">Link 3</a>
    	</li>
  	</ul>
	</nav>
	<br><br>
	<form name="index" method="post" action="/">
	<table>
		<tr>
			<td>
				商品名：<input type="text" name="name" required/>
				金額：<input type="number" name="price"/>
				存貨數：<input type="number" name="store"/>
				<input type="file" accept="image/*"/>
			</td>
		</tr>
	</table>
	<br>
	<div class="btn-group" align="right">
  		<button type="button" class="btn btn-primary" onclick="goInsert()">新增</button>
  		<button type="button" class="btn btn-secondary" onclick="goUpdate()">修改</button>
  		<button type="button" class="btn btn-dark" onclick="goDelete()">刪除</button>
  		<button type="button" class="btn btn-success" onclick="goSearch()">查詢</button>
	</div>
	<table align="center">
		<tr>
			<th><input type="checkbox" name="selAll" value="N" onclick="selectAll(this);"></th>
			<th>商品名</th>
			<th>存貨</th>
			<th>金額</th>
		</tr>
		<c:if test="${list=='[]'}"><tr><td>No data!<td></tr></c:if>
		<c:forEach items="${list}" var="Item" varStatus="varStatus">
			<c:if test="${varStatus.count%4!=0}">
   				<tr>
			</c:if>
			
			<div class="card" style="width:400px">
    			<img class="card-img-top" src="img_avatar1.png" alt="Card image" style="width:25%">
    			<div class="card-body">
      				<h4 class="card-title">${Item.T01_NAME}</h4>
      				
      				<p class="card-text">
      					<input type="checkbox" name="selchk_${varStatus.count}" value="N" onclick="checkBox(this);"/>
      					<input type="hidden" name="T01_ID_${varStatus.count}" value="${Item.T01_ID}"/>
						品名：<input type="text" name="T01_NAME_${varStatus.count}" value="${Item.T01_NAME}"/><br>
						存貨：<input type="number" name="T01_STORE_${varStatus.count}" value="${Item.T01_STORE}"/><br>
						金額：<input type="number" name="T01_PRICE_${varStatus.count}" value="${Item.T01_PRICE}"/>
      				</p>
    			</div>
			</div>
				<c:if test="${varStatus.last}">
   					<input type="hidden" name="listCount" value="${varStatus.count}">
				</c:if>
			<c:if test="${varStatus.count%4!=0}">
   				</tr>
			</c:if>
		</c:forEach>
	</table>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    </form>
</body>
</html>