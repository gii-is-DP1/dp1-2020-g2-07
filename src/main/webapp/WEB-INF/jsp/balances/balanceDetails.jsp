<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<style>
.accordion {
  background-color: #eee;
  color: #444;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
  transition: 0.4s;
}
.active, .accordion:hover {
  background-color: #ccc;
}
.accordion:after {
  content: '\002B';
  color: #777;
  font-weight: bold;
  float: right;
  margin-left: 5px;
}
.active:after {
  content: "\2212";
}
.panel {
  padding: 0 18px;
  background-color: white;
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.2s ease-out;
}
</style>

<petclinic:layout pageName="Income Statement">
    <h2>Income Statement Details</h2>
    
    <th>Subscriptions</th>
    <button class="accordion"><c:out value="${balance.subs}$"/></button>
	<div class="panel">
  	<p>
  		<c:forEach items="${subs}" var="sub">
  			<td>
  				Client: <c:out value="${sub.cliente.first_name}"/>
  			</td>
  			<td>
  				Quantity: <c:out value="${sub.cantidad}"/>$
  				<br>
  			</td>
        </c:forEach>
    </p>
	</div>
	
	<th>Tokens</th>
    <button class="accordion"><c:out value="${balance.bonos}$"/></button>
	<div class="panel">
  	<p>
  		<c:forEach items="${tokens}" var="token">
  			 <td>
                    Code: <c:out value="${token.codigo}"/>
             </td>
             <td>
                    Price: <c:out value="${token.precio}"/>$
                    <br>
            </td>
        </c:forEach>
    </p>
	</div>
	
	<th>Maintenance</th>
    <button class="accordion"><c:out value="${balance.mante}$"/></button>
	<div class="panel">
  	<p>
  		Electricity: 25$
  		<br>
  		Water: 75$
    </p>
	</div>
	
	<th>Salaries</th>
    <button class="accordion"><c:out value="${balance.salaries}$"/></button>
	<div class="panel">
  	<p>
  		<c:forEach items="${salaries}" var="salary">
  			<td>
  				Employee: <c:out value="${salary.employee.first_name}"/>
  			</td>
  			<td>
                Quantity: <c:out value="${salary.quantity}"/>$
                <br>
            </td>
        </c:forEach>
    </p>
	</div>
    
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