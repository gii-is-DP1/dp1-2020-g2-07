<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="employees">
    <h2>Employees</h2>

    <table id="employeesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nick</th>
            <th>Name & Surnames</th>
            <th>Address</th>
            <th>IBAN</th>
            <th>Profession</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>
                    <spring:url value="/employees/{employeeId}" var="employeeUrl">
                        <spring:param name="employeeId" value="${employee.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(employeeUrl)}"><c:out value="${employee.nick}"/></a>
                </td>
                <td>
                    <c:out value="${employee.nombre} ${employee.apellidos}"/>
                </td>
                <td>
                    <c:out value="${employee.direccion}"/>
                </td>
                <td>
                    <c:out value="${employee.IBAN}"/>
                </td>
                <td>
                    <c:out value="${employee.profession}"/>
                </td>
        </c:forEach>
        </tbody>
    </table>
    <p>
        <a href="/employees/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add employee</a>
    </p>
</petclinic:layout>
