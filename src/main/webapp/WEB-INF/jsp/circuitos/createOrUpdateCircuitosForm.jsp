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
    <link rel="stylesheet" href="/resources/css/CSS.css">
    <form:form modelAttribute="circuito" class="form-horizontal" id="add-circuito-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name " name="name"/>
            <c:if test="${!circuito['new']}">                
            <div class="form-group">
                    <label class="col-sm-2 control-label">Capacity</label>
                    <div class="col-sm-10">
                        <c:out value="${circuito.aforo}"/>
                    </div>
                </div>
            </c:if>
            <petclinic:inputField label="Description" name="descripcion"/>
			<petclinic:multiSelect names = "${salas}" name="salas" label = "Rooms in the circuit"></petclinic:multiSelect>

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