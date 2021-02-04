<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="userNotEnable">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>	
			<body>    
    			<h2>Client users</h2>
    			
				<div class="table-title">
    				<table style="height: 20%" id="usersTable" class="table-fill">
        				<thead>
        					<tr>
					            <th class="text-left">Username</th>
					            <th class="text-left">Password</th>
					            <th class="text-left">Enable</th>
					            <th width="20%" class="text-left">Turn on/Delete</th>
					        </tr>
        				</thead>
        				<tbody class="table-hover">
        					<c:forEach items="${clientUsers}" var="user">
            					<tr>
					                <td class="text-left">
					                    <c:out value="${user.username}"/>
					                </td>
					                <td class="text-left">
					                    <c:out value="${user.password}"/>
					                </td>
					                <td class="text-left">
					                    <c:out value="${user.enabled}"/>
					                </td>
                					<td class="text-center">
					                    <c:choose>
					                        <c:when test="${user.enabled == false}">
					                            <spring:url value="/admin/users/{username}/turn_on" var="userUrl">
					                                <spring:param name="username" value="${user.username}"/>
					                            </spring:url>
                            					<a href="${fn:escapeXml(userUrl)}"><span class="glyphicon glyphicon-off" aria-hidden="true"></span></a>
                        					</c:when>
                        					<c:otherwise>
                            					<spring:url value="/admin/users/{username}/delete" var="userUrl">
					                                <spring:param name="username" value="${user.username}"/>
					                            </spring:url>
                            					<a href="${fn:escapeXml(userUrl)}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                        					</c:otherwise>
                    					</c:choose>
                					</td>
            					</tr>
        					</c:forEach>
        				</tbody>
    				</table>
				</div>
	
    			<h2 style="margin-top: 5%">Employee users</h2>
    			
				<div class="table-title">
    				<table style="height: 20%" id="usersTable" class="table-fill">
        				<thead>
        					<tr>
					            <th class="text-left">Username</th>
					            <th class="text-left">Password</th>
					            <th class="text-left">Enabled</th>
					            <th width="20%" class="text-left">Turn on/Delete</th>
					        </tr>
        				</thead>
       	 				<tbody class="table-hover">
					        <c:forEach items="${employeeUsers}" var="user">
					            <tr>
					                <td class="text-left">
					                    <c:out value="${user.username}"/>
					                </td>
					                <td class="text-left">
					                    <c:out value="${user.password}"/>
					                </td>
					                <td class="text-left">
					                    <c:out value="${user.enabled}"/>
					                </td>
					                <td class="text-center">
                    					<c:choose>
                        					<c:when test="${user.enabled == false}">
					                            <spring:url value="/admin/users/{username}/turn_on" var="userUrl">
					                                <spring:param name="username" value="${user.username}"/>
					                            </spring:url>
                            					<a href="${fn:escapeXml(userUrl)}"><span class="glyphicon glyphicon-off" aria-hidden="true"></span></a>
                        					</c:when>
                        				<c:otherwise>
				                            <spring:url value="/admin/users/{username}/delete" var="userUrl">
				                                <spring:param name="username" value="${user.username}"/>
				                            </spring:url>
                            				<a href="${fn:escapeXml(userUrl)}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                        				</c:otherwise>
                    				</c:choose>
                				</td>
           	 				</tr>
        				</c:forEach>
        			</tbody>
    			</table>
			</div>
			
			<a href="birthdays"><img id="imagen" src="/resources/images/cake.png" alt="Check birthdays" style="width:10%; height:10%;"></a>
    	</body>
    </html>
</petclinic:layout>
