<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="horarios">
    <h2>TimeTable</h2>

    <table id="horariosTable" class="table table-striped">
        <thead>
        <tr>
            <th>From</th>
            <th>To</th>
            <th>Employees</th>
            <th width=2%></th>
            <th width=2%></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${horarios}" var="horario">
            <tr>
                <td>
                    <c:out value="${horario.horaIni}"/>
                </td>
                <td>
                    <c:out value="${horario.horaFin}"/>
                </td>
                
                 <td>
                	<ul>
                		<c:forEach var="employee" items="${horario.employees}">
                        <c:out value="${employee.nombre} "/>
                        <c:out value=","/>
                         </c:forEach>
                	</ul>
                
                </td>  
               
                <td>
                    <a href="/horarios/${horario.id}/edit">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                </td>
                <td>
                    <a href="/horarios/${horario.id}/delete">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="form-group">
   	    <p>
       		<a href="/horarios/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add TimeTable</a>
    	</p>
	</div>
</petclinic:layout>