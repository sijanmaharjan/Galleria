<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="loginForm">
    <form class="loginForm" method="post" action="galleria.login" onsubmit="$('#redirect').val(window.location.href);">
        <input type="text" name="username" placeholder="username"/><br/>
        <input type="password" name="password" placeholder="password"/><br/>
        <button id="login-btn" type="submit">Login</button><br/>
        <a class="create-account btn btn-link" onclick="showSignupFormOnLoginContainer()">create new account</a>
        <input type="text" id="redirect" name="redirect" hidden style="height: 0px"/><br/>
    </form>
</div>