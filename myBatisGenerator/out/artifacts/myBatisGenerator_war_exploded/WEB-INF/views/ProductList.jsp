<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: AJH41
  Date: 2023-10-08
  Time: 3:29 PM
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
            <th>Product Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <s:iterator value="listProduct" status="stat"> <!--struct-tags-->
            <tr>
                <td><s:property value="#stat.count" /></td>  <%--#stat.count是指当前行是第几个而不是id值--%>
                <td><s:property value="name" /></td>
                <td><s:property value="description" /></td>
                <td><s:property value="price" /></td>
            </tr>
        </s:iterator>
    </table>
</div>
</body>
</html>
