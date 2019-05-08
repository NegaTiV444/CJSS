<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="errorCode" required="true" %>
<%@ attribute name="errorMessage" required="true" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>${errorCode}</title>
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
        <div class="name">${errorCode}</div>
        <div class="title">${errorMessage}</div>
    </article>
</main>
<tags:footer/>
</body>
</html>
