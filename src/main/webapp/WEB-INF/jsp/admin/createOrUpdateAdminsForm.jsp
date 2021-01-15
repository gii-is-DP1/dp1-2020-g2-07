<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="admins">
<!DOCTYPE html >
	<html>
		<head>
			<link rel="stylesheet" href="/resources/css/CSS.css">
		</head>	
		<body>
			<script>
				 function validateForm(){
				        var username = document.forms["adminForm"]["user.username"].value;
				        var password = document.forms["adminForm"]["user.password"].value;
	
				        if(username == "" || username == null || password == "" || password == null){
				            alert("User and password must be filled");
				            return false;
				        }
				        return true;
				    }
			</script>    
    		<h2><c:if test="${admin['new']}">New </c:if> Admin</h2>
    		
    		<form:form name="adminForm" modelAttribute="admin" class="form-horizontal" id="add-admin-form" onsubmit="return validateForm()">
        		<div class="form-group has-feedback">
            		<c:choose>
  						<c:when test="${admin['new']}">
							<petclinic:inputField label="Username" name="user.username"/>
						</c:when>
						<c:otherwise>
                    		<petclinic:inputField readonly="true" label="Username" name="user.username"/>
                		</c:otherwise>
            		</c:choose>
            		<petclinic:inputField label="Password" name="user.password"/>
        		</div>
        		
        		<div class="form-group">
            		<div class="col-sm-offset-2 col-sm-10">
                		<c:choose>
                    		<c:when test="${admin['new']}">
                        		<button style="margin-left: 88%" class="btn btn-default" type="submit">Add Admin</button>
                    		</c:when>
	                    	<c:otherwise>
	                        	<button class="btn btn-default" type="submit">Update Admin</button>
	                    	</c:otherwise>
                		</c:choose>
            		</div>
        		</div>
    		</form:form>
    	</body>
    </html>
</petclinic:layout>
