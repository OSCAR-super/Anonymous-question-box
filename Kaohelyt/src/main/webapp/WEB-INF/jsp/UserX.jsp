<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/3/25
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <title>修改信息</title>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/vue.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script>
        function back() {
            window.location.href="${pageContext.request.contextPath}/AllUser.action";
        }
        function xg() {
            window.location.href = "${pageContext.request.contextPath}/xiugai.action"
        }
        function send1() {
            a = document.getElementById("email1").value
            $.ajax({
                cache: true,
                type: "POST",
                url: "${pageContext.request.contextPath}/sendEmail2.action",
                data: { 'UserEmail': a },
                dataType: "text",
                async: false,
                error: function (request) {
                    alert("Connection error:" + request.error);
                },
                success: function (data) {
                    alert("SUCCESS!");
                }
            });
        }
        function send2() {
            a = document.getElementById("email1").value
            $.ajax({
                cache: true,
                type: "POST",
                url: "${pageContext.request.contextPath}/sendEmail3.action",
                data: { 'UserEmail': a },
                dataType: "text",
                async: false,
                error: function (request) {
                    alert("Connection error:" + request.error);
                },
                success: function (data) {
                    alert("SUCCESS!");
                }
            });
        }
        function send3() {
            a = document.getElementById("email1").value
            $.ajax({
                cache: true,
                type: "POST",
                url: "${pageContext.request.contextPath}/sendEmail4.action",
                data: { 'UserEmail': a },
                dataType: "text",
                async: false,
                error: function (request) {
                    alert("Connection error:" + request.error);
                },
                success: function (data) {
                    alert("SUCCESS!");
                }
            });
        }
        function send4() {
            a = document.getElementById("email1").value
            $.ajax({
                cache: true,
                type: "POST",
                url: "${pageContext.request.contextPath}/sendEmail5.action",
                data: { 'UserEmail': a },
                dataType: "text",
                async: false,
                error: function (request) {
                    alert("Connection error:" + request.error);
                },
                success: function (data) {
                    alert("SUCCESS!");
                }
            });
        }
    </script>
    <style>
        #touxiang {
            width: 8em;
            height: 8em;
            border: 1px solid burlywood;
            border-radius: 50%;
        }

        span {
            font-size: 20px;
            margin-left: 10px;
        }

        #yes {
            color: white;
            border: rgb(91, 184, 93);
            border-radius: 5%;
            background-color: rgb(91, 184, 93);
            padding: 0 5px;
        }

        #no {
            color: white;
            border: rgb(214, 84, 79);
            border-radius: 5%;
            background-color: rgb(214, 84, 79);
            padding: 0 5px;
        }
    </style>
</head>

<body>
<br><br>

<center>
    <div>
        <img id="touxiang" <c:if test="${face=='123'}">src="${pageContext.request.contextPath}/img/01.jpg"</c:if>
             <c:if test="${face!='123'}">src="${pageContext.request.contextPath}/showeinvoice.action"</c:if>/>
    </div>
</center><br><br>
<div style="display:flex;width:26%;margin-left:37%;">
    <div class="input-group" id="name">
        <span class="input-group-addon">昵称</span>
        <input type="text" class="form-control" readonly id="nc" placeholder="10字以内" value="${user.userName}">
    </div> <button onclick="xg()" style="margin-left: 10px;" type="button" class="btn btn-default">修改</button><br>
</div><br>

<div style="display:flex;width:26%;margin-left:37%;">
    <div class="input-group" >
        <span class="input-group-addon">用户名</span>
        <input id="UserName" type="text" class="form-control" readonly placeholder="10字以内" value="${user.userLoginName}">
    </div> <button style="margin-left: 10px;" onclick="send2()" type="button" class="btn btn-default">发邮箱修改</button><br>
</div><br>

<div style="display:flex;width:26%;margin-left:37%;">
    <div class="input-group">
        <span class="input-group-addon">密码</span>
        <input type="text" class="form-control" readonly value="安全等级：高">
    </div> <button style="margin-left: 10px;" onclick="send3()" type="button" class="btn btn-default">发邮箱修改</button><br>
</div><br>

<div style="display:flex;margin-left:37%;">
    <div class="input-group" id="email" style="width:35%;">
        <span class="input-group-addon">邮箱</span>
        <input type="text" class="form-control" readonly id="email1" placeholder="假装有邮箱" value="${user.userEmail}">
    </div><button style="margin-left: 10px;" onclick="send4()" type="button" class="btn btn-default">发邮箱修改</button>
    <c:if test="${user.userJ=='yes'}"><span id="yes">已激活</span></c:if>
    <c:if test="${user.userJ=='no'}"><span onclick="send1()" id="no">点我激活</span>
    </c:if>
</div><br>

<div style="display:flex;width:26%;margin-left:37%;">
    <button type="button" class="btn btn-default active ">提问箱状态:</button>
    <c:if test="${user.k=='yes'}"><span onclick="initFileList('dK')" id="yes">打开,点我关闭</span></c:if>
    <c:if test="${user.k=='no'}"><span onclick="initFileList('K')" id="no">关闭，点我开启</span>
    </c:if>
</div><br>

<div style="display:flex;width:26%;margin-left:37%;">
    <div class="input-group">
        <span class="input-group-addon">提问箱数</span>
        <input type="text" class="form-control" readonly value="${pp}">
    </div><br>
</div><br>

<div style="display:flex;width:26%;margin-left:37%;">
    <div class="input-group">
        <span class="input-group-addon">回复数</span>
        <input type="text" class="form-control" readonly value="${hh}">
    </div><br>
</div><br>

<div style="display:flex;width:26%;margin-left:37%;">
    <div class="input-group">
        <span class="input-group-addon">提问数</span>
        <input type="text" class="form-control" readonly value="${pp}">
    </div><br>
</div>

<br><br>
<center>
    <input type="button" class="btn btn-default" value="返回主页" onclick="back()" />
</center>

</body>
<script type="text/javascript" language="javascript">
    function initFileList(f) {
        a = document.getElementById("UserName").value
        var form = $("<form></form>");
        form.attr('action',"http://111.230.173.74/"+f+".action");
        form.attr('method','post');
        var input1 = $("<input type='text' />");
        input1.attr("name","UserName");
        input1.attr('value',a);
        form.append(input1);
        $('body').append(form);
        form.submit();
    }
</script>
</html>