<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="employees">
	<!DOCTYPE html >
			<html>
				<head>
					<link rel="stylesheet" href="/resources/css/CSS.css">
				</head>	
				<body>      
    				<h2>Employees</h2>
					<div class="table-title">
    					<table id="employeesTable" style="height: 20%;" class="table-fill">
        					<thead>
        						<tr>
            						<th class="text-left">First and Last Name</th>
            						<th class="text-left">Address</th>
            						<th class="text-left">IBAN</th>
            						<th class="text-left">Profession</th> 
        						</tr>
        					</thead>
        					<tbody class="table-hover">
        						<c:forEach items="${employees}" var="employee">
            						<tr>
                						<td class="text-left">
                    						<spring:url value="/employees/{employeeId}" var="employeeUrl">
                        						<spring:param name="employeeId" value="${employee.id}"/>
                    						</spring:url>
                    						<a href="${fn:escapeXml(employeeUrl)}"><c:out value="${employee.first_name} ${employee.last_name}"/></a>
                						</td>
                						<td class="text-left">
                    						<c:out value="${employee.address}"/>
                						</td>
                						<td class="text-left">
                    						<c:out value="${employee.IBAN}"/>
                						</td>
                						<td class="text-left">
                    						<c:out value="${employee.profession}"/>
                						</td> 
                					</tr>        
        						</c:forEach>
        					</tbody>
    					</table>
    				</div>
    				<sec:authorize access="hasAuthority('admin')">
        				<p>
            				<a style="margin-left: 85%; margin-top: 2%;" href="/employees/new" class="btn  btn-default"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add employee</a>
        				</p>
    				</sec:authorize>
    			</body>
    		</html>
</petclinic:layout>
