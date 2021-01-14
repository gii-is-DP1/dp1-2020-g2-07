<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="clientes">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>	
			<body>
				<sec:authorize access="hasAuthority('admin')">
    				<h2>Clients</h2>
    					<div class="table-title">
    					<table style="height: 20%" id="clientesTable" class="table-fill">
        					<thead>
        						<tr>
            						<th>First Name & Last Name</th>
            						<sec:authorize access="hasAuthority('admin')">
            							<th class="text-left">Address</th>
            							<th class="text-left">IBAN</th>
            							<th class="text-left">Subscription</th>
            						</sec:authorize>
        						</tr>
        					</thead>
        					<tbody>
       					 		<c:forEach items="${clientes}" var="cliente">
            						<tr>
                						<td class="text-left">
                							<c:choose>
                    							<c:when test="${cliente.suscripcion == null}">
							                        <spring:url value="/clientes/{clientId}" var="clientUrl">
							                            <spring:param name="clientId" value="${cliente.id}"/>
							                        </spring:url>
							                        <a href="${fn:escapeXml(clientUrl)}"><c:out value="${cliente.first_name} ${cliente.last_name}"/><p style="color: red; font-weight: bold;">[MUST SELECT SUBTYPE]</p></a>
							                    </c:when>
							                    <c:otherwise>
							                        <spring:url value="/clientes/{clientId}" var="clientUrl">
							                            <spring:param name="clientId" value="${cliente.id}"/>
							                        </spring:url>
							                        <a href="${fn:escapeXml(clientUrl)}"><c:out value="${cliente.first_name} ${cliente.last_name}"/></a>
							                    </c:otherwise>
               			 					</c:choose>
                						</td>
               						 	<sec:authorize access="hasAuthority('admin')">
                							<td class="text-left">
							                    <c:out value="${cliente.address}"/>
							                </td>
							                <td class="text-left">
							                    <c:out value="${cliente.IBAN}"/>
							                </td>
							                <td class="text-left">
							                    <c:out value="${cliente.suscripcion}"/>
							                </td>
							    		</sec:authorize>
            						</tr>
        						</c:forEach>
        					</tbody>
    					</table>
    					</div>
    					<sec:authorize access="hasAuthority('admin')">
						    <p>
						        <a style="margin-left: 88%;" href="/clientes/new" class="btn  btn-default"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add Client</a>
						    </p>
    					</sec:authorize>
    				</sec:authorize>
    				<sec:authorize access="hasAuthority('client')">
    					<c:redirect url="/clientes/${c.id}"/>
    				</sec:authorize>
    			</body>
    		</html>
</petclinic:layout>
