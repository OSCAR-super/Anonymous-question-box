<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/4/26
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <title>举报</title>
    <style>
        .box{
            width:50%;
            height: 400px;
            margin-top: 50px;
            margin-left: 25%;
            border: 2px solid #c0d4ff;
        }

    </style>
</head>
<body>
<div class="box">
    <center><p>您正在举报<span id="username" style="color: red;">${UserName}</span></p></center>
    <hr style="border:1px solid #c0d4ff;">
    <div style="margin-left: 20%;">
        <button type="button" class="btn btn-default">诈骗广告</button>
        <button style="margin-left: 3em;" type="button" class="btn btn-default">辱骂黄暴</button>
        <button style="margin-left: 3em;" type="button" class="btn btn-default">游戏作弊</button>
        <button style="margin-left: 3em;" type="button" class="btn btn-default">反动涉政</button>
    </div><br>
    <div>
        <center><p style="font-size:1.5em;color: hsl(220, 13%, 67%);">举报原因</p></center>
        <textarea id="con" style="width: 80%;margin-left: 10%; resize:none;" class="form-control" rows="3" placeholder="点击输入详细描述或其他举报原因"></textarea>
        <br>
        <button onclick="jubao2()" type="button" style="width: 20%;margin-left: 25%;color:black;background-color: rgb(1, 195, 255);border:none;" class="btn btn-primary"><b>取消</b></button>
        <button onclick="jubao()" type="button" style="width: 20%;margin-left: 10%;color:black;background-color: #ffe249;border:none;" class="btn btn-primary"><b>举报</b></button>
    </div>
</div>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>

    function jubao2() {
        window.location.href="${pageContext.request.contextPath}/AllUser.action";
    }
    function addFun(){
        let buttons=document.getElementsByClassName("btn btn-default");
        for(let i=0;i<4;i++){
            buttons[i].onclick=function(){
                if($(this).hasClass('btn btn-default active')){
                    $(this).removeClass('btn btn-default active')
                    $(this).addClass('btn btn-default')
                }else{
                    $(this).addClass('btn btn-default active');
                }
            }
        }
    }
    function jubao() {
        let types="";
        let content=document.getElementById("con").value;
        let buttons=document.getElementsByClassName("btn btn-default");
        for(let i=0;i<4;i++){
            if(buttons[i].className=="btn btn-default active"){
                types+=buttons[i].innerText+",";
            }
        }
        types=types.substring(0,types.length-1);
        types+=content;
        var form = $("<form></form>");
        form.attr('action',"http://111.230.173.74/jubaoN.action");
        form.attr('method','post');
        var input1 = $("<input type='text' />");
        input1.attr("name",'JubaoName');
        input1.attr('value','${UserName}');
        form.append(input1);
        var input2 = $("<input type='text' />");
        input2.attr("name",'JubaoN');
        input2.attr('value',types);
        form.append(input2);
        var input3 = $("<input type='text' />");
        input3.attr("name",'JID');
        input3.attr('value','${JID}');
        form.append(input3);
        $('body').append(form);
        form.submit();

    }
    addFun();

</script>

</body>
</html>