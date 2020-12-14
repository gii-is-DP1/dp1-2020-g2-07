<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clientes">
    <h2>Clientes</h2>

    <table id="clientesTable" class="table table-striped">
        <thead>
        <tr>
            <th>First Name & Last Name</th>
            <th>Address</th>
            <th>IBAN</th>
            <th>Subscription</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientes}" var="cliente">
            <tr>
                <td>
                    <spring:url value="/clientes/{clientId}" var="clientUrl">
                        <spring:param name="clientId" value="${cliente.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(clientUrl)}"><c:out value="${cliente.first_name} ${cliente.last_name}"/></a>
                </td>
                <td>
                    <c:out value="${cliente.address}"/>
                </td>
                <td>
                    <c:out value="${cliente.IBAN}"/>
                </td>
                <td>
                    <c:out value="${cliente.suscripcion}"/>
                </td>
                <td>
                    <a href="/clientes/${cliente.id}/edit">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                </td>
                <td>
                    <a href="/clientes/${cliente.id}/delete">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>
        <a href="/clientes/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add client</a>
    </p>
</petclinic:layout>