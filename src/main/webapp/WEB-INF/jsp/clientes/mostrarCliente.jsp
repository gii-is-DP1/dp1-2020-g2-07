<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Mostrar Cliente">
    <h2>Detalles del Cliente</h2>
    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${cliente.nombre} ${cliente.apellidos}"/></b></td>
        </tr>
        <tr>
            <th>Dirección</th>
            <td><c:out value="${cliente.direccion}"/></td>
        </tr>
        <tr>
            <th>IBAN</th>
            <td><c:out value="${cliente.IBAN}"/></td>
        </tr>
        <tr>
            <th>Suscripción</th>
            <td><c:out value="${cliente.suscripcion}"/></td>
        </tr>
    </table>
    
	<spring:url value="/clientes/{clienteId}/edit" var="editUrl">
    <spring:param name="clienteId" value="${cliente.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Cliente</a>
	

</petclinic:layout>