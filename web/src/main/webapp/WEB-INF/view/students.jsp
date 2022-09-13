<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.gb.spring.domain.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
<h1>Students</h1>
<br>
<a href="${studentsUrl}">Show all students</a>
<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>age</td>
    </tr>
    <% if (request.getAttribute("students") != null) %>
    <% for (Student value : (List<Student>) request.getAttribute("students")) {%>
    <tr>
        <td><%=value.getId()%>.</td>
        <td><%=value.getName()%>
        </td>
        <td><%=value.getAge()%>
        </td>
    </tr>
    <%}%>
</table>
<c:url var="addUrl" value="/students/add"></c:url>
<c:url var="deleteUrl" value="/students/delete"></c:url>
<a href="${addUrl}">ADD</a>

<form action="students/edit" method="post">
    update student with id:
    <input type="text" name="id"/>
    <input type="submit" value="EDIT"/>
</form>
<a href="${deleteUrl}">DELETE</a>

</body>
</html>