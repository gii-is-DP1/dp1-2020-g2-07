<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="admin">
    <a href="/clientes"><span class="glyphicon glyphicon-th-list" aria-hidden="true"><span>Clients</span></span></a>
    <br>
    <a href="/employees"><span class="glyphicon glyphicon-th-list" aria-hidden="true"><span>Employees</span></span></a>


</petclinic:layout>
