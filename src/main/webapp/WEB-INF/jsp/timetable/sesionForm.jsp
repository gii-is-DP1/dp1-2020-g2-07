<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>



<petclinic:layout pageName="sesiones">
  <jsp:attribute name="customScript">
        <script>
            $(function () {
				$('.horaInicio').timepicker({
				    timeFormat: 'h:mm p',
				    interval: 60,
				    minTime: '10',
				    maxTime: '6:00pm',
				    defaultTime: '11',
				    startTime: '10:00',
				    dynamic: false,
				    dropdown: true,
				    scrollbar: true
				}); 
            });
        </script>
        
        
    </jsp:attribute>
   <jsp:body>
    <h2>
        New session
    </h2>

    <form:form modelAttribute="newSesion" class="form-horizontal" id="add-sesion-form">
        <div class="form-group has-feedback">
          <petclinic:selectField label="From" name="horaInicio" names="${hours_op}" size="1"/>
          <petclinic:selectField label="To" name="horaFin" names="${hours_end}" size="1"/>
          <input type="hidden" name="horario" value="${horarioID}">
           <div class="control-group">
                    <petclinic:selectField name="sala" label="Rooms " names="${salas}" size="5"/>
                </div>
          
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                  <button class="btn btn-default" type="submit">Add session</button>
            </div>
        </div>
    </form:form>
    
            <br/>
        <b>Previous sessions of the day</b>
    <table id="sesionesPrevias" class="table table-striped">
 	  <thead>
        <tr>
        	<th>Rooms</th>
            <th>From</th>
            <th>To</th>
        </tr>
        </thead>
   	 	<tbody>
        	<c:forEach items="${sesion}" var="sesion">
        	<c:if test="${sesion.horario.getId()==horarioID}">
<%--         	<c:if test="${!sesion['new']}"> --%>
            <tr>
            <td><c:out value="${sesion.sala}"/></td>
            <td><c:out value="${sesion.horaInicio}"/></td>
            <td><c:out value="${sesion.horaFin}"/></td>
            </tr>
<%--             </c:if> --%>
            </c:if>
            </c:forEach>
        </tbody>
    </table>
     </jsp:body>
</petclinic:layout>