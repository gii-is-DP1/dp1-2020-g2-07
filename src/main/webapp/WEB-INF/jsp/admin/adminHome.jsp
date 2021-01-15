<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Hola -->
<petclinic:layout pageName="admin">
	<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
				<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.1.0/pure-min.css">
				<link rel="stylesheet" href="http://weloveiconfonts.com/api/?family=fontawesome">
			</head>
			<body>
    			<h2>Admins</h2>
    			<div class="table-title">
    				<table id="adminsTable" style="height: 20%;" class="table-fill">
        				<thead>
        					<tr>
            					<th class="text-left">Username</th>
        					</tr>
        				</thead>
        				<tbody class="table-hover">
        					<c:forEach items="${admins}" var="admin">
            					<tr>
					                <td class="text-left">
					                    <c:out value="${admin.getUser().username}"/>
					                </td>
            					</tr>
        					</c:forEach>
        				</tbody>
    				</table>
    			</div>
    			
     			<p>
        			<a style="margin-left: 87%" href="/admin/new" class="btn  btn-default"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add admin</a>
    			</p>
	
    			<div style="margin-top: 3%">
    				<h3 style="margin-left: 5%">You have <c:out value="${petitions}"></c:out> petitions of sign up without approve</h3>
    				<svg style="margin-top:-7%;margin-left: 0%" height="50px" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 200 200"><defs><style>.cls-1{fill:#fff;}.cls-2{fill:#ffbd29;}.cls-3{mask:url(#mask);}.cls-4{fill:#ffce20;}.cls-14,.cls-5,.cls-6{fill:none;}.cls-10,.cls-5,.cls-6{stroke:#124458;stroke-width:3px;}.cls-14,.cls-6{stroke-linecap:round;}.cls-7{mask:url(#mask-2);}.cls-8{mask:url(#mask-3);}.cls-9{fill:#fff4ae;}.cls-10{fill:#cf3346;}.cls-11{mask:url(#mask-4);}.cls-12{fill:#ff5963;}.cls-13{mask:url(#mask-5);}.cls-14{stroke:#fff;stroke-width:6px;}</style><mask id="mask" x="60" y="116" width="55" height="56" maskUnits="userSpaceOnUse"><g id="alert-b"><circle id="alert-a" class="cls-1" cx="85" cy="147" r="25"/></g></mask><mask id="mask-2" x="70" y="0" width="24" height="25" maskUnits="userSpaceOnUse"><g id="alert-d"><circle id="alert-c" class="cls-1" cx="85" cy="16" r="9"/></g></mask><mask id="mask-3" x="0" y="10" width="154" height="143" maskUnits="userSpaceOnUse"><g id="alert-f"><path id="alert-e" class="cls-1" d="M141,119q1.92,7.29,5.67,9.28h0A13.86,13.86,0,0,1,154,140.5,12.5,12.5,0,0,1,141.5,153H28.5A12.5,12.5,0,0,1,16,140.5,14,14,0,0,1,23.75,128q4-2,6.25-9V73.5a55.5,55.5,0,0,1,111,0Z"/></g></mask><mask id="mask-4" x="101" y="12" width="56" height="56" maskUnits="userSpaceOnUse"><g id="alert-h"><circle id="alert-g" class="cls-1" cx="127" cy="42" r="26"/></g></mask><mask id="mask-5" x="101" y="16" width="52" height="52" maskUnits="userSpaceOnUse"><g id="alert-j"><circle id="alert-i" class="cls-1" cx="127" cy="42" r="26"/></g></mask></defs><title>Asset 1</title><g id="Layer_2" data-name="Layer 2"><g id="Layer_1-2" data-name="Layer 1"><g class="bell"><g class="ball"><circle id="alert-a-2" data-name="alert-a" class="cls-2" cx="85" cy="147" r="25"/><g class="cls-3"><circle class="cls-4" cx="90" cy="141" r="25"/></g><circle class="cls-5" cx="85" cy="147" r="25"/><path class="cls-6" d="M84.5,164.5A16.5,16.5,0,0,1,68,148"/></g><circle id="alert-c-2" data-name="alert-c" class="cls-2" cx="85" cy="16" r="9"/><g class="cls-7"><circle class="cls-4" cx="79" cy="9" r="9"/></g><circle class="cls-5" cx="85" cy="16" r="7.5"/><path id="alert-e-2" data-name="alert-e" class="cls-2" d="M29,119q-1.92,7.29-5.67,9.28h0A13.86,13.86,0,0,0,16,140.5,12.5,12.5,0,0,0,28.5,153h113A12.5,12.5,0,0,0,154,140.5,14,14,0,0,0,146.25,128q-4-2-6.25-9V73.5a55.5,55.5,0,0,0-111,0Z"/><g class="cls-8"><path class="cls-4" d="M13,111q-1.92,7.29-5.67,9.28h0A13.86,13.86,0,0,0,0,132.5,12.5,12.5,0,0,0,12.5,145h113A12.5,12.5,0,0,0,138,132.5,14,14,0,0,0,130.25,120q-4-2-6.25-9V65.5a55.5,55.5,0,0,0-111,0Z"/></g><path class="cls-5" d="M30.45,119.38l.05-.19V73.5a54,54,0,0,1,108,0v45.73l.07.22c1.59,5,3.9,8.33,7,9.88a12.49,12.49,0,0,1,6.91,11.17,11,11,0,0,1-11,11H28.5a11,11,0,0,1-11-11A12.32,12.32,0,0,1,24,129.61C27,128,29.07,124.61,30.45,119.38Z"/><path class="cls-9" d="M75.81,123.17q-9.68-.61-17.93-.33A153.63,153.63,0,0,0,42,124.23h0a4.6,4.6,0,0,0-4,4.71h0a3.62,3.62,0,0,0,3.75,3.49l.38,0A153.07,153.07,0,0,1,58.16,131q8.3-.28,18,.34h0a3.67,3.67,0,0,0,3.89-3.43c0-.12,0-.24,0-.36h0a4.55,4.55,0,0,0-4.25-4.37Z"/><path class="cls-9" d="M47,62c-4-3.44-1.74-14.49,5-22.2s15.34-9.17,19.3-5.73-4.26,4.49-11,12.2S51,65.46,47,62Z"/><circle class="cls-9" cx="47.14" cy="71.86" r="3.45"/><path class="cls-6" d="M103.08,33a43.13,43.13,0,0,1,22.68,25.27"/></g>
  						<g class="alert"><circle id="alert-g-2" data-name="alert-g" class="cls-10" cx="127" cy="42" r="26"/><g class="cls-11"><circle class="cls-12" cx="131" cy="38" r="26"/></g><circle id="alert-i-2" data-name="alert-i" class="cls-5" cx="127" cy="42" r="26"/><g class="cls-13"><path class="cls-14" d="M127.5,26.5v19m0,10V56"/></g></g></g></g>
 					</svg>
 				</div>
 				
    			<section class="dashboard">
    				<section class="dashboard pure-g-r clearfix">
    					<div class="pure-u-1-3 dashboard-piece" style="background-color: #F77F7D;">
        					<div class="dashboard-content">
            					<h2>Clients</h2>
            					<a href="/clientes"><img id="imagen" src="/resources/images/clientes.png" style="width:50%; vertical-align:text-bottom;" ><span></span></a>
        					</div>
    					</div>
    					
    					<div class="pure-u-1-3 dashboard-piece" style="background-color: #7D8EF7">
        					<div class="dashboard-content">
            					<h2>Employees</h2>
            					<a style= margin-top: -3%;" href="/employees"><img id="imagen" src="/resources/images/empleado.png" style="width:50%;" ></a>
        					</div>
    					</div>
    					
    					<div class="pure-u-1-3 dashboard-piece" style="background-color: #ACF3B1">
        					<div class="dashboard-content">
                				<h2>Users</h2>
                				<a href="/admin/users"><img id="imagen" src="/resources/images/usuario.png" style="width:50%;"></a>  
        					</div>
        					<div class="bar-vertical" style="height:70%;"></div>
    					</div>
    					
     					<div class="pure-u-1-3 dashboard-piece" style="background-color: #8DF2F7">
        					<div class="dashboard-content">
            					<h2>Email Individual</h2>
           						<a href="/admin/newEmail"><img id="imagen" src="/resources/images/email.png" style="width:50%;"></a>
        					</div>
    					</div>
    					
    					<div style="background-color: #D8FAF8" class="pure-u-1-3 dashboard-piece">
        					<div class="dashboard-content">
           		 				<h2></h2>
           						<img alt="" src="/resources/images/logo.png">
        					</div>
    					</div>
    					
    					<div class="pure-u-1-3 dashboard-piece" style="background-color: #BD8DF7">
        					<div class="dashboard-content">
           		 				<h2>Announcement</h2>
            					<a href="/admin/newAnnouncement"><img id="imagen" src="/resources/images/announcement.png" style="width:50%;"></a>
        					</div>
    					</div>
					</section>
				</section>
			</body>
		</html>
</petclinic:layout>
