<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/5/1
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改昵称</title>
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
    input{
        width: 300px;
        height: 30px;
    }
</style>
<body>
<div  class="container" style="width: 400px;margin-top:20px;">
    <form action="${pageContext.request.contextPath}/xiuUser.action" method="post">
        <input type='hidden' value='${CSRFToken}' id='csrftoken'>
        <div class="form-group" id="a">
            <label for="userloginname" >昵称：</label><span v-if="!isValid" style="color:red;"><span class="label label-danger">!</span> 昵称格式不对</span>
        <input type="text" v-model="name" id="userloginname" name="UserName" class="form-control" value="${userName}">
</div><br><br>
        <div class="form-group">
            <center><button id="btn" type="submit" disabled value="提交"  class="btn btn btn-primary">提交</button></center></div>
    </form>
</div>
<script>
    var a=new Vue({
        el:"#a",
        data:{
            name:"",
            isValid:true
        },
        watch:{
            name:function(val){
                if(val.length<2||val.length>10){
                    this.isValid=false;
                    let btn=document.getElementById("btn");
                    btn.disabled=true;
                }
                else{
                    this.isValid=true;
                    let btn=document.getElementById("btn");
                    btn.disabled=false;
                }
            }
        }
    })

</script>
</body>
</html>
