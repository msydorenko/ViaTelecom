<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<c:set var="title" value="Admin page" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/templates/_menu.jspf" %>
<div align="center">
    <h2><fmt:message key="admin.change.tariff"/> ${tariffForUpdate.name}</h2>

    <form action="viatelecom" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" value="editTariff"/>
        <input type="hidden" name="tariffIdForUpdate" value="${tariffForUpdate.id}"/>
        <input type="hidden" name="tariffServiceId" value="${tariffForUpdate.spr_service_id}"/> <br>
        <br>
        <table class="table-border table-dark">
            <tr>
                <td><fmt:message key="admin.change.tariff.name.Tariff"/>:</td>
                <td><input type="text" name="tariffName" required="required" cols="20"/></td>
            </tr>
            <tr>
                <td><fmt:message key="admin.change.tariff.price.Tariff"/>:</td>
                <td><input type="number" min="0" name="tariffPrice" required="required" cols="20"/></td>
            </tr>
            <tr>
                <td><fmt:message key="admin.change.tariff.description.Tariff"/>:</td>
                <td><textarea name="tariffDescription" rows="10" cols="20"></textarea></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="admin.change.tariff.update.Tariff"/>"/>
    </form>
</div>

</body>
</html>
