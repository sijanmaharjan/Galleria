<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" uri="/WEB-INF/galleria.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../conf.jsp"%>
<jsp:useBean id="p" scope="request" beanName="p" type="java.lang.String"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Galleria - Dashboard</title>
    <link rel="shortcut icon" href="galleria.svg" />
    <link href="css/dashboard-styles.css" rel="stylesheet" />
    <link href="css/dash.css" rel="stylesheet" />
    <link href="css/user_profile.css" rel="stylesheet" />
    <link href="css/img-viewer.css" rel="stylesheet"/>
    <script src="js/jquery.min.js"></script>
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
    <script src="js/request-failure-handler.js"></script>
    <style>
        #profile-container{
            !important;
            z-index: 10000;
        }
    </style>
</head>
<body class="sb-nav-fixed">
    <%@include file="navbar.jsp"%>
    <div id="layoutSidenav">
        <%@include file="sidebar.jsp"%>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid">
                    <c:choose>
                        <c:when test='<%=p==null || p.equals("a")%>'>
                            <%@include file="dash/index.jsp"%>
                        </c:when>
                        <c:when test='<%="b".equals(p)%>'>
                            <%@include file="dash/users.jsp"%>
                        </c:when>
                        <c:when test='<%="c".equals(p)%>'>
                            <%@include file="dash/requests.jsp"%>
                        </c:when>
                        <c:when test='<%="d".equals(p)%>'>
                            <%@include file="dash/categories.jsp"%>
                        </c:when>
                    </c:choose>
                </div>
            </main>
            <footer class="py-4 bg-light mt-auto">
                <div class="container-fluid">
                    <div class="d-flex align-items-center justify-content-between small">
                        <div class="text-muted">Copyright &copy; <%=appName%> 2020</div>
                    </div>
                </div>
            </footer>
        </div>
    </div>

    <%@include file="../home/common/mask.jsp"%>
    <%@include file="../home/common/img-viewer.jsp"%>
    <%@include file="dash/user_profile.jsp"%>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
</body>
</html>
