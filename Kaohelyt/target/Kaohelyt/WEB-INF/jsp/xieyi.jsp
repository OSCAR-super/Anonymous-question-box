<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/4/4
  Time: 20:35
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
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <title>邮箱激活</title>
    <style>
        #xieyi{
            width: 50%;
            height: 20em;
            margin-left: 25%;
            margin-top: 1%;
            border: 2px solid #0084ff;
            border-radius: 2%;
        }
        .checkbox{
            margin-left: 25%;
            font-size: 1.2em;
        }
        button{
            margin-left: 40%;
            width: 20%;
        }
        input{
            width: 18px;
            height: 18px;
        }
        #jg{
            width: 40%;
            margin-left: 25%;
            display: none;
        }
    </style>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script>
        function tijiao(){
            let ck = document.getElementById("ck");
            if(ck.checked==false){
                let jg = document.getElementById("jg");
                jg.style.display = "block";
                return false;
            }else {
                b=document.getElementById("1234").value
                $.ajax({
                    cache: true,
                    type: "POST",
                    url:"${pageContext.request.contextPath}/ji.action",
                    data:{'UserId':b},
                    dataType:"text",
                    async: false,
                    error: function (jqXHR, textStatus, errorThrown) {
                        /*弹出jqXHR对象的信息*/
                        alert(jqXHR.responseText);
                        alert(jqXHR.status);
                        alert(jqXHR.readyState);
                        alert(jqXHR.statusText);
                        /*弹出其他两个参数的信息*/
                        alert(textStatus);
                        alert(errorThrown);
                    },
                        success: function(data) {
                        alert("SUCCESS!");
                    }
                });
                return false;
            }
        }
    </script>
</head>
<body>
<div id="xieyi">
    <p style="margin-left: 2%;">我是一篇非常正经的协议...假装有很多各字... </p>
</div>
<div class="checkbox" >
    <label>
        <input type="checkbox" id="ck">我已阅读并同意协议
    </label>
</div>
<div class="alert alert-warning" id="jg">
    <a href="#" class="close" data-dismiss="alert">
        &times;
    </a>
    <strong>警告！</strong>请先阅读并同意此协议。
</div>
<br>
<div>
<button type="button" class="btn btn-default" onclick="tijiao()">邮箱激活</button>
</div>
<div>
<input type="hidden" value="${UserId}" id="1234">
</div>

</body>
</html>