<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="circuitos">
    <h2>
        <c:if test="${circuito['new']}">New </c:if> Circuit
    </h2>
    <form:form modelAttribute="circuito" class="form-horizontal" id="add-circuito-form">
        <div class="form-group has-feedback">

            <petclinic:inputField label="Nombre " name="name"/>
            <petclinic:inputField label="Aforo" name="aforo"/>
            <petclinic:inputField label="Descripción" name="descripcion"/>
             <c:forEach items="${salas}" var="sala">
			<div class="control-group">
                    <petclinic:selectField name="Salas" label="Salas " names="${sala.name}" size="5"/>
               </div>
               </c:forEach>

            <petclinic:inputField label="Name " name="name"/>
            <petclinic:inputField label="Capacity" name="aforo"/>
            <petclinic:inputField label="Employee" name="empleado"/>
            <petclinic:inputField label="Description" name="descripcion"/>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${circuito['new']}">
                        <button class="btn btn-default" type="submit">Add Circuit</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Circuit</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>