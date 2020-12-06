<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bonoForm">
        <script>
            $(function () {
                $("#duracion").datepicker({dateFormat: 'yyyy-mm-dd'});
            });
        </script>

    <h2>
        <c:if test="${bono['new']}">Nuevo Bono</c:if> 
    </h2>
    <form:form modelAttribute="bono" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback"> 
            <petclinic:inputField label="Codigo" name="codigo"/> 
            <petclinic:inputField label="Precio" name="precio"/>
            <petclinic:inputField label="Duracion" name="duracion"/>  
            <petclinic:inputField label="Descripcion" name="descripcion"/>             
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${bono['new']}">
                        <button class="btn btn-default" type="submit">Añadir Bono</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar Bono</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout> 