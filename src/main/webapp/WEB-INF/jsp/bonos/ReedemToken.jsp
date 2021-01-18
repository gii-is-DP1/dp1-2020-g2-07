<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bonos">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>
			<body>    
    		<h2>Redeem Token</h2>
    		<c:out value="${cliente}"/>
    		<form:form modelAttribute="tokencode" class="form-horizontal" id="add-token-form">
        		<div class="form-group has-feedback">
            		<petclinic:inputField label="Code" name="code"/>
        		</div>
        		<button style="margin-left: 88%" class="btn btn-default" type="submit">Redeem</button>
   			 </form:form>
			</body>
		</html>
</petclinic:layout>