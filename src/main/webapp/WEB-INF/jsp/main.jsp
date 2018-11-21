<%@ include file="/WEB-INF/jspf/directive.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<c:set var="title" value="ViaTelecom" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="flex">
    <a href="viatelecom?command=locale&i18n=ru&page=main"><img src="images/Russia.png"/> </a>
    <a href="viatelecom?command=locale&i18n=en&page=main"><img src="images/United-States.png"/></a>
</div>
<%@ include file="/WEB-INF/templates/_menu.jspf" %>
<div style="margin: 10px">
    <span>
    <form method="post" action="viatelecom?command=tariffList">
        <input type="hidden" name="serviceId" value="1"/>
        <button class="myButton" type="submit">
            <fmt:message key="admin.button.internet"/>
        </button>
    </form>
    </span>
    <span>
    <form method="post" action="viatelecom?command=tariffList">
        <input type="hidden" name="serviceId" value="2"/>
        <button class="myButton" type="submit">
            <fmt:message key="admin.button.tv"/>
        </button>
    </form>
    </span>
    <span>
    <form method="post" action="viatelecom?command=tariffList">
        <input type="hidden" name="serviceId" value="3"/>
        <button class="myButton" type="submit">
            <fmt:message key="admin.button.telephone"/>
        </button>
    </form>
    </span>
</div>

<c:if test="${userRole.name eq 'client'}">
    <div>
        <p><fmt:message key="client.welcome"/>, ${user.first_name} ${user.last_name}!</p>
        <p><fmt:message key="client.welcome.bill.number"/> № ${user.bill.number}.</p>
        <p><fmt:message key="client.welcome.bill.value"/>: ${user.bill.value} UAH</p>
    </div>
</c:if>
<c:if test="${not empty errorMessage}">
    <div class="">${errorMessage}</div>
</c:if>
<div>
    <c:if test="${not empty tariffList && tariffList != null}">
        <table class="tabtab">
            <tr>
                <th class="th">
                    <div class="row">
                        <fmt:message key="table.tariff.name"/>
                        <form method="post" action="viatelecom?command=sort">
                            <select onchange="submit()" name="value">
                                <option>sort</option>
                                <option value="az"><fmt:message key="table.tariff.az"/></option>
                                <option value="za"><fmt:message key="table.tariff.za"/></option>
                            </select>
                        </form>
                    </div>
                </th>
                <th class="th">
                    <div class="row">
                        <fmt:message key="table.tariff.price"/>
                        <form method="post" action="viatelecom?command=sort">
                            <select onchange="submit()" name="value">
                                <option>sort</option>
                                <option value="min"><fmt:message key="table.tariff.min"/></option>
                                <option value="max"><fmt:message key="table.tariff.max"/></option>
                            </select>
                        </form>
                    </div>
                </th>
                <th><fmt:message key="table.tariff.description"/></th>
                <th>
                    <c:choose>
                        <c:when test="${userRole.name eq 'admin'}">
                            <fmt:message key="table.tariff.edit"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="table.tariff.subscribe"/>
                        </c:otherwise>
                    </c:choose>
                </th>
                <c:if test="${userRole.name eq 'admin'}">
                    <th><fmt:message key="table.tariff.delete"/></th>
                </c:if>
            </tr>
            <c:forEach var="tariff" items="${tariffList}">
                <tr>
                    <td>${tariff.name}</td>
                    <td>${tariff.price}</td>
                    <td>${tariff.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${userRole.name eq 'admin'}">
                                <form class="" method="post" action="viatelecom?command=openEditTariffPage">
                                    <input type="hidden" name="tariffId" value="${tariff.id}"/>
                                    <button class="myButton" type="submit">
                                        <fmt:message key="admin.button.editTariff"/>
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <c:set var="count" value="0"/>
                                <c:forEach var="tariffUser" items="${user.tariffs}">
                                    <c:if test="${tariffUser.id == tariff.id}">
                                        <c:set var="count" value="1"/>
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${count==0}">
                                        <form class="" method="post" action="viatelecom?command=userSubscribeTariff">
                                            <input type="hidden" name="uid" value="${Math.random()}"/>
                                            <input type="hidden" name="tariffId" value="${tariff.id}"/>
                                            <button class="myButton" type="submit">
                                                <fmt:message key="client.button.addTariff"/>
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form class="" method="post" action="viatelecom?command=userUnsubscribeTariff">
                                            <input type="hidden" name="tariffId" value="${tariff.id}"/>
                                            <button class="myButton" type="submit">
                                                <fmt:message key="client.button.deleteTariff"/>
                                            </button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${userRole.name eq 'admin'}">
                        <td>
                            <form class="" method="post" action="viatelecom?command=deleteTariff">
                                <input type="hidden" name="tariffId" value="${tariff.id}"/>
                                <button class="myButton" type="submit">
                                    <fmt:message key="admin.button.deleteTariff"/>
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div style="margin: 10px">
    <form method="post" action="viatelecom?command=download">
        <button class="myButton" type="submit">
            <fmt:message key="admin.button.download"/>
        </button>
    </form>
</div>
<c:if test="${userRole.name eq 'admin'}">
    <div style="text-align: center; margin: 10px">
        <form action="viatelecom" method="post">
            <div class="tarifForm align-items-center">
                <input type="hidden" name="uid" value="${Math.random()}"/>
                <input type="hidden" name="command" value="addTariff"/>
                <select style="margin: 10px" name="service">
                    <c:forEach var="service" items="${serviceList}">
                        <option value="${service.id}">${service.name}</option>
                    </c:forEach>
                </select>
                <input style="margin: 10px" type="text" name="name" placeholder="Tariff name" required>
                <input style="margin: 10px" type="number" name="price" min="0" max="1000" placeholder="Price" required>
                <input style="margin: 10px" type="text" name="description"
                       placeholder="<fmt:message key="admin.change.tariff.description.Tariff"/>" required>
                <input style="margin: 10px" type="submit" class="myButton"
                       value="<fmt:message key="admin.button.addTariff"/>"/>
            </div>
        </form>
    </div>
</c:if>
</body>
</html>
