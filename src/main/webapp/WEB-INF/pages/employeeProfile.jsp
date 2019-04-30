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
<header>
    <div class="logo">
        <a href="<c:url value="/main"/>"><h1>C.J.S.S.</h1></a>
    </div>
    <div class="logout">
        <form action="<c:url value="/main"/>" method="post">
            <button>Logout</button>
        </form>
    </div>
</header>
<main>
    <div class="logout">
        <form action="<c:url value="/vacancies"/>" method="post">
            <button>Find vacancies for me</button>
        </form>
    </div>
    <div class="main-container">
        <div class="main-info">
            <div class="photo-container">
                <img class="photo" src="${pageContext.servletContext.contextPath}/images/pro.png">
            </div>
            <div class="name">${employee.name}</div>
            <div class="email">${employee.email}</div>
            <div class="date">${employee.birthDate}</div>
            <div class="edit-button"><a href="<c:url value="/profile/employee/edit"/>">Edit <i class="far fa-edit"></i></a></div>   <%--TODO profile edit--%>
        </div>
        <div class="information">
            <div class="element">
                <div class="information_item">Education</div>
                <div class="item_description">${employee.education}</div>
            </div>
            <div class="element">
                <div class="information_item">Skills</div>
                <div class="item_description">${requestScope.skills}</div>
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
