<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="employee" type="com.cjss.model.employee.Employee" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/profile-edit.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</head>
<body>
<header>
    <div class="logo">
        <a href="<c:url value="/main"/>"><h1>C.J.S.S.</h1></a>
    </div>
</header>
<main>
    <form id="profile-form" method="post">
        <div class="main-container">
            <div class="main-info">
                <div class="photo-container">
                    <img class="photo" src="${pageContext.servletContext.contextPath}/images/pro.png">
                </div>
                <input type="text" name="name" class="name" value="${employee.name}" required>
                <input type="email" name="email" class="email" value="${employee.email}" required>
                <input type="date" name="date" class="date" value="${employee.birthDate}" required>
                <input type="submit" class="edit-button" value="Apply">
            </div>
            <div class="information">
                <div class="element">
                    <div class="information_item">Education</div>
                    <textarea form="profile-form" name="education" class="item_description" required>
                        ${employee.education}
                    </textarea>
                </div>
                <div class="element">
                    <div class="skills">
                        <div class="tskills">Skills</div>
                        <div class="lang">
                            <div>
                                <input type="checkbox" name="JAVA" id="java"
                                    <c:if test = "${fn:contains(requestScope.skills, 'JAVA')}">
                                       checked="checked"
                                    </c:if>
                                >
                                <label for="java">Java</label>
                            </div>
                            <div>
                                <input type="checkbox" name="PYTHON" id="python"
                                <c:if test = "${fn:contains(requestScope.skills, 'PYTHON')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="python">Python</label>
                            </div>
                            <div>
                                <input type="checkbox" name="C" id="c"
                                <c:if test = "${fn:contains(requestScope.skills, 'C')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="c">C</label>
                            </div>
                            <div>
                                <input type="checkbox" name="CPP" id="cpp"
                                <c:if test = "${fn:contains(requestScope.skills, 'CPP')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="cpp">CPP</label>
                            </div>
                        </div>
                        <div class="lang">
                            <div>
                                <input type="checkbox" name="DOTNET" id=".net"
                                <c:if test = "${fn:contains(requestScope.skills, 'DOTNET')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for=".net">.NET</label>
                            </div>
                            <div>
                                <input type="checkbox" name="JS" id="js"
                                <c:if test = "${fn:contains(requestScope.skills, 'JS')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="js">JS</label>
                            </div>
                            <div>
                                <input type="checkbox" name="HTMLCSS" id="htmlcss"
                                <c:if test = "${fn:contains(requestScope.skills, 'HTMLCSS')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="htmlcss">HTML & CSS</label>
                            </div>
                            <div>
                                <input type="checkbox" name="PHP" id="php"
                                <c:if test = "${fn:contains(requestScope.skills, 'PHP')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="php">PHP</label>
                            </div>
                        </div>
                        <div class="lang">
                            <div>
                                <input type="checkbox" name="DELPHY" id="delphy"
                                <c:if test = "${fn:contains(requestScope.skills, 'DELPHY')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="delphy">Delphy</label>
                            </div>
                            <div>
                                <input type="checkbox" name="RUBY" id="ruby"
                                <c:if test = "${fn:contains(requestScope.skills, 'RUBY')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="ruby">Ruby</label>
                            </div>
                            <div>
                                <input type="checkbox" name="SWIFT" id="swift"
                                <c:if test = "${fn:contains(requestScope.skills, 'SWIFT')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="java">Swift</label>
                            </div>
                            <div>
                                <input type="checkbox" name="ANDROID" id="android"
                                <c:if test = "${fn:contains(requestScope.skills, 'ANDROID')}">
                                       checked="checked"
                                </c:if>
                                >
                                <label for="android">Android</label>
                            </div>
                        </div>
                    </div>
                    <input type="text" name="other" class="item_description" placeholder="Other skills"
                           value="${employee.other}">
                </div>
                <div class="element">
                    <div class="information_item">Experience</div>
                    <textarea form="profile-form" name="experience" class="item_description">${employee.experience}</textarea>
                </div>
                <div class="element">
                    <div class="information_item">Hobbies</div>
                    <textarea form="profile-form" name="hobbies" class="item_description">${employee.hobbies}</textarea>
                </div>
            </div>
        </div>
    </form>
</main>
<tags:footer/>
</body>
</html>