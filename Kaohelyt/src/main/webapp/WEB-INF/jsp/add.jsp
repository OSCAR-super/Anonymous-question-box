<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/3/13
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户注册</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/md5.js"></script>
    <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
    <script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
    <script>Vue.config.productionTip=false</script>

    <script>
        function back(){
            window.history.go(-1);
        }

        function jiami(){
            document.getElementById("address").value = hex_md5(
                document.getElementById("address").value
            );
            document.getElementById("qq").value = hex_md5(
                document.getElementById("qq").value
            );
        }
        function send() {
            b=document.getElementById("email").value
            //window.location.href="${pageContext.request.contextPath}/sendEmail.action?address="+a;
            $.ajax({
                cache: true,
                type: "POST",
                url:"${pageContext.request.contextPath}/sendEmail.action",
                data:{"UserEmail":b},
                dataType:"text",
                async: false,
                error: function(request) {
                    alert("Connection error:"+request.error);
                },
                success: function(data) {
                    alert("SUCCESS!");
                }
            });
        }
    </script>

    <style>
        .form-control{
            width: 30%;
            margin-left: 38%;
        }
        label{
            margin-left: 38%;
        }
    </style>
</head>
<body>
<div class="container">
    <center><h3>用户注册页面</h3></center>

    <form action="${pageContext.request.contextPath}/AddUser.action" method="post">
        <input type='hidden' value='${CSRFToken}' id='csrftoken'>
        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="UserEmail" placeholder="请输入邮箱地址"/>
        </div>
        <div class="form-group">
            <label for="email1">请输入邮箱内的验证码：</label><button type="button" class="btn btn-info btn-xs" onclick="send()">点击发送验证码</button>
            <input type="text" class="form-control" id="email1" name="EmailCode" placeholder="请输入4位验证码"/>
        </div>
        <div class="form-group">
            <label for="name">昵称：</label>
            <input type="text" class="form-control" id="name" name="UserName" placeholder="请输入昵称">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="sex" value="男" checked="checked"/>男
            <input type="radio" name="sex" value="女"/>女
        </div>

        <div class="form-group" id="mingzi">
            <label for="age">用户名：</label>  <span v-if="!isValid" style="color:red;"><span class="label label-danger">!</span> 用户名已存在</span>
            <input type="text" v-model="username" class="form-control" id="age" name="UserLoginName" placeholder="请输入用户名">
        </div>
        <div class="form-group" id="fir">
            <label for="address">请输入密码：</label><span v-if="!isZhengque" style="color:red;"><span class="label label-danger">!</span> 密码不规范</span>
            <input v-model="fpass" type="password" class="form-control" id="address" name="UserPassword1" placeholder="6-16位，不能全为数字"/>
        </div>
        <div class="form-group" id="sec">
            <label for="qq">请确认密码：</label> <span v-if="!isPassword" style="color:red;"><span class="label label-danger">!</span> 两次密码不一致！</span>
            <input v-model="spass" type="password" class="form-control" id="qq" name="UserPassword2" placeholder="请确认密码"/>
        </div>
        <div class="form-group">
            <label for="yy">请输入邀请码：</label>
            <input type="text" class="form-control" id="yy" name="yy" placeholder="请输入邀请码，必填"/>
        </div>

        <div class="" style="text-align: center">
            <input class="btn" id="tijiao" disabled type="submit" value="提交" onclick="jiami()" />
            <input class="btn" type="reset" value="重置" />
            <input class="btn" type="button" value="返回" onclick="back()"/>
        </div>
    </form>

</div>
<script src="${pageContext.request.contextPath}/js/addVue.js"></script>
</body>
</html>

