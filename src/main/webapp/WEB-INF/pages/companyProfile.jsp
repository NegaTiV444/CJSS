<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="company" type="com.cjss.model.company.Company" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Company</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/company.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body>
<header>
    <div class="logo">
        <a href="<c:url value="/"/>"><h1>C.J.S.S.</h1></a>
    </div>
</header>
<main>
    <a href="<c:url value="/"/>">  <%--TODO profile edit--%>
        <div class="edit-btn">
            Edit
        </div>
    </a>
    <section class="main-info">
        <div class="company-name">
            ${company.name}
        </div>
    </section>
    <section class="secondary-info">
        <div class="info-element">
            <div class="el-description">
                ${company.description}
            </div>
            <div class="el-title">
                ${company.foundationDate}
            </div>
        </div>
        <div class="info-element">
            <div class="el-description">
                ${company.sphere}
            </div>
            <div class="el-title">
                Sphere
            </div>
        </div>
        <div class="info-element">
            <div class="el-description">
                ${company.employeesCount}
            </div>
            <div class="el-title">
                Employees
            </div>
        </div>
    </section>
    <section class="about_section">
        <div class="map">
            <img src="${pageContext.servletContext.contextPath}/images/map.png" class="img-map">
        </div>
        <div class="description">
            <div class="desc-title">${company.address}</div>
            <div class="desc-info">
                ${company.description}
            </div>
        </div>
    </section>
</main>
<footer>
    <div class="linksMenu">
        <div class="link"><span>VK</span></div>
        <div class="link">Facebook</div>
        <div class="link"><span>Twitter</span></div>
    </div>
    <div class="TM">
        <span>© 2019-2019 Evil Corp. All rights reserved.</span>
    </div>
</footer>
</body>
</html>