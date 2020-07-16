<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book list</title>
</head>
<body>
<c:if test="${empty bookList}">
    No available book list in PostgreSQL book table
</c:if>
<c:if test="${!empty bookList}">
    <ul>
        <c:forEach var="book" items="${bookList}">
            <li>id = ${book.id}, title = ${book.title}</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>
