
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="col-md-12">
    	<spring:url value="/resources/images/logoUS.png" htmlEscape="true" var="USImage"/>
    	<img class="img-responsive" src="${USImage}"/>
    </div>
    <div class="row">
    <h2>Project G2-07</h2>
    <p><h2>Group G2-07</h2>
    <p><ul>
    <c:forEach items="${persons}" var="person">
    	<li>${person.firstName}  ${person.lastName}</li>
    </c:forEach>
    </ul>
    </div>
</petclinic:layout>