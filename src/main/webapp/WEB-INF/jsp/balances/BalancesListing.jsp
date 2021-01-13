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
<!DOCTYPE html >
	<html>
		<head>
			<link rel="stylesheet" href="/resources/css/CSS.css">
		</head>	
		<body>
    <h2 style="margin-left: 34%">Income Statements</h2>
    <div class="table-title">
    <table id="clientesTable" style="vertical-align: middle" class="table-fill">
        <thead>
        <tr>
            <th class="text-left">Month</th>
            <th class="text-left">Year</th>
            <th class="text-left" width="2%"></th>
            <th class="text-left" width="2%"></th>
        </tr>
        </thead>
        <tbody class="table-hover">
        <c:forEach items="${balances}" var="balance">
            <tr>
                <td class="text-left">
                    <c:out value="${balance.month}"/>
                </td>
                <td class="text-left">
                    <c:out value="${balance.year}"/>
                </td>
                <td class="text-left">
                    <a href="/balances/${balance.id}">
                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                    </a>
                </td>
                <td class="text-left">
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
    </div>
</petclinic:layout>
