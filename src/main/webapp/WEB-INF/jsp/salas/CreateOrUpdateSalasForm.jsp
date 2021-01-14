<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="salas">
<!DOCTYPE html >
<html>
<head>
	<link rel="stylesheet" href="/resources/css/CSS.css">

</head>
<body>
    <h2>
        <c:if test="${sala['new']}">New </c:if> Room
    </h2>
    <form:form modelAttribute="sala" class="form-horizontal" id="add-sala-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:inputField label="Capacity" name="aforo"/>
            <petclinic:inputField label="Description" name="descripcion"/>
            <petclinic:selectField name="room_type" label="Employee Profession" names="${room_type}" size="1"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${sala['new']}">
                        <button style="margin-left: 88%" class="btn btn-default" type="submit">Add room</button>
                    </c:when>
                    <c:otherwise>
                        <button style="margin-left: 87%" class="btn btn-default" type="submit">Update room</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>