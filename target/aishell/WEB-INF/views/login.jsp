<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/16
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
    <title>登录</title>
</head>
<body>

<header>
    <%-- 主窗体 --%>
    <div class="wicket">

        <div class="middle">
            <form action="/login" method="post">
                <p class="title">登 录</p>
                <p class="p1">用户名:</p>
                <input class="line" name="username" type="text"/>
                <p class="p1">密码:</p>
                <input class="line" name="password" type="password"/>
                <br/>
                <br/>
                <p class="p1">${error}</p>
                <input class="button1" type="submit" name="submit" value="登  录"/>
            </form>

        </div>
    </div>

    <%-- 页面特效--%>
    <div class="bg">
        <canvas id="display"></canvas>
        <div id="blachole">
        </div>
    </div>
</header>

<script src="/js/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="/js/constellation.js"></script>
</body>
</html>
