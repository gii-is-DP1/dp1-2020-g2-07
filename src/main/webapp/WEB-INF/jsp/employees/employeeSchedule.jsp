<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="employees">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>	
			<body>   
				<h2>Details of the schedule of <c:out value="${employee.first_name} ${employee.last_name}"/> for <c:out value="${horario.fecha}"/></h2>
				<div class="table-title">
    				<table style="height: 20%" id="employeesSchedule" class="table-fill">
 	 					<thead>
        					<tr>
        						<th class="text-left">Rooms</th>
            					<th class="text-left">From</th>
            					<th class="text-left">To</th>  
        					</tr>
        				</thead>
   	 					<tbody>
        					<c:forEach items="${sesion}" var="sesion">
        					<c:if test="${sesion.horario.getId()==horario.id}">
            					<tr>
            						<td class="text-left">
            							<c:out value="${sesion.sala}"/>
            						</td>
            						<td class="text-left">
            							<c:out value="${sesion.horaInicio}"/>
            						</td>
            						<td class="text-left">
            							<c:out value="${sesion.horaFin}"/>
            						</td>
            					</tr>
            				</c:if>
            			</c:forEach>
        			</tbody>
    			</table>
			</div>
		</body>
	</html>

</petclinic:layout>