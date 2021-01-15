<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>
			<body>
				<h2>Something happened...</h2>
	
    			<spring:url value="/resources/images/error.jpg" var="error"/>
    			<img style="margin-left: 14%;" src="${error}"/>
				<br>
				<br>
				<p>${exception.message}</p>
			</body>
		</html>
</petclinic:layout>
