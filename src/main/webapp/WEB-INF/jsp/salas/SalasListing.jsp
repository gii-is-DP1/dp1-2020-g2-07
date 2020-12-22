<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="salas">
    <h2>Rooms</h2>

    <table id="salasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Capacity</th>
            <th>Description</th>
            <th width=2%></th>
            <th width=2%></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${salas}" var="sala">
            <tr>
                <td>
                    <spring:url value="/salas/{salaId}" var="salaUrl">
                        <spring:param name="salaId" value="${sala.id}"/>
                    </spring:url>
                <a href="${fn:escapeXml(salaUrl)}"><c:out value="${sala.name}"/></a>
                </td>
               
                <td>
                    <c:out value="${sala.aforo}"/>
                </td>
                <td>
                    <c:out value="${sala.descripcion}"/>
                </td>
                <td>
                    <sec:authorize access="hasAuthority('admin')">
                        <a href="/salas/${sala.id}/edit">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </a>
                    </sec:authorize>
                </td>
                <td>
                    <sec:authorize access="hasAuthority('admin')">
                        <a href="/salas/${sala.id}/delete">
                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        </a>
                    </sec:authorize>
                </td>    
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="form-group">
   	    <p>
            <sec:authorize access="hasAuthority('admin')"> 
               <a href="/salas/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add room</a>
            </sec:authorize>
    	</p>
	</div>
</petclinic:layout>