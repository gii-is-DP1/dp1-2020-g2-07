<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="circuitos">
    <h2>Circuitos</h2>

    <table id="circuitosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Aforo</th>
            <th>Descripción</th>
            <th>Salas</th>
 
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
               
                <c:forEach items="${salas}" var="sala">
                <c:if test="${circuito.id==circuito.salas.id}"> </c:if> 
                <c:out value="${sala.name}"/>
               
                </c:forEach>
                
                </td>
                <td>
                <c:forEach items="${circuito.salas}" var="salas">
                    <c:out value="${salas.name}"/>
                </c:forEach>
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
