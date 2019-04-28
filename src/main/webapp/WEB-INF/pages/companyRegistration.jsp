<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/register-cmp.css">
</head>
<body>
<div>
    <form method="post" action="<c:url value="/profile"/>">
        <header>
            <div class="logo">
                <a href="<c:url value="/"/>"><h1>C.J.S.S.</h1></a>
            </div>
            <section class="loginSection">
                <div class="element">
                    <input type="email" name="email" placeholder="E-mail" required>
                </div>
                <div class="element">
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <div class="element">
                    <input class="loginbutton" type="submit" name="login" value="Log In" required>
                </div>
                <input type="hidden" name="type" value="company">
            </section>
        </header>
    </form>
</div>
<a href="<c:url value="/registration-employee"/>">
    <button>I'm an employee</button>
</a>
<main>
    <div class="container">
        <form id="register-form" method="post">
            <h2>COMPANY REGISTRATION FORM</h2>
            <select form="register-form" name="org" required>
                <option>Ltd.</option>
                <option>LLC</option>
                <option>OJSC</option>
                <option>TOO</option>
                <option>PS</option>
                <option>LP</option>
                <option>Non-Profit</option>
            </select>
            <input type="text" name="name" placeholder="Company name" required
            <c:if test="${not empty param.name}">
                   value="${param.name}"
            </c:if>>
            <c:if test="${not empty param.registerMsg}">
                <c:if test="${param.registerMsg == 'name.or.email.is.taken.error'}">
                    <p style="color:red">Company with such name or email is already registered.</p>
                </c:if>
            </c:if>
            <div class="contacts">
                <input type="text" name="city" placeholder="City" required
            <c:if test="${not empty param.city}">
                   value="${param.city}"
            </c:if>>
                <input type="date" name="fdate" placeholder="Foundation" required
            <c:if test="${not empty param.fdate}">
                   value="${param.fdate}"
            </c:if>>
            </div>
            <input type="email" name="email" placeholder="E-mail" required
            <c:if test="${not empty param.email}">
                   value="${param.email}"
            </c:if>>
            <div class="contacts">
                <input type="text" name="phone" placeholder="Telephone" required
            <c:if test="${not empty param.phone}">
                   value="${param.phone}"
            </c:if>>
                <input type="site" name="site" placeholder="Site" required
            <c:if test="${not empty param.site}">
                   value="${param.site}"
            </c:if>>
            </div>
            <input type="password" name="password" id="password1" placeholder="Password" required>
            <input type="password" name="confirm_password" id="password2" placeholder="Confirm password" required>
            <div class="agree">
                By clicking on “Register Company”, you confirm that you have read and fully agree with the terms of use
                of the site.
            </div>
            <input type="submit" value="REGISTER COMPANY">
        </form>
    </div>
</main>
<tags:footer/>
</body>
</html>
