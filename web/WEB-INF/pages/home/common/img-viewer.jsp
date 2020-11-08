<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="preview-image-container">
    <img id="image-view" src=""/>
    <a id="prev-img-rating"></a>
    <a id="prev-img-title"></a>
    <a id="prev-img-actions"><i class="favourite fas fa-heart fa-2x"></i>&nbsp;&nbsp;<b class="price" style="font-size: 24pt"></b>&nbsp;&nbsp;</a>
    <a id="prev-img-desc"></a>
</div>
<script>
    function previewImage(event, id, admin) {
        const url = admin ? 'galleria.admin.view(image)' : 'galleria.view(image)';
        $.get(url, {
            id: id
        }, function (data) {
            const image = data;
            const container = $('#preview-image-container');
            $(container).find("#image-view").attr("src", (!image.ownedOrFree ? "thumbnails/" : "images/") + image.source);
            const fav = $(container).find('#prev-img-actions i.favourite');
            if (admin) {
                $(container).find('#prev-img-rating').hide();
                $(fav).hide();
                $(container).find('#prev-img-actions b.price').hide();
            } else {
                $(container).find('#prev-img-rating').show();
                $(container).find('#prev-img-rating').html(image.ratings);
                $(fav).show();
                $(fav).removeClass("fas");
                $(fav).removeClass("far");
                $(fav).addClass(image.favourite ? "fas" : "far");
                $(container).find('#prev-img-actions b.price').show();
                $(container).find('#prev-img-actions b.price').text(image.free ? "Free" : "$" + image.price);
            }
            $(container).find('#prev-img-title').text(image.title);
            $(container).find('#prev-img-desc').text(image.description);
            $('#preview-image-container').show(120);
            $('.full-body-mask').fadeIn(120);
        }).fail(handleRequestFailure);
        event.stopPropagation();
    }
    $('#image-view').mouseover(function () {
        $('#prev-img-desc').fadeOut(100);
    });
    $('#image-view').mouseout(function () {
        $('#prev-img-desc').fadeIn(100);
    });
    $(document).click(function () {
        $('#preview-image-container').hide(100);
        $('.full-body-mask').fadeOut(100);
    });
    $('#image-view').click(function (event) {
        event.stopPropagation();
    })
</script>