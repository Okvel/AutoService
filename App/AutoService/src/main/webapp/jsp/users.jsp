<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Users!</title>
    </head>
    <body>
        <table>
            <tr>
                <td>ID</td>
                <td>First name</td>
                <td>Last name</td>
                <td>Patronymic</td>
                <td>Role</td>
                <td>Experience</td>
            </tr>
            <c:forEach items="${users}" var="elem">
                <tr>
                    <td>${elem.id}</td>
                    <td>${elem.personInfo.firstName}</td>
                    <td>${elem.personInfo.lastName}</td>
                    <td>${elem.personInfo.patronymic}</td>
                    <td>${elem.role.name}</td>
                    <td>${elem.experience} year</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
