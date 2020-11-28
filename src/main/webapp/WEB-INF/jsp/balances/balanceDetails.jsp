<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<petclinic:layout pageName="Income Statement">
    <h2>Income Statement Details</h2>

    <table class="table table-striped">
        <tr>
            <th>Subscriptions</th>
            <td><b><c:out value="${balance.subs}"/></b></td>
        </tr>
        <tr>
            <th>Tokens</th>
            <td><b><c:out value="${balance.bonos}"/></b></td>
        </tr>
        <tr>
            <th>Salaries</th>
            <td><b><c:out value="${balance.salaries}"/></b></td>
        </tr>
        <tr>
            <th>Manteinance</th>
            <td><b><c:out value="${balance.mante}"/></b></td>
        </tr>
        
    </table>
    
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
</petclinic:layout>