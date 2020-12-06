<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>



<petclinic:layout pageName="horarios">
  <jsp:attribute name="customScript">
        <script>
//             $(function () {
//                 $("#fecha").datepicker({dateFormat: 'yy/mm/dd'});
//             });          
        </script>
        
        <script>
        	
	        $(function () {
        		$("#horaIni").timepicker();
    		});
        
// 	        $(function () {
// 	            $("#horaIni").timepicker({timeFormat: 'HH:mm:ss'});
// 	        });
        </script>
        
        <script>
// 	        $(function () {
// 	            $("#horaFin").timepicker({timeFormat: 'HH:mm:ss'});
// 	        });
        </script>

    </jsp:attribute>
   <jsp:body>
    <h2>
        New TimeTable
    </h2>

    <form:form modelAttribute="horario" class="form-horizontal" id="add-employee-form">
        <div class="form-group has-feedback">
          <petclinic:inputField label="Date" name="fecha"/>
          <petclinic:inputField label="From" name="horaIni"/>
          <petclinic:inputField label="To" name="horaFin"/>
           <div class="control-group">
                    <petclinic:selectField name="sala" label="Rooms " names="${salas}" size="5"/>
                </div>
          
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                  <button class="btn btn-default" type="submit">Add TimeTable</button>
            </div>
        </div>
    </form:form>
     </jsp:body>
</petclinic:layout>