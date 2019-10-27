<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/17
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>

    <link rel="stylesheet" type="text/css" href="/css/crowd.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css"/>
</head>
<body>
<header>
    <div class="bg">
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
                    <input class="button1" type="submit" name="submit" value="登  录"/>
                </form>

            </div>
        </div>
        <canvas id="display"></canvas>
        <div id="blachole">
            </div>
        </div>
</header>
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/js/constellation.js"></script>
</body>
</html>
