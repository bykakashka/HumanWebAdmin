<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="mySidenav" class="sidenav">
    <a href="/admin/book" class="test"><spring:message code="menu.books"/></a>
    <a href="/admin/event"><spring:message code="menu.events"/></a>
    <a href="/admin/users"><spring:message code="menu.users"/></a>
</div>
<span id="openMenu"><spring:message code="menu"/></span>