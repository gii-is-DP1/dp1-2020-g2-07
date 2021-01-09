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

    function validateForm(){
        var dateStart = document.forms["payForm"]["dateStart"].value;
        var dateEnd = document.forms["payForm"]["dateEnd"].value;

        if(dateStart == "" || dateStart == null || dateEnd == "" || dateEnd == null){
            alert("Dates must be filled")
            return false;
        }

        return true;
    }
</script>

<petclinic:layout pageName="salaries">
    <h2>
        <c:if test="${revenue['new']}">New </c:if> Salary
    </h2>
    <form:form name="payForm" modelAttribute="revenue" class="form-horizontal" id="add-employee-form"
               onsubmit="return validateForm();">
        <div class="form-group has-feedback">
            <petclinic:localDate pattern="yyyy-MM-dd" label="Date start" name="dateStart"/>
            <petclinic:localDate pattern="yyyy-MM-dd" label="Date end" name="dateEnd"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${revenue['new']}">
                        <button class="btn btn-default" type="submit">Add Salary</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Salary</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
