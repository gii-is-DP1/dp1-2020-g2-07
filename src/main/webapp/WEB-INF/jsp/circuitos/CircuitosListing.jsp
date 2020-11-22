<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="circuitos">
    <h2>Circuits</h2>

    <table id="circuitosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Capacity</th>
            <th>Description</th>
            <th>Rooms</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${circuitos}" var="circuito">
            <tr>
                <td>
                    <c:out value="${circuito.name} "/>
                </td>
                <td>
                    <c:out value="${circuito.aforo}"/>
                </td>
                <td>
                    <c:out value="${circuito.descripcion}"/>
                </td>
                <td>
                	<ul>
                		<c:forEach items="${circuito.salas}" var="Sala">
                    		<li>${Sala.name}</li>
                		</c:forEach>
                	</ul>
                
                </td>         

                <td>
                    <a href="/circuitos/${circuito.id}/edit">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                </td>
                <td>
                    <a href="/circuitos/${circuito.id}/delete">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
     <p>
        <a href="/circuitos/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add circuit</a>
    </p>
</petclinic:layout>
