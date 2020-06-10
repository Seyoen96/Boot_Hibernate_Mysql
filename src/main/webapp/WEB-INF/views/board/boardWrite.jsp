<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<style type="text/css">
.r {
	color:red;
}
</style>
</head>

<body>
<c:import url="../template/nav.jsp"></c:import>

<div class="container">
<h2> ${board} ${path} </h2>


<%-- <c:import url="../template/boardForm.jsp"></c:import> --%>
<c:import url="../template/springForm.jsp"></c:import>

	<script type="text/javascript">
	$(document).ready(function() {
		  $('#summernote').summernote({
			  height: 300
		 });
	});

	var board = '${path}';
	if(board=='Write'){
		$('#num').remove();
	}

	$('#add').click(function(){
		$('#f').append('<input type="file" name="files">');
				
	});
	
	
	</script>

</div>

</body>
</html>