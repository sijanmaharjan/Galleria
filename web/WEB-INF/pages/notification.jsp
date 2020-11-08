<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Notifications</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <style>
        body{
            background-color: #e1e1e1;
        }
        .notification-text{
            overflow-wrap: break-word;
            padding: 0px 5px;
            border-radius: 3px;
            background-color: #d1d1d1;
            margin-left: 25px;
        }
        .del-notification-icon{
            cursor: pointer;
            margin-top: 3px;
        }
    </style>
</head>
<body>
<div class="container">
    <jsp:useBean id="messages" scope="request" type="java.util.List<bean.entity.Notification>"/>
    <c:forEach var="notification" items="${messages}">
        <div class="row">
            <div class="col-sm-12">
                <i onclick="window.location='galleria.remove(message)?id=${notification.id}'" class="del-notification-icon fas fa-times-circle float-left"></i>
                <p class="notification-text">${notification.message}</p>
            </div>
        </div>
    </c:forEach>
</div>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/5ac2d1c4e4.js" crossorigin="anonymous"></script>
</body>
</html>
