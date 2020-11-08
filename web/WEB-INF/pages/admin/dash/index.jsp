<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="app_status" beanName="app_status" scope="request" type="bean.view.AppStatus"/>
<jsp:useBean id="mostly_liked_images" beanName="mostly_liked_images" scope="request" type="java.util.List<bean.view.MostlyLikedImage>"/>
<jsp:useBean id="highly_rated_images" beanName="highly_rated_images" scope="request" type="java.util.List<bean.view.HighlyRatedImage>"/>
<jsp:useBean id="mostly_sold_images" beanName="mostly_sold_images" scope="request" type="java.util.List<bean.view.MostlySoldImage>"/>
<jsp:useBean id="most_expensive_images" beanName="most_expensive_images" scope="request" type="java.util.List<bean.view.MostExpensiveImage>"/>
<jsp:useBean id="top_earning_accounts" beanName="top_earning_accounts" scope="request" type="java.util.List<bean.view.TopEarningAccount>"/>
<jsp:useBean id="top_rated_accounts" beanName="top_rated_accounts" scope="request" type="java.util.List<bean.view.TopRatedAccount>"/>

<h1 class="mt-4">Dashboard</h1>
<div class="row">
    <div class="col-xl-3 col-md-6">
        <div class="card bg-primary text-white mb-4">
            <div class="card-body" style="cursor:default;"><b>${app_status.totalImages}</b> Images</div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6">
        <div class="card bg-info text-white mb-4">
            <div class="card-body" style="cursor:default;" onclick="window.location='galleria.admin?p=b'"><b>${app_status.totalUsers}</b> User Accounts</div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6">
        <div class="card bg-success text-white mb-4">
            <div class="card-body" style="cursor:default;"><b>$<u:Rounded number="${app_status.taxCollected}"/></b> Tax Collected</div>
        </div>
    </div>
    <div class="col-xl-3 col-md-6">
        <div class="card bg-danger text-white mb-4">
            <div class="card-body" style="cursor:default;"><b>$<u:Rounded number="${app_status.serviceChargeCollected}"/></b> SC Collected</div>
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
                        <td>${image.title} (${image.likes} likes) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right" onclick="previewImage(event, '${image.id}', true)"></i></td>
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
                        <td>${image.title} (<u:Rounded number="${image.rates}"/> stars by ${image.rateCount} users) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right" onclick="previewImage(event, '${image.id}', true)"></i></td>
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
                        <td>${image.title} (${image.sales} users) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right" onclick="previewImage(event, '${image.id}', true)" ></i></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="card-body row">
        <div class="table-responsive col-sm-4">
            <table class="table table-bordered" id="dataTable4" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><i class="fas fa-dollar-sign"></i> Mostly Expensive Images</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="image" items="${most_expensive_images}">
                    <tr>
                        <td>${image.title} ($<u:Rounded number="${image.price}"/>) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right" onclick="previewImage(event, '${image.id}', true)"></i></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="table-responsive col-sm-4">
            <table class="table table-bordered" id="dataTable5" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><i class="fas fa-star"></i> Top Rated Profiles</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="account" items="${top_rated_accounts}">
                    <tr>
                        <td>${account.firstName} ${account.lastName} (<u:Rounded number="${account.overallRating}"/> stars) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right" onclick="displayProfile(<u:MapUser UID="${account.id}"/>, false)"></i></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="table-responsive col-sm-4">
            <table class="table table-bordered" id="dataTable6" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th><i class="fas fa-dollar-sign"></i> Top Earning Profiles</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="account" items="${top_earning_accounts}">

                    <tr>
                        <td>${account.firstName} ${account.lastName} ($<u:Rounded number="${account.earnings}"/> earned) <i class="cursor-pointer fas fa-arrow-alt-circle-right float-right" onclick="displayProfile(<u:MapUser UID="${account.id}"/>, false)" ></i></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>