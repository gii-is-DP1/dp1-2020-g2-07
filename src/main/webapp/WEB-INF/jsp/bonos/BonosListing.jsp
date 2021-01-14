<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bonos">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>
			<body>    
    			<h2>Tokens</h2>
					<div class="table-title">
    					<table id="bonosTable" class="table-fill">
        					<thead>
        						<tr>
            						<th class="text-left">Code </th>
            						<th class="text-left">Price</th>
            						<th class="text-left">Valid Start</th>
            						<th class="text-left">Valid End</th>
            						<th class="text-left">Description</th>
            						<th class="text-left" width="2%"></th>
        						</tr>
        					</thead>
        					<tbody class="table-hover">
        						<c:forEach items="${bonos}" var="bono">
            						<tr>
                						<td class="text-left">
                							<c:out value="${bono.codigo}"/>  
                						</td>
                						<td class="text-left">
                							<c:out value="${bono.precio}"/>
                						</td>
                						<td class="text-left">
                    						<c:out value="${bono.date_start}"/>
                						</td>
                						<td class="text-left">
                    						<c:out value="${bono.date_end}"/>
                						</td>
                						<td class="text-left">
                    						<c:out value="${bono.descripcion}"/>
                						</td>
                						<td class="text-left">
                    						<a href="/bonos/${bono.id}/delete">
                        						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    						</a>
                						</td>
           	 						</tr>
        						</c:forEach>
        					</tbody>
    					</table>
    				</div>
    			</body>
    		</html>
</petclinic:layout>
