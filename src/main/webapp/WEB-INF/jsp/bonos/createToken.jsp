<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bonoForm">
<!DOCTYPE html >
		<html>
			<head>
				<link rel="stylesheet" href="/resources/css/CSS.css">
			</head>
			<script>
			    function validateForm(){
			    	var v_code = document.forms["createTokenForm"]["codigo"].value;
			    	var v_price = document.forms["createTokenForm"]["precio"].value;
			    	var v_priceRegex = /^[0-9]+$/;
			    	var v_description = document.forms["createTokenForm"]["descripcion"].value;
			    	if(v_code.length > 12){
			    		alert("Code must be 12 characters long or less");
			            return false;
			    	}
			    	else if (v_price == ""){
			    		alert("Price must be filled");
			            return false;
			    	}else if(!v_priceRegex.test(v_price)){
			    		alert("Price must be numerical");
			            return false;
			    	}else if(v_description.length < 3 || v_description.length > 100){
			    		alert("Description must be beetwen 3 and 100 characters");
			            return false;
			    	}
			    	return true;
			    }
		    </script>
			<body>
    			<h2>New Token</h2>
    				<form:form name="createTokenForm" modelAttribute="bono" class="form-horizontal" id="add-owner-form" onsubmit="return validateForm();">
        				<div class="form-group has-feedback"> 
            				<petclinic:inputField label="Code" name="codigo"/> 
            				<petclinic:inputField label="Price" name="precio"/>
            				<petclinic:inputField label="Description" name="descripcion"/>
							<petclinic:selectField label="Select a session" name="session" names="${session}" size="1"/>       
        				</div>
        					<div class="form-group">
            					<div class="col-sm-offset-2 col-sm-10">
                					<c:choose>
                    					<c:when test="${bono['new']}">
                        					<button style="margin-left: 88%" class="btn btn-default" type="submit">Add Token</button>
                    					</c:when>
                    					<c:otherwise>
                        					<button class="btn btn-default" type="submit">Update Token</button>
                    					</c:otherwise>
                					</c:choose>
            					</div>
        				</div>
    				</form:form>
    			</body>
    		</html>
</petclinic:layout> 