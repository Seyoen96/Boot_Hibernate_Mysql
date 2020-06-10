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
	<table class="table table-striped">
	    <thead>
	      <tr>
	        <th>Name</th>
	        <th>Email</th>
	        <th>Phone</th>
	      </tr>
	    </thead>
	    
	    <tbody>
	      <tr>
	        <td>${vo.name}</td>
	        <td>${vo.email}</td>
	       <td>${vo.phone}</td>
	      </tr>
	      </tbody>
	</table>

	    <a href="memberUpdate?id=${vo.id}" class="btn btn-primary">Update</a>
		<a href="memberDelete?id=${vo.id}" class="btn btn-danger">Delete</a>
	     
  	</form>


</div>

</body>
</html>

	
