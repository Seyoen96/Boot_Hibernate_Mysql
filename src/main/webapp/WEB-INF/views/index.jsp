<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./template/boot.jsp"></c:import>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>

<body>
<c:import url="./template/nav.jsp"></c:import>


<div class="container">
	<h1>Index page</h1>
	<h1><spring:message code="hello"></spring:message></h1>
	<%-- <h1><spring:message code="hello"></spring:message></h1>
	<h1><spring:message code="member.welcome" arguments="${name},${phone}" argumentSeparator=","></spring:message></h1>
	<h1><spring:message code="member.deny" text="기본 메세지"></spring:message></h1>
	
	<!-- 다른 위치에 사용하고 싶을 때 -->
	<spring:message code="member.update" text="test message" var="test"></spring:message>
	<img src="./images/3.jpg">
	<!-- 여기에 출력 -->
	<h1>${test}</h1> --%>
</div>



</html>