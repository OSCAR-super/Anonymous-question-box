<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2019/12/11
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>匿名の提问箱登录</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/md5.js"></script>
    <script type="text/javascript">
        function refreshCode() {
            var code=document.getElementById("vcode");
            code.src="${pageContext.request.contextPath}/CheckCodeServlet.action?time"+new Date().getTime();
        }

        function jiami(){
            document.getElementById("password").value = hex_md5(
                document.getElementById("password").value
            );
        }
    </script>
</head>

<body>
<div class="container" style="width: 500px;" >
    <center>  <h3 style="text-align: center;">匿名の提问箱登录</h3></center>
    <form action="${pageContext.request.contextPath}/UserLoginS.action" method="post">
        <input type='hidden' value='${CSRFToken}' id='csrftoken'>
        <div class="form-group" style="width: 380px;margin-left: 40px;" >
            <label for="user">用户名：</label><br>
            <input type="text" name="UserLoginName" class="form-control" id="user" placeholder="请输入用户名或邮箱"/>
        </div>
        <br>
        <div class="form-group" style="width: 380px;margin-left: 40px;">
            <label for="password">密码：</label><br>
            <input type="password" name="UserPassword" class="form-control" id="password" placeholder="请输入密码"/>
        </div>
        <br>
        <div class="form-inline" style="width: 450px;margin-left: 40px;">
            <label for="vcode">验证码：</label><br>
            <input type="text" name="verifycode" style="width: 299px;"  class="form-control" id="verifycode" placeholder="请输入验证码" />
            <a href="javascript:refreshCode()" ><img src="${pageContext.request.contextPath}/CheckCodeServlet.action" title="看不清点击刷新" id="vcode"/></a>
        </div>
        <hr/>
        是否记住密码：<input type="checkbox" name="isAutoLogin" value="true">
        <div class="form-group" style="text-align: center;">
            <br>    <input class="btn btn btn-primary" type="submit"  onclick="jiami()" value="登录">
        </div>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/jupAddUser.action">注册新账号      </a><a href="javascript:void(0)" onclick="initFileList('y','AllUser','v')">游客登录               </a>
    <br>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <strong>${login_msg}</strong>
    </div>
</div>
</body>
<script type="text/javascript" language="javascript">
    function initFileList(idP,f,n) {
        var form = $("<form></form>");
        form.attr('action',"http://111.230.173.74/"+f+".action");
        form.attr('method','post');
        var input1 = $("<input type='text' />");
        input1.attr("name",n);
        input1.attr('value',idP);
        form.append(input1);
        $('body').append(form);
        form.submit();
    }
</script>
</html>
