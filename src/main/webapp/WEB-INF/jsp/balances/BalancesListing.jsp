<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
           
           

<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
map = new HashMap<Object,Object>(); map.put("label", "Health"); map.put("y", 35); map.put("exploded", true); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Finance"); map.put("y", 20); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Career"); map.put("y", 18); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Education"); map.put("y", 15); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Family"); map.put("y", 5); list.add(map);
map = new HashMap<Object,Object>(); map.put("label", "Real Estate"); map.put("y", 7); list.add(map);
 
String dataPoints = gsonObj.toJson(list);
%>           
           
                  
<petclinic:layout pageName="clientes">
    <h2>Balances</h2>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>


<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
	 
	var chart = new CanvasJS.Chart("chartContainer", {
		theme: "light2",
		animationEnabled: true,
		exportFileName: "New Year Resolutions",
		exportEnabled: true,
		title:{
			text: "Top Categories of New Year's Resolution"
		},
		data: [{
			type: "pie",
			showInLegend: true,
			legendText: "{label}",
			toolTipContent: "{label}: <strong>{y}%</strong>",
			indexLabel: "{label} {y}%",
			dataPoints : <%out.print(dataPoints);%>
		}]
	});
	 
	chart.render();
	 
	}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 70%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>  


