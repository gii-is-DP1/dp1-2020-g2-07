<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="circuitos">
    <h2>
        <c:if test="${circuito['new']}">New </c:if> Circuit
    </h2>
    <form:form modelAttribute="circuito" class="form-horizontal" >
     <input type="hidden" name="id" value="${circuito.id}"/>
        <div class="form-group has-feedback">      
            <petclinic:inputField label="Name " name="name"/>                
            <div class="form-group">
                    <label class="col-sm-2 control-label">Capacity</label>
                    <div class="col-sm-10">
                        <c:out value="${circuito.aforo}"/>
                    </div>
                </div>
            <petclinic:inputField label="Description" name="descripcion"/>
			<div class="formGroup">            
            	<div class="col-sm-10">
            	<label>Rooms in the circuit:</label>
            		<form:checkboxes items="${salas}" path="salas" delimiter="&nbsp;&nbsp;&nbsp;"/>
            </div>	
            <div class="has-error ">
            		<form:errors path="salas" class="help-block"/>            	
            	</div>  
           </div> 
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${circuito['new']}">
                        <button class="btn btn-default" type="submit">Add Circuit</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Circuit</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>