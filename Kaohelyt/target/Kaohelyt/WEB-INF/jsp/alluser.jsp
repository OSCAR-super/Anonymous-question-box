<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/3/8
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>主页</title>
    <link href="${pageContext.request.contextPath}/css/PinLuen.css" rel="stylesheet" type="text/css" >
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid" style="background-color: rgb(159, 191, 238);">
        <div class="navbar-header">
            <a class="navbar-brand" disabled="true">匿名提问箱</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><c:if test="${G=='yes'}"><a href="${pageContext.request.contextPath}/fj.action"><span class="glyphicon glyphicon-user"></span> 举报信息</a></c:if></li>
            <li><a href="${pageContext.request.contextPath}/UserLoginOut.action"> <span class="glyphicon glyphicon-log-in"></span><c:if test="${check=='已激活'}">登出</c:if><c:if test="${check=='未激活'}">登录</c:if></a></li>
            <li><c:if test="${check=='已激活'}"><a href="${pageContext.request.contextPath}/showUser.action"><span class="glyphicon glyphicon-user"></span> 个人信息</a></c:if></li>
        </ul>
    </div>
</nav>
<div style="display: flex;">
    <!--头像-->
    <div id="tou">
        <div id="ti"><img id="touxiang" <c:if test="${face=='123'}">src="${pageContext.request.contextPath}/img/01.jpg"</c:if><c:if test="${face!='123'}">src="${pageContext.request.contextPath}/showeinvoice.action"</c:if>/>
            <c:if test="${check=='已激活'}"><div class="upload"><span id="xiu">修改头像</span><input type="file" class="upload1"></div></c:if>
        </div>
    </div>

    <div id="container">
        <!--提问区-->
        <div style="width: 35%;">
            <div id="top">
                <h3 style="margin-left: 2%;">对你的提问</h3><br>
                <ul class="list-group">
                    <c:if test="${PinLuen.number!=0}">
                        <c:forEach items="${pinluen}" var="PinLuen">
                            <c:if test="${PinLuen.de=='no'}">

                                <li class="list-group-item" style="width:100%;" >
                                    <div style="width:100%;word-wrap:break-word;">
                                            ${PinLuen.neiron}
                                        <c:if test="${PinLuen.number!=0}">
                                            <span class="xiaoxi">${PinLuen.number}</span></c:if>
                                        <button type="button" class="btn btn-danger"onclick="initFileList('${PinLuen.idP}','dH','IdP')">删除</button>
                                        <button type="button" class="btn btn-info" onclick="initFileList('${PinLuen.idP}','AddH','IdP')">回复</button>
                                    </div>
                                </li>

                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${PinLuen.number==0}">
                        <c:forEach items="${pinluen}" var="PinLuen">
                            <c:if test="${PinLuen.de=='no'}">
                                <li class="list-group-item" style="width:100%;">
                                    <div style="width:100%;word-wrap:break-word;">
                                            ${PinLuen.neiron}
                                        <c:if test="${PinLuen.number!=0}">
                                            <span class="xiaoxi">${PinLuen.number}</span></c:if>
                                        <button type="button" class="btn btn-danger"onclick="initFileList('${PinLuen.idP}','dH','IdP')">删除</button>
                                        <button type="button" class="btn btn-info" onclick="initFileList('${PinLuen.idP}','AddH','IdP')">回复</button>

                                    </div>
                                </li>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>

            <ul id="fenye" class="pagination" style="margin-left: 10%;">

            </ul>
        </div>
        <!--好友列表-->
        <div id="friend">
            <div class="input-group" style="width: 40%;margin-left: 20%;">
                <span class="input-group-btn">
                </span>
            </div>
            <br>
            <div id="middle" >
                <span style="margin-left: 20%;float: left;" id="alluser" onclick="showAlluser()" class="hhh">全部用户</span>
                <span style="margin-left: 3em;" id="fff" onclick="showFriend()" class="fff">好友列表</span>
                <span style="margin-left: 3em;" id="hhh" onclick="showHate()" class="hhh">黑名单</span><br><br>
                <!--好友列表-->
                <div id="friendList">
                    <table class="table table-condensed" style="width: 70%;margin-left: 10%;">
                        <thead>
                        <tr>
                            <th style="width:200px;">用户名</th>
                            <th>对Ta提问</th>
                            <th>删除Ta</th>
                            <th>拉黑Ta</th></tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${haoyou}" var="Haoyou">
                            <tr>
                                <td><img src="${pageContext.request.contextPath}/showeinvoices.action?UserId=${Haoyou.laheiN}" style="width:30px;height:30px;border:1px solid pink;border-radius:50%;">${Haoyou.laheiB}</td>
                                <td><button onclick="initFileList2('${Haoyou.laheiB}','${Haoyou.laheiN}','PinLuen','UserName','UserId')" type="button" class="btn btn-info">提问</button></td>
                                <td><button onclick="initFileList('${Haoyou.laheiN}','shanchu','LaheiN')" type="button" class="btn btn-danger">删除</button></td>
                                <td><button onclick="initFileList2('${Haoyou.laheiB}','${Haoyou.laheiN}','lahei','LaheiB','LaheiN')" type="button" class="btn btn-warning">拉黑</button></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <ul id="ul1" class="pagination" style="margin-left: 10%;">

                    </ul>

                </div>

                <!--黑名单-->
                <div id="hate">
                    <table id="hatetable" class="table table-condensed" style="width: 70%;margin-left: 10%;">
                        <thead>
                        <tr>
                            <th style="width:200px;">用户名</th>
                            <th>删除Ta</th>
                            <th>还是做回朋友吧</th></tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lahei}" var="Lahei">
                            <tr>
                                <td><img src="${pageContext.request.contextPath}/showeinvoices.action?UserId=${Lahei.laheiN}" style="width:30px;height:30px;border:1px solid pink;border-radius:50%;">${Lahei.laheiB}</td>
                                <td><button onclick="initFileList('${Lahei.laheiN}','shanchu','LaheiN')" type="button" class="btn btn-info">删除</button></td>
                                <td><button onclick="initFileList2('${Lahei.laheiN}','${Lahei.laheiB}','qlahei','LaheiN','LaheiB')" type="button" class="btn btn-warning">取消拉黑</button></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                    <ul id="ul2" class="pagination" style="margin-left: 10%;">

                    </ul>
                </div>


                <!--所有用户-->
                <div id="allu">
                    <table id="alltable" class="table table-condensed" style="width: 70%;margin-left: 10%;">
                        <thead>
                        <tr>
                            <th style="width:200px;">用户名</th>
                            <th>添加</th>
                            <th>举报</th>
                            <th>状态</th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${user}" var="User">
                            <tr>
                                <td><img src="${pageContext.request.contextPath}/showeinvoices.action?UserFace=${User.userFace}" style="width:30px;height:30px;border:1px solid pink;border-radius:50%;">${User.userName}</td>
                                <td><c:if test="${check=='已激活'}"><button onclick="initFileList2('${User.userName}','${User.userId}','haoyou','LaheiB','LaheiN')" type="button" class="btn btn-primary">添加</button></c:if></td>
                                <td><c:if test="${check=='已激活'}"><button type="button" class="btn btn-warning" onclick="initFileList2('${User.userName}','${User.userId}','jubao','UserName','JID')">举报</button></c:if></td>
                                <td><c:if test="${G=='yes'}"> <c:if test="${User.f=='no'}"><button type="button" class="btn btn-warning" onclick="initFileList('${User.userId}','f','UserId')">封禁</button></c:if><c:if test="${User.f=='yes'}"><button type="button" class="btn btn-warning" onclick="initFileList('${User.userName}','ff','UserName')">解封</button></c:if></c:if>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <ul id="ul3" class="pagination" style="margin-left: 10%;">

                    </ul>
                </div>


            </div>
        </div>
    </div>
</div>
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
    function initFileList2(id,idP,f,m,n) {
        var form = $("<form></form>");
        form.attr('action',"http://111.230.173.74/"+f+".action");
        form.attr('method','post');
        var input2 = $("<input type='text' />");
        input2.attr("name",m);
        input2.attr('value',id);
        form.append(input2);
        var input1 = $("<input type='text' />");
        input1.attr("name",n);
        input1.attr('value',idP);
        form.append(input1);
        $('body').append(form);
        form.submit();
    }
</script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/alluser.js"></script>
</body>
</html>