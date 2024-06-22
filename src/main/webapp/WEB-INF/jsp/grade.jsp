<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Grades</title>
</head>
<body>
    <h1>Grades</h1>
    <c:if test="${not empty applicationScope.grades}">
        <table border="1">
            <tr>
                <th>Username</th>
                <th>Grade</th>
            </tr>
            <c:forEach var="entry" items="${applicationScope.grades}">
                <tr>
                    <td><c:out value="${entry.key}" /></td>
                    <td><fmt:formatNumber value="${entry.value}" /></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <form action="/" method="get">
        <input type="submit" value="Back to Home" style="margin-top: 40px;">
    </form>
</body>
</html>
