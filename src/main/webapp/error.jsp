<%@ page isErrorPage="true" %>
<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>

    <c:set var="title" value="Error" scope="page"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div>
    <a href="viatelecom?command=main"><fmt:message key="error.forward.main"/></a>
</div>
<div>
    <img src="images/error-page.jpg" <%--width="100%" height="100%"--%> alt="Error">
</div>
</body>
</html>
