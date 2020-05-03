<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/1/30
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>注册成功</title>
    <link href="${pageContext.request.contextPath}/css/c.css" rel="stylesheet" type="text/css" />
</head>
<body>
<p><span id="count">5</span>秒后<a href="${pageContext.request.contextPath}/UserLogin.action">回到登录页</a></p>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/c.js"></script>
</body>
</html>
