<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
	<form:form modelAttribute="boardVO" action="${board}${path}" method="post" enctype="multipart/form-data">
		<!-- value 쓰지 않아도 변수명 같으면 자동으로 매핑해줌 -->	
		<!-- name으로 파라미터 명 안줘도 자동으로 -->
		
		<form:input path="num" type="hidden" name="num" id="num"/>
	  <div class="form-group">
	    <label for="title">Title:</label>
	    <form:input path="title" type="text" class="form-control" id="title"/>
	    <form:errors path="title" cssClass="r"></form:errors>
	  </div>
	  <div class="form-group">
	    <label for="writer">Writer:</label>
	    <form:input path="writer" type="text" class="form-control" id="writer"/>
	  	<%-- <input type="hidden" class="form-control" value="${member.id}" name="writer"> --%>
	  	<form:errors path="writer"></form:errors>
	  </div>
	  <div class="form-group">
	    <label for="contents">Contents:</label>
	    <form:textarea path="contents" rows="" cols="" class="form-control" id="summernote"></form:textarea>
	    <h1><form:errors path="contents"></form:errors></h1>
	  </div>
	  
	  <input type="button" class="btn btn-info" id="add" value="FileAdd">
	  <div class="form-group" id="f">	  	
	  </div>

	  <button type="submit" class="btn btn-default">Submit</button>
	</form:form>


</body>
</html>