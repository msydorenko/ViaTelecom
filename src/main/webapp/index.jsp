<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<c:set var="title" value="ViaTelecom" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div>
    <a href="viatelecom?command=locale&i18n=ru&page=index">RU</a>
    <a href="viatelecom?command=locale&i18n=en&page=index">EN</a>
</div>
<%@ include file="/WEB-INF/templates/_menu.jspf" %>

<img src="images/index.jpg" <%--width="100%"--%> height="100%" alt="ViaTelecom">
</body>
</html>
