<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bonos">
    <h2>Tokens</h2>

    <table id="bonosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 25%">Code </th>
            <th style="width: 25%">Price</th>
            <th style="width: 25%">Valid Start</th>
            <th style="width: 25%">Valid End</th>
            <th style="width: 25%">Description</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bonos}" var="bono">
            <tr>
                <td>
                	<c:out value="${bono.codigo}"/>  
                </td>
                <td>
                	<c:out value="${bono.precio}"/>
                </td>
                <td>
                    <c:out value="${bono.date_start}"/>
                </td>
                <td>
                    <c:out value="${bono.date_end}"/>
                </td>
                <td>
                    <c:out value="${bono.descripcion}"/>
                </td>
                <td>
                    <a href="/bonos/${bono.id}/edit">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                </td>
                <td>
                    <a href="/bonos/${bono.id}/delete">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
     <p>
    	<a href="/bonos/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Añadir Bono</a>
    </p>
</petclinic:layout>
