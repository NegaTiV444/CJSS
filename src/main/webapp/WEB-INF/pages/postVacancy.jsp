<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Post vacancy</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/styles/post_vacancy.css">
</head>
<body>
<tags:header/>
<main>
    <div class="container">
        <form id="post-form" method="post">
            <h2>POST VACANCY</h2>
            <input type="text" name="position" placeholder="Position" required>
            <input type="text" name="location" placeholder="Location" required>
            <textarea form="post-form" name="description" placeholder="Description"></textarea>
            <section class="skills">
                <div>Skills</div>
                <div class="lang">
                    <div>
                        <input type="checkbox" name="java" id="java">
                        <label for="java">Java</label>
                    </div>
                    <div>
                        <input type="checkbox" name="python" id="python">
                        <label for="java">Python</label>
                    </div>
                    <div>
                        <input type="checkbox" name="c" id="c">
                        <label for="java">C</label>
                    </div>
                    <div>
                        <input type="checkbox" name="cpp" id="cpp">
                        <label for="java">CPP</label>
                    </div>
                </div>
                <div class="lang">
                    <div>
                        <input type="checkbox" name=".net" id=".net">
                        <label for="java">.NET</label>
                    </div>
                    <div>
                        <input type="checkbox" name="js" id="js">
                        <label for="java">JS</label>
                    </div>
                    <div>
                        <input type="checkbox" name="htmlcss" id="htmlcss">
                        <label for="java">HTML & CSS</label>
                    </div>
                    <div>
                        <input type="checkbox" name="php" id="php">
                        <label for="java">PHP</label>
                    </div>
                </div>
                <div class="lang">
                    <div>
                        <input type="checkbox" name="delphy" id="delphy">
                        <label for="java">Delphy</label>
                    </div>
                    <div>
                        <input type="checkbox" name="ruby" id="ruby">
                        <label for="java">Ruby</label>
                    </div>
                    <div>
                        <input type="checkbox" name="swift" id="swift">
                        <label for="java">Swift</label>
                    </div>
                    <div>
                        <input type="checkbox" name="android" id="android">
                        <label for="java">Android</label>
                    </div>
                </div>
            </section>
            <textarea form="post-form" name="conditions" placeholder="Conditions"></textarea>
            <input type="submit" value="POST">
        </form>
    </div>
</main>
<tags:footer/>
</body>
</html>