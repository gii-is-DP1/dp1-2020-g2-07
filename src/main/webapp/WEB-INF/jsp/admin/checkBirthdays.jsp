<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="birthdays">
	<!DOCTYPE html >
		<html>
			<head>
                <link rel="stylesheet" href="/resources/css/CSS.css">
				<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.1.0/pure-min.css">
				<link rel="stylesheet" href="http://weloveiconfonts.com/api/?family=fontawesome">
			</head>	
			<body>
                
                <h1>User birthdays</h1>
    			<form:form modelAttribute="tempBean"  class="form-horizontal" id="send birthdays form">
        			<div class="form-group has-feedback">
                        <petclinic:selectField names="${months}" size="1" label="Month:" name="month" />
			            <button style="margin-left: 77%" class="btn btn-default" type="submit">Check birthdays for this month</button>
        			</div>
                </form:form>
                
                <c:if test="${not empty month}">
                    <h2>Client birthdays for <c:out value="${month}"></c:out></h2>
                    <c:choose>
                        <c:when test="${empty clientBdays}">
                            <p>There are no birthdays.</p>
                        </c:when>
                        <c:otherwise>
                            <div class="table-title">
                                <table style="height: 20%" id="usersTable" class="table-fill">
                                    <thead>
                                        <tr>
                                            <th class="text-left">Name</th>
                                            <th class="text-left">Surname</th>
                                            <th class="text-left">Username</th>
                                            <th class="text-left">DOB</th>
                                            <th class="text-left">Age</th>
                                        </tr>
                                    </thead>
                                    <tbody class="table-hover">
                                        <c:forEach items="${clientBdays}" var="user">
                                            <tr>
                                                <td class="text-left">
                                                    <c:out value="${user.first_name}"/>
                                                </td>
                                                <td class="text-left">
                                                    <c:out value="${user.last_name}"/>
                                                </td>
                                                <td class="text-left">
                                                    <a href="/clientes/${user.id}"><c:out value="${user.user.username}"/></a>
                                                </td>
                                                <td class="text-left">
                                                    <c:out value="${user.DOB}"/>
                                                </td>
                                                <td class="text-left">
                                                    <c:out value="${user.age} (will turn ${user.age + 1})"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                        </c:otherwise>
                    </c:choose>

                    <h2>Employee birthdays for <c:out value="${month}"></c:out></h2>
                    <c:choose>
                        <c:when test="${empty employeeBdays}">
                            <p>There are no birthdays.</p>
                        </c:when>
                        <c:otherwise>
                            <div class="table-title">
                                <table style="height: 20%" id="usersTable" class="table-fill">
                                    <thead>
                                        <tr>
                                            <th class="text-left">Name</th>
                                            <th class="text-left">Surname</th>
                                            <th class="text-left">Username</th>
                                            <th class="text-left">DOB</th>
                                            <th class="text-left">Age</th>
                                        </tr>
                                    </thead>
                                    <tbody class="table-hover">
                                        <c:forEach items="${employeeBdays}" var="user">
                                            <tr>
                                                <td class="text-left">
                                                    <c:out value="${user.first_name}"/>
                                                </td>
                                                <td class="text-left">
                                                    <c:out value="${user.last_name}"/>
                                                </td>
                                                <td class="text-left">
                                                    <a href="/employees/${user.id}"><c:out value="${user.user.username}"/></a>
                                                </td>
                                                <td class="text-left">
                                                    <c:out value="${user.DOB}"/>
                                                </td>
                                                <td class="text-left">
                                                    <c:out value="${user.age} (will turn ${user.age + 1})"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                        </c:otherwise>
                    </c:choose>
                </c:if>
    		</body>
    	</html>
</petclinic:layout>
