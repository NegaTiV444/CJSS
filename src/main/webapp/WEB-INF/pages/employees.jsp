<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="employees" type="java.util.ArrayList" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/resumes.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body>
<tags:header/>
<main>
    <h1>Resumes</h1>
    <section class="content">
        <c:forEach var="employee" items="${employees}">
            <article class="resume">
                <div class="image-col">
                    <img class="photo" src="${pageContext.servletContext.contextPath}/images/pro.png">
                </div>
                <div class="info-col">
                    <div class="name">
                        <a href="<c:url value="employees/${employee.id}"/>">${employee.name}</a>
                    </div>
                    <div class="birth el">${employee.birthDate}</div>
                    <div class="education el">${employee.education}</div>
                    <div class="skills el"><c:forEach var="skill"
                                                      items="${employee.skills}">${skill.value} </c:forEach></div>
                </div>
            </article>
        </c:forEach>
    </section>
</main>
<tags:footer/>
</body>
</html>