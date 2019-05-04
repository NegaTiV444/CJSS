<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="employee" type="com.cjss.model.employee.Employee" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>C.J.S.S.</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/profile.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body>
<tags:header/>
<main>
    <div class="main-container">
        <div class="main-info">
            <div class="photo-container">
                <img class="photo" src="${pageContext.servletContext.contextPath}/images/pro.png">
            </div>
            <div class="name">${employee.name}</div>
            <div class="email">${employee.email}</div>
            <div class="date">${employee.birthDate}</div>
        </div>
        <div class="information">
            <div class="element">
                <div class="information_item">Education</div>
                <div class="item_description">${employee.education}</div>
            </div>
            <div class="element">
                <div class="information_item">Skills</div>
                <div class="item_description">
                    <c:forEach var="skill" items="${employee.skills}">${skill.value} </c:forEach>
                </div>
            </div>
            <div class="element">
                <div class="information_item">Experience</div>
                <span class="item_description">${employee.experience}</span>
            </div>
            <div class="element">
                <div class="information_item">Hobbies</div>
                <div class="item_description">${employee.hobbies}</div>
            </div>
        </div>
    </div>
</main>
<tags:footer/>
</body>
</html>
