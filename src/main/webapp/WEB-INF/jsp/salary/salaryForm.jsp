<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<script>
    $(function () {
        $("#dateStart").datepicker({dateFormat: 'yyyy-mm-dd'});
        $("#dateEnd").datepicker({dateFormat: 'yyyy-mm-dd'});
    });
</script>

<petclinic:layout pageName="salaries">
    <h2>
        <c:if test="${revenue['new']}">New </c:if> Salary
    </h2>
    <form:form modelAttribute="revenue" class="form-horizontal" id="add-employee-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Date start" name="dateStart"/>
            <petclinic:inputField label="Date end" name="dateEnd"/>
            <petclinic:inputField label="Hours Worked" name="hoursWorked"/>
            <petclinic:inputField label="Quantity" name="quantity"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${revenue['new']}">
                        <button class="btn btn-default" type="submit">add Salary</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Salary</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
