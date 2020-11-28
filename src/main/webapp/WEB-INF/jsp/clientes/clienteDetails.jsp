<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="clients">

    <h2>Client Profile</h2>


    <table class="table table-striped" id="emploee_details">
        <tr>
            <th>Name & Surname</th>
            <td><b><c:out value="${cliente.nombre} ${cliente.apellidos}"/></b></td>
        </tr>
        <tr>
            <th>Addres</th>
            <td><b><c:out value="${cliente.direccion}"/></b></td>
        </tr>
        <tr>
            <th>IBAN</th>
            <td><b><c:out value="${cliente.IBAN}"/></b></td>
        </tr>
        <tr>
            <th>Categoria</th>
            <td><b><c:out value="${cliente.suscripcion}"/></b></td>
        </tr>
    </table>

    <spring:url value="{id}/edit" var="editUrl">
        <spring:param name="id" value="${cliente.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit</a>

    <spring:url value="{id}/delete" var="deleteUrl">
        <spring:param name="id" value="${cliente.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete</a>

    <br/>
    <br/>
    <br/>

    <h3>Pays of <c:out value="${cliente.nombre} ${cliente.apellidos}"/></h3>
    <table id="employeesSalaries" class="table table-striped">
        <thead>
        <tr>
            <th>Date</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cliente.pagos}" var="pay">
            <tr>
                <td>
                    <c:out value="${pay.fEmision}"/>
                </td>
                <td>
                    <c:out value="${pay.cantidad}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <spring:url value="/clientes/{clientId}/newPay" var="clientePayUrl">
        <spring:param name="clientId" value="${cliente.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(clientePayUrl)}"><span class="glyphicon glyphicon-plus"
                                                       aria-hidden="true"></span>New Pay</a>
</petclinic:layout>
