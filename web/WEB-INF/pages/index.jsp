<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="conf.jsp"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%
    String error = (String) pageContext.getServletContext().getAttribute("error");
    if(error != null) {
        pageContext.getServletContext().removeAttribute("error");
    }
%>
<jsp:useBean id="images" beanName="images" scope="request" type="java.util.List<bean.entity.Image>"/>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="sijan maharjan">
  <title>Galleria</title>
  <link rel="shortcut icon" href="galleria.svg" />
  <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/the-big-picture.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
</head>

<body>
    <button id="loginBtn" onclick="toggleLoginForm()">login</button>

    <%@include file="public/navbar.jsp"%>

  <header>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
          <%int i =0;%>
          <c:forEach items="${images}" var="image">
              <div class="carousel-item <%=((i++)==0?"active":"")%>" style="background-image: url('images/${image.source}')">
                  <!-- Page Content -->
                  <section>
                      <div class="text-color-white">
                          <div class="row">
                              <div class="col-lg-6">
                                  <h1 class="mt-5"><%=appName.toUpperCase()%></h1>
                                  <p>The most trending image sharing platform on the internet.</p>
                              </div>
                          </div>
                      </div>
                  </section>
                  <div class="carousel-caption d-none d-md-block">
                      <h2 class="display-4">${image.title}</h2>
                      <p class="lead">${image.description}</p>
                  </div>
              </div>
          </c:forEach>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </header>

<%--  <about-section>--%>
  <%@include file="public/about.jsp"%>
<%--  </about-section>--%>

  <!-- UAC FORM SECTION -->
    <%@include file="public/login.jsp"%>
    <%@include file="public/signup.jsp"%>
    <script src="js/uac.js"></script>
  <!-- /UAC FORM SECTION -->

  <!-- Bootstrap core JavaScript -->
  <script src="js/bootstrap.bundle.min.js"></script>
  <script src="js/nav.js"></script>
  <c:if test="<%=error != null%>">
      <script>
          alert('<%=error%>');
      </script>
  </c:if>
</body>

</html>
