<%@ page isErrorPage="true" %>
<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html>
<head>
    <c:set var="title" value="Error" scope="page"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<div>
    <a href="/WEB-INF/jsp/main.jsp"><fmt:message key="error.forward.main"/></a>
</div>
<div>
    <img src="images/error.jpg" <%--width="100%"--%> height="100%" alt="Error">
</div>
</body>
</html>
