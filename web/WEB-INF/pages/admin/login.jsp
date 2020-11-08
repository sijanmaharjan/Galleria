<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%
    Integer failureCount = (Integer) session.getAttribute("failureCount");
    if(failureCount == null){
        failureCount = 0;
        session.setAttribute("failureCount", failureCount);
    }else {
        session.setAttribute("failureCount", failureCount+1);
    }
    if(failureCount >= 4){
        response.sendError(403);
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Galleria - Restricted</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Chango" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="https://kit.fontawesome.com/5ac2d1c4e4.js" crossorigin="anonymous"></script>
    <script src="js/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/admin.css"/>
    <link rel="stylesheet" href="css/admin/login.css"/>
</head>
<body>
<div class="text-center">

    <div id="notfound">
        <div class="notfound">
            <div>
                <div class="notfound-404">
                    <h1>!</h1>
                </div>
                <h2>Restricted<br>Access</h2>
            </div>
            <p style="cursor: default;">
                <a onclick="$('#loginModal').modal('show')" style="!important;color: #222222;">Verify</a> if you represent administrative personnel.
                Or, <a href="galleria.oh" style="cursor: pointer"><b>Visit Gallery</b></a><br>
<%--                shows warning if user fails for 5 times to login, then he/she will be blocked for a session--%>
                <span class='text-danger'>${(failureCount > 0) ? (5 - failureCount) : ""}${(failureCount > 0) ? " more hits and you will be blocked!":""}</span>
            </p>
        </div>
    </div>
</div>

<!-- Modal HTML -->
<div id="loginModal" class="modal fade">
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Submit Identification</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <form action="j_security_check" method="post">
                    <div class="form-group">
                        <i class="fa fa-user"></i>
                        <input type="text" class="form-control" name="j_username" placeholder="Username" required="required">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock"></i>
                        <input type="password" class="form-control" name="j_password" placeholder="Password" required="required">
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-primary btn-block btn-lg" value="Sign in">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>