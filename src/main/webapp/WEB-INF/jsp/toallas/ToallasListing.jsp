<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="toallas">
	<h2>Toallas</h2>
	    <table id="toallasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 20%;">Amount</th>       
            <th></th>   
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${toallas}" var="toalla">
            <tr>
                <td>                    
                    <c:out value="${toalla.cantidad}"/>
                </td>              
                <td>
                	<a href="/toallas/${toalla.id}/delete">
                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                	</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</petclinic:layout> 
