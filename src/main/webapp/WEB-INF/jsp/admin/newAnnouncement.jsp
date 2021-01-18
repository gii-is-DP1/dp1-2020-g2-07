<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="email">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>	
			<body>
    			<form:form modelAttribute="email" class="form-horizontal" id="send email form">
        			<div class="form-group has-feedback">
			            <petclinic:selectField label="To:" name="address" names="${['ALL_CLIENTS', 'ALL_EMPLOYEES', 'ALL']}" size="3"/>
			            <petclinic:selectField label="Subject:" name="subject" names="${['NEWS LETTER MINERAL HOUSE SPA', 'SPECIAL OFFER MINERAL HOUSE SPA', 'WORK ADVICE SPA', 'INCIDENT MINERAL HOUSE SPA']}" size="1"/>
			            <petclinic:inputField label="Body:" name="body"/>
			            <button style="margin-left: 88%" class="btn btn-default" type="submit">Send email</button>
			        </div>
    			</form:form>
    		</body>
    	</html>
</petclinic:layout>
