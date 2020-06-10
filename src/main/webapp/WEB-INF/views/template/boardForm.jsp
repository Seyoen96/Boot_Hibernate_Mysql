<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<form action="${board}${path}" method="post" enctype="multipart/form-data">
		<input type="hidden" name="num" id="num" value="${vo.num}">
	  <div class="form-group">
	    <label for="title">Title:</label>
	    <input type="text" class="form-control" value="${vo.title}" id="title" name="title">
	  </div>
	  <div class="form-group">
	    <label for="writer">Writer:</label>
	    <input type="text" class="form-control" disabled="disabled" value="${member.id}" id="writer">
	  	<input type="hidden" class="form-control" value="${member.id}" name="writer">
	  </div>
	  <div class="form-group">
	    <label for="contents">Contents:</label>
	    <textarea rows="" cols="" class="form-control" id="summernote" name="contents">${vo.contents}</textarea>
	  </div>
	  
	  <input type="button" class="btn btn-info" id="add" value="FileAdd">
	  <div class="form-group" id="f">
	  	
	  </div>

	  <button type="submit" class="btn btn-default">Submit</button>
	</form>
	
