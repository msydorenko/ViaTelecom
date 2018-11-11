<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="WEB-INF/css/style.css"/>
<%--
    <script type="text/javascript" src="WEB-INF/js/loginJs.js"/>
--%>
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
        <input type="submit" value="Log in">
    </form>
</div>


</body>
</html>
