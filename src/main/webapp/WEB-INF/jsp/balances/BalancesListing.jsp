<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="clientes">
    <h2>Income Statements</h2>
    <table id="clientesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Month</th>
            <th>Year</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${balances}" var="balance">
            <tr>
                <td>
                    <c:out value="${balance.month}"/>
                </td>
                <td>
                    <c:out value="${balance.year}"/>
                </td>
                <td>
                    <a href="/balances/${balance.id}">
                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                    </a>
                </td>
                <td>
                	<sec:authorize access="hasAuthority('admin')">
                    	<a href="/balances/${balance.id}/edit">
                        	<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                    	</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
