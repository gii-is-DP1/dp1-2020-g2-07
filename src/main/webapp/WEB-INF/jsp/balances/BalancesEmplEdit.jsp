<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Income Statement Employee Edit">
	<h2>
        Add Employee to Income Statement
    </h2>
    
    <form:form name="createTokenForm" modelAttribute="username" class="form-horizontal" id="add-owner-form">
    <petclinic:inputField label="Username" name="username"/>
    
    
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<button class="btn btn-default" type="submit">Add Employee</button>
            </div>
        </div>
     </form:form>
</petclinic:layout>