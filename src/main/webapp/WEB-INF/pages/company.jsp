<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="company" type="com.cjss.model.company.Company" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Company</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/company.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body>
<tags:header/>
<main>
    <section class="main-info">
        <div class="company-name">
            ${company.name}
        </div>
    </section>
    <section class="secondary-info">
        <div class="info-element">
            <div class="el-description">
                ${company.foundationDate}
            </div>
            <div class="el-title">
                Foundation date
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
            <div class="desc-title">${company.city} ${company.address}</div>
            <div class="desc-info">${company.description}</div>
        </div>
        <div class="company-contacts">
            <div class="desc-title">Contacts</div>
            <div class="site"><a href="http://${company.site}">${company.site}</a></div>
            <div class="email">${company.email}</div>
            <div class="phone">${company.phone}</div>
        </div>
    </section>
</main>
<tags:footer/>
</footer>
</body>
</html>