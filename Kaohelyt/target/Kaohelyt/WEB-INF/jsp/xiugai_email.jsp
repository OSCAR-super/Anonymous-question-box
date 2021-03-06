<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/3/22
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改用户名</title>
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
<div  class="container" id="email" style="width: 400px;margin-top:20px;">
    <form action="${pageContext.request.contextPath}/xiuUserE.action" method="post">

        <input type='hidden' value='${CSRFToken}' id='csrftoken'>
        <div class="form-group" >
            <label for="email" >邮箱：</label><span v-if="!isValid" style="color:red;"><span class="label label-danger">!</span> 邮箱已存在</span>

        <input type="text"  v-model="eee" name="email" class="form-control" ></div>
        <br>
        <div class="form-group">
            <center><button type="submit" id="btn" value="提交"  class="btn btn btn-primary">提交</button></center></div>
    </form>
</div>
<script>
    var email=new Vue({
        el:'#email',
        data(){
            return{
                isValid:true,
                eee:""
            }
        },
        watch:{
            eee:function(val){
                axios.get('http://111.230.173.74/fE.action?UserEmail='+val)
                    .then(res=>{
                        if(res.data===1){
                            this.isValid=true;
                            let btn=document.getElementById("btn");
                            btn.disabled=false;
                        }else{
                            this.isValid=false;
                            let btn=document.getElementById("btn");
                            btn.disabled=true;
                        }
                    })
                    .catch(error=>{
                        console.log(error);
                    })
            }
        }
    })

</script>
</body>
</html>