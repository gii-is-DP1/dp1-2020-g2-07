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


<script type="text/javascript">
window.onload = function() { 
	
	var chart = new CanvasJS.Chart("chartContainer", {	
		animationEnabled: true,
		title:{
			text: "Income Statement, 2020"
		},
		axisX:{
			labelMaxWidth: 75
		},
		axisY: {
			prefix: "$",
			includeZero: true
		},	
		data: [{
			type: "waterfall",
			yValueFormatString: "$#,##0",
			indexLabel: "{y}",
			risingColor: "#50cdc8",
			fallingColor: "#ff6969",
			dataPoints: ${dataPoints}
		}]
	});
	chart.render();
	 
	}
	
</script>
<div id="chartContainer" style="height: 370px; width: 80%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
