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
            <th>Nick</th>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Direccion</th>
            <th>IBAN</th>
            <th>Suscripcion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientes}" var="cliente">
            <tr>
                <td>
                	 <spring:url value="/clientes/{clienteId}" var="clienteUrl">
                        <spring:param name="clienteId" value="${cliente.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(clienteUrl)}"><c:out value="${cliente.nick}"/></a>
                </td>
                <td>
                    <c:out value="${cliente.nombre}"/>
                </td>
                <td>
                    <c:out value="${cliente.apellidos}"/>
                </td>
                <td>
                    <c:out value="${cliente.direccion}"/>
                </td>
                <td>
                    <c:out value="${cliente.IBAN}"/>
                </td>
                <td>
                    <c:out value="${cliente.suscripcion}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>
