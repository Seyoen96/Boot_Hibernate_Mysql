<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<h2>Member Update</h2>
	<form class="form-horizontal" action="./memberUpdate" method="post">
			<div class="form-group">
	      <label class="control-label col-sm-2" for="name">ID:</label>
	      <div class="col-sm-10">          
	        <input type="text" class="form-control" id="name" name="id" readonly="readonly" value="${vo.id}">
	      	<input type="hidden" name="pw" value="${vo.pw}">
	      </div>
	      </div>
	      <div class="form-group">
	      <label class="control-label col-sm-2" for="name">Name:</label>
	      <div class="col-sm-10">          
	        <input type="text" class="form-control" id="name" name="name" value="${vo.name}">
	      </div>
	    </div>
	    
	      <div class="form-group">
	      <label class="control-label col-sm-2" for="email">Email:</label>
	      <div class="col-sm-10">          
	        <input type="text" class="form-control" id="email" name="email" value="${vo.email}"/>
	      </div>
	    </div>
	    
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="phone">Phone:</label>
	      <div class="col-sm-10">          
	        <input type="text" class="form-control" id="phone" name="phone" value="${vo.phone}"/>
	      </div>
	    </div>
	     
	    <div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <button type="submit" class="btn btn-default">Submit</button>
	      </div>
	    </div>
  	</form>


</div>

</body>
</html>

	
