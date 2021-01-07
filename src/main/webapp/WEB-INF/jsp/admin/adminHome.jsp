<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="admin">
    <h2>Admins</h2>
    <table id="adminsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Username</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${admins}" var="admin">
            <tr>
                <td>
                    <c:out value="${admin.getUser().username}"/>
                </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>
        <a href="/admin/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add admin</a>
    </p>

    <a href="/clientes"><span class="glyphicon glyphicon-th-list" aria-hidden="true"><span>Clients</span></span></a>
    <br>
    <a href="/employees"><span class="glyphicon glyphicon-th-list" aria-hidden="true"><span>Employees</span></span></a>
    <br>
    <br>
    <h3>You have <c:out value="${petitions}"></c:out> petitions of sign up without approve</h3>
    <br>
    <br>
    <a href="/admin/newEmail"><span class="glyphicon glyphicon-envelope" aria-hidden="true">Individual Email</span></a>
    <br>
    <a href="/admin/newAnnouncement"><span class="glyphicon glyphicon-envelope" aria-hidden="true">Announcement</span></a>
    <br>
    <br>
    <a href="/admin/users"><span class="glyphicon glyphicon-user" aria-hidden="true">Users</span></a>
</petclinic:layout>
