<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="owners">
    <h2>
        <c:if test="${cliente['new']}">New </c:if> Cliente
    </h2>
    <form:form modelAttribute="cliente" class="form-horizontal" id="add-cliente-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="First Name" name="first_name"/>
            <petclinic:inputField label="Last Name" name="last_name"/>
            <petclinic:inputField label="Address" name="address"/>
            <petclinic:inputField label="IBAN" name="IBAN"/>
            
            <c:choose>
  				<c:when test="${cliente['new']}">
					<petclinic:inputField label="Username" name="user.username"/>
					<petclinic:inputField label="Password" name="user.password"/>
				</c:when>
				<c:otherwise>
  					 <sec:authorize access="hasAuthority('admin')" var="hasAccess"></sec:authorize>
            <c:choose>
                <c:when test="${hasAccess}">
                    <div class="suscription-group">
                        <petclinic:selectField name="suscripcion" label="Subscription" names="${['MATINAL', 'VESPERTINO', 'PREMIUM']}" size="1"/>
                    </div>
                    <petclinic:inputField label="Username" name="user.username"/>
                </c:when>
                <c:otherwise>
                    <div class="suscription-group">
                        <petclinic:inputField disabled="true" name="suscripcion" label="Subscription"/>
                    </div>
                    <petclinic:inputField disabled="true" label="Username" name="user.username"/>
                </c:otherwise>
            </c:choose>
 					<petclinic:inputField label="Password" name="user.password"/>
       			</c:otherwise>
			</c:choose>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cliente['new']}">
                        <button class="btn btn-default" type="submit">Add Client</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Client</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
