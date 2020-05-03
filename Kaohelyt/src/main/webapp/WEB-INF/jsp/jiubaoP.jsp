<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2020/5/1
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>举报信息</title>
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


    <div id="middle" class="form-group">

        <tr>举报内容：</tr></br>
        <table class="form-group">
            <c:forEach items="${Jubao}" var="j">
                <tr>
                    <c:if test="${j.JD=='no'}">
                    <td class="form-control">
                        <div style="width:100%;word-wrap:break-word;">
                            被举报人：${j.jubaoName}，举报内容:${j.jubaoN}
                            <img onclick="initFileList2('${j.jubaoId}','df','JID')" src="${pageContext.request.contextPath}/img/delete.svg"></c:if>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div style="display: flex;"><a href="${pageContext.request.contextPath}/AllUser.action" class="btn btn-default" style="width: 20%;margin-left: 10%;" >返回</a></div>

    </div>
</div>
<script type="text/javascript" language="javascript">
    function initFileList2(idP,f,n) {
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
</body>
</html>
