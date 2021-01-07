<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="userNotEnable">
    <h2>Client users</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Enable</th>
            <th>Turn on/Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clientUsers}" var="user">
            <tr>
                <td>
                    <c:out value="${user.username}"/>
                </td>
                <td>
                    <c:out value="${user.password}"/>
                </td>
                <td>
                    <c:out value="${user.enabled}"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.enabled == false}">
                            <spring:url value="/admin/users/{username}/turn_on" var="userUrl">
                                <spring:param name="username" value="${user.username}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(userUrl)}">
                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <spring:url value="/admin/users/{username}/delete" var="userUrl">
                                <spring:param name="username" value="${user.username}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(userUrl)}">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2>Employee users</h2>

    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Enabled</th>
            <th>Turn on/Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employeeUsers}" var="user">
            <tr>
                <td>
                    <c:out value="${user.username}"/>
                </td>
                <td>
                    <c:out value="${user.password}"/>
                </td>
                <td>
                    <c:out value="${user.enabled}"/>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.enabled == false}">
                            <spring:url value="/admin/users/{username}/turn_on" var="userUrl">
                                <spring:param name="username" value="${user.username}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(userUrl)}">
                                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <spring:url value="/admin/users/{username}/delete" var="userUrl">
                                <spring:param name="username" value="${user.username}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(userUrl)}">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
