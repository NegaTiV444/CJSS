<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>About</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/about.css">
</head>
<body>
<header>
    <div class="logo">
        <a href="<c:url value="/main"/>"><h1>C.J.S.S.</h1></a>
    </div>
</header>
<main>
    <article class="about-article">
        <div class="name">C.J.S.S.</div>
        <div class="title">about company</div>
        <div class="description">
            An evil corporation is a trope in popular culture that portrays a corporation as ignoring social
            responsibility in order to make money for its shareholders. Evil corporations can be seen to represent the
            danger of combining capitalism with larger hubris.
        </div>
    </article>
</main>
<tags:footer/>
</body>
</html>