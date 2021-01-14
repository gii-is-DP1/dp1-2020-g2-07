<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="salaDetails">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>
			<body>
    			<h2>Room details </h2>    
    			<table style="height: 20%;" class="table-fill" id="sala_details">
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
					<sec:authorize access="hasAuthority('client')">
        				<tr>
        					<th>Next Sessions</th>
        						<td>
        							<c:choose>
        								<c:when test="${!sesiones.isEmpty()}">
       		        						<form:form modelAttribute="cita" class="form-horizontal" id="apuntarseASesion">
				        						<div class="form-group has-feedback">
				            						<petclinic:selectField label="Select a session" name="sesion" names="${sesiones}" size="1"/>
				            						<input type="hidden" name="cliente" value="${cliente}">
				        						</div>
				        						<div class="form-group">
				            						<div class="col-sm-offset-2 col-sm-10">
				                  						<button class="btn btn-default" type="submit">Sign up to the session</button>
				            						</div>
				        						</div>
			    							</form:form>
        								</c:when>
        								<c:otherwise>
        									<b style="color:red;">Sorry, this room doesn't have sessions soon!</b>
        								</c:otherwise>
        							</c:choose>
        						</td>
        					</tr>
       		 			</sec:authorize>
    				</table>
       			</body>
       		</html>
</petclinic:layout>