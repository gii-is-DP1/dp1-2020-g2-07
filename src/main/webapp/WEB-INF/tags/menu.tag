<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true"%>

<nav>
<div class="body-wrap">
  <div class="container">
    <nav class="navbar navbar-inverse" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/"> Mineral House Spa</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
           <sec:authorize access="hasAuthority('client')">
            <li><a href="/clientes">My Profile</a></li>
            </sec:authorize>
            
             <sec:authorize access="hasAuthority('employee')">
            <li><a href="/employees">My Profile</a></li>
            </sec:authorize>
            
            <sec:authorize access="hasAnyAuthority('admin','employee')">
            <li><a href="/balances">Income Statements</a></li>
            </sec:authorize>
            
            <li><a href="/salas">Rooms</a></li>
            
            <li><a href="/circuitos">Circuits</a></li>
            
            <sec:authorize access="hasAuthority('admin')">
            <li><a href="/admin">Admin</a></li>
            </sec:authorize>
            
            <sec:authorize access="hasAnyAuthority('admin','client')">
            <li><a href="/bonos">Tokens</a></li>
            </sec:authorize>
          </ul>

          <ul class="nav navbar-nav navbar-right">
             <sec:authorize access="!isAuthenticated()">
                    <li><a href="<c:url value="/login" />">Login</a></li>
                    <li><a href="<c:url value="/clientes/new" />">Register</a></li>
             </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                    <li class="dropdown">
                    <a href="<c:url value="/logout" />" class="boton_1" style="color: #FF0000">
                    <span class="glyphicon glyphicon-off"></span>
                    <strong><sec:authentication property="name" /></strong>
                    </a> 
             </sec:authorize>
         </ul>
        </div>
      </div>
    </nav>
  </div>
</div>
</nav>
