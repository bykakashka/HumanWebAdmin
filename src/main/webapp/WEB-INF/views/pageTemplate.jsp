<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<title>${title}</title>

<script defer src="/webjars/humanlib/0.0.1-SNAPSHOT/app-bundle.js"></script>
<link rel="stylesheet" href="/css/app.css"  type="text/css"/>
<jsp:include page="menu.jsp"/>
<jsp:include page="${page}.jsp"/>
</html>