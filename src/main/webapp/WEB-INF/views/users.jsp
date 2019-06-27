<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<div class="container">
    <table class="table">
        <thead>
        <td><spring:message code="users.nickname"/></td>
        <td><spring:message code="users.name"/></td>
        <td><spring:message code="users.gander"/></td>
        <td><spring:message code="users.age"/></td>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.nickname}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</html>