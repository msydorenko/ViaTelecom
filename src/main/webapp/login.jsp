<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<c:set var="title" value="ViaTelecom" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<div id="container">
    <h1>Log In</h1>
    <c:if test="${not empty errorMessage}">
        <div class="">${errorMessage}</div>
    </c:if>
    <form action="viatelecom" method="post">
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="login" placeholder="Login">
        <input type="password" name="password" placeholder="Password">
        <%--<div class="g-recaptcha" &lt;%&ndash;align="center"&ndash;%&gt; data-sitekey="6LeQPnoUAAAAACX1F1RAO2wYTB8kh7ThB6o-5w4P"></div>
        <span id="captcha" style="margin-left:100px;color:red"></span><br/>
        <input type="button" onclick="validate(this.form)" value="<fmt:message key="login.button.login"/>"/>--%>
        <input type="submit"  value="<fmt:message key="login.button.login"/>">
    </form>
</div>





</body>
</html>
