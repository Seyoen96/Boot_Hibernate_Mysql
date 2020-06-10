<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<h2>Form Test Page</h2>
<form:form modelAttribute="memberVO" class="form-horizontal" action="./memberJoin" method="post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">ID:</label>
      <div class="col-sm-10">
        <form:input path="id" type="text" class="form-control" id="id" placeholder="Type id"/>
      	<h3><form:errors path="id"></form:errors></h3>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pw">Password:</label>
      <div class="col-sm-10">          
        <form:input path="pw" type="password" class="form-control" id="pw" placeholder="Type password"/>
        <h3><form:errors path="pw"></form:errors></h3>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwCheck">Password Check:</label>
      <div class="col-sm-10">          
        <form:input path="pwCheck" type="password" class="form-control" id="pwCheck" placeholder="Type password"/>
        <h3><form:errors path="pwCheck"></form:errors></h3>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="id">Age:</label>
      <div class="col-sm-10">
        <form:input path="age" type="text" class="form-control" id="age" placeholder="Type age"/>
     	<h3><form:errors path="age"></form:errors></h3>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email:</label>
      <div class="col-sm-10">          
        <form:input path="email" type="text" class="form-control" id="email" placeholder="Type email"/>
      	<h3><form:errors path="email"></form:errors></h3>
      </div>
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