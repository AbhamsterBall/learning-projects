<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: AJH41
  Date: 2023-10-09
  Time: 10:44 AM
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
            <th>dName</th>
            <th>rank</th>
            <th>page</th>
        </tr>
        <s:iterator value="productList" status="stat"> <!--struct-tags-->
            <tr>
                <td><s:property value="empno" /></td>  <%--#stat.count是指当前行是第几个而不是id值--%>
                <td><s:property value="dname" /></td>
                <td><s:property value="rank" /></td>
                <td><s:property value="page" /> </td>
            </tr>
        </s:iterator>
    </table>
</div>
<div style="text-align: center"><a href=<s:property value="url" />> next </a></div>
</body>
</html>
