<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="employees">


<h3>Details of the TimeTable of <c:out value="${employee.nombre} ${employee.apellidos}"/></h3>
    <table id="employeesTimeTable" class="table table-striped">
 	  <thead>
        <tr>
        	<th>Rooms</th>
            <th>From</th>
            <th>To</th>
            
        </tr>
        </thead>
   	 	<tbody>
        <c:forEach items="${employee.horarios}" var="horario">
            <tr>
            <td>
            <c:out value="${horario.sala}"/>
            </td>
            <td>
            <c:out value="${horario.horaIni}"/>
            </td>
            <td>
            <c:out value="${horario.horaFin}"/>
            </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>






</petclinic:layout>