<%@ tag trimDirectiveWhitespaces="true" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header>
    <div class="logo">
        <a href="<c:url value="/main"/>"><h1>C.J.S.S.</h1></a>
    </div>
    <div>
        <a href="<c:url value="/profile"/>">
            <img class="profile_photo" src="${pageContext.servletContext.contextPath}/images/pro.png">
        </a>
    </div>
</header>
