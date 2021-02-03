<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="clients">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>
			<body>
				<h2>Client Profile</h2>
				<c:choose>
					<c:when test="${cliente.isBirthday()}">
						<h3>Today is ${cliente.first_name}'s birthday! Happy birthday from the Mineral House Spa team!</h3>
						<img name="honk" src="/resources/images/honkhonk.gif" alt="Honk honk!" width="100" height="100">
					</c:when>
				</c:choose>

				<div class="table-title">
				    <table class="table-fill" id="emploee_details">
				        <tr>
				            <th class="text-left">Name & Surname</th>
				            <td class="text-left"><b><c:out value="${cliente.first_name} ${cliente.last_name}"/></b></td>
				        </tr>
				        <tr>
				            <th class="text-left">Address</th>
				            <td class="text-left"><b><c:out value="${cliente.address}"/></b></td>
						</tr>
						<tr>
							<th>Date of Birth</th>
							<td><b><c:out value="${cliente.DOB} (Age: ${cliente.age})"/></b></td>
						</tr>
				        <tr>
				            <th class="text-left">IBAN</th>
				            <td class="text-left"><b><c:out value="${cliente.IBAN}"/></b></td>
				        </tr>
				        <tr>
				            <th class="text-left">Subscription</th>
				            <td class="text-left"><b><c:out value="${cliente.suscripcion}"/></b></td>
				        </tr>
				        <tr>
				            <th class="text-left">Email</th>
				            <td class="text-left"><b><c:out value="${cliente.email}"/></b></td>
				        </tr>
				    </table>
				</div>
				
				<sec:authorize access="hasAuthority('client')">
			    	<button onclick="location.href = '/bonos/redeem_token';" id="myButton" class="btn btn-default" >Reedem Token</button>
				</sec:authorize>
				
			    <sec:authorize access="hasAuthority('admin')">
				    <spring:url value="{id}/delete" var="deleteUrl">
				        <spring:param name="id" value="${cliente.id}"/>
				    </spring:url>
				    <a style="margin-left: 88%; margin-top: 0%;" href="${fn:escapeXml(deleteUrl)}" class="btn btn-default">Delete</a>
			    </sec:authorize>
    
    			<spring:url value="{id}/edit" var="editUrl">
        			<spring:param name="id" value="${cliente.id}"/>
    			</spring:url>
    			<a style="margin-left: 95%; margin-top: -4.5%" href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit</a>

			    <br/>
			    <br/>
			    <br/>

    			<h2>Pays of <c:out value="${cliente.first_name} ${cliente.last_name}"/></h2>
				    <div class="table-title">
				    <table id="employeesSalaries" class="table-fill">
				        <thead>
				        <tr>
				            <th class="text-left">Date</th>
				            <th class="text-left">Quantity</th>
				        </tr>
				        </thead>
				        <tbody class="table-hover">
					        <c:forEach items="${cliente.pagos}" var="pay">
					            <tr>
					                <td class="text-left">
					                    <c:out value="${pay.fEmision}"/>
					                </td>
					                <td class="text-left">
					                    <c:out value="${pay.cantidad}"/>
					                </td>
					            </tr>
					        </c:forEach>
        				</tbody>
    				</table>
    			</div>
    			
			    <sec:authorize access="hasAuthority('admin')">
			        <spring:url value="/clientes/{clientId}/newPay" var="clientePayUrl">
			            <spring:param name="clientId" value="${cliente.id}"/>
			        </spring:url>
			        <a style="margin-left: 90%" href="${fn:escapeXml(clientePayUrl)}" class="btn btn-default"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>New Pay</a>
			    </sec:authorize>
    
				<br/>
			    <br/>
			    <br/>
	
    			<h2>Appointments of <c:out value="${cliente.first_name} ${cliente.last_name}"/></h2>
    			
    			<div class="table-title">
    				<table style="height: 20%;" id="employeesSalaries" class="table-fill">
        				<thead>
        					<tr>
					            <th class="text-left">Date</th>
					            <th class="text-left">Start Time</th>
					            <th class="text-left">End Time</th>
					            <th class="text-left">Room</th>
					        </tr>
        				</thead>
        				<tbody class="table-hover">
        					<c:forEach items="${cliente.citas}" var="cita">
					            <tr>
					                <td>
					                    <c:out value="${cita.sesion.horario.fecha}"/>
					                </td>
					                <td>
					                    <c:out value="${cita.sesion.horaInicio}"/>
					                </td>
					                <td>
					                    <c:out value="${cita.sesion.horaFin}"/>
					                </td>
					                <td>
					                    <c:out value="${cita.sesion.sala.name}"/>
					                </td>
					            </tr>
					        </c:forEach>
        				</tbody>
    				</table> 
   				 </div>
   			</body>
   		</html>
</petclinic:layout>
