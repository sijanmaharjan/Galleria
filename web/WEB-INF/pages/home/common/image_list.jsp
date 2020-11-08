<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<DetailedImage> imageL = (List<DetailedImage>) request.getAttribute("images");
%>
<div class="row">
    <c:if test="<%="b".equals(p) && imageL.isEmpty()%>">
        <a href="galleria.dash?p=c">Upload Some</a>
    </c:if>
    <c:if test="<%=imageL.size()>3%>">
        <c:forEach var="image" items="<%=imageL.subList(3, imageL.size())%>">
            <div class="col-lg-4 col-md-6 mb-4" onclick="previewImage(event, '${image.id}')" style="cursor: default">
                <div class="card h-100">
                    <jsp:useBean id="imgTmp2" class="bean.view.DetailedImage"/>
                    <jsp:setProperty name="imgTmp2" value="${image.free}" property="free"/>
                    <a class="img-150"><img class="card-img-top" src="
                    <%if(!imgTmp2.getFree()){%>
                        <u:IsOwnedOrAccessible UID="${UID}" IID="${image.id}">
                            <if:true>images</if:true>
                            <if:false>thumbnails</if:false>
                        </u:IsOwnedOrAccessible>
                    <%} else { out.print("thumbnails"); }%>/${image.source}" alt="${image.title}"></a>
                    <div class="custom card-body">
                        <p class="card-title">
                            <b class="img-title"><c:out value="${image.title}"/></b>
                        </p>
                        <p>${image.free?"Free": "$".concat(image.price)}
                            <a class="float-right">
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
                                            <i class="fas fa-download" onclick="downloadImage('${image.id}')" style="cursor: pointer"></i>
                                        </if:true>
                                        <if:false>
                                            <i class="fas fa-cart-arrow-down" onclick="addToWishlist(this, '${image.id}', <u:IsInWishlist UID="${UID}" IID="${image.id}"/>);" style="cursor: pointer"></i>
                                        </if:false>
                                    </u:IsOwnedOrAccessible>
                            </a>
                        </p>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">
                            <div>
                                <u:Rating stars="${image.ratings}" count="${image.rateCount}"/>
                            </div>
                            <a>
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
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
<script>
    const images = $("img");
    $(images).each(i => {
        $(images[i]).attr("src", $(images[i]).attr("src").replaceAll(" ",""));
    });
</script>