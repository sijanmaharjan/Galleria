<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" beanName="user" scope="request" type="bean.view.DetailedUser"/>
<jsp:useBean id="mostly_liked_images" beanName="mostly_liked_images" scope="request" type="java.util.List<bean.view.MostlyLikedImage>"/>
<jsp:useBean id="highly_rated_images" beanName="highly_rated_images" scope="request" type="java.util.List<bean.view.HighlyRatedImage>"/>
<jsp:useBean id="mostly_sold_images" beanName="mostly_sold_images" scope="request" type="java.util.List<bean.view.MostlySoldImage>"/>
<h1 class="mt-4">Dashboard</h1>
<div class="row">
    <div class="col-xl-3 col-md-6">
        <div class="card bg-primary text-white mb-4">
            <div class="card-body" style="cursor:default;" onclick="window.location='galleria.dash?p=c'"><b>${user.totalUploads}</b> Images</div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6">
        <div class="card bg-info text-white mb-4">
            <div class="card-body" style="cursor:default;"><b>${user.favourites}</b> Favourites</div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6">
        <div class="card bg-success text-white mb-4">
            <div class="card-body" style="cursor:default;"><b>$<u:Rounded number="${user.earned}"/></b> on wallet</div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6">
        <div class="card bg-danger text-white mb-4">
            <div class="card-body" style="cursor:default;"><b>$<u:Rounded number="${user.redeemeed}"/></b> redeemed</div>
        </div>
    </div>
</div>
<div class="card mb-4">
    <div class="card-body row">
        <div class="table-responsive col-sm-4">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><i class="fas fa-heart"></i> Mostly Liked Images</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="image" items="${mostly_liked_images}">
                    <tr>
                        <td>${image.title} (${image.likes} likes) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right" onclick="previewImage(event, '${image.id}')"></i></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="table-responsive col-sm-4">
            <table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><i class="fas fa-star"></i> Highly Rated Images</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="image" items="${highly_rated_images}">
                    <tr>
                        <td>${image.title} (<u:Rounded number="${image.rates}"/> stars by ${image.rateCount} users) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right"  onclick="previewImage(event, '${image.id}')"></i></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="table-responsive col-sm-4">
            <table class="table table-bordered" id="dataTable3" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><i class="fas fa-dollar-sign"></i> Mostly Sold Images</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="image" items="${mostly_sold_images}">
                    <tr>
                        <td>${image.title} (<u:Rounded number="${image.sales}"/> users) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right" onclick="previewImage(event, '${image.id}')" ></i></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
