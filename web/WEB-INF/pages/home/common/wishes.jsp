<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row" style="padding: 50px">
    <c:if test="<%=((List<Wish>)request.getAttribute("wishes")).isEmpty()%>">
        <h2 style="text-align: center;width: 100%">Your Wishlist Is Empty.</h2>
    </c:if>
    <c:forEach var="image" items="${wishes}">
        <div class="col-lg-4 col-md-6 mb-4" onclick="previewImage(event, '${image.imageId}')">
            <div class="card h-100">
                <a class="img-150"><img class="card-img-top" src="thumbnails/${image.source}" alt=""></a>
                <div class="custom card-body">
                    <p class="card-title">
                        <b class="img-title"><c:out value="${image.title}"/></b>
                    </p>
                    <p>${image.free?"Free": "$".concat(image.price)}
                        <a class="float-right">
                            <i onclick="markFavourite(event, this, '${image.imageId}', '${image.favourite?"dislike":"like"}');" class='${image.favourite?"fas":"far"} fa-heart' style="cursor: pointer"></i>
                            <i onclick="window.location = 'galleria.remove(wish)?id=${image.imageId}';event.stopPropagation();" class="far fa-times-circle" style="cursor: pointer"></i>
                        </a>
                    </p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">
                        <div>
                            <c:forEach var="star" begin="1" end="${image.ratings.intValue()}" >
                                <i class="fas fa-star"></i>
                            </c:forEach>
                            <c:forEach var="star" begin="${image.ratings.intValue()+1}" end="5" >
                                <i class="far fa-star"></i>
                            </c:forEach>
                            (<u:Rounded number="${image.ratings}"/> stars)
                        </div>
                        <i class="fas fa-user"></i>&nbsp;<u:Uploader UID="${image.uploadedBy}" current="${UID}"/>
                    </small>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div id="download-wish-list">
    <h1>Checkout Successful. Keep Exploring...</h1>
    <div>
        Download your images:
        <ul>
            <c:forEach items="${wishes}" var="image">
                <li style="width: 100%">${image.title} <i class="float-right fas fa-download" style="cursor: pointer" onclick="downloadImage('${image.imageId}');"></i></li>
            </c:forEach>
        </ul>
    </div>
    <a class="float-right">
        <button class="btn btn-success" onclick="window.location='galleria.oh?p=e'">Done</button>
    </a>
</div>
