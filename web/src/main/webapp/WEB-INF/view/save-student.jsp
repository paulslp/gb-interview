<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="ru.gb.spring.domain.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h1><%=request.getAttribute("actionType")%> student</h1>
<form:form action="save" modelAttribute="student">
    <form:hidden path="id"/>
    <br>
    name:<form:input path="name"/>
    <br>
    age:<form:input path="age"/>
    <input type="submit" value=<%=request.getAttribute("actionType")%>>
</form:form>
<c:url var="allStudentsUrl" value="/students"></c:url>
<a href="${allStudentsUrl}">ALL-STUDENTS</a>
</body>
</html>