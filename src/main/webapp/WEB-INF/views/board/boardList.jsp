<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
span{
	float: left;
}

</style>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
<c:import url="../template/nav.jsp"></c:import>


<div class="container">
	<h2> ${board} List </h2>
	<form action="./${board}List" id="frm" class="form-inline" style="margin-bottom: 8px;">
		<input type="hidden" name="curPage" id="p"> 
		 <div class="input-group input-group-sm col-xs-2">
		    <select class="form-control" id="sel1" name ="kind">
				   <option id="title" value="title">Title</option>
				   <option id="contents" value="contents">Contents</option>
				   <option id="writer" value="writer">Writer</option>
			</select>
		 </div>
		 <div class="input-group input-group-sm col-xs-4">
		     <input type="text" class="form-control" placeholder="Search" name="search" value="${param.search}">
		     <div class="input-group-btn">
		        <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
		      </div>
		 </div>
	</form>
		  
	<table class="table table-hover">
		<tr>
			<td>Num</td>
			<td>Title</td>
			<td>Writer</td>
			<td>Date</td>
			<td>Hit</td>
		</tr>
		
		<!-- Page로 보냈을 때  page.content로 값 꺼내오기 -->
		<c:forEach items="${page.content}" var="vo">
		<tr>
			<td>${vo.num}</td>
			<td>
			<c:catch>
				<c:forEach begin="1" end="${vo.depth}">
					&nbsp;&nbsp;→
				</c:forEach>
			</c:catch>
			<a href="${board}Select?num=${vo.num}">${vo.title}</a></td>
			<td>${vo.writer}</td>
			<td>${vo.regDate}</td>
			<td>${vo.hit}</td>  
		</tr>
		</c:forEach>
		
	</table>
	
	
	<%-- <ul class="pagination">
		<c:catch>
		<c:if test="${pager.curBlock gt 1}">
			<li><a href="./${board}List?curPage=${pager.startNum-1}">이전</a></li>
		</c:if>
		<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li><a href="./${board}List?curPage=${i}">${i}</a></li>
		</c:forEach>
		<c:if test="${pager.curBlock lt pager.totalBlock}">
			<li><a href="./${board}List?curPage=${pager.lastNum+1}">다음</a></li>
		</c:if>
		</c:catch>
	</ul> --%>
	
	
	
		<div style="float: left;">
			<span><a href="#" class="pager" title="0">&lt;&lt;</a></span>
			<span><a href="#" class="pager" title="${page.number-1}"> &lt;</a></span>
			<c:forEach begin="${page.number}" end="${page.number+4}" var="i">
				
				<c:if test="${i lt page.totalPages}">
					<span><a href="#" class="pager" title="${i}">${i+1}</a></span>
				</c:if>
			</c:forEach>
			<span><a href="#" class="pager" title="${page.number+1}">&gt;</a></span>
			<span><a href="#" class="pager" title="${page.totalPages-1}">&gt;&gt;</a></span>
		</div>
	
	  
	<a href="./${board}Write" class="btn btn-danger" style="float: right;">Write</a>
	
</div>

<script type="text/javascript">

	$(".pager").click(function(){
		var page=$(this).attr("title");
		$("#p").val(page);
		$("#frm").submit();
	});
	
	var kind = '${param.kind}';
	if(kind == ''){
		$("#title").prop("selected",true);
	} else{
		$("#"+kind).prop("selected",true)
	}

	var result = '${result}';
	if(result != ''){
		if(result == '1'){
			alert("success");
		} 
		else{
			alert("fail");
		}
	}
	

</script>

</body>
</html>