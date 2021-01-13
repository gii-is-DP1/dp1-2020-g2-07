<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="salas">
<!DOCTYPE html >
<html>
<head>
	<link rel="stylesheet" href="/resources/css/CSS.css">

</head>
<body>
     <h2>Rooms</h2>
 	<div class="table-title">
    <table id="salasTable" class="table-fill">
        <thead>
        <tr>
            <th class="text-left">Name</th>
            <th class="text-left">Capacity</th>
            <th class="text-left">Description</th>
            <th class="text-left" width=2%></th>
            <th class="text-left" width=2%></th>
            <th class="text-left" width=2%></th>
        </tr>
        </thead>
        <tbody class="table-hover">
        <c:forEach items="${salas}" var="sala">
            <tr>
                <td class="text-left">
                    <spring:url value="/salas/{salaId}" var="salaUrl">
                        <spring:param name="salaId" value="${sala.id}"/>
                    </spring:url>
                <a href="${fn:escapeXml(salaUrl)}"><c:out value="${sala.name}"/></a>
                </td>
               
                <td class="text-left">
                    <c:out value="${sala.aforo}"/>
                </td>
                <td class="text-left">
                    <c:out value="${sala.descripcion}"/>
                </td>
                <td class="text-left">
                    <sec:authorize access="hasAuthority('admin')">
                        <a href="/salas/${sala.id}/edit">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </a>
                    </sec:authorize>
                </td>
                <td class="text-left">
                    <sec:authorize access="hasAuthority('admin')">
                        <a href="/salas/${sala.id}/createtoken">
                            <span class="glyphicon glyphicon-barcode" aria-hidden="true"></span>
                        </a>
                    </sec:authorize>
                </td>
                <td class="text-left">
                    <sec:authorize access="hasAuthority('admin')">
                        <a href="/salas/${sala.id}/delete">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </a>
                    </sec:authorize>
                </td>    
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
    <div class="form-group">
   	    <p>
            <sec:authorize access="hasAuthority('admin')"> 
               <a style="margin-left: 87%;margin-top: 2%" href="/salas/new" class="btn btn-default"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add room</a>
            </sec:authorize>
    	</p>
	</div>
</petclinic:layout>