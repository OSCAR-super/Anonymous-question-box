<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/3/9
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>提问的回复</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link href="${pageContext.request.contextPath}/css/PinLuen.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container" style="width: 400px;" >

    <h2>提问区</h2>
    <div id="middle" class="form-group">

        <tr>提问内容：</tr></br>
        <table class="form-group">
            <tr>
                <td class="form-control">
                    <div style="width:100%;word-wrap:break-word;">
                        ${PinLuen.neiron}</div>
                </td>
            </tr>
        </table>
        <table class="form-group">
            <tr>回复：</tr></br>
            <c:forEach items="${H}" var="Hueifu">
                <tr>
                    <td class="form-control"> <div style="width:100%;word-wrap:break-word;">
                            ${Hueifu.HR}:${Hueifu.HN}</div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${email=='已激活'}">
            <form action="${pageContext.request.contextPath}/AddHH.action" method="post">
                <input type='hidden' value='${CSRFToken}' id='csrftoken'>
                <div><input type="text" name="IdP" value="${PinLuen.idP}" hidden></div>
                <div class="form-group">
                    <label>你的回复：</label>

                    <textarea type="text"  class="form-control" name="HN"></textarea>

                </div>
                <div style="display: flex;">
                    <input type="submit" class="form-control" style="width: 20%;" value="提交">
                    <a href="${pageContext.request.contextPath}/AllUser.action" class="btn btn-default" style="width: 20%;margin-left: 10%;" >返回</a>
                </div>
            </form>
        </c:if>
    </div>
</div>

</body>
</html>