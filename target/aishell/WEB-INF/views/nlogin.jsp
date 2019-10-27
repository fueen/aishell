<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/20
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <%--<link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans'>--%>

    <link rel="stylesheet" href="/css/nlogin.css">


</head>

<body>

<%-- 主窗体 --%>
<p class="tip"></p>
<div class="cont">

    <div class="form sign-in">
        <form action="/login" method="post">
        <h2>欢迎登录</h2>
        <label>
            <span>用户名</span>
            <input type="text" name="username" value="${username}"/>
        </label>
        <label>
            <span>密码</span>
            <input type="password" name="password"/>
        </label>
        <p class="forgot-pass">忘记密码?</p>
        <p class="p1">${error}</p>
        <button type="submit" class="submit">登  录</button>
        <button type="button" class="fb-btn">或许你想通过<span>facebook？</span></button>
        </form>
    </div>
    <div class="sub-cont">
        <div class="img">
            <div class="img__text m--up">
                <h2>Welcome to login</h2>
                <p>掀开你充满香气的衣裙</p>
                <br/>
                <p>把我疼痛的头深深埋藏</p>
                <br/>
                <p>怒斥，像闻一朵枯萎的花一样</p>
                <br/>
                <p>闻一闻往日爱情的温馨</p>
            </div>
            <div class="img__text m--in">
                <h2>Welcome to register</h2>
                <p>不要温柔地走进那段良夜</p>
                <br/>
                <p>老年应在落暮时分燃烧咆哮</p>
                <br/>
                <p>怒斥，怒斥这光明的消谢</p>
                <br/>
            </div>
            <div class="img__btn">
                <span class="m--up">注  册</span>
                <span class="m--in">去登录</span>
            </div>
        </div>
        <div class="form sign-up">
            <h2>欢迎加入我们</h2>
            <form>
            <label>
                <span>用户名</span>
                <input type="text" name="username"/>
            </label>
            <label>
                <span>密码</span>
                <input type="password" name="password"/>
            </label>
            <label>
                <span>确认密码</span>
                <input type="password" name="npassword"/>
            </label>
            <p class="p1">${pwderror}</p>
            <button type="submit" class="submit">注  册</button>
            <button type="button" class="fb-btn">或许你想通过<span>facebook？</span></button>
            </form>
        </div>
    </div>

</div>


<script src="/js/nlogin.js"></script>
<script src="/js/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="/js/constellation.js"></script>
</body>
</html>

