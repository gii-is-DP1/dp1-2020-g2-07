<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="employees">


<h3>Details of the schedule of <c:out value="${employee.first_name} ${employee.last_name}"/> for <c:out value="${horario.fecha}"/></h3>

    <table id="employeesSchedule" class="table table-striped">
 	  <thead>
        <tr>
        	<th>Rooms</th>
            <th>From</th>
            <th>To</th>
            
        </tr>
        </thead>
   	 	<tbody>
        	<c:forEach items="${sesion}" var="sesion">
        	<c:if test="${sesion.horario.getId()==horario.id}">
            <tr>
            <td>
            <c:out value="${sesion.sala}"/>
            </td>
            <td>
            <c:out value="${sesion.horaInicio}"/>
            </td>
            <td>
            <c:out value="${sesion.horaFin}"/>
            </td>
            </tr>
            </c:if>
            </c:forEach>
        </tbody>
    </table>

</petclinic:layout>