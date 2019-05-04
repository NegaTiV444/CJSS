<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="vacancies" type="java.util.ArrayList" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Vacancies</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/vacancies.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body>
<tags:header/>
<main>
    <h1>Vacancies</h1>
    <div  class="content">
        <form>
            <input type="text" name="query" value="${param.query}">
            <button> Find</button>
        </form>
    </div>
    <section class="content">
        <c:forEach var="vacancy" items="${vacancies}">
            <article class="vacancy">
                <a href="<c:url value="vacancies/${vacancy.id}"/>">
                    <h2 class="title">${vacancy.position}</h2>
                </a>
                <div class="info">
                    <i class="fas fa-landmark"></i>
                    <a href="<c:url value="/companies/${vacancy.companyName}"/>"><span>${vacancy.companyName}</span></a>
                    <i class="fas fa-map-marker-alt"></i>
                    <span>${vacancy.location}</span>
                </div>
                <div class="description">
                        ${vacancy.description}
                </div>
            </article>
        </c:forEach>
    </section>
</main>
<tags:footer/>
</body>
</html>