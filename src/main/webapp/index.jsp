<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<c:set var="title" value="ViaTelecom" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="flex">
    <a href="viatelecom?command=locale&i18n=ru&page=index"><img src="images/Russia.png"/></a>
    <a href="viatelecom?command=locale&i18n=en&page=index"><img src="images/United-States.png"/></a>
</div>

<%@ include file="/WEB-INF/templates/_menu.jspf" %>
<div>
    <img src="images/index1.jpg" alt="ViaTelecom">
</div>
</body>
</html>
