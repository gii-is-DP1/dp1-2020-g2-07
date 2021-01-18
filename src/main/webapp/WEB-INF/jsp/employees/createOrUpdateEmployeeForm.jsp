<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="employees">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>
			<script>
    			function validateForm(){
        			var fName = document.forms["employeeForm"]["first_name"].value.trim();
        			var lName = document.forms["employeeForm"]["last_name"].value.trim();
        			var age = document.forms["employeeForm"]["age"].value;
        			var address = document.forms["employeeForm"]["address"].value;
        			var IBAN = document.forms["employeeForm"]["IBAN"].value;
        			var email = document.forms["employeeForm"]["email"].value;
        			var nameRegex = /^[a-zA-Z].*[\s\.]*$/;
        			var IBANRegex = /([a-zA-Z]{2})\s*\t*(\d{2})\s*\t*(\d{4})\s*\t*(\d{4})\s*\t*(\d{2})\s*\t*(\d{10})/;
        			var emailRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        			if(fName == "" || fName == null || lName == "" || lName == null){
            			alert("First and last name must be filled");
            			return false;
        			}else if(!nameRegex.test(fName) || !nameRegex.test(lName)){
            			alert("First and last name cant have numbers or special characters");
            			return false;
        			}else if(fName.length < 3 || fName.length > 20){
            			alert("First name must be between 3 and 20 characters");
            			return false;
        			}else if(lName.length < 3 || fName.length > 25){
           				alert("Last name must be between 3 and 25 characters");
            			return false;
        			}else if(age < 18 || age==""){
           				alert("Age must filled and be greater or equal to 18");
            			return false;
        			}
        			if(address.trim() == "" || address == null){
            			alert("Address must be filled");
            			return false;
        			}
        			if(IBAN.trim() == "" || IBAN == null){
            			alert("IBAN must be filled");
            			return false;
        			}else if(!IBANRegex.test(IBAN)){
            			alert("IBAN must be spelled appropriately");
            			return false;
        			}
        			if(email.trim() == "" || email == null){
            			alert("Email must be filled");
            			return false;
        			}else if(!emailRegex.test(email)){
            			alert("IBAN must be spelled appropriately");
            			return false;
        			}
        			return true;
    			}
			</script>
			<body>   
    			<h2 style="margin-top: -4%"><c:if test="${employee['new']}">New </c:if> Employee</h2>
    				<form:form name="employeeForm" modelAttribute="employee" class="form-horizontal" id="add-employee-form" onsubmit="return validateForm()">
        				<div class="form-group has-feedback">
           					<petclinic:inputField label="First Name" name="first_name"/>
            				<petclinic:inputField label="Last Name" name="last_name"/>
            				<petclinic:inputField label="Age" name="age"/>
            				<petclinic:inputField label="Address" name="address"/>
            				<petclinic:inputField label="IBAN" name="IBAN"/>
            				<petclinic:inputField label="Email" name="email"/>
            				<sec:authorize access="hasAuthority('admin')" var="hasAccess"></sec:authorize>
            				<c:choose>
                				<c:when test="${hasAccess}">
                    				<c:choose>
                        				<c:when test="${employee['new']}">
                            				<div class="profession-group">
                                				<petclinic:selectField name="profession" label="Profession" names="${['LIFE_GUARD', 'CLEANER', 'MASSAGIST']}" size="1"/>
                            				</div>
                        				</c:when>
                        				<c:otherwise>
                            				<div class="profession-group">
                                				<petclinic:inputField readonly="true" name="profession" label="Profession"/>
                            				</div>
                        				</c:otherwise>
                    				</c:choose>
                   		 			<c:choose>
                        				<c:when test="${employee['new']}"><petclinic:inputField label="Username" name="user.username"/></c:when>
                        				<c:otherwise>
                        					<petclinic:inputField readonly="true" label="Username" name="user.username"/>
                        				</c:otherwise>
                    				</c:choose>
                				</c:when>
                					<c:otherwise>
                    					<div class="profession-group">
                        					<petclinic:inputField readonly="true" name="profession" label="Profession"/>
                    					</div>
                    					<petclinic:inputField readonly="true" label="Username" name="user.username"/>
                					</c:otherwise>
            					</c:choose>
            					<petclinic:inputField label="Password" name="user.password"/>
        					</div>
        					<div class="form-group">
            					<div class="col-sm-offset-2 col-sm-10">
                					<c:choose>
                    					<c:when test="${employee['new']}">
                        					<button class="btn btn-default" style="margin-left: 87%;" type="submit">add Employee</button>
                    					</c:when>
                    					<c:otherwise>
                        					<button class="btn btn-default" style="margin-left: 84%;" type="submit">Update Employee</button>
                    					</c:otherwise>
                					</c:choose>
            					</div>
        					</div>
    					</form:form>
    				</body>
    			</html>
</petclinic:layout>
