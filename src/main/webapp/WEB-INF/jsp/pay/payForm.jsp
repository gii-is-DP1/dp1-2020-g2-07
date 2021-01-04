<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<script>
    $(function () {
        $("#fEmision").datepicker({dateFormat: 'yyyy-mm-dd'});
    });

    function validateForm(){
        var fEmision = document.forms["payForm"]["fEmision"].value;
        var date = new Date().toISOString().slice(0,10);

        if(fEmision == "" || fEmision == null){
            alert("Date must be filled")
            return false;
        }

        if (fEmision < date) {
            alert("Date must be set before current date")
            return false;
        }

        return true;
    }
</script>

<petclinic:layout pageName="pays">
    <h2>
        <c:if test="${pago['new']}">New </c:if> Salary
    </h2>
    <form:form name="payForm" modelAttribute="pago" class="form-horizontal" id="add-employee-form"
               onsubmit="return validateForm()">
        <div class="form-group has-feedback">
            <petclinic:localDate pattern="yyyy-MM-dd" label="Date" name="fEmision"/>
            <petclinic:inputField label="Quantity" name="cantidad"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${pago['new']}">
                        <button class="btn btn-default" type="submit">add Pay</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Pay</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
