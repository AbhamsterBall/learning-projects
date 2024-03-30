<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: AJH41
  Date: 2023-10-09
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <table width="80%" border="1" style="border-collapse: collapse;">
        <tr>
            <th>No</th>
            <th>Name</th>
            <th>Sex</th>
            <th>Age</th>
        </tr>
        <s:iterator value="userList" status="stat"> <!--struct-tags-->
            <tr>
                <td><s:property value="id" /></td>
                <td><s:property value="name" /></td>
                <td><s:property value="sex" /></td>
                <td><s:property value="age" /></td>
            </tr>
        </s:iterator>
    </table>
</div>
</body>
</html>
