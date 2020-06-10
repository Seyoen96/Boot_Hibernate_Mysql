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

<form:form  modelAttribute="memberVO" class="form-horizontal" action="./memberJoin" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">ID:</label>
      <div class="col-sm-10">
        <form:input path="id" type="text" class="form-control" id="id" placeholder="Type id" />
      	<h5><form:errors path="id"></form:errors></h5>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pw">Password:</label>
      <div class="col-sm-10">          
        <form:input  type="password" class="form-control" id="pw" placeholder="Type password" path="pw"/>
      	<h5><form:errors path="pw"></form:errors></h5>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2">Password Check:</label>
      <div class="col-sm-10">          
        <form:input type="password" class="form-control" placeholder="Type password" path="pwCheck"/>
      	<h5><form:errors path="pwCheck"></form:errors></h5>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">Name:</label>
      <div class="col-sm-10">          
        <form:input type="text" class="form-control" id="name" placeholder="Type name" path="name"/>
      	<h5><form:errors path="name"></form:errors></h5>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">          
        <form:input type="email" class="form-control" id="email" placeholder="Type email" path="email"/>
      	<h5><form:errors path="email"></form:errors></h5>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="phone">Phone:</label>
      <div class="col-sm-10">          
        <form:input type="text" class="form-control" id="phone" placeholder="Type phone" path="phone"/>
      	<h5><form:errors path="phone"></form:errors></h5>
      </div>
    </div>
    
    
    <div class="form-group" id="f">	  
    <input type="file" name="files">	
	</div>
    
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>
  </form:form>


</div>

</body>
</html>