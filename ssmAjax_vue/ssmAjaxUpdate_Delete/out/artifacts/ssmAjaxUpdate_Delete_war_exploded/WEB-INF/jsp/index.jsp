<%--
  Created by IntelliJ IDEA.
  User: AJH41
  Date: 2023-10-24
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script><%@include file="js/jquery.js"%></script>
<%--    <script><%@include file="js/vue.js"%></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <style><%@include file="css/all.css"%></style>
</head>
<body>

<div class="middle" id="index">

    <div id="re" style="height: 30px"></div>

    <form id="form" class="form-container" @submit.prevent="getTable" v-if="data !== null && data.length > 0"> <%--阻止提交--%>
        <input v-for="key in Object.keys(data[0])"
               type="text" :placeholder="key" :name="key" :style="'width: ' + inputWidth + 'px'"/>
        <input type="submit" value="search"/>
    </form>

    <table id="dataTable" class="tableStyle" v-if="data !== null && data.length > 0">
        <thead>
        <tr>
            <th v-for="key in Object.keys(data[0])">{{ key }}</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in data">
            <td v-for="key in Object.keys(item)">{{ item[key] || 'N/A' }}</td>
            <td><a :href="'/update/' + item[primaryKey]">Update</a></td>
            <td><a @click="deleteItem(item[primaryKey])" href="#">Delete</a></td>
        </tr>
        </tbody>
    </table>

    <div class="menu">
        <a href="#" @click="header">Header</a>
        <a href="#" @click="up">Page Up</a>
        <a href="#" @click="next">Next Page</a>
        <a href="#" @click="footer">Footer</a>
    </div>

</div>
</body>
<script><%@include file="js/index.js"%></script>
</html>
