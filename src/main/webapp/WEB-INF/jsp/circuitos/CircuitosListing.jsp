<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
            <th>Empleado</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${circuitos}" var="circuito">
            <tr>
                <td>
                    <c:out value="${circuito.name}"/>
                </td>
                <td>
                    <c:out value="${circuito.aforo}"/>
                </td>
                <td>
                    <c:out value="${circuito.descripcion}"/>
                </td>
                <td>
                    <c:out value="${cliente.empleado}"/>
                </td>

                <td>
                    <a href="/circuitos/${circuito.id}/edit">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                </td>
                <td>
                    <a href="/circuitos/${circuitos.id}/delete">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
     <div class="form-group">
    	<div>
    	    <button type="submit" class="btn btn-default">Añadir nuevo circuito</button>
   		</div>
	</div>
</petclinic:layout>
