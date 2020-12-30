<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script>
    function validateForm(){
        var fName = document.forms["clientForm"]["first_name"].value.trim();
        var lName = document.forms["clientForm"]["last_name"].value.trim();
        var address = document.forms["clientForm"]["address"].value;
        var IBAN = document.forms["clientForm"]["IBAN"].value;
        var email = document.forms["clientForm"]["email"].value;
        var username = document.forms["clientForm"]["user.username"].value;
        var password = document.forms["clientForm"]["user.password"].value;
        var nameRegex = /^(?!-)[a-zA-Z-]*[a-zA-Z]$/;
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
<petclinic:layout pageName="owners">
    <h2>
        <c:if test="${cliente['new']}">New </c:if> Cliente
    </h2>
    <form:form name="clientForm" modelAttribute="cliente" class="form-horizontal" id="add-cliente-form"
            onsubmit="return validateForm()">
        <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="first_name"/>
            <petclinic:inputField label="Last Name" name="last_name"/>
            <petclinic:inputField label="Address" name="address"/>
            <petclinic:inputField label="IBAN" name="IBAN"/>
            <petclinic:inputField label="Email" name="email"/>

            <sec:authorize access="hasAuthority('admin')" var="hasAccess"></sec:authorize>
            <c:choose>
  				<c:when test="${cliente['new']}">
					<petclinic:inputField label="Username" name="user.username"/>
					<petclinic:inputField label="Password" name="user.password"/>
				</c:when>
				<c:otherwise>

                    <c:choose>

                        <c:when test="${hasAccess}">
                            <petclinic:selectField name="suscripcion" label="Subscription" names="${['MATINAL', 'VESPERTINO', 'PREMIUM']}" size="1"/>
                            <petclinic:inputField label="Username" name="user.username"/>
                        </c:when>
                        <c:otherwise>
                            <petclinic:inputField disabled="true" name="suscripcion" label="Subscription"/>
                            <petclinic:inputField disabled="true" label="Username" name="user.username"/>
                        </c:otherwise>
                    </c:choose>
                            <petclinic:inputField label="Password" name="user.password"/>
                </c:otherwise>
			</c:choose>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cliente['new']}">
                        <button class="btn btn-default" type="submit">Add Client</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Client</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>