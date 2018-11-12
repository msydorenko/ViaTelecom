<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="WEB-INF/css/style.css"/>
    <%--
        <script type="text/javascript" src="js/loginJs.js"/>
    --%>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script type="text/javascript" src="js/capcha.js"></script>
</head>
<body>

<div id="container">
    <h1>Log In</h1>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </c:if>
    <form action="viatelecom" method="post">
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="login" placeholder="Login">
        <input type="password" name="password" placeholder="Password">
        <div class="g-recaptcha" align="center" data-sitekey="6LeQPnoUAAAAACX1F1RAO2wYTB8kh7ThB6o-5w4P"></div>
        <span id="captcha" style="margin-left:100px;color:red"></span><br/>
        <input type="button" onclick="validate(this.form)" value="Log in">
        <%--<input type="submit"  value="Log in">--%>
    </form>
</div>

</body>
</html>
