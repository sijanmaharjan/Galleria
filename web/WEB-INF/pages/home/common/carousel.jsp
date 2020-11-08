<%@ page import="java.util.List" %>
<%@ page import="bean.view.DetailedImage" %>
<%@ page import="bean.entity.Image" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<DetailedImage> images = (List<DetailedImage>) request.getAttribute("images");
%>
<c:if test="<%=images.isEmpty()%>"><h2 style="margin-top: 30px">No Images Found</h2></c:if>
<div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel" onmouseover="displayInfo(this)" onmouseleave="hideInfo(this)" <c:if test="<%=images.isEmpty()%>">hidden</c:if>>
    <ol class="carousel-indicators">
        <%for(int i = 0; i< Math.min(images.size(), 3); i++){%>
            <li data-target="#carouselExampleIndicators" data-slide-to="<%=i%>" class="<%=(i==0?"active":"")%>"></li>
        <%}%>
    </ol>
    <div class="carousel-inner" role="listbox">
        <c:forEach var="image" items="<%=((images.size()>3)?images.subList(0, 3):images)%>">
            <div class="carousel-item img-900-350 ${images.indexOf(image) == 0?"active":""}"  onclick="previewImage(event, '${image.id}')">
                <div class="carousel-title-background"></div>
                <div class="carousel-title">${image.title}</div>
                <div class="carousel-action-background"></div>
                <div class="carousel-rating">
                    <u:Rating stars="${image.ratings}" />
                    <small>
                        (<u:Rounded number="${image.ratings}"/> stars by ${image.rateCount} users)<br>
                        <a id="c-uploader">
                            <i class="fas fa-user"></i>&nbsp;<u:Uploader UID="${image.uploadedBy}" current="${UID}"/>
                            <u:ifAccessible UID="${UID}" IID="${image.id}">
                                <u:HasRated UID="${UID}" IID="${image.id}">
                                    <if:true>
                                        <b class="float-right" style="margin-top: 3px; cursor: pointer" onclick="unrateImage('${image.id}')">UNRATE</b>
                                    </if:true>
                                    <if:false>
                                        <b class="float-right" style="margin-top: 3px; cursor: pointer" onclick="rateImage('${image.id}')">RATE NOW</b>
                                    </if:false>
                                </u:HasRated>
                            </u:ifAccessible>
                        </a>
                    </small>
                </div>
                <div class="carousel-actions">
                    <c:choose>
                        <c:when test='<%="b".equals(p)%>'>
                            <i class="far fa-times-circle" style="cursor: pointer" onclick="deleteImage('${image.id}')"></i>&nbsp;<i class="fas fa-pen" onclick="displayUpdateContainer('${image.id}', '${image.source}', '${image.category}', '${image.title}', '', ${image.price});" style="cursor: pointer"></i>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <u:ifNotOwned UID="${UID}" IID="${image.id}">
                                <i onclick="markFavourite(event, this, '${image.id}', '${image.favourite?"dislike":"like"}');" class='${image.favourite?"fas":"far"} fa-heart' style="cursor: pointer"></i>&nbsp;
                            </u:ifNotOwned>
                        </c:otherwise>
                    </c:choose>
                    <u:IsOwnedOrAccessible UID="${UID}" IID="${image.id}">
                        <if:true>
                            <i class="fas fa-download" onclick="downloadImage('${image.id}')" style="cursor: pointer"></i> ${image.free? "" : "$"}${image.free? "Free" : image.price}
                        </if:true>
                        <if:false>
                            <a onclick="addToWishlist(this, '${image.id}', <u:IsInWishlist UID="${UID}" IID="${image.id}"/>);" style="cursor: pointer"><i class="fas fa-cart-arrow-down"></i> $${image.price}</a>
                        </if:false>
                    </u:IsOwnedOrAccessible>
                </div>
                <jsp:useBean id="imgTmp" class="bean.view.DetailedImage"/>
                <jsp:setProperty name="imgTmp" value="${image.free}" property="free"/>
                <img class="d-block img-fluid" src="
                    <%if(!imgTmp.getFree()){%>
                        <u:IsOwnedOrAccessible UID="${UID}" IID="${image.id}">
                            <if:true>images</if:true>
                            <if:false>thumbnails</if:false>
                        </u:IsOwnedOrAccessible>
                    <%} else { out.print("thumbnails"); }%>/${image.source}" alt="${image.title}">
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
<script>
    function displayInfo(element) {
        $(element).children('div').children('div').children("div.carousel-title-background").show();
        $(element).children('div').children('div').children("div.carousel-title").show();
        $(element).children('div').children('div').children("div.carousel-action-background").show();
        $(element).children('div').children('div').children("div.carousel-actions").show();
        $(element).children('div').children('div').children("div.carousel-rating").show();
    }
    function hideInfo(element) {
        $(element).children('div').children('div').children("div.carousel-title-background").hide();
        $(element).children('div').children('div').children("div.carousel-title").hide();
        $(element).children('div').children('div').children("div.carousel-action-background").hide();
        $(element).children('div').children('div').children("div.carousel-actions").hide();
        $(element).children('div').children('div').children("div.carousel-rating").hide();
    }
</script>