<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<petclinic:layout pageName="Income Statement">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>	
			<body>
		 		<div id="detalles">
    				<h2>Income Statement Details</h2>
    				
    				<th >Subscriptions</th>
    				<button class="accordion" style="width: 35%"><c:out value="${balance.subs}$"/></button>
					<div class="panel">
  						<p>
  							<c:forEach items="${subs}" var="sub">
  								<td>
  									<strong>Client:</strong> <c:out value="${sub.cliente.first_name}"/>
  								</td>
  								<td>
  									<strong>Quantity:</strong> <c:out value="${sub.cantidad}"/>$
  									<br>
  								</td>
        					</c:forEach>
    					</p>
					</div>
	
					<th>Tokens</th>
    				<button class="accordion" style="width: 40%"><c:out value="${balance.bonos}$"/></button>
					<div class="panel">
  						<p>
  							<c:forEach items="${tokens}" var="token">
  			 					<td>
                    				<strong>Code:</strong> <c:out value="${token.codigo}"/>
             					</td>
             					<td>
                    				<strong>Price:</strong> <c:out value="${token.precio}"/>$
                    				<br>
            					</td>
        					</c:forEach>
    					</p>
					</div>
	
					<th>Salaries</th>
    				<button class="accordion" style="width: 40%"><c:out value="${balance.salaries}$"/></button>
					<div class="panel">
  						<p>
  							<c:forEach  items="${salaries}" var="salary">
  								<td>
  									<strong>Employee:</strong> <c:out value="${salary.employee.first_name}"/>
  								</td>
  								<td>
                					<strong>Quantity:</strong> <c:out value="${salary.quantity}"/>$
                					<br>
            					</td>
        					</c:forEach>
    					</p>
					</div>	
				</div>
				
    			<div id="grafica">
					<script>
						var acc = document.getElementsByClassName("accordion");
						var i;

						for (i = 0; i < acc.length; i++) {
  							acc[i].addEventListener("click", function() {
    							this.classList.toggle("active");
    							var panel = this.nextElementSibling;
    							if (panel.style.maxHeight) {
     	 							panel.style.maxHeight = null;
    							} else {
      								panel.style.maxHeight = panel.scrollHeight + "px";
    							} 
  							});
						}
					</script>
  
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
									risingColor: "#002CE1",
									fallingColor: "#ed1515",
									dataPoints: ${dataPoints}
								}]
						});
						chart.render();
					}
	
				</script>
			</div>
			<div id="chartContainer" style="height: 370px; width: 80%; margin-left: 40%; margin-top: 5%"></div>
			<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>  
		</body>
	</html>
</petclinic:layout>