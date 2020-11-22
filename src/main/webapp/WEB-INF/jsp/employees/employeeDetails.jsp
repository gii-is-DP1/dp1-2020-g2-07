<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="employees">

    <h2>Employee Profile</h2>


    <table class="table table-striped" id="emploee_details">
        <tr>
            <th>Name & Surname</th>
            <td><b><c:out value="${employee.nombre} ${employee.apellidos}"/></b></td>
        </tr>
        <tr>
            <th>Addres</th>
            <td><b><c:out value="${employee.direccion}"/></b></td>
        </tr>
        <tr>
            <th>IBAN</th>
            <td><b><c:out value="${employee.IBAN}"/></b></td>
        </tr>
        <tr>
            <th>Profession</th>
            <td><b><c:out value="${employee.profession}"/></b></td>
        </tr>
    </table>

        <a href="/employees/${employee.id}/edit" class="btn btn-default">Edit</a>
        <a href="/employees/${employee.id}/delete" class="btn btn-default">Delete</a>

    <br/>
    <br/>
    <br/>

    <h3>Salaries of <c:out value="${employee.nombre} ${employee.apellidos}"/></h3>
    <table id="employeesSalaries" class="table table-striped">
        <thead>
            <tr>
                <th>Date Start</th>
                <th>Date End</th>
                <th>Hours Worked</th>
                <th>Cuantity(Euros)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${employee.salaries}" var="salarie">
                <tr>
                    <td>
                        <c:out value="${salarie.dateStart}"/>
                    </td>
                    <td>
                        <c:out value="${salarie.dateEnd}"/>
                    </td>
                    <td>
                        <c:out value="${salarie.hoursWorked}"/>
                    </td>
                    <td>
                        <c:out value="${salarie.cuantity}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
