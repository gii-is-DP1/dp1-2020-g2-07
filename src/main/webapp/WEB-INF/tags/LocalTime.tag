<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="hora" required="true" type="java.time.LocalTime" %>
<%@ attribute name="pattern" required="true" type="java.lang.String" %>


<fmt:parseDate value="${hora}" pattern="HH:mm:ss" var="parsedTime" type="time"/>
<fmt:formatDate value="${parsedTime}" type="time" pattern="${pattern}"/>
