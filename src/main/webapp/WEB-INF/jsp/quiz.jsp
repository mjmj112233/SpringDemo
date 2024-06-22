<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
    <h1>Quiz</h1>
    <form action="quiz" method="post">
        <c:forEach var="question" items="${questions}" varStatus="loopStatus">
            <div>
                <p>${question.questionText}</p>
                <c:forEach var="option" items="${question.options}" varStatus="status">
                    <input type="radio" name="question_${loopStatus.index}" value="${status.index}" /> ${option}<br>
                </c:forEach>
            </div>
        </c:forEach>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
