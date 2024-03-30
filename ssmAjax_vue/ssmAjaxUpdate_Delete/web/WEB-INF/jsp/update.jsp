<%--
  Created by IntelliJ IDEA.
  User: AJH41
  Date: 2023-10-27
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script><%@include file="js/jquery.js"%></script>
    <style><%@include file="css/all.css"%></style>
</head>
<body>
    <div id="update" class="middle">
        <form id="form" class="form-container" @submit.prevent="updateEmp">
            <table class="tableStyle">
                <tr v-for="key in Object.keys(entity)">
                    <td><span>{{ key }}</span></td>
                    <td><input type="text" :value="entity[key]" :name="key" :id="key"></td>
                </tr>
            </table>
            <input type="submit"/>
        </form>

        <div id="updateReturn" class="menu">
            <span id="re"></span><br/>
            <a href="/index">HOME</a>
        </div>
    </div>

</body>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
<script><%@include file="js/update.js"%></script>
</html>
