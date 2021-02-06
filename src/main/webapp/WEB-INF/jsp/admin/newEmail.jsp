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
            <script>
                function validateForm(){
                    var address = document.forms["send email form"]["address"].value;
                    var subject = document.forms["send email form"]["subject"].value;
                    var body = document.forms["send email form"]["body"].value;

                    var emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

                    if(address == "" || address == null){
                        alert("Wrong email format")
                        return false;
                    } else if(!emailRegex.test(address)){
                        alert("Address cant be empty")
                        return false;
                    }

                    if (subject == "" || subject == null || subject.length >= 40 || subject.length <= 3){
                        alert("Subject cant be null, contain more than 40 characters or less than 3")
                        return false;
                    }

                    if (body == "" || body == null || body.length >= 40 || body.length <= 3){
                        alert("Body cant be null, contain more than 400 charcters or less than 3")
                        return false;
                    }

                }
            </script>
    			<form:form modelAttribute="email" class="form-horizontal" id="send email form" onsubmit="return validateForm()">
        			<div class="form-group has-feedback">
			            <petclinic:inputField label="To:" name="address"/>
			            <petclinic:inputField label="Subject:" name="subject"/>
			            <petclinic:inputField label="Body:" name="body"/>
			            <button style="margin-left: 88%" class="btn btn-default" type="submit">Send email</button>
        			</div>
    			</form:form>
    		</body>
    	</html>
</petclinic:layout>
