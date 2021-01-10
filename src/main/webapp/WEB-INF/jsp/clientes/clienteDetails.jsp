<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="clients">

    <h2>Client Profile</h2>
    <sec:authorize access="hasAuthority('client')">
    <button onclick="location.href = '/bonos/redeem_token';" id="myButton" class="float-left submit-button" >Reedem Token</button>
	</sec:authorize>

    <table class="table table-striped" id="emploee_details">
        <tr>
            <th>Name & Surname</th>
            <td><b><c:out value="${cliente.first_name} ${cliente.last_name}"/></b></td>
        </tr>
        <tr>
            <th>Addres</th>
            <td><b><c:out value="${cliente.address}"/></b></td>
        </tr>
        <tr>
            <th>IBAN</th>
            <td><b><c:out value="${cliente.IBAN}"/></b></td>
        </tr>
        <tr>
            <th>Subscription</th>
            <td><b><c:out value="${cliente.suscripcion}"/></b></td>
        </tr>
        <tr>
            <th>Email</th>
            <td><b><c:out value="${cliente.email}"/></b></td>
        </tr>
    </table>

    <spring:url value="{id}/edit" var="editUrl">
        <spring:param name="id" value="${cliente.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit</a>

    <sec:authorize access="hasAuthority('admin')">
    <spring:url value="{id}/delete" var="deleteUrl">
        <spring:param name="id" value="${cliente.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete</a>
    </sec:authorize>

    <br/>
    <br/>
    <br/>

    <h3>Pays of <c:out value="${cliente.first_name} ${cliente.last_name}"/></h3>
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
    <sec:authorize access="hasAuthority('admin')">
        <spring:url value="/clientes/{clientId}/newPay" var="clientePayUrl">
            <spring:param name="clientId" value="${cliente.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(clientePayUrl)}"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>New Pay</a>
    </sec:authorize>
    
    <h3>Appointments of <c:out value="${cliente.first_name} ${cliente.last_name}"/></h3>
    <table id="employeesSalaries" class="table table-striped">
        <thead>
        <tr>
            <th>Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Room</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cliente.citas}" var="cita">
            <tr>
                <td>
                    <c:out value="${cita.sesion.horario.fecha}"/>
                </td>
                <td>
                    <c:out value="${cita.sesion.horaInicio}"/>
                </td>
                <td>
                    <c:out value="${cita.sesion.horaFin}"/>
                </td>
                <td>
                    <c:out value="${cita.sesion.sala.name}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>  
</petclinic:layout>
