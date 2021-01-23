<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="sesiones">
		<!DOCTYPE html >
			<html>
				<head>
					<link rel="stylesheet" href="/resources/css/CSS.css">
				</head>
				<body>
    				<h2 style="margin-top: -4%">New session</h2>
    				
    				<form:form modelAttribute="newSesion" class="form-horizontal" id="add-sesion-form">
        				<div class="form-group has-feedback">
          					<petclinic:selectField label="From" name="horaInicio" names="${horaInicio}" size="1"/>
          					<petclinic:selectField label="To" name="horaFin" names="${horaFin}" size="1"/>
          					<input type="hidden" name="horario" value="${horarioID}">
           						<div class="control-group">
                    				<petclinic:selectField name="sala" label="Rooms " names="${sala}" size="5"/>
                				</div>  
        				</div>
        				<div class="form-group">
            				<div class="col-sm-offset-2 col-sm-10">
                  				<button style="margin-left: 88%; margin-top: -2%;" class="btn btn-default" type="submit">Add session</button>
            				</div>
        				</div>
    				</form:form>
            		<br/>
        			<h2>Previous sessions of the day</h2>
        			<div class="table-title">
    					<table style="height: 20%;" id="sesionesPrevias" class="table-fill">
 	  						<thead>
        						<tr>
						        	<th>Rooms</th>
						            <th>From</th>
						            <th>To</th>
						        </tr>
        					</thead>
   	 						<tbody>
        						<c:forEach items="${sesion}" var="sesion">
        							<c:if test="${sesion.horario.getId()==horarioID}">
            							<tr>
								            <td><c:out value="${sesion.sala}"/></td>
								            <td><c:out value="${sesion.horaInicio}"/></td>
								            <td><c:out value="${sesion.horaFin}"/></td>
            							</tr>
            						</c:if>
            					</c:forEach>
        					</tbody>
   						 </table>
    				</div>
    			</body>
    		</html>
</petclinic:layout>