<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<petclinic:layout pageName="clientes">
    <h2>Income Statements</h2>
    <table id="clientesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Month</th>
            <th>Year</th>
            <th>Subscriptions</th>
            <th>Bonos</th>
            <th>Salaries</th>
            <th>Manteinance</th>
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
                    <c:out value="${balance.subs}"/>
                </td>
                <td>
                    <c:out value="${balance.bonos}"/>
                </td>
                <td>
                    <c:out value="${balance.salaries}"/>
                </td>
                <td>
                    <c:out value="${balance.mante}"/>
                </td>
                <td>
                    <a href="/balances/${balance.id}">
                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>