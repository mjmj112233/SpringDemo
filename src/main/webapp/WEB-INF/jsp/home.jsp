<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Welcome, <c:out value="${username}" />!</h1>
<ul>
    <li>
        <a href="quiz">
            <c:choose>
                <c:when test="${hasTakenQuiz}">
                    Retake Quiz
                </c:when>
                <c:otherwise>
                    Take Quiz
                </c:otherwise>
            </c:choose>
        </a>
    </li>
    <li><a href="grade">View Grades</a></li>
    <li style="margin-top: 40px"><a href="logout">Logout</a></li>
</ul>
</body>
</html>
