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
    <title>Document</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/md5.js"></script>
</head>
<style>
    .imgview{
        width: 150px;
        height: 150px;
        border-radius: 50%;
        border: 1px solid red;
    }
    .a{
        position: relative;
        display: block;
        text-decoration: none;
        color: grey;
    }
    .fileopen{
        position: absolute;
        left: 0;
        top: 0;
        opacity: 0;
        filter: alpha(opacity=0);
        width: 64px;
        overflow: hidden;
    }
</style>
<body>
<div  class="container" style="width: 400px;">
<form action="">
    <div id="post" class="form-group">
        <a  href="javascript:void(0);" class="a"><input type="file" class="fileopen" class="form-control">上传图片</a>
        <br>
        <img src="" alt="" class="imgview" accept="image/png, image/jpeg, image/gif, image/jpg">
    </div>
    <br><br>
    <div class="form-group">
    <input type="submit" value="提交" class="btn btn btn-primary"></div>
</form>
</div>
<script>
    var input =  document.querySelector("input");
    //当选择完成图片之后调用
    input.onchange = function(){
        //1. 拿到fileinput里面的文件, 这个file是一个file对象， file对象不能直接展示的
        var file =input.files[0];
        console.log(file);

        //2. 读取文件，成功img标签可以直接使用的格式
        //FileReader类就是专门用来读文件的
        var reader = new FileReader();

        //3. 开始读文件
        //readAsDataURL: dataurl它的本质就是图片的二进制数据， 进行base64加密后形成的一个字符串， 这个字符串可以直接作用img标签的图片资源使用

        reader.readAsDataURL(file);

        //4. 因为文件读取是一个耗时操作， 所以它在回调函数中，才能够拿到读取的结果
        reader.onload = function() {
            console.log(reader.result);
            //直接使用读取的结果
            document.querySelector("img").src = reader.result;
        }
        document.querySelector("img").src = file;
    }
</script>
</body>
</html>
