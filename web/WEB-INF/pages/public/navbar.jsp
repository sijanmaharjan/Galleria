<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-bottom">
    <div class="container">
        <a class="navbar-brand" href="#"><b><%=appName.toUpperCase()%></b></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active" onclick="onNavItemActivate(this)">
                    <a class="nav-link" href="#">Home</a>
                </li>
                <li class="nav-item" onclick="onNavItemActivate(this)">
                    <a class="nav-link" href="#about">About</a>
                </li>
                <li id="signupBtn" class="nav-item" onclick="onNavItemActivate(this)">
                    <a class="nav-link" href="#" onclick="toggleSignupForm()">Signup</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
