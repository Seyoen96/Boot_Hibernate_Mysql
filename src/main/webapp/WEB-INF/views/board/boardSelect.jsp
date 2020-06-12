<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
<c:import url="../template/nav.jsp"></c:import>
<div class="container">

<table class="table table-striped">
    <thead>
      <tr>
        <th>Title</th>
        <th>Contents</th>
        <th>Writer</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${vo.title}</td>
        <td>${vo.contents}</td>
       <td>${vo.writer}</td>
      </tr>
      </tbody>
</table>

	<c:catch>
	 <c:if test="${not empty files}">
	 <c:forEach items="boardFiles" var="files">	
	<img alt="" src="${pageContext.request.contextPath}/resources/upload/notice/${files.fileName}"/>
	</c:forEach>
	</c:if>
 	</c:catch>
 

<c:if test="${board eq 'qna'}">
<a href="${board}Reply?num=${vo.num}" class="btn btn-info">Reply</a>
</c:if>

<a href="${board}Update?num=${vo.num}" class="btn btn-primary">Update</a>
<a href="${board}Delete?num=${vo.num}" class="btn btn-danger">Delete</a>

</div>


</body>
</html>