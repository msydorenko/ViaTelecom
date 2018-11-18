<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ taglib prefix="entity" tagdir="/WEB-INF/tags" %>

<html>
<c:set var="title" value="ViaTelecom" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div>
    <a href="viatelecom?command=locale&i18n=ru&page=main">RU</a>
    <a href="viatelecom?command=locale&i18n=en&page=main">EN</a>
</div>
<%@ include file="/WEB-INF/templates/_menu.jspf" %>

<c:if test="${not empty errorMessage}">
    <div class="">${errorMessage}</div>
</c:if>
<div>
    <c:if test="${not empty completeUser && completeUser != null}">
        <table>
            <tr>
                <th><fmt:message key="admin.clients.table.fname"/></th>
                <th><fmt:message key="admin.clients.table.lname"/></th>
                <th><fmt:message key="admin.clients.table.bnumber"/></th>
                <th><fmt:message key="admin.clients.table.status"/></th>
                <th><fmt:message key="admin.clients.table.edit"/></th>
                <th><fmt:message key="admin.clients.table.delete"/></th>
            </tr>
            <c:forEach var="oneUser" items="${completeUser}">
                <tr>
                    <entity:client client="${oneUser}"/>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div>
    <form class="" method="post" action="viatelecom">
        <input type="hidden" name="command" value="clientData"/>
        <button class="" type="submit">
            <fmt:message key="admin.button.user.add"/>
        </button>
    </form>
</div>
</body>
</html>