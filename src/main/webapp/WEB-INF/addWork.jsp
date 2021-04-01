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
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<title>新增工作</title>
<script>
function addData(){
	var sHtml = $('.summernote').summernote('code');
	$('[name=content]').val(sHtml);
	$('form').submit();
}
function addTag(){
	var addType = $('[name=typeSample]').val();
	var oldType = $('[name=type]').val();
	var newTags = '<span class="badge badge-primary">' + addType + '</span> ';
	var newType = '';
	
	if(oldType == ''){
		newType = addType;
	}else{
		newType = oldType + ',' + addType;
	}
	
	$('[name=type]').val(newType);
	$('[name=typeSample]').val('');
	$('#tags').append(newTags);
}

</script>
</head>
<body>
	<jsp:include page="Nav.jsp"></jsp:include>
	<form action="/addNewWork">
	<div class="container">
		<h1>新增工作</h1>
		<div id="dateText">新增時間：</div>
		<div class="row">
			<div class="col-sm-12 form-group input-group-prepend">
			<span class="input-group-text">工作標題</span>
			<input class="form-control" type="text" name="title" placeholder="請輸入工作標題">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4 form-group input-group-prepend">
			<span class="input-group-text">標籤</span>
				<input class="form-control" type="text" name="typeSample" placeholder="請輸入標籤" onblur="addTag();">
				<input type="hidden" name="type">
			</div>
			<div class="col-sm-4 form-group input-group-prepend">
			<span class="input-group-text">負責人</span>
				<input class="form-control" type="text" name="owner" placeholder="請輸入負責人">
			</div>
			<div class="col-sm-4 form-group input-group-prepend">
				<span class="input-group-text">迫切性</span>
  					<select class="form-control" name="urgency">
  					<c:forEach items="${ddlList}" var="Item" varStatus="varStatus"><option>${Item}</option></c:forEach>
  					</select>
			</div>
		</div>
		<div id='tags'></div>
		<div class="summernote"></div>
		<input name="content" type="hidden">
		<br>
		<div class="row">
		<div class="col-sm-11"></div>
		<div class="col-sm-1">
		<input class="btn btn-primary" type="submit" name="addDataBtn" onclick="addData();" value="新增" align="right">
		</div>
		</div>
	</div>
	</form>
	 
	<jsp:include page="footer.jsp"></jsp:include>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script>
$(document).ready(function() {
	var dateNow = new Date();
	var dateVal = dateNow.getFullYear() + '/' + (dateNow.getMonth()+1) + '/' + dateNow.getDate();
	$('#dateText').append(dateVal);
	$('.summernote').summernote({
		height: 165,
		lang: 'zh-TW',
		placeholder: '請輸入工作細節',
		callbacks: {
			onImageUpload: function(files) {
				sendFile(files[0]);
			},
			onMediaDelete: function(target) {
				deleteFile(target);
			}
		}
	});
});

function sendFile(files) {  
    data = new FormData();  
    data.append("files", files);  
    $.ajax({  
        data: data,  
        type: "POST",  
        url: "/uploadImg",  //將該請求發送到自己的Controller層的處理方法路徑
        cache: false,
        contentType: false,  
        processData: false,  
        success: function(datasrc) {
            $(':focus').append('<img height="300" width="500" src="/article_image/' + datasrc.fileName +'"></img>');
        },  
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + " " + errorThrown);
        }  
    });  
}

function deleteFile(target) {
	var imgSrc = target[0].currentSrc;
	var data = new FormData();
	data.append("imgSrc", imgSrc);
	$.ajax({
		data: data,
		type: "POST",
		url: "/deleteImg",
		cache: false,
		contentType: false,
		processData: false,
		success:function(data){
			console.log(data);
		}
	});
}

</script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-zh-TW.min.js"></script>
</body>
</html>