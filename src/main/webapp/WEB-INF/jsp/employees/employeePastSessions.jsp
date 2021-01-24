<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="pastSessions">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>
			<body>
				<h2 style="margin-top: -4%">Previous worked days of <c:out value="${employee.first_name} ${employee.last_name}"/></h2>
    			<div class="table-title">
    				<table style="height:20%;" id="sesionesPrevias" class="table-fill">
 	  					<thead>
        					<tr>
        						<th class="text-left">Date</th>
        						<th class="text-left" width=2%></th>
        					</tr>
        				</thead>
   	 					<tbody class="table-hover">
        					<c:forEach items="${past}" var="horario">
            					<tr>
            						<td class="text-left"><c:out value="${horario.fecha}"/></td>
            						<td class="text-left">
            							<spring:url value="/employees/{employeeId}/schedule/{horarioId}" var="employeePastSessionsViewUrl">
       			 							<spring:param name="employeeId" value="${employee.id}"/>
       			 							<spring:param name="horarioId" value="${horario.id}"/>
    									</spring:url>
   										<a href="${fn:escapeXml(employeePastSessionsViewUrl)}">
    									<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>
    								</td>
           	 					</tr>
            				</c:forEach>
        				</tbody>
    				</table>
    			</div>
    		</body>
    	</html>
</petclinic:layout>