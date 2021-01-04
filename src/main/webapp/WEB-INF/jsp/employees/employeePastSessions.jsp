<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="pastSessions">

<h3>Previous worked days of <c:out value="${employee.first_name} ${employee.last_name}"/></h3>
    <table id="sesionesPrevias" class="table table-striped">
 	  <thead>
        <tr>
        	<th>Date</th>
        	<th width=5%></th>
        </tr>
        </thead>
   	 	<tbody>
        	<c:forEach items="${past}" var="horario">
            <tr>
            <td><c:out value="${horario.fecha}"/></td>
            <td>
            <spring:url value="/employees/{employeeId}/TimeTable/{horarioId}" var="employeePastSessionsViewUrl">
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
    
</petclinic:layout>