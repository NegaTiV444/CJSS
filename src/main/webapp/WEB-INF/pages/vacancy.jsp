<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="vacancy" type="com.cjss.model.vacancy.Vacancy" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Vacancy</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/vacancy.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body>
<tags:header/>
<main>
    <section class="content">
        <article class="vacancie">
            <h2 class="title">${vacancy.position}</h2>
            <div class="info">
                <i class="fas fa-landmark"></i>
                <span>${vacancy.companyName}</span>
                <i class="fas fa-map-marker-alt"></i>
                <span>${vacancy.location}</span>
            </div>
            <h3>Description:</h3>
            <div class="duties">
                ${vacancy.description}
            </div>
            <h3>Skills:</h3>
            <ol class="skills">
                <c:forEach var="skill" items="${vacancy.requiredSkills}">
                <li>${skill.value}</li>
                </c:forEach>
            </ol>
            <h3>Conditions:</h3>
            <div class="conditions">
                ${vacancy.conditions}
            </div>
        </article>
    </section>
</main>
<tags:footer/>
</body>
</html>