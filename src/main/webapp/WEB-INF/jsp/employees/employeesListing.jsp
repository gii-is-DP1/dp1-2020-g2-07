<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="employees">
    <h2>Employees</h2>

    <table id="employeesTable" class="table table-striped">
        <thead>
        <tr>
            <th>First and Last Name</th>
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
                    <a href="${fn:escapeXml(employeeUrl)}"><c:out value="${employee.first_name} ${employee.last_name}"/></a>
                </td>
                <td>
                    <c:out value="${employee.address}"/>
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
    <sec:authorize access="hasAuthority('admin')">
        <p>
            <a href="/employees/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add employee</a>
        </p>
    </sec:authorize>
</petclinic:layout>
