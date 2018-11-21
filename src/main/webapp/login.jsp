<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/style1.css">
</head>
<body>
<form autocomplete='off' class='form' action="viatelecom" method="post">
    <input type="hidden" name="command" value="login"/>
    <div class='control'>
        <h1>
            Sign In
        </h1>
    </div>
    <c:if test="${not empty errorMessage}">
        <div class="">${errorMessage}</div>
        <br><br>
    </c:if>
    <div class='control block-cube block-input'>

        <input type="text" name="login" placeholder="<fmt:message key="login.placeholder.login"/> ">
        <div class='bg-top'>
            <div class='bg-inner'></div>
        </div>
        <div class='bg-right'>
            <div class='bg-inner'></div>
        </div>
        <div class='bg'>
            <div class='bg-inner'></div>
        </div>
    </div>
    <div class='control block-cube block-input'>
        <input type="password" name="password" placeholder="<fmt:message key="login.placeholder.password"/> ">
        <div class='bg-top'>
            <div class='bg-inner'></div>
        </div>
        <div class='bg-right'>
            <div class='bg-inner'></div>
        </div>
        <div class='bg'>
            <div class='bg-inner'></div>
        </div>
    </div>
    <input type="submit" class='btn block-cube block-cube-hover' value="<fmt:message key="login.button.login"/>">
    <div class='bg-top'>
        <div class='bg-inner'></div>
    </div>
    <div class='bg-right'>
        <div class='bg-inner'></div>
    </div>
    <div class='bg'>
        <div class='bg-inner'></div>
    </div>
    </input>
</form>
</body>
</html>












<%--

<html>
<c:set var="title" value="ViaTelecom" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<div class="container border" style="text-align: center; border-color: #005cbf">
    <h1>Log In</h1>
    <c:if test="${not empty errorMessage}">
        <div class="">${errorMessage}</div>
    </c:if>
    <form action="viatelecom" method="post">
        <input type="hidden" name="command" value="login"/>
        <div class="">
            <label><fmt:message key="login.label.login"/></label>
            <input type="text" name="login" placeholder="<fmt:message key="login.placeholder.login"/> ">
        </div>
        <div class="">
            <label><fmt:message key="login.label.password"/></label>
            <input type="password" name="password" placeholder="<fmt:message key="login.placeholder.password"/> ">
        </div>
        &lt;%&ndash;<div class="g-recaptcha" &lt;%&ndash;align="center"&ndash;%&gt; data-sitekey="6LeQPnoUAAAAACX1F1RAO2wYTB8kh7ThB6o-5w4P"></div>
        <span id="captcha" style="margin-left:100px;color:red"></span><br/>
        <input type="button" onclick="validate(this.form)" value="<fmt:message key="login.button.login"/>"/>&ndash;%&gt;
        <div>
            <input type="submit" value="<fmt:message key="login.button.login"/>">
        </div>
    </form>
</div>


</body>
</html>
--%>
