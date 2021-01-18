<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
	<div class="col-md-12"></div>

	<!DOCTYPE html>
		<html>
			<head>
				<meta name="viewport" content="width=device-width, initial-scale=1">
				<style>
					.mySlides {display: none;}
					img {vertical-align: middle;}
					/* Slideshow container */
					.slideshow-container {
					  max-width: 1000px;
					  position: relative;
					  margin: auto;
					  border-style: solid;
					}
					/* Number text (1/3 etc) */
					.numbertext {
					  color: #f2f2f2;
					  font-size: 12px;
					  padding: 8px 12px;
					  position: absolute;
					  top: 0;
					}
					/* The dots/bullets/indicators */
					.dot {
					  height: 15px;
					  width: 15px;
					  margin: 0 2px;
					  background-color: #bbb;
					  border-radius: 50%;
					  display: inline-block;
					  transition: background-color 0.6s ease;
					}
					.active {
					  background-color: #717171;
					}
					/* Fading animation */
					.fade {
					  -webkit-animation-name: fade;
					  -webkit-animation-duration: 3s;
					  animation-name: fade;
					  animation-duration: 3s;
					}
					@-webkit-keyframes fade {
					  from {opacity: .4} 
					  to {opacity: 1}
					}
					@keyframes fade {
					  from {opacity: .4} 
					  to {opacity: 1}
					}
					/* On smaller screens, decrease text size */
					@media only screen and (max-width: 300px) {
					  .text {font-size: 11px}
					}
				</style>
			</head>
			<body>
				<div class="slideshow-container">
					<div class="mySlides fade">
			  			<div class="numbertext">1 / 3</div>
			  			<img src="/resources/images/fachada.jpg" style="width:100%">
					</div>
			
					<div class="mySlides fade">
			  			<div class="numbertext">2 / 3</div>
			  			<img src="/resources/images/personal.jpg" style="width:100%; height: 100%">
					</div>
			
					<div class="mySlides fade">
			  			<div class="numbertext">3 / 3</div>
			  			<img src="/resources/images/taquillas.jpg" style="width:100%">
					</div>
			
				</div>
				<br>	
			
				<div style="text-align:center">
			  		<span class="dot"></span> 
			  		<span class="dot"></span> 
			 		<span class="dot"></span> 
				</div>

				<script>
					var slideIndex = 0;
					showSlides();
					function showSlides() {
				  		var i;
				  		var slides = document.getElementsByClassName("mySlides");
				  		var dots = document.getElementsByClassName("dot");
				  		for (i = 0; i < slides.length; i++) {
				    		slides[i].style.display = "none";  
				  		}
				  		slideIndex++;
				  		if (slideIndex > slides.length) {slideIndex = 1}    
				  		for (i = 0; i < dots.length; i++) {
				    		dots[i].className = dots[i].className.replace(" active", "");
				  		}
				  		slides[slideIndex-1].style.display = "block";  
				  		dots[slideIndex-1].className += " active";
				  		setTimeout(showSlides, 2000); // Change image every 2 seconds
					}
				</script>
			</body>
		</html>


		<!DOCTYPE html >
			<html>
				<head>
					<link rel="stylesheet" href="/resources/css/CSS.css">
				</head>
				<body>
					<div>
						<img id="divisor1" src="/resources/images/divisor1.png" style="width:100%" >
					</div>
					<h2 id="uno"></h2>
					<div class="polaroid" id="jacuzzi" style="margin-top:2%">
				  		<a href="/salas"><img id="imagen" src="/resources/images/jacuzzi.jpg" style="width:100%" ></a>
				  		<div class="container">
				  			<p>Jacuzzi</p>
				 		 </div>
					</div>
					<div class="polaroid" id="chorros" style="margin-top: 1%">
				  		<a href="/salas"><img id="imagen" src="/resources/images/piscinachorros.jpg" style="width:100%" ></a>
				  		<div class="container">
				  			<p>Relaxing Pool</p>
				  		</div>
					</div>
					<div class="polaroid" style="margin-left: 54%; margin-top: -68% " id="piscina">
				  		<a href="/salas"><img id="imagen" src="/resources/images/piscina.jpg" style="width:100%" ></a>
				  		<div class="container">
				  			<p>Swimming Pool </p>
				  		</div>
					</div>
					<div class="polaroid" style="margin-left: 54%; margin-top: 1%" id="sauna">
				  		<a href="/salas"><img id="imagen" src="/resources/images/sauna.jpg" style="width:100%" ></a>
				  		<div class="container">
				  			<p>Sauna</p>
				  		</div>
					</div>
					
					<div>
						<img id="divisor2" src="/resources/images/divisor2.png" style="width:100%" >
					</div>
					
					<div id="tabla-precios" style="margin-top:-40%">
						<div class="precio-col" >
 							<div class="precio-col-header">
 							<img style="width: 50%; margin-left: 22%; margin-top: -10%" src="/resources/images/mañana.png">
 								
 							</div>
							<div class="precio-col-features">
								<p>Morning Subscription</p>
								<p>You can access to spa in the morning</p>
							 	<p>The best option if you have busy afternoons</p>
							 	<p>20 pound sterlings/month</p>
							 	<h3 style="text-align: center; margin-top: 4%">Contact Us!</h3>
	 						</div>
 						</div>

						<div class="precio-col">
 							<div class="precio-col-header">
 								<img style="width: 50%; margin-left: 22%;" src="/resources/images/lingote.png">
 							</div>
							<div class="precio-col-features">
 								<p>Premium Subscription</p>
								<p>You can access to spa when you want</p>
								<p>The best option if you want to enter the spa whenever you want</p>
								<p>35 Pound sterlings/month</p>
								<h3 style="text-align: center; margin-top: 4%">Contact Us!</h3>
 							</div>
 						</div>

						<div class="precio-col">
 							<div class="precio-col-header">
 								<img style="width: 40%; margin-left: 30%; " src="/resources/images/tarde.png">
 							</div>
							<div class="precio-col-features">
								<p>Afternoon Subscription </p>
								<p>You can access to spa when you want</p>
								<p>The best option if you have busy mornings</p>
								<p>20 Pound sterlings/month</p>
								<h3 style="text-align: center; margin-top: 4%">Contact Us!</h3>
 							</div>
 						</div>
					</div>
					
					<div><img id="divisor3" src="/resources/images/divisor3.png" style="width:100%" ></div>
					
					<div style="margin-top: -35%; margin-left: 9%">
				    			<div style="border-style: solid; width: 30%">
				    			<h2 style="margin-left: 2%">You can Find Us in:</h2>
				        		<p style="margin-left: 2%">35 Forth St<br>
				          			United Kingdom
				        		<br>
				        		<br>
				        		<h2 style="margin-left: 2%">Contact Us:</h2>
				        		<p style="margin-left: 2%"> Tel: +44 955 22 22 22 / +44 600 50 50 55<br>
				  				E-Mail:info@spa.es
				        		</p>
				        		</div>
				        	
				     		<div>
        					<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d36647.313471115915!2d-1.6087626896064664!3d54.965082887178305!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xbf6003de25b50c7b!2sMineral%20House%20Spa%2C%20Pool%20%26%20Gymnasium!5e0!3m2!1ses!2ses!4v1607182668435!5m2!1ses!2ses" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
							</div>
						
					</div>
				</body>
			</html>
</petclinic:layout>