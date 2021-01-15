
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of the active menu: home, owners, vets or error"%>

<%-- <nav class="navbar navbar-default" role="navigation" style="margin-right: -15%">
    <div class="container">
    <!-- <img id="logo" alt="logo" src="/resources/images/logo.png" style="margin-left: -35%; margin-top: -2%"> -->
				 
        <div class="navbar-header">
            <a class="navbar-brand"
               href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#main-navbar">
                <span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse" id="main-navbar" style="margin-top: -15%;">
            <ul class="nav navbar-nav" style="margin-left: -17%">
				 
                <petclinic:menuItem active="${name eq 'home'}" url="/"
                                    title="home page">

                    <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                    <span>Home</span>
                </petclinic:menuItem>
                
                <sec:authorize access="hasAuthority('client')">
                    <petclinic:menuItem active="${name eq 'My profile'}" url="/clientes" title="Myprofile">
                        <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                        <span>My profile</span>
                    </petclinic:menuItem>
                </sec:authorize>
                
                <sec:authorize access="hasAuthority('employee')">
                    <petclinic:menuItem active="${name eq 'My profile'}" url="/employees" title="Myprofile">
                        <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                        <span>My profile</span>
                    </petclinic:menuItem>
                </sec:authorize>


                <sec:authorize access="hasAuthority('admin')">
                    <petclinic:menuItem active="${name eq 'Income Statements'}" url="/balances" title="Income Statements">
                        <span class="glyphicon glyphicon-piggy-bank" aria-hidden="true"></span>
                        <span>Income Statements</span>
                    </petclinic:menuItem>
                </sec:authorize>

                <petclinic:menuItem active="${name eq 'Rooms'}" url="/salas" title="salas activas del spa">
                    <span class="glyphicon glyphicon-tint" aria-hidden="true"></span>
                    <span>Rooms</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'Circuits'}" url="/circuitos" title="Circuits">
                    <span class="glyphicon glyphicon-road" aria-hidden="true"></span>
                    <span>Circuits</span>
                </petclinic:menuItem>

                <sec:authorize access="hasAuthority('admin')">
                    <petclinic:menuItem active="${name eq 'Admin'}" url="/admin" title="adminHome">
                        <span class="glyphicon glyphicon-king" aria-hidden="true"></span>
                        <span>Admin</span>
                    </petclinic:menuItem>
                </sec:authorize>

                <sec:authorize access="hasAuthority('admin')">
                    <petclinic:menuItem active="${name eq 'Tokens'}" url="/bonos"
                                        title="Tokens">
                        <span class="glyphicon glyphicon-ruble" aria-hidden="true"></span>
                        <span>Tokens</span>
                    </petclinic:menuItem>
                </sec:authorize>


            </ul>
            <ul class="nav navbar-nav navbar-right" style="margin-right: -13%; margin-top: 5%">
                <sec:authorize access="!isAuthenticated()">
                    <li><a href="<c:url value="/login" />">Login</a></li>
                    <li><a href="<c:url value="/clientes/new" />">Register</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
                        <strong><sec:authentication property="name" /></strong> <span
                                class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="navbar-login">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <p class="text-center">
                                                <span class="glyphicon glyphicon-user icon-size"></span>
                                            </p>
                                        </div>
                                        <div class="col-lg-8">
                                            <p class="text-left">
                                                <strong><sec:authentication property="name" /></strong>
                                            </p>
                                            <p class="text-left">
                                                <a href="<c:url value="/logout" />"
                                                   class="btn btn-primary btn-block btn-sm">Logout</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <!--
                                                        <li>
                                                            <div class="navbar-login navbar-login-session">
                                                                <div class="row">
                                                                    <div class="col-lg-12">
                                                                        <p>
                                                                            <a href="#" class="btn btn-primary btn-block">My Profile</a>
                                                                            <a href="#" class="btn btn-danger btn-block">Change
                                                                                Password</a>
                                                                        </p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                            -->
                        </ul></li>
                </sec:authorize>
            </ul>
        </div>



    </div>
</nav> --%>

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
            
            <sec:authorize access="hasAuthority('admin')">
            <li><a href="/bonos">Tokens</a></li>
            </sec:authorize>
          </ul>

          <ul class="nav navbar-nav navbar-right">
             <sec:authorize access="!isAuthenticated()">
                    <li><a href="<c:url value="/login" />">Login</a></li>
                    <li><a href="<c:url value="/clientes/new" />">Register</a></li>
             </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
                        <strong><sec:authentication property="name" /></strong> <span
                                class="glyphicon glyphicon-chevron-down"></span>
                    </a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="navbar-login">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <p class="text-center">
                                                <span class="glyphicon glyphicon-user icon-size"></span>
                                            </p>
                                        </div>
                                        <div class="col-lg-8">
                                            <p class="text-left">
                                                <strong><sec:authentication property="name" /></strong>
                                            </p>
                                            <p class="text-left" style="">
                                                   <a href="<c:url value="/logout" />" class="boton_1" style="color: #002CE1">Logout</a>  
                                            </p>
                                        </div>
                                    </div>
                                </div>
                          	   <li class="divider"></li>
                            <!--
                                                        <li>
                                                            <div class="navbar-login navbar-login-session">
                                                                <div class="row">
                                                                    <div class="col-lg-12">
                                                                        <p>
                                                                            <a href="#" class="btn btn-primary btn-block">My Profile</a>
                                                                            <a href="#" class="btn btn-danger btn-block">Change
                                                                                Password</a>
                                                                        </p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </li>
                            -->
                        </ul></li>
                </sec:authorize>
              </ul>
        </div>
        <!-- /.navbar-collapse -->
      </div>
      <!-- /.container-fluid -->
    </nav>
  </div>
</div>
</nav>
