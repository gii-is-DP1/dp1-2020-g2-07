<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
              description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation" style="margin-right: -15%">
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

                <petclinic:menuItem active="${name eq 'Clients'}" url="/clientes"
                                    title="clients">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                    <span>Clients</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'Income Statements'}" url="/balances"
                                    title="Income Statements">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                    <span>Income Statements</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'Rooms'}" url="/salas"
                                    title="salas activas del spa">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                    <span>Rooms</span>
                </petclinic:menuItem>
                <petclinic:menuItem active="${name eq 'Circuits'}" url="/circuitos"
                                    title="Circuits">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                    <span>Circuits</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'Employees'}" url="/employees" title="employees">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                    <span>Employees</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'Towels'}" url="/toallas" title="Towels">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                    <span>Towels</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'error'}" url="/oups"
                                    title="trigger a RuntimeException to see how it is handled">
                    <span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
                    <span>Error</span>
                </petclinic:menuItem>

                <petclinic:menuItem active="${name eq 'Tokens'}" url="/bonos"
                                    title="Tokens">
                    <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                    <span>Tokens</span>
                </petclinic:menuItem>

            </ul>
            <ul class="nav navbar-nav navbar-right" style="margin-right: -13%; margin-top: 5%">
                <sec:authorize access="!isAuthenticated()">
                    <li><a href="<c:url value="/login" />">Login</a></li>
                    <li><a href="<c:url value="/users/new" />">Register</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>Ã¯Â¿Â½
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
</nav>
