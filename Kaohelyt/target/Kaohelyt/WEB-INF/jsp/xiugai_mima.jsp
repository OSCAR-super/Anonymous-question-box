<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/5/1
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改密码</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/md5.js"></script>
    <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
    <script src="https://cdn.staticfile.org/axios/0.18.0/axios.min.js"></script>
</head>
<style>
    input {
        width: 300px;
        height: 30px;
    }
</style>

<body>
<div class="container" id="pass" style="width: 400px;margin-top:20px;">
    <form action="${pageContext.request.contextPath}/xiuUserP.action" method="post">
        <input type='hidden' value='${CSRFToken}' id='csrftoken'>
        <div class="form-group">
            <label for="username">原密码：</label></div>
        <input type="text" id="username" name="UserP" class="form-control">
        <br><br>
        <div class="form-group">
            <label for="password">修改密码：</label><span v-if="!isPro" style="color:red;"><span
                class="label label-danger">!</span> 密码格式不正确</span></div>
        <input type="password" v-model="one" id="password" name="UserPassword" placeholder="6-16位，不能是纯数字"
               class="form-control">
        <br><br>
        <div class="form-group">
            <label for="pw2">确认密码：</label><span v-if="!isValid" style="color:red;"><span
                class="label label-danger">!</span> 两次密码不一致</span>
            <input type="password" v-model="two" id="pw2" name="UserPassword2" placeholder="请再次输入你要修改的密码"
                   class="form-control"></div>
        <br><br>
        <div class="form-group">
            <input type="submit" id="btn" disabled value="提交"  class="btn btn btn-primary"></div>
    </form>
</div>
<script>

    var pass = new Vue({
        el: "#pass",
        data: {
            isPro: true,
            isValid: true,
            one: "",
            two: ""
        },
        watch: {
            one: function (val) {

                if (val.length < 2 || val.length > 16 || /^\d+$/.test(val)) {
                    this.isPro = false;
                }else{
                    this.isPro=true;
                }
                localStorage.setItem("one",val);

            },
            two:function(val){
                if(val!==localStorage.getItem("one")){
                    this.isValid=false;
                }else{
                    this.isValid=true;
                }
                yz();
            }
        }
    });
    function yz(){
        var btn = document.getElementById("btn");
        if(pass.isPro===true&&pass.isValid===true){
            btn.disabled=false;
        }
        else{
            btn.disabled=true;
        }
    }

</script>
</body>

</html>