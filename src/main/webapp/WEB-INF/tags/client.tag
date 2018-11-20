<%@tag pageEncoding="UTF-8" %>
<%@tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="client" required="true"  type="ua.gmail.sydorenko.database.entity.User" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<td>${client.first_name}</td>
<td>${client.last_name}</td>
<td>${client.bill.number}</td>
<td><c:choose>
    <c:when test="${client.blocked}">
        <form class="" method="post" action="viatelecom">
            <input type="hidden" name="command" value="changeUserStatus"/>
            <input type="hidden" name="action" value="unblock"/>
            <input type="hidden" name="idUserForChange" value="${client.id}"/>
            <button class="myButton" type="submit">
                <fmt:message key="admin.clients.table.block"/>
            </button>
        </form>
    </c:when>
    <c:otherwise>
        <form class="" method="post" action="viatelecom">
            <input type="hidden" name="command" value="changeUserStatus"/>
            <input type="hidden" name="action" value="block"/>
            <input type="hidden" name="idUserForChange" value="${client.id}"/>
            <button class="myButton" type="submit">
                <fmt:message key="admin.clients.table.active"/>
            </button>
        </form>
    </c:otherwise>
</c:choose></td>
<td>
    <form class="" method="post" action="viatelecom">
        <input type="hidden" name="command" value="clientData"/>
        <input type="hidden" name="idUserForChange" value="${client.id}"/>
        <button class="myButton" type="submit">
            <fmt:message key="admin.clients.table.user.edit"/>
        </button>
    </form>
</td>
<td>
    <form class="" method="post" action="viatelecom">
        <input type="hidden" name="command" value="deleteUser"/>
        <input type="hidden" name="idUserForChange" value="${client.id}"/>
        <button class="myButton" type="submit">
            <fmt:message key="admin.clients.table.user.delete"/>
        </button>
    </form>
</td>