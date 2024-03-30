<%--
  Created by IntelliJ IDEA.
  User: AJH41
  Date: 2023-10-23
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    ${emps}
    <div align="center">
    <table width="80%" border="1" style="border-collapse: collapse;">
    <c:forEach var="row" items="${emps}">
        <tr>
            <td>${row.empEmpno}</td>
            <td>${row.empEname}</td>
            <td>${row.empJob}</td>
            <td>${row.empMgr}</td>
            <td>${row.empHiredate}</td>
            <td>${row.empSal}</td>
            <td>${row.empComm}</td>
            <td>${row.empDeptno}</td>
            <td><a href="#">修改</a></td>
            <td><a href="#">删除</a></td>
            <td><a href="#">添加</a></td>
        </tr>
    </c:forEach>
    </table>
    </div>
</body>
</html>
