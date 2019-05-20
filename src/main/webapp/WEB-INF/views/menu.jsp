<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" id="closeMenu">&times;</a>
    <a href="/admin/book" class="test"><spring:message code="menu.books"/></a>
    <a href="/admin/event"><spring:message code="menu.events"/></a>
    <a href="#">Clients</a>
    <a href="#">Contact</a>
</div>
<span id="openMenu"><spring:message code="menu"/></span>