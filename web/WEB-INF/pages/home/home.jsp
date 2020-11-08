<%@ page import="java.time.Year" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../conf.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="u" uri="/WEB-INF/galleria.tld" %>
<jsp:useBean id="UID" beanName="UID" scope="session" type="java.lang.String"/>
<%
    String p = request.getParameter("p");
    String error = (String) pageContext.getServletContext().getAttribute("error");
    if(error != null) {
        pageContext.getServletContext().removeAttribute("error");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Galleria</title>
        <link rel="shortcut icon" href="galleria.svg" />
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/shop-homepage.css" rel="stylesheet">
        <link href="css/dash.css" rel="stylesheet">
        <link href="jquery-ui/jquery-ui.min.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
        <link href="css/mask.css" rel="stylesheet">
        <link href="css/user_profile.css" rel="stylesheet">
        <link href="css/img-viewer.css" rel="stylesheet">
        <script src="js/jquery.min.js"></script>
        <script src="jquery-ui/jquery-ui.min.js"></script>
        <script src="js/request-failure-handler.js"></script>
        <style>
            .navbar{
                opacity: 0.95;
                backdrop-filter: blur(5px);
            }
            #user-option{
                cursor: pointer;
            }
            .nav-options{
                display: none;
                background-color: rgba(220,220,220, 0.7);
                backdrop-filter: blur(5px);
                position: fixed;
                top: 50px;
                width: 140px;
                margin-left: -50px;
                padding: 10px;
                border-radius: 5px;
            }
            .notification-container{
                display: none;
                background-color: #e1e1e1;
                backdrop-filter: blur(5px);
                position: fixed;
                top: 50px;
                width: 260px;
                margin-left: -100px;
                padding: 5px;
                border-radius: 5px;
                cursor: pointer;
                max-height: 400px;
                overflow-y: scroll;
            }
            .notification-container .custom-btn{
                !important;
                margin-top: 10px;
            }
            #not-option{
                cursor: pointer;
            }

            #rating-container{
                position: fixed;
                z-index: 3000;
                padding: 40px 50px;
                border-radius: 5px;
                top:40vh;
                left: 40vw;
                background-color: #e1e1e1;
                display: none;
            }
            #rating-container .fa-star{
                cursor: pointer;
            }
            #download-wish-list{
                display: none;
                position: fixed;
                top: 35vh;
                left: 25vw;
                background-color: #e1e1e1;
                padding: 25px 30px;
                border-radius: 20px;
                z-index: 2000;
            }
        </style>
    </head>
    <body>
        <%@include file="common/mask.jsp"%>
        <%@include file="common/nav-bar.jsp"%>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <c:choose>
                        <c:when test='<%=(p==null||p.equals("a")||"b".equals(p)||"c".equals(p))%>'>
                            <%@include file="common/sidebar.jsp"%>
                        </c:when>
                        <c:when test='<%="e".equals(p)%>'>
                            <div style="min-height: 70vh"></div>
                        </c:when>
                        <c:otherwise>
                            <%@include file="common/wish_list.jsp"%>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-lg-9">
                    <c:choose>
                        <c:when test='<%=(p==null||p.equals("a")||"b".equals(p)||"c".equals(p)||"e".equals(p))%>'>
                            <%@include file="common/carousel.jsp"%>
                            <%@include file="common/image_list.jsp"%>
                        </c:when>
                        <c:otherwise>
                            <%@include file="common/wishes.jsp"%>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; <%=appName%> <%=Year.now().toString()%></p>
            </div>
        </footer>


        <%@include file="common/user_profile.jsp"%>
        <%@include file="common/img-viewer.jsp"%>

        <!-- IA FORM -->
            <%@include file="dash/img_form.jsp"%>
            <script>const bankDetailRequired = ${((user.accountNumber==null||user.accountNumber.trim().length()==0)?"true":"false")};</script>
<%--            <script src="js/imgForm.js"></script>--%>
        <!-- /IA FORM -->

        <%@include file="common/rate.jsp"%>

        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/fav.js"></script>
        <script src="js/wish.js"></script>
        <script src="https://kit.fontawesome.com/5ac2d1c4e4.js" crossorigin="anonymous"></script>

        <c:if test="<%=error != null%>">
            <script>
                alert('<%=error%>');
            </script>
        </c:if>
    </body>
</html>
