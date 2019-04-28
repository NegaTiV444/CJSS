<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>C.J.S.S.</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
</head>
<body>
<tags:header/>
<main>
    <section class="menu_section">
        <div class="container">
            <div class="slogan">
                There is job for everyone!
            </div>
            <div class="main-btns">
                <div class="element">
                    <a href="<c:url value="/registration-employee"/>"><span>Place resume</span></a>
                </div>
                <a href="<c:url value="/post-vacancy"/>">
                    <div class="element">
                        <span>Post vacancy</span>
                    </div>
                </a>
            </div>
        </div>
    </section>
    <section class="sum">
        <a href="<c:url value="/vacancies"/>">
            <div class="el">
                <div class="count">4</div>
                <div class="item">Vacancies</div>
            </div>
        </a>
        <a href="<c:url value="/resumes"/>">
            <div class="el">
                <div class="count">1</div>
                <div class="item">Resumes</div>
            </div>
        </a>
        <div class="el">
            <div class="count">1</div>
            <div class="item">Companies</div>
        </div>
    </section>
    <section class="find">
        <a href="<c:url value="/vacancies"/>">
            <div class="element">
                <span>Find vacancy</span>
            </div>
        </a>
    </section>
    <section class="about_section">
        <div class="description">
            <div class="desc-title"><a href="<c:url value="/about"/>" style="color: #ffd800;">C.J.S.S.</a><br>Centralized Job Searching System</div>
            <div class="desc-info">
                An evil corporation is a trope in popular culture that portrays a corporation as ignoring social responsibility in order to make money for its shareholders. Evil corporations can be seen to represent the danger of combining capitalism with larger hubris.
            </div>
        </div>
        <div class="map">
            <img src="${pageContext.servletContext.contextPath}/images/map.png" class="img-map">
        </div>
    </section>
</main>
<tags:footer/>
</body>
</html>
