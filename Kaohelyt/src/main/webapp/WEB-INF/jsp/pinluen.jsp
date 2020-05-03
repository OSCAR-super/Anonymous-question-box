<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/3/9
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>匿名的提问</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/PinLuen.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="container" style="width: 400px;" >

    <h2>提问区</h2>
    <div id="middle" class="form-group">

        <tr>提问内容：</tr></br>
        <table class="form-group">
            <c:forEach items="${pinluen}" var="PinLuen">
                <tr>
                    <c:if test="${PinLuen.de=='no'}">
                        <td class="form-control"><a href="javascript:void(0)" onclick="initFileList('${PinLuen.idP}','AddH','IdP')" >
                            <div style="width:100%;word-wrap:break-word;">
                                    ${PinLuen.neiron}</div>
                        </a>
                            <c:if test="${G=='已激活'}"><img onclick="initFileList2('${UserId}','${PinLuen.idP}','ddH','UserB','IdP')" src="${pageContext.request.contextPath}/img/delete.svg"></c:if>
                        </td></c:if>
                </tr>
            </c:forEach>
        </table>

        <form action="${pageContext.request.contextPath}/AddPinLuen.action" method="post">
            <input type='hidden' value='${CSRFToken}' id='csrftoken'>
            <div><input type="text" name="UserB" value="${UserId}" hidden></div>
            <div class="form-group">
                <label>你的匿名提问：</label>
                <textarea type="text"   class="form-control" name="neiron"><c:if test="${email!='已激活'}"><c:if test="${K!='yes'}">您的邮箱未激活或被对方拉黑</c:if></c:if></textarea>
            </div>
            <div style="display: flex;"><c:if test="${K=='yes'}"><c:if test="${email=='已激活'}">
                <input type="submit" class="form-control" style="width: 20%;" value="提交"></c:if></c:if>
                <a href="${pageContext.request.contextPath}/AllUser.action" class="btn btn-default" style="width: 20%;margin-left: 10%;" >返回</a></div>

        </form>

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
</body>
</html>