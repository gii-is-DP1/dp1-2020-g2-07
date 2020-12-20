<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="salaDetails">

    <h2>Room details </h2>
    
    

    <table class="table table-striped" id="sala_details">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${sala.name}"/></b></td>
        </tr>
        <tr>
            <th>Capacity</th>
            <td><b><c:out value="${sala.aforo}"/></b></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><b><c:out value="${sala.descripcion}"/></b></td>
        </tr>
        
<!--         ESTO SOLO SE MUESTRA A LOS CLIENTES -->
        <tr>
        	<th>Next Sessions</th>
        	<c:if test="${sesiones.isEmpty()}">
        	<td><b style="color:red;">Sorry, this room doesn't have sessions soon!</b></td>
        	</c:if><c:if test="${!sesiones.isEmpty()}">
        <td>
        
        <form:form modelAttribute="cita" class="form-horizontal" id="apuntarseASesion">
	        <div class="form-group has-feedback">
	            <petclinic:selectField label="Select a session" name="sesion" names="${sesiones}" size="1"/>
	        </div>
	        <div class="form-group">
	            <div class="col-sm-offset-2 col-sm-10">
	                  <button class="btn btn-default" type="submit">Sign up to the session</button>
	            </div>
	        </div>
    	</form:form>
        </td>
        </c:if>
        </tr>
    </table>
       
</petclinic:layout>