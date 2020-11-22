<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="salas">
    <h2>Rooms</h2>

    <table id="salasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th width= 15%>Horario</th>
            <th>Employee</th>
            <th>Capacity</th>
            <th>Description</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${salas}" var="sala">
            <tr>
                <td>
                    <c:out value="${sala.name}"/>
                </td>
                <td>
                    <c:out value="${sala.horario}"/>
                </td>
                <td>
                    <c:out value="${sala.empleado}"/>
                </td>
                <td>
                    <c:out value="${sala.aforo}"/>
                </td>
                <td>
                    <c:out value="${sala.descripcion}"/>
                </td>

                <td>
                    <a href="/salas/${sala.id}/edit">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                </td>
                <td>
                    <a href="/salas/${sala.id}/delete">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="form-group">
   	    <p>
       		<a href="/salas/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add room</a>
    	</p>
	</div>
</petclinic:layout>