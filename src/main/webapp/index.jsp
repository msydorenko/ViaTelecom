<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="WEB-INF/jspf/directive.jspf"%>
<html>
<body>

<form class="p-5" method="post" action="viatelecom?command=login">
    <div class="form-group">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">${errorMessage}</div>
        </c:if>
        <label for="login"><fmt:message key="login.label.login"/></label>
        <input type="text" class="form-control" id="login" name="login"
               placeholder="<fmt:message key="login.placeholder.login"/>"
               size="13" maxlength="13" required>
    </div>
    <div class="form-group">
        <label for="password"><fmt:message key="login.label.password"/></label>
        <input type="password" class="form-control" id="password" name="password"
               placeholder="<fmt:message key="login.placeholder.password"/>" minlength="5"
               maxlength="40" required>
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="login.button.login"/></button>
</form>

</body>
</html>
