<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<c:set var="title" value="Admin page" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/templates/_menu.jspf" %>

<form class="" action="viatelecom?command=">
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.fname"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.fname"/>"
                           default="${userForUpdate.first_name}" required>
                </c:when>
                <c:otherwise>
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.fname"/>" required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.lname"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.lname"/>"
                           default="${userForUpdate.last_name}" required>
                </c:when>
                <c:otherwise>
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.lname"/>" required>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.login"/></label>
            <input type="text" placeholder="<fmt:message key="admin.table.data.client.login"/>" required>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.password"/> </label>
            <input type="text" placeholder="<fmt:message key="admin.table.data.client.password"/>" required>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.country"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.country"/>"
                           default="${userForUpdate.address.country}" required>
                </c:when>
                <c:otherwise>
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.country"/> " required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.city"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.city"/>"
                           default="${userForUpdate.address.city}" required>
                </c:when>
                <c:otherwise>
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.city"/>" required>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.street"/> </label>
            <input type="text" placeholder="<fmt:message key="admin.table.data.client.street"/>" required>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.house"/> </label>
            <input type="text" placeholder="№" pattern="[0-9]{3}" required>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.flat"/> </label>
            <input type="text" pattern="[0-9]{3}" required>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.bill.phone"/> </label>
            <input type="tel" placeholder="380" pattern="[0-9]{12}" required>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.bill.email"/></label>
            <input type="email" placeholder="@gmail.com" required>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.bill.number"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.bill.number"/>"
                           default="${userForUpdate.bill.number}" required>
                </c:when>
                <c:otherwise>
                    <input type="text" placeholder="<fmt:message key="admin.table.data.client.bill.number"/>" required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.bill.balance"/> </label>
            <input type="text" placeholder="<fmt:message key="menu.deposit.uah"/>" required>
        </div>
    </div>
    <button class="" type="submit"><fmt:message key="admin.button.user.confirm"/></button>
</form>
</body>
</html>
