<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="vacancies" type="java.util.ArrayList" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Manager</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/vacancies-manager.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body>
<tags:header/>
<main>
    <div class="row head">
        <h1>Vacancy management</h1>
        <div class="btnAdd"><a href="<c:url value="/post-vacancy"/>">Add vacancy</a></div>
    </div>
    <section class="content">
        <c:forEach var="vacancy" items="${vacancies}">
            <article class="vacancy">
                <div class="row">
                    <h2 class="title">
                        <a href="<c:url value="vacancies/${vacancy.id}"/>">${vacancy.position}</a>
                    </h2>
                    <form id="delete${vacancy.id}" method="post" action="<c:url value="../vacancies/${vacancy.id}"/>">
                        <input type="submit" hidden>
                    </form>
                    <script>
                    function deleteClick()
                    {
                        document.forms["delete${vacancy.id}"].submit();
                    }
                    </script>
                    <i class="fas fa-times" onclick="deleteClick()"></i>
                </div>
                <div class="info">
                    <i class="fas fa-landmark"></i>
                    <span>${vacancy.companyName}</span>
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