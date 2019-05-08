<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/register-cl.css">
</head>
<body>
<div>
    <form method="POST" action="<c:url value="profile"/>">
        <header>
            <div class="logo">
                <a href="<c:url value="/"/>"><h1>C.J.S.S.</h1></a>
            </div>
            <section class="loginSection">
                <c:if test="${not empty param.loginMsg}">
                    <c:choose>
                        <c:when test="${param.loginMsg == 'wrong.email.error'}">
                            <p style="color:red">Account with such email doesn't exist  </p>
                        </c:when>
                        <c:when test="${param.loginMsg == 'wrong.password.error'}">
                            <p style="color:red">Wrong password  </p>
                        </c:when>
                    </c:choose>
                </c:if>
                <div class="element">
                    <input type="email" name="email" placeholder="E-mail" required>
                </div>
                <div class="element">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <div class="element">
                    <input class="loginbutton" type="submit" name="login" value="Log In">
                </div>
                <input type="hidden" name="type" value="employee">
            </section>
        </header>
    </form>
</div>
<a href="<c:url value="/registration-company"/>">
    <button class="btnCompany">I'm a company</button>
</a>
<main>
    <div class="container">
        <form method="post" id="register-form">
            <h2>EMPLOYEE REGISTRATION FORM</h2>
            <div class="person-name">
                <input type="text" name="first_name" placeholder="First name" required>
                <input type="text" name="last_name" placeholder="Last name" required>
            </div>
            <div class="birth">
                <label for="date">Birth</label>
                <input type="date" name="date" id="date">
            </div>
            <input type="password" name="password" id="password1" placeholder="Password" required>
            <input type="password" name="confirm_password" id="password2" placeholder="Confirm password" required>
            <input type="email" name="email" placeholder="E-mail" required>
            <c:if test="${not empty param.registerMsg}">
                <c:if  test="${param.registerMsg == 'email.is.taken.error'}">
                    <p style="color:red">This email is already taken</p>
                </c:if >
            </c:if>
            <textarea form="register-form" name="education" placeholder="Education"></textarea>
            <section class="skills">
                <div>Skills</div>
                <div class="lang">
                    <div>
                        <input type="checkbox" name="JAVA" id="java">
                        <label for="java">Java</label>
                    </div>
                    <div>
                        <input type="checkbox" name="PYTHON" id="python">
                        <label for="python">Python</label>
                    </div>
                    <div>
                        <input type="checkbox" name="C" id="c">
                        <label for="c">C</label>
                    </div>
                    <div>
                        <input type="checkbox" name="CPP" id="cpp">
                        <label for="cpp">C++</label>
                    </div>
                </div>
                <div class="lang">
                    <div>
                        <input type="checkbox" name="DOTNET" id=".net">
                        <label for=".net">.NET</label>
                    </div>
                    <div>
                        <input type="checkbox" name="JS" id="js">
                        <label for="js">JavaScript</label>
                    </div>
                    <div>
                        <input type="checkbox" name="HTMLCSS" id="htmlcss">
                        <label for="htmlcss">HTML & CSS</label>
                    </div>
                    <div>
                        <input type="checkbox" name="PHP" id="php">
                        <label for="php">PHP</label>
                    </div>
                </div>
                <div class="lang">
                    <div>
                        <input type="checkbox" name="DELPHY" id="delphy">
                        <label for="java">Delphy</label>
                    </div>
                    <div>
                        <input type="checkbox" name="RUBY" id="ruby">
                        <label for="java">Ruby</label>
                    </div>
                    <div>
                        <input type="checkbox" name="SWIFT" id="swift">
                        <label for="java">Swift</label>
                    </div>
                    <div>
                        <input type="checkbox" name="ANDROID" id="android">
                        <label for="java">Android</label>
                    </div>
                </div>
            </section>
            <input type="text" name="other" placeholder="Other skills">
            <textarea form="register-form" name="experience" placeholder="Experience"></textarea>
            <div class="agree">
                By clicking "Register", you confirm that you have read, fully agree and accept the terms of the "Agreement on the provision of employment assistance services".
            </div>
            <input type="submit" value="REGISTER">
        </form>
    </div>
</main>
<tags:footer/>
</body>
</html>
