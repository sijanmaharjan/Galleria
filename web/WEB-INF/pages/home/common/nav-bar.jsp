<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" style="border-bottom: 2px solid #888">
    <div class="container">
        <a class="navbar-brand" href="#"><b><%=appName.toUpperCase()%></b></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class='nav-item <c:if test='<%=p==null||p.equals("a")%>'><%="active"%></c:if>'>
                    <a class="nav-link" href="galleria.oh?p=a">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class='nav-item <c:if test='<%="b".equals(p)%>'><%="active"%></c:if>'>
                    <a class="nav-link" href="galleria.oh?p=b">My Images</a>
                </li>
                <li class='nav-item <c:if test='<%="c".equals(p)%>'><%="active"%></c:if>'>
                    <a class="nav-link" href="galleria.oh?p=c">Favourites</a>
                </li>
                <li class='nav-item <c:if test='<%="d".equals(p)%>'><%="active"%></c:if>'>
                    <a class="nav-link" href="galleria.oh?p=d">Wishlist</a>
                </li>
                <li class='nav-item <c:if test='<%="e".equals(p)%>'><%="active"%></c:if>'>
                    <a class="nav-link" href="galleria.oh?p=e">Purchased</a>
                </li>
                <li class="nav-item">
                    <div class="nav-link" id="not-option" onclick="
                          $('#nav-options').hide(100);
                          const element = $('#notification-container');
                          if($(element).is(':hidden')){
                              document.getElementById('notification-container').contentDocument.location.reload(true);
                              $(element).slideDown(120);
                          }else{
                              $(element).hide(100);
                          }
                        ">
                        <i class="fas fa-bell"></i>
                        <iframe class="notification-container" id="notification-container" src="http://localhost:8080/web_war_exploded/galleria.messages"></iframe>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link">
                        <i id="user-option" class="custom-user fas fa-user" onclick="
                          $('#notification-container').hide(100);
                          const element = $('#nav-options');
                          if($(element).is(':hidden')){
                              $(element).slideDown(120);
                          }else{
                              $(element).hide(100);
                          }
                        "></i>
                        <div class="nav-options" id="nav-options">
                            <button class="custom-btn" onclick="displayProfile()">View Profile</button>
                            <button class="custom-btn" onclick="location.href='galleria.dash'">Dashboard</button>
                            <button class="custom-btn" onclick="location.href='galleria.logout'">Log Out</button>
                        </div>
                    </a>
                </li>
                <li class="nav-item" style="cursor:pointer;" onclick="displayProfile()">
                    <a class="nav-link">
                        <u:Formatted firstName="${user.firstName}" lastName="${user.lastName}"/>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<script>
    document.addEventListener("click", function () {
        $('#nav-options').hide(100);
    })
    document.addEventListener("click", function () {
        $('#notification-container').hide(100);
    })
    document.getElementById('nav-options').addEventListener("click", function (event) {
        event.stopPropagation()
    })
    document.getElementById('user-option').addEventListener("click", function (event) {
        event.stopPropagation()
    })
    document.getElementById('not-option').addEventListener("click", function (event) {
        event.stopPropagation()
    })
    document.getElementById('not-option').addEventListener("click", function (event) {
        event.stopPropagation()
    })
</script>