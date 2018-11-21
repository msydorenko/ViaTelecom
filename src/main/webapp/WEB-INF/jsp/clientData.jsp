<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<head>
    <title>
        Edit client
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <script type="text/javascript" src="js/validateUserForm.js"></script>
</head>
<body style="background-image: url('/images/fon.jpg'); background-size: 100% 100%">
<%@ include file="/WEB-INF/templates/_menu.jspf" %>

<c:if test="${not empty errorMessage}">
    <div>${errorMessage}</div>
    <br>
</c:if>
<form name="AddOrUpdateForm" class="needs-validation" method="post" action="viatelecom" onsubmit="return checkform();">
    <input type="hidden" name="command" value="createOrUpdate"/>
    <input type="hidden" name="uid" value="${Math.random()}"/>
    <c:if test="${userForUpdate != null}">
        <input type="hidden" name="idUserForUpdate" value="${userForUpdate.id}"/>
    </c:if>
    <div class="form-row">
        <div class="col-md-4 mb-3">
            <label for="validationCustom01"><fmt:message key="admin.table.data.client.fname"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="fname" class="form-control" id="validationCustom01"
                           placeholder="<fmt:message key="admin.table.data.client.fname"/>"
                           value="${userForUpdate.first_name}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="fname" class="form-control" id="validationCustom01"
                           placeholder="<fmt:message key="admin.table.data.client.fname"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-4 mb-3">
            <label for="validationCustom02"><fmt:message key="admin.table.data.client.lname"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="lname" class="form-control" id="validationCustom02"
                           placeholder="<fmt:message key="admin.table.data.client.lname"/>"
                           value="${userForUpdate.last_name}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="lname" class="form-control" id="validationCustom02"
                           placeholder="<fmt:message key="admin.table.data.client.lname"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="form-row">
        <div class="col-md-4 mb-3">
            <label for="validationCustom03"><fmt:message key="admin.table.data.client.login"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="login" class="form-control" id="validationCustom03"
                           placeholder="<fmt:message key="admin.table.data.client.login"/>"
                           value="${userForUpdate.login}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="login" class="form-control" id="validationCustom03"
                           placeholder="<fmt:message key="admin.table.data.client.login"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-4 mb-3">
            <label for="validationCustom04"><fmt:message key="admin.table.data.client.password"/></label>
            <input type="text" name="password" class="form-control" id="validationCustom04"
                   placeholder="<fmt:message key="admin.table.data.client.password"/>"
                   autofocus required>
        </div>
    </div>
    <div class="form-row">
        <div class="col-md-4 mb-3">
            <label for="validationCustom05"><fmt:message key="admin.table.data.client.country"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="country" class="form-control" id="validationCustom05"
                           placeholder="<fmt:message key="admin.table.data.client.country"/>"
                           value="${userForUpdate.address.country}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="country" class="form-control" id="validationCustom05"
                           placeholder="<fmt:message key="admin.table.data.client.country"/> "
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-4 mb-3">
            <label for="validationCustom06"><fmt:message key="admin.table.data.client.city"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="city" class="form-control" id="validationCustom06"
                           placeholder="<fmt:message key="admin.table.data.client.city"/>"
                           value="${userForUpdate.address.city}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="city" class="form-control" id="validationCustom06"
                           placeholder="<fmt:message key="admin.table.data.client.city"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="form-row">
        <div class="col-md-3 mb-3">
            <label for="validationCustom07"><fmt:message key="admin.table.data.client.street"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="street" class="form-control" id="validationCustom07"
                           placeholder="<fmt:message key="admin.table.data.client.street"/>"
                           value="${userForUpdate.address.street}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="street" class="form-control" id="validationCustom07"
                           placeholder="<fmt:message key="admin.table.data.client.street"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-1 mb-3">
            <label for="validationCustom08"><fmt:message key="admin.table.data.client.house"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="house" class="form-control" id="validationCustom08" placeholder="№"
                           value="${userForUpdate.address.house}" autofocus
                           required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="house" class="form-control" id="validationCustom08" placeholder="№"
                           pattern="[0-9]{1,3}" autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-1 mb-3">
            <label for="validationCustom09"><fmt:message key="admin.table.data.client.flat"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="flat" class="form-control" id="validationCustom09" placeholder="№"
                           value="${userForUpdate.address.flat}" autofocus
                           required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="flat" class="form-control" id="validationCustom09" placeholder="№"
                           pattern="[0-9]{1,3}" autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="form-row">
        <div class="col-md-4 mb-3">
            <label for="validationCustom10"><fmt:message key="admin.table.data.client.bill.phone"/></label>
            <input type="tel" name="phone" class="form-control" id="validationCustom10" placeholder="380xxxxxxxxx"
                   autofocus required>
        </div>
        <div class="col-md-4 mb-3">
            <label for="validationCustom11"><fmt:message key="admin.table.data.client.bill.email"/></label>
            <input type="email" name="email" class="form-control" id="validationCustom11" placeholder="@gmail.com"
                   autofocus required>
        </div>
    </div>
    <div class="form-row">
        <div class="col-md-4 mb-3">
            <label for="validationCustom12"><fmt:message key="admin.table.data.client.bill.number"/></label>
            <c:choose>
                <c:when test="${userForUpdate != null}">
                    <input type="text" name="bill"
                           class="form-control" id="validationCustom12"
                           placeholder="<fmt:message key="admin.table.data.client.bill.number"/>"
                           value="${userForUpdate.bill.number}" autofocus required>
                </c:when>
                <c:otherwise>
                    <input type="text" name="bill"
                           class="form-control" id="validationCustom12"
                           placeholder="<fmt:message key="admin.table.data.client.bill.number"/>"
                           autofocus required>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-4 mb-3">
            <label for="validationCustom13"><fmt:message key="admin.table.data.client.bill.balance"/></label>

            <input type="number" name="balance" min=0 max=10000 class="form-control" id="validationCustom13"
                   placeholder="<fmt:message key="menu.deposit.uah"/>"
                   autofocus required>
        </div>
    </div>
    <button class="btn btn-primary myButton" name="balance" type="submit"><fmt:message
            key="admin.button.user.confirm"/></button>
</form>
</body>
</html>


<%--<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<c:set var="title" value="ViaTelecom" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/templates/_menu.jspf" %>

<c:if test="${not empty errorMessage}">
    <div class="">${errorMessage}</div>
    <br>
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
                    <input type="text" name="fname" placeholder="<fmt:message key="admin.table.data.client.fname"/>"
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
            <input type="tel" name="phone" placeholder="380xxxxxxxxx" autofocus required>
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
            <input type="number" name="balance" min=0 max=10000
                   placeholder="<fmt:message key="menu.deposit.uah"/>" autofocus required>
        </div>
    </div>
    <button class="" name="balance" type="submit"><fmt:message key="admin.button.user.confirm"/></button>
</form>
</body>
</html>--%>
