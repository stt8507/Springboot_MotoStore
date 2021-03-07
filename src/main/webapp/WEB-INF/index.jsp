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
<title>修改資料</title>
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

function Cmentcli(obj){
	var objInput = $(obj).find('[name ^= T01_COMMENT]');
	var objSpan = $(obj).find('span');
	var valBefore = $(objInput).val();
	var valAft = prompt('請輸入產品介紹',valBefore);
	if(valAft != null){
		$(objInput).val(valAft);
		$(objSpan).text(valAft);
	}
}
function gotoPage(value){
	
	$('[name=gotoPage]').val(value);
	$('[name=index]').attr('action', '/index');
	$('[name=index]').submit();
}
</script>
</head>
<body>
	<jsp:include page="Nav.jsp"></jsp:include>
	<form name="index" method="post" enctype="multipart/form-data"
			action="/">
			<div align="center" class="container from-group">
			<div class="row">
				<span class="col-sm-4"></span>
				<span class="input-group col-sm-5" >
      				<span class="input-group-prepend">
        			<span class="input-group-text" >商品名：</span>
        			<input type="text" class="form-control" name="searchName">
      				</span>
      			</span>
      			<span class="col-sm-3">
      				<button type="button" class="btn btn-secondary" onclick="goUpdate()">修改</button>
					<button type="button" class="btn btn-dark" onclick="goDelete()">刪除</button>
					<button type="button" class="btn btn-success" onclick="goSearch()">查詢</button>
      			</span>
      		</div>
			</div>
			<br />
			
			<br />
			<div class="container">
			<c:if test="${list!='[]' and list!=null}">
				<ul class="pagination">
				<li class="page-item"><a class="page-link" onclick="gotoPage(${(ppObj.currentPage - 1) eq 0 ?1:(ppObj.currentPage - 1)})">Previous</a></li>
				<c:forEach var="i" begin="1" end="${ppObj.totalPage > 10 ? 10:ppObj.totalPage}" varStatus="varStatus">
				<c:choose>
					<c:when test="${i == ppObj.currentPage}">
						<li class="page-item active"><a class="page-link" onclick="gotoPage(${i})">[${i}]</a></li></c:when>
					<c:otherwise><li class="page-item"><a class="page-link" onclick="gotoPage(${i})">[${i}]</a></li></c:otherwise>
				</c:choose>
				<c:if test="${varStatus.last && (varStatus.count >= 10)}">
				<li class="page-item"><a class="page-link" onclick="gotoPage(${((ppObj.currentPage%10) + ppObj.recordPerPage) gt (ppObj.totalPage) ? ppObj.totalPage: ((ppObj.currentPage%10) + ppObj.recordPerPage)})">...</a></li>
				</c:if>
				</c:forEach>
				
				<li class="page-item"><a class="page-link" onclick="gotoPage(${(ppObj.currentPage + 1) gt (ppObj.totalPage) ? ppObj.totalPage: (ppObj.currentPage + 1)})">Next</a></li>
				</ul>
			</c:if>
			
			<table class="table table-bordered">
				<thead>
				<tr>
					<th><input type="checkbox" name="selAll" value="N"
						onclick="selectAll(this);"></th>
					<th>商品名</th>
					<th>存貨</th>
					<th>金額</th>
					<th>檔案</th>
					<th>評論</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${(list=='[]') or list==null}">
					<tr align="center">
						<td colspan="6">No data!</td>
					</tr>
				</c:if>
				<c:forEach items="${list}" var="Item" varStatus="varStatus">
					<tr>
						<td><input type="checkbox" name="selchk_${varStatus.count}"
							value="N" onclick="checkBox(this);" /></td>
						<td><input type="hidden" name="T01_ID_${varStatus.count}"
							value="${Item.T01_ID}" />
							<input type="text" name="T01_NAME_${varStatus.count}" value="${Item.T01_NAME}" /></td>
						<td><input type="number" name="T01_STORE_${varStatus.count}"
							value="${Item.T01_STORE}" /></td>
						<td><input type="number" name="T01_PRICE_${varStatus.count}"
							value="${Item.T01_PRICE}" /></td>
						<td><label for="T01_PIC_${varStatus.count}"> 
							<span id="text${varStatus.count}"> 
							<c:choose>
								<c:when test="${Item.T01_PICNAME!=''}">${Item.T01_PICNAME}</c:when>
								<c:otherwise>未選擇任何檔案</c:otherwise>
							</c:choose>
							</span>
							</label> 
							<input type="file" onchange="fileChange(this);" id="T01_PIC_${varStatus.count}" name="T01_PIC_${varStatus.count}"
							style="display: none" /> 
							<input type="hidden" value="${Item.T01_PICNAME}" name="T01_PICNAME_${varStatus.count}">
						</td>
						<td onclick="Cmentcli(this);" class="comment">
							<span>${Item.T01_COMMENT}</span>
							<input type="hidden" name="T01_COMMENT_${varStatus.count}" value="${Item.T01_COMMENT}" />
						</td>
						<c:if test="${varStatus.last}">
							<input type="hidden" name="listCount" value="${varStatus.count}">
						</c:if>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			
			
			
			<div align="center" class="row">
				<div class="input-group col-sm-12 " >
      				<div class="input-group-prepend">
        			<span class="input-group-text" >商品名：</span>
        			<input type="text" class="form-control" name="name" required />
      
        			<span class="input-group-text">金額：</span>
        			<input type="number" class="form-control" name="price" maxlength="5" />
        		
        			<span class="input-group-text">存貨數：</span>
        			<input type="number" class="form-control" name="store" />
        		
        			<span class="input-group-text">檔案：</span>
        			<input type="file" class="form-control" name="file" />	
        			</div>
  					<textarea class="form-control" rows="5" name="comment" placeholder="Comment..."></textarea>
      			</div>
      		</div>
      		<br>
      		<div class="row">
      			<span class="col-sm-11"></span>
      			<span class="input-group col-sm-1">
      				<button type="button" class="btn btn-primary" onclick="goInsert()">新增</button>
      			</span>
			</div>
			</div>
			<!-- Hidden Column -->
			<input type="hidden" name="nameForPage" value="${nameForPage}">
			<input type="hidden" name="totalPage" value="${ppObj.totalPage}">
			<input type="hidden" name="currentPage" value="${ppObj.currentPage}">
			<input type="hidden" name="totalRecord"  value="${ppObj.totalRecord}">
			<input type="hidden" name="recordPerPage" value="${ppObj.recordPerPage}">
			<input type="hidden" name="gotoPage" value="${ppObj.gotoPage}">
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
