<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
 <!--   <h2><fmt:message key="welcome"/></h2> -->
    <div class="col-md-12">
    	<spring:url value="/resources/images/spa1.jpg" htmlEscape="true" var="ImagenSpa"/>
    	<img src="/resources/images/spa1.jpg" class="img-responsive" src="${SpaImage} "/>
    </div>
	
	
<!DOCTYPE html >
<html>
<head>
<style>
div.polaroid {
  margin-top: 10%;
  width: 40%;
  background-color: white;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin-bottom: 25px;
}

div.container {
  text-align: justify;
  padding: 10px 20px;
}

</style>
</head>
<body>

<h2 style="margin-top: 50%">Some of our Rooms</h2>

<div class="polaroid" id="jacuzzi" style="margin-top:-1%">
  <img src="/resources/images/jacuzzi.jpg" style="width:100%" >
  <div class="container">
  <p>Jacuzzi</p>
  </div>
</div>

<div class="polaroid" id="chorros">
  <img src="/resources/images/piscinachorros.jpg" style="width:100%" >
  <div class="container">
  <p>Relaxing Pool</p>
  </div>
</div>

<div class="polaroid" style="margin-left: 60%; margin-top: -76% " id="piscina">
  <img src="/resources/images/piscina.jpg" style="width:100%" >
  <div class="container">
  <p>Swimming Pool </p>
  </div>
</div>

<div class="polaroid" style="margin-left: 60%; margin-top: 11%" id="sauna">
  <img src="/resources/images/sauna.jpg" style="width:100%" >
  <div class="container">
  <p>Sauna</p>
  </div>
</div>
</body>
</html>

<!DOCTYPE html>
<html>
<head>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>

<h2 style=margin-top:10%;>Subscriptions Available</h2>

<table>
  <tr>
    <th>Subscription</th>
    <th>Monthly Fee (Euros) </th>
    <th>Description</th>
  </tr>
  <tr>
    <td>Morning Subscription</td>
    <td>20</td>
    <td>You can access to spa in the morning.</td>
  </tr>
  <tr>
    <td>Afternoon Subscription</td>
    <td>20</td>
    <td>You can access to spa in the afternoon.</td>
  </tr>
  <tr>
    <td>Premium Subscription</td>
    <td>35</td>
    <td>You can access to spa when you want.</td>
  </tr>
</table>

</body>
</html>

<!DOCTYPE html>
<html>
<head>
	<style>
h1{margin-top: 10%;}
iframe{
	margin-left: 35%;
	margin-top: -20%;
}
	</style>  
</head>
	<body>
      <h1>About Us</h1>
       <p></p>
      <table>
            <tr><h2>You can Find Us in:</h2>
          <p>35 Forth St<br>
          United Kingdom
          <br>
          <br>
          <h2>Contact Us:</h2>
          <p> Tel: +44 955 22 22 22 / +44 600 50 50 55<br>
  			E-Mail:info@spa.es
          </p></tr>
          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d36647.313471115915!2d-1.6087626896064664!3d54.965082887178305!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xbf6003de25b50c7b!2sMineral%20House%20Spa%2C%20Pool%20%26%20Gymnasium!5e0!3m2!1ses!2ses!4v1607182668435!5m2!1ses!2ses" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
        
      </table>
   </body>
</html>

</petclinic:layout>
