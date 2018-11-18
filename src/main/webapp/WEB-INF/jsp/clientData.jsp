<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<c:set var="title" value="ViaTelecom" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/templates/_menu.jspf" %>

<c:if test="${not empty errorMessage}">
    <div class="">${errorMessage}</div>
</c:if>
<form name="AddOrUpdateForm" class="" method="post" action="viatelecom" onsubmit="return checkform();">
    <input type="hidden" name="command" value="createOrUpdate"/>
    <input type="hidden" name="uid" value="${Math.random()}"/>
    <c:if test="${userForUpdate != null}">
        <input type="hidden" name="idUserForUpdate" value="${userForUpdate.id}"/>
    </c:if>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.fname"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text"  name="fname" placeholder="<fmt:message key="admin.table.data.client.fname"/>"
                           value="${userForUpdate.first_name}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="fname" placeholder="<fmt:message key="admin.table.data.client.fname"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.lname"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="lname" placeholder="<fmt:message key="admin.table.data.client.lname"/>"
                           value="${userForUpdate.last_name}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="lname" placeholder="<fmt:message key="admin.table.data.client.lname"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.login"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="login" placeholder="<fmt:message key="admin.table.data.client.login"/>"
                           value="${userForUpdate.login}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="login" placeholder="<fmt:message key="admin.table.data.client.login"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.password"/> </label>
            <input type="text" name="password" placeholder="<fmt:message key="admin.table.data.client.password"/>"
                   autofocus required>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.country"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="country" placeholder="<fmt:message key="admin.table.data.client.country"/>"
                           value="${userForUpdate.address.country}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="country"
                           placeholder="<fmt:message key="admin.table.data.client.country"/> " autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.city"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="city" placeholder="<fmt:message key="admin.table.data.client.city"/>"
                           value="${userForUpdate.address.city}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="city" placeholder="<fmt:message key="admin.table.data.client.city"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.street"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="street" placeholder="<fmt:message key="admin.table.data.client.street"/>"
                           value="${userForUpdate.address.street}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="street" placeholder="<fmt:message key="admin.table.data.client.street"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.house"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="house" placeholder="№" value="${userForUpdate.address.house}" autofocus
                           required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="house" placeholder="№" pattern="[0-9]{1,3}" autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.flat"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="flat" placeholder="№" value="${userForUpdate.address.flat}" autofocus
                           required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="flat" placeholder="№" pattern="[0-9]{1,3}" autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.bill.phone"/> </label>
            <input type="tel" name="phone" placeholder="380xxxxxxxxx"  autofocus required>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.bill.email"/></label>
            <input type="email" name="email" placeholder="@gmail.com"
                   autofocus required>
        </div>
    </div>
    <div class="">
        <div class="">
            <label><fmt:message key="admin.table.data.client.bill.number"/> </label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="bill"
                           placeholder="<fmt:message key="admin.table.data.client.bill.number"/>"
                           value="${userForUpdate.bill.number}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="bill"
                           placeholder="<fmt:message key="admin.table.data.client.bill.number"/>" autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="">
            <label><fmt:message key="admin.table.data.client.bill.balance"/> </label>
            <input type="number" name="balance" min=0 max = 10000
                   placeholder="<fmt:message key="menu.deposit.uah"/>" autofocus required>
        </div>
    </div>
    <button class="" name="balance" type="submit"><fmt:message key="admin.button.user.confirm"/></button>
</form>
</body>
</html>
